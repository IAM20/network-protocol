package com.github.iam20.device.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import com.github.iam20.device.model.MacAddress;
import com.github.iam20.device.model.MacAddressBuilder;

@Slf4j
public class ArpExecutor {
	private static final String UNKNOWN_MAC_ADDR = "(incomplete)";
	private static final String UNKNOWN_MAC_ADDR2 = "<incomplete>";
	private static final String BROADCAST_MAC_ADDR = "ff:ff:ff:ff:ff:ff";
	private static final Pattern pattern = Pattern.compile("(.*) \\((.*)\\) at (.*) (.*) on (.*)");

	private static List<String> inetAddressList = InetAddressListMaker.getInetList();
	private static Set<String> inetAddressMap = InetAddressListMaker.getInetSet();

	private static void executePingAll() {
		for (String inetAddr : inetAddressList) {
			try {
				InetAddress inetAddress = InetAddress.getByName(inetAddr);
				if (inetAddress.isReachable(100)) {
					log.debug("{} is reachable!", inetAddr);
				} else {
					log.debug("{} is unreachable (connection timeout)", inetAddr);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String executeArp() {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(byteArrayOutputStream);

		CommandLine cmdLine = CommandLine.parse("arp");
		cmdLine.addArgument("-a");

		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
		try {
			executor.execute(cmdLine);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Failed to execute arp");
			System.exit(1);
		}
		return byteArrayOutputStream.toString();
	}

	private static List<MacAddress> stringToMacAddress(String str) {
		List<MacAddress> macAddresses = new ArrayList<>();
		Matcher matcher;
		String[] strings = str.split("\n");
		for (String line : strings) {
			matcher = pattern.matcher(line);
			String macAddr, ipAddr;

			if (matcher.find()) {
				macAddr = matcher.group(3);
				ipAddr = matcher.group(2);
				if (macAddr.equals(UNKNOWN_MAC_ADDR)
				 || macAddr.equals(BROADCAST_MAC_ADDR)
				 || macAddr.equals(UNKNOWN_MAC_ADDR2)
				 || !inetAddressMap.contains(ipAddr)) continue;
				MacAddress macAddress = new MacAddressBuilder().macAddr(macAddr.toUpperCase())
						.build();
				macAddresses.add(macAddress);
			}
		}

		return macAddresses;
	}


	public static List<MacAddress> findAllDevice() {
		executePingAll();
		String result = executeArp();
		return stringToMacAddress(result);
	}
}
