package com.github.iam20.core;

import com.github.iam20.config.ApplicationConfig;
import com.github.iam20.service.Connect;
import com.pi4j.wiringpi.Gpio;

public class CoapClientApplication {
	public static void run() {
		ApplicationConfig.init();
		Connect.session();
		Gpio.wiringPiSetup();
	}
}
