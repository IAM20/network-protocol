package com.github.iam20.rasp.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import com.github.iam20.rasp.model.Device;
import com.github.iam20.rasp.model.DeviceBuilder;

@Slf4j
public class ApplicationConfig {
	private static Device device;
	private static String serverIp;
	private static String serverPort;
	private static int pollingInterval;

	public static void init() {
		try (InputStream input = ApplicationConfig.class
			.getClassLoader()
			.getResourceAsStream("application.properties")) {
			Properties properties = new Properties();
			properties.load(input);
			device = new DeviceBuilder()
					.deviceName(System.getProperty("user.name"))
					.dimmingValue(0)
					.ledOn(false)
					.polling(properties.getProperty("polling"))
					.build();
			pollingInterval = Integer.parseInt((String)properties.get("pollingInterval"));
			serverIp = (String)properties.get("serverIp");
			serverPort = (String)properties.get("serverPort");

		} catch (IOException e) {
			log.error("Failed to read application.properties file");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static Device getDevice() {
		return device;
	}

	public static int getPollingInterval() {
		return pollingInterval;
	}

	public static String getServerBaseUri() {
		return "coap://" + serverIp + ":" + serverPort +"/";
	}
}
