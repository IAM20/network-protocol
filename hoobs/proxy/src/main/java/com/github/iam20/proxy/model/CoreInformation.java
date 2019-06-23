package com.github.iam20.proxy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoreInformation {
	private List<MacAddress> macAddresses;
	private TempHumid tempHumid;

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
