package com.github.iam20.server.core;

import java.util.ArrayList;
import java.util.List;

import com.github.iam20.server.model.CoreInformation;
import com.github.iam20.server.model.MacAddress;
import com.github.iam20.server.model.TempHumid;
import com.github.iam20.server.model.TempHumidBuilder;
import org.springframework.stereotype.Component;

@Component
public class InfoManager {
	public static CoreInformation getInformation() {
		return CoreInformation.builder()
				.macAddresses(getMacAddresses())
				.tempHumid(getTempHumid())
				.build();
	}

	public static List<MacAddress> getMacAddresses() {
		return new ArrayList<>();
	}

	public static TempHumid getTempHumid() {
		return new TempHumidBuilder()
				.celsius(10.0)
				.humid(30.0)
				.build();
	}

	public static void setInformation(CoreInformation coreInformation) {

	}
}
