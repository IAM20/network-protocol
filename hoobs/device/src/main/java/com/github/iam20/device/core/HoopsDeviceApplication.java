package com.github.iam20.device.core;

import com.github.iam20.device.config.ApplicationConfig;
import com.github.iam20.device.model.TempHumidBuilder;
import com.pi4j.wiringpi.Gpio;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HoopsDeviceApplication {
	public static void run() {
		ApplicationConfig.init();
		if (Gpio.wiringPiSetup() == -1) {
			log.info("Failed to setup wiring pi");
			ApplicationConfig.setTempHumid(new TempHumidBuilder()
					.celcius(-275.0)
					.humid(-1.0)
					.build());
		} else {
			TempHumidGetter.makeThread().run();
		}
		MacAddressGetter.makeThread().run();
		System.out.println(ApplicationConfig.getCoreInformation().toString());
		CoapSender.send();
		log.info("JOBs finished");
	}
}
