package com.github.iam20.device.core;

import static com.pi4j.wiringpi.Gpio.wiringPiSetup;
import static com.pi4j.wiringpi.GpioUtil.*;

import com.github.iam20.device.config.ApplicationConfig;
import com.github.iam20.device.model.TempHumid;
import com.github.iam20.device.model.TempHumidBuilder;
import com.pi4j.wiringpi.Gpio;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TempHumidGetter {
	private static final int TIMEOUT = 85;
	public static Thread makeThread() {
		return new Thread(() -> {
			int laststate = Gpio.HIGH;
			int j = 0;
			int[] dht11Dat = {0, 0, 0, 0, 0};
			Gpio.pinMode(3, Gpio.OUTPUT);
			Gpio.digitalWrite(3, Gpio.LOW);
			Gpio.delay(18);

			Gpio.digitalWrite(3, Gpio.HIGH);
			Gpio.pinMode(3, Gpio.INPUT);

			TempHumid tempHumid;
			for (int i = 0; i < TIMEOUT; i++) {
				int counter = 0;
				while(Gpio.digitalRead(3) == laststate) {
					counter++;
					Gpio.delayMicroseconds(1);
					if (counter == 255) break;
				}
				if ((i >= 4) && (i % 2 == 0)) {
					dht11Dat[(j / 8) % 5] <<= 1;
					if (counter > 16) {
						dht11Dat[(j / 8) % 5] |= 1;
					}
					j++;
				}
			}
			if ((j >= 40) && dht11Dat[4] == ((
					dht11Dat[0] + dht11Dat[1] + dht11Dat[2] + dht11Dat[3]) & 0xFF
			)) {
				float h = (float) ((dht11Dat[0] << 8) + dht11Dat[1])/ 10;
				if (h > 100) {
					h = dht11Dat[0];
				} float c = (float) (((dht11Dat[2] & 0x7F) << 8) + dht11Dat[3]) / 10;
				if (c > 125) {
					c = dht11Dat[2];
				}
				if ((dht11Dat[2] & 0x80) != 0) {
					c = -c;
				}
				tempHumid = new TempHumidBuilder()
						.humid((double) h)
						.celcius((double) c)
						.build();
			} else {
				tempHumid = new TempHumidBuilder()
						.humid(- 1.0)
						.celcius(-278.0)
						.build();
			}
			ApplicationConfig.setTempHumid(tempHumid);
		});
	}
}
