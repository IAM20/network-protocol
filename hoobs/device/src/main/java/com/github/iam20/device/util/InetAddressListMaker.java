package com.github.iam20.device.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

@Slf4j
public class InetAddressListMaker {
	private static List<String> inetList = new ArrayList<>();
	private static Set<String> inetSet = new HashSet<>();

 	public static void initInetAddrPool(Pair<String, Integer> addressAndMaskPair) {
		String address = addressAndMaskPair.getLeft();
		Integer mask = addressAndMaskPair.getRight();

		String[] parts = address.split("\\.");
		StringBuilder binaryIpAddressBuf = new StringBuilder();
		for (String str : parts) {
			int temp = Integer.parseInt(str);
			str = addPadding(Integer.toBinaryString(temp));
			binaryIpAddressBuf.append(str);
		}
		address = binaryIpAddressBuf.toString();
		log.debug(address);
		log.debug("{}", mask);
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < mask; i++) {
			buf.append(address.charAt(i));
		}

		int rightPadding = 1 << (32 - mask);
		for (int i = 2; i < rightPadding - 1; i++) {
			String lastElement = Integer.toBinaryString(i);
			lastElement = addPadding(lastElement, 32 - mask);
			String binaryInetAddress = buf.toString() + lastElement;
			StringBuilder inetAddressBuf = new StringBuilder();

			for (int j = 0; j < 4; j++) {
				int partAsDecimal = Integer.parseInt(binaryInetAddress.substring(j * 8, (j + 1) * 8 ), 2);
				String numb = Integer.toString(partAsDecimal);
				inetAddressBuf.append(numb);
				if (j != 3) {
					inetAddressBuf.append(".");
				}
			}
			String inetAddress = inetAddressBuf.toString();
			inetList.add(inetAddress);
			inetSet.add(inetAddress);
		}
	}

	public static Set<String> getInetSet() {
 		return inetSet;
	}

	public static List<String> getInetList() {
 		return inetList;
	}

	private static String addPadding(String str) {
		return addPadding(str, 8);
	}

	private static String addPadding(String str, Integer space) {
		int length = str.length();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i + length < space; i++) {
			buf.append("0");
		}
		buf.append(str);
		return buf.toString();
	}
}
