package com.github.iam20.rasp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
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
