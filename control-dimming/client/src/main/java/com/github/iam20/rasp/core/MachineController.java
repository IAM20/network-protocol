package com.github.iam20.rasp.core;

import static com.pi4j.wiringpi.Gpio.wiringPiSetup;

import com.pi4j.wiringpi.SoftPwm;
import lombok.extern.slf4j.Slf4j;

import com.github.iam20.rasp.model.Device;

@Slf4j
public class MachineController extends Thread {
	private static final int PIN_NUMBER = 1;
	private Device device;

	public MachineController(Device device) {
		this.device = device;
	}

	@Override
	public void run() {
		wiringPiSetup();
		SoftPwm.softPwmCreate(PIN_NUMBER, 0, 100);
		while (true) {
			int dimmingValue = device.getDimmingValue();
			if (dimmingValue > 0) {
				device.setLedOn(true);
				SoftPwm.softPwmWrite(1, dimmingValue);
				log.info("LED Dimming value {}", dimmingValue);
			} else {
				device.setLedOn(false);
				SoftPwm.softPwmWrite(1, 0);
				log.info("LED OFF");
			}
		}
	}
}
