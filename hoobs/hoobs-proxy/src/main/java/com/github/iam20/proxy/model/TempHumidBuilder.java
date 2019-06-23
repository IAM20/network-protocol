package com.github.iam20.proxy.model;

public class TempHumidBuilder {
	private TempHumid tempHumid;
	public TempHumidBuilder() {
		tempHumid = new TempHumid();
	}

	public TempHumidBuilder celsius(double celsius) {
		tempHumid.setCelsius(celsius);
		return this;
	}

	public TempHumidBuilder humid(double humid) {
		tempHumid.setHumid(humid);
		return this;
	}

	public TempHumid build() {
		return tempHumid;
	}
}
