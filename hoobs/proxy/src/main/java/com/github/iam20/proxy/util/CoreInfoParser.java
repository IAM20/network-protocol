package com.github.iam20.proxy.util;

import com.github.iam20.proxy.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CoreInfoParser {
	public static CoreInformation parse(JSONObject json) {
		JSONArray jsonArray = json.getJSONArray("macaddresses");
		int len = jsonArray.length();
		List<MacAddress> macAddresses = new ArrayList<>(len);
		for (int i = 0; i < len; i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			macAddresses.add(new MacAddressBuilder()
					.orgName((String)object.get("orgname"))
					.macAddr((String)object.get("macaddr"))
					.build());
		}
		JSONObject tempHumidJson = json.getJSONObject("temphumid");
		TempHumid tempHumid;
		tempHumid = new TempHumidBuilder()
				.humid(tempHumidJson.getDouble("humid"))
				.celsius(tempHumidJson.getDouble("celsius"))
				.build();

		return CoreInformation.builder()
				.macAddresses(macAddresses)
				.tempHumid(tempHumid)
				.build();
	}
}
