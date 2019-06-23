package com.github.iam20.proxy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MacAddress {
	private String macAddr;
	private String vendor;
}
