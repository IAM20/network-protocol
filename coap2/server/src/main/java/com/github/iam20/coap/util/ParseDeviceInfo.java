package com.github.iam20.coap.util;

import com.github.iam20.coap.model.Device;
import com.github.iam20.coap.model.DeviceBuilder;
import org.json.JSONObject;

public class ParseDeviceInfo {
	public static Device parse(JSONObject json) {
		return new DeviceBuilder()
				.dimmingValue((int)json.get("dimmingvalue"))
				.deviceName(json.getString("devicename"))
				.polling((boolean)json.get("polling"))
				.ledOn((boolean)json.get("ledon"))
				.build();
	}
}
