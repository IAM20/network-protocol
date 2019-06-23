package com.github.iam20.server.config;

import com.github.iam20.server.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationConfig {
	private static CoreInformation coreInformation = new CoreInformation();

	public static void setCoreInformation(CoreInformation ci) {
		coreInformation = ci;
	}

	public static CoreInformation getCoreInformation() {
		return coreInformation;
	}

	public static void setTempHumid(TempHumid tempHumid) {
		coreInformation.setTempHumid(tempHumid);
	}

	public static void setMacAddresses(List<MacAddress> macAddresses) {
		coreInformation.setMacAddresses(macAddresses);
	}

	/* TODO: This is for prototype. After we made the core module, remove this code. */
	@Bean
	public CoreInformation coreInformation() {
		List<MacAddress> macAddresses = Arrays.asList(
				new MacAddressBuilder()
						.macAddr("ff:ff:ff:ff:ff:ff")
						.vendor("Apple Inc")
						.build(),
				new MacAddressBuilder()
						.macAddr("fe:ff:ff:ff:ff:ff")
						.vendor("samsung electronics")
						.build()
		);
		TempHumid tempHumid = new TempHumidBuilder()
				.humid(0.0)
				.celsius(10.0)
				.build();
		coreInformation.setMacAddresses(macAddresses);
		coreInformation.setTempHumid(tempHumid);
		return coreInformation;
	}
}
