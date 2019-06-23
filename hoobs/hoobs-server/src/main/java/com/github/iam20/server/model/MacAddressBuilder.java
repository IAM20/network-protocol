package com.github.iam20.server.model;

public class MacAddressBuilder {
	private MacAddress macAddress;

	public MacAddressBuilder() {
		macAddress = new MacAddress();
	}

	public MacAddressBuilder macAddr(String macAddr) {
		macAddress.setMacAddr(macAddr);
		return this;
	}

	public MacAddressBuilder vendor(String orgName) {
		macAddress.setVendor(orgName);
		return this;
	}

	public MacAddress build() {
		return macAddress;
	}
}
