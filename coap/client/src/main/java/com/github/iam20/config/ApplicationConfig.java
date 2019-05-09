package com.github.iam20.config;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ApplicationConfig {

	private static int pollingInterval;

	private static String mySystemId;
	private static String coapServerPort = "5683";
	private static String mode = "pull";
	private static String serverIp = "127.0.0.1";

	private static String state = "off";
	private static String control;

	public static void init() {
		try (InputStream input = ApplicationConfig.class.getClassLoader().getResourceAsStream("application.properties")) {
			Properties properties = new Properties();
			properties.load(input);
			mySystemId = (String)properties.get("systemId");
			serverIp = (String)properties.get("serverIp");
			coapServerPort = (String)properties.get("serverPort");
			mode = (String)properties.get("mode");
			pollingInterval = Integer.parseInt((String)properties.get("pollingInterval"));
		} catch (IOException e) {
			mySystemId = "DEVICE1";
			serverIp = "127.0.0.1";
			coapServerPort = "5683";
			mode = "push";
			pollingInterval = 3000;
		}
		log.info(serverIp);
	}

	public static int getPollingInterval() {
		return pollingInterval;
	}

	public static String getMySystemId() {
		return mySystemId;
	}

	public static String getCoapServerPort() {
		return coapServerPort;
	}

	public static String getMode() {
		return mode;
	}

	public static String getServerIp() {
		return serverIp;
	}

	public static String getState() {
		return state;
	}

	public static String getControl() {
		return control;
	}

	public static void setPollingInterval(int interval) {
		pollingInterval = interval;
	}

	public static void setCoapServerPort(String port) {
		coapServerPort = port;
	}

	public static void setMode(String mode1) {
		mode = mode1;
	}

	public static void setState(String state1) {
		state = state1;
	}

	public static void setControl(String control1) {
		control = control1;
	}

	public static String getBaseUri() {
		return "coap://" +  serverIp + "/" + coapServerPort + "/";
	}
}
