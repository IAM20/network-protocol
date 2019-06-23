package com.github.iam20.device;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import com.github.iam20.device.config.ApplicationConfig;
import com.github.iam20.device.model.MacAddress;
import com.github.iam20.device.util.ArpExecutor;

@Slf4j
public class ArpExecutorTest {
	@Before
	public void before() {
		ApplicationConfig.init();
	}

	@Test
	public void executeArpTest() {
		List<MacAddress> result = ArpExecutor.findAllDevice();
		for (MacAddress macAddress : result) {
			log.info("Device info :         {}", macAddress.getOrgName());
			log.info("Mac address info :    {}", macAddress.getMacAddr());
		}
	}
}
