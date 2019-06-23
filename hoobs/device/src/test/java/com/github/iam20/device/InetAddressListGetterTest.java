package com.github.iam20.device;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import com.github.iam20.device.util.InetAddressListMaker;

@Slf4j
public class InetAddressListGetterTest {
	private static final List<Pair<String, Integer>> immutableTestPool = Arrays.asList(
			new ImmutablePair<>("192.168.0.1", 24),
			new ImmutablePair<>("172.30.36.61", 24),
			new ImmutablePair<>("172.30.36.61", 20)
	);

	@Test
	public void test() {
		for (Pair<String, Integer> testData : immutableTestPool) {
			List<String> inetAddresses = InetAddressListMaker.getInetList();
			int expectedDataNumber = 1 << 32 - testData.getRight();
			assertEquals(expectedDataNumber, inetAddresses.size());
			for (String str : inetAddresses) {
				log.info("IP Address : {}", str);
			}
		}
	}
}
