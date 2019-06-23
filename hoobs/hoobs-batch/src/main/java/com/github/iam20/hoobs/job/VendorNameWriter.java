package com.github.iam20.hoobs.job;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.github.iam20.hoobs.sql.DbSqls;
import com.github.iam20.hoobs.model.MacAddress;

@Slf4j
@Component
public class VendorNameWriter implements ItemStreamWriter<MacAddress> {
	private Map<String, String> macVendorMap;
	private RowMapper<MacAddress> rowMapper = BeanPropertyRowMapper.newInstance(MacAddress.class);
	private NamedParameterJdbcTemplate jdbcTemplate;
	private ExecutionContext executionContext;

	public VendorNameWriter(@Qualifier("hoobsDataSource")DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		macVendorMap = new HashMap<>();
	}

	@Override
	public void write(List<? extends MacAddress> list) {
		log.info("{} items from reader", list.size());
		List<MapSqlParameterSource> namedParameters = new ArrayList<>();
		for (MacAddress macAddress : list) {
			String macAddr = macAddress.getMacAddr();
			if (macVendorMap.get(macAddr) == null) {
				log.info("{} will be inserted into database", macAddr);
				MapSqlParameterSource namedParameter = new MapSqlParameterSource();
				namedParameter.addValue("macAddr", macAddress.getMacAddr());
				namedParameter.addValue("vendor", macAddress.getVendor());
				namedParameters.add(namedParameter);
			}
		}
		log.info("{} items are going to be inserted into database", namedParameters.size());
		jdbcTemplate.batchUpdate(DbSqls.INSERT_MAC_ADDRESSES, namedParameters.toArray(MapSqlParameterSource[]::new));
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		this.executionContext = executionContext;
		List<MacAddress> macAddresses = jdbcTemplate.query(DbSqls.GET_MAC_ADDRESSES, rowMapper);

		for (MacAddress macAddr : macAddresses) {
			macVendorMap.put(macAddr.getMacAddr(), macAddr.getVendor());
		}
		log.info("THE NUMBER OF MAC ADDR : {}", macAddresses.size());
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		this.executionContext = executionContext;
	}

	@Override
	public void close() throws ItemStreamException {
	}
}
