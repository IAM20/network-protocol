package com.github.iam20.coap.core;

import java.util.Scanner;

import com.github.iam20.coap.model.Device;

public class CoapServerService {
	private static final Scanner scanner = new Scanner(System.in);
	public static Thread makeManagerThread() {
		return new Thread(() -> {
			while (true) {
				System.out.println("Input the device name : ");
				String deviceName = scanner.next();
				Device device = CoapServerApplication.getDevice(deviceName);
				if (device == null) {
					System.out.println("Device name " + deviceName + " does not exist");
					continue;
				}
				System.out.println("Input the dimming value : ");
				int dimmingValue = scanner.nextInt();
				device.setDimmingValue(dimmingValue);
			}
		});
	}
}
