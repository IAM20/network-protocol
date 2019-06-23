package com.github.iam20.device;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import com.github.iam20.device.config.ApplicationConfig;

@Slf4j
public class ApplicationConfigTest {
	@Test
	public void test() {
		ApplicationConfig.init();
		log.info("ip config : {}", ApplicationConfig.getIpConfig());
		log.info("proxy server info : {}", ApplicationConfig.getProxyServerUri());
	}
}
