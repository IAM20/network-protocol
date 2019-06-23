package com.github.iam20.device.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MacAddress {
	private String macAddr;
	private String orgName = "Unknown";
}
