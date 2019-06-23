package com.github.iam20.proxy.util;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.github.iam20.proxy.model.CoreInformation;
import com.github.iam20.proxy.model.MacAddress;

@Slf4j
@Component
public class VendorNameGetter {
	private static final String SQL_LEFT = "SELECT * FROM mac_and_vendor WHERE ";
	private static final RowMapper<MacAddress> rowMapper = BeanPropertyRowMapper.newInstance(MacAddress.class);
	private static NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public VendorNameGetter(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public static CoreInformation getVendorName(CoreInformation coreInformation) {
		List<MacAddress> macAddresses = coreInformation.getMacAddresses();
		Map<String, MacAddress> macAddressMap = new HashMap<>();

		StringBuilder buf = new StringBuilder(SQL_LEFT);
		for (MacAddress macAddress : macAddresses) {
			macAddress.setMacAddr(macAddress.getMacAddr().toUpperCase());
			String prefix = macAddress.getMacAddr().substring(0, 8);
			buf.append("mac_addr = '").append(prefix).append("' OR ");
			macAddressMap.put(prefix, macAddress);
		}
		buf.append("''");
		String sql = buf.toString();
		log.debug(sql);
		List<MacAddress> queryResult = jdbcTemplate.query(sql, rowMapper);
		for (MacAddress macAddress : queryResult) {
			MacAddress tempMacAddr = macAddressMap.get(macAddress.getMacAddr());
			macAddress.setMacAddr(tempMacAddr.getMacAddr());
		}
		coreInformation.setMacAddresses(queryResult);
		return coreInformation;
	}
}
