package com.github.iam20.coap.model;

public class DeviceBuilder {
	private Device device;

	public DeviceBuilder() {
		device = new Device();
	}

	public DeviceBuilder deviceName(String name) {
		device.setDeviceName(name);
		return this;
	}

	public DeviceBuilder dimmingValue(int dimmingValue) {
		device.setDimmingValue(dimmingValue);
		return this;
	}

	public DeviceBuilder dimmingValue(String dimmingValue) {
		return dimmingValue(Integer.parseInt(dimmingValue));
	}

	public DeviceBuilder polling(boolean isPolling) {
		device.setPolling(isPolling);
		return this;
	}

	public DeviceBuilder polling(String polling) {
		if (polling == null) polling = "false";
		return polling(Boolean.parseBoolean(polling));
	}

	public DeviceBuilder ledOn(Boolean ledOn) {
		device.setLedOn(ledOn);
		return this;
	}

	public DeviceBuilder ledOn(String ledOn) {
		if (ledOn == null) ledOn = "false";
		return ledOn(Boolean.parseBoolean(ledOn));
	}

	public Device build() {
		return device;
	}
}
