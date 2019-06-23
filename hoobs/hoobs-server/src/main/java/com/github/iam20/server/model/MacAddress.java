package com.github.iam20.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MacAddress {
	private String macAddr;
	private String vendor;
}
