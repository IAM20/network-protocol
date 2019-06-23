package com.github.iam20.device.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
		for (MacAddress mac : macAddresses) {
			buf.append(mac.getMacAddr()).append('\n');
		}
		buf.append("Temperature : ").append(tempHumid.getCelsius()).append("\n");
		buf.append("Humidity : ").append(tempHumid.getHumid());
		return buf.toString();
	}
}
