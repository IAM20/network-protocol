package com.github.iam20.proxy.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CoreInformation {
	private List<MacAddress> macAddresses;
	private TempHumid tempHumid;

	public CoreInformation() {
		macAddresses = new ArrayList<>();
		tempHumid = new TempHumid();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof CoreInformation) {
			CoreInformation coreInformation = (CoreInformation) object;
			if (!tempHumid.equals(coreInformation.getTempHumid())) return false;
			if (macAddresses.size() == coreInformation.getMacAddresses().size()) {
				int len = macAddresses.size();
				for (int i = 0; i < len; i++) {
					String left = macAddresses.get(i).getMacAddr();
					String right = coreInformation.getMacAddresses().get(i).getMacAddr();
					if (!left.equals(right)) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		int i = 0;
		buf.append("MAC Addresses\n");
		for (MacAddress macAddress : macAddresses) {
			buf.append(i).append(" : ").append(macAddress.getMacAddr()).append("\n");
			buf.append(i++).append(" : ").append(macAddress.getVendor()).append("\n");
		}
		buf.append("Temperature and humidity\n");
		buf.append("Temperature : ").append(tempHumid.getCelsius()).append("\n");
		buf.append("Humidity : ").append(tempHumid.getHumid()).append("\n");

		return buf.toString();
	}
}
