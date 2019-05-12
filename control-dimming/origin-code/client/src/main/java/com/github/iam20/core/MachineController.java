package com.github.iam20.core;

import com.pi4j.wiringpi.SoftPwm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MachineController extends Thread {
	private String control;
	private static final int PIN_NUMBER = 1;

	public MachineController(String control) {
		this.control = control;
	}

	@Override
	public void run() {
		super.run();
		SoftPwm.softPwmCreate(PIN_NUMBER, 0, 100);

		log.info("LED CONTROL");
		log.info("DIMMING = " + control);

		if (control.equals("ON")) {
			SoftPwm.softPwmWrite(PIN_NUMBER, 100);
			log.info("Pin number {}", PIN_NUMBER);
			log.info("ON");
		} else {
			SoftPwm.softPwmWrite(PIN_NUMBER, 0);
			log.info("Pin number {}", PIN_NUMBER);
			log.info("OFF");
		}
	}
}
