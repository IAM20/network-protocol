package com.github.iam20.proxy.model;

public class  MacAddressBuilder {
	private MacAddress macAddress;

	public MacAddressBuilder() {
		macAddress = new MacAddress();
	}

	public MacAddressBuilder macAddr(String macAddr) {
		macAddress.setMacAddr(macAddr);
		return this;
	}

	public MacAddressBuilder vendor(String vendor) {
		macAddress.setVendor(vendor);
		return this;
	}

	public MacAddress build() {
		return macAddress;
	}
}
