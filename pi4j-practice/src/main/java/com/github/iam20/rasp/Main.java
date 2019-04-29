package com.github.iam20.rasp;

import static com.pi4j.wiringpi.Gpio.wiringPiSetup;
import com.pi4j.wiringpi.SoftPwm;

public class Main {
	public static void main(String[] args) throws InterruptedException {

		// initialize wiringPi library
		wiringPiSetup();

		// create soft-pwm pins (min=0 ; max=100)
		SoftPwm.softPwmCreate(1, 0, 100);

		for (int i = 0; i <= 100; i++) {
			SoftPwm.softPwmWrite(1, i);
			Thread.sleep(100);
		}

		// fade LED to fully OFF
		for (int i = 100; i >= 0; i--) {
			SoftPwm.softPwmWrite(1, i);
			Thread.sleep(100);
		}

		// make sure to stop software PWM driver/thread if you done with it.
		SoftPwm.softPwmStop(1);
	}
}
