package com.github.iam20.device.model;

public class TempHumidBuilder {
	private TempHumid tempHumid;
	public TempHumidBuilder() {
		tempHumid = new TempHumid();
	}

	public TempHumidBuilder celcius(double value) {
		tempHumid.setCelsius(value);
		return this;
	}

	public TempHumidBuilder humid(double value) {
		tempHumid.setHumid(value);
		return this;
	}

	public TempHumid build() {
		return tempHumid;
	}
}
