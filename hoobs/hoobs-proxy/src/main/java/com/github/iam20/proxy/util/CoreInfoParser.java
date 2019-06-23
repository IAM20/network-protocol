package com.github.iam20.proxy.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.iam20.proxy.model.*;

public class CoreInfoParser {
	public static CoreInformation parse(JSONObject json) {
		JSONArray jsonArray = json.getJSONArray("macAddresses");
		int len = jsonArray.length();
		List<MacAddress> macAddresses = new ArrayList<>(len);
		for (int i = 0; i < len; i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			macAddresses.add(new MacAddressBuilder()
					.vendor((String)object.get("vendor"))
					.macAddr((String)object.get("macAddr"))
					.build());
		}
		JSONObject tempHumidJson = json.getJSONObject("tempHumid");
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
