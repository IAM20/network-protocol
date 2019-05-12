package com.github.iam20.coap.model;

import lombok.Data;

import com.github.iam20.coap.resource.ObserveResource;

@Data
public class DeviceInfo {
	private String deviceId;
	private String mode;
	private String state;
	private String event;
	private boolean eventFlag;
	private ObserveResource observeResource;

	public DeviceInfo(String deviceId, String state, String mode) {
		this.deviceId = deviceId;
		this.state = state;
		this.mode = mode;
	}

	public boolean controlEvent(String newState) {
		event = newState;
		System.out.println("1");
		if (mode.equals("pull")) {
			eventFlag = true;
			System.out.println("2");
		} else {
			if (observeResource == null) {
				return false;
			}
			System.out.println("3");
			observeResource.changed();
		}

		return true;
	}

	@Override
	public String toString() {
		return "deviceId : " + deviceId + "\n" +
				"mode : " + mode + "\n" +
				"state : " + state +"\n" +
				"event : " + event + "\n" +
				"eventFlag : " + eventFlag +"\n";
	}
}
