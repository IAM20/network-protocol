package com.github.iam20.coap.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
	String deviceName;
	Integer dimmingValue;
	boolean polling;
	boolean ledOn;
}
