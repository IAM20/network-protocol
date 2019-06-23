package com.github.iam20.device.core;

import com.github.iam20.device.config.ApplicationConfig;
import com.github.iam20.device.model.MacAddress;
import com.github.iam20.device.util.ArpExecutor;

import java.util.List;

public class MacAddressGetter {
	public static Thread makeThread() {
		return new Thread(() -> {
			List<MacAddress> result = ArpExecutor.findAllDevice();
			ApplicationConfig.setMacAddresses(result);
		});
	}
}
