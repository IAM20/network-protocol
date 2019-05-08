package com.github.iam20.service;

import static com.github.iam20.util.TimerTaskWrapper.wrap;

import java.util.Timer;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.iam20.config.ApplicationConfig;

@Slf4j
public class Report {
	static Thread reportThread() {
		return new Thread(() -> {
			Timer timer = new Timer();
			timer.schedule(wrap(() -> {
				CoapClient client = new CoapClient();
				String uri = ApplicationConfig.getBaseUri() + "report/" + ApplicationConfig.getMySystemId();
				client.setURI(uri);
				JSONObject json = new JSONObject();

				try {
					String state = ApplicationConfig.getState();
					switch (state) {
						case "ON":
						case "OFF":
							json.put("State", state);
							break;
						default:
							json.put("State", "OFF");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				CoapResponse response = client.put(json.toString(), MediaTypeRegistry.APPLICATION_JSON);
				log.info("Report response : {}" + response.getResponseText());
			}), 0, ApplicationConfig.getPollingInterval());
		});
	}
}
