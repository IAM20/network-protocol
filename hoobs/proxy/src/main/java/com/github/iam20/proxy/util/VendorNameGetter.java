package com.github.iam20.proxy.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.github.iam20.proxy.config.ApplicationConfig;
import com.github.iam20.proxy.model.CoreInformation;
import com.github.iam20.proxy.model.MacAddress;

@Slf4j
public class VendorNameGetter {
	private static final String SQL_LEFT = "SELECT * FROM mac_and_vendor WHERE ";
	private static final RowMapper<MacAddress> rowMapper = BeanPropertyRowMapper.newInstance(MacAddress.class);

	public static CoreInformation getVendorName(CoreInformation coreInformation) {
		NamedParameterJdbcTemplate jdbcTemplate = ApplicationConfig.getJdbcTemplate();
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
