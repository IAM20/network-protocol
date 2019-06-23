package com.github.iam20.hoobs.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import lombok.extern.slf4j.Slf4j;

import com.github.iam20.hoobs.model.MacAddress;

@Slf4j
public class MacAddressParser {
	private static final Pattern pattern = Pattern.compile("(.*)-(.*)-(.*)   \\(hex\\)\t\t(.*)");
	public static MacAddress parse(String rawString) {
		Matcher matcher = pattern.matcher(rawString);
		if (matcher.find()) {
			MacAddress macAddress = new MacAddress();
			String macAddr = matcher.group(1) + ":" + matcher.group(2) + ":" + matcher.group(3);
			macAddress.setMacAddr(macAddr);
			macAddress.setVendor(matcher.group(4));
			return macAddress;
		}
		return null;
	}
}
