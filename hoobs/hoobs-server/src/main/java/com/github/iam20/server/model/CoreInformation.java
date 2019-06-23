package com.github.iam20.server.model;

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
}
