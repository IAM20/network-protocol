package com.github.iam20.device.model;

public class MacAddressBuilder {
	private MacAddress macAddress;

	public MacAddressBuilder() {
		macAddress = new MacAddress();
	}

	public MacAddressBuilder macAddr(String macAddr) {
		macAddress.setMacAddr(macAddr);
		return this;
	}

	public MacAddressBuilder orgName(String orgName) {
		macAddress.setOrgName(orgName);
		return this;
	}

	public MacAddress build() {
		return macAddress;
	}
}
