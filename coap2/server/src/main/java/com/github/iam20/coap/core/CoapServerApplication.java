package com.github.iam20.coap.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.californium.core.CoapServer;

import com.github.iam20.coap.model.Device;
import com.github.iam20.coap.service.DeviceService;

public class CoapServerApplication {
	private static Map<String, Device> deviceMap = new HashMap<>();
	private static CoapServer server = new CoapServer();

	public static void run() {
		/* coap server run */
		server.add(new DeviceService());
		server.start();
	}

	public static void addDevice(Device device) {
		deviceMap.put(device.getDeviceName(), device);
	}

	public static void removeDevice(String name) {
		deviceMap.remove(name);
	}

	public static Device getDevice(String name) {
		return deviceMap.get(name);
	}
}
