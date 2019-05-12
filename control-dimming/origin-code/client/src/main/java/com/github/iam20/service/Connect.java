package com.github.iam20.service;

import static com.github.iam20.service.Control.controlThread;
import static com.github.iam20.service.Observe.observeThread;
import static com.github.iam20.service.Report.reportThread;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.iam20.config.ApplicationConfig;

@Slf4j
public class Connect {
	public static void session() throws JSONException {
		CoapClient client = new CoapClient();

		String uri = ApplicationConfig.getBaseUri() + "connect";
		log.info("Connect URI : {}", uri);
		client.setURI(uri);

		JSONObject json = new JSONObject();
		json.put("DeviceID", ApplicationConfig.getMySystemId());
		json.put("State", ApplicationConfig.getState());
		json.put("Mode", ApplicationConfig.getMode());

		String payload = json.toString();
		log.info("Request payload below\n{}", payload);
		CoapResponse coapResponse = client.post(payload, MediaTypeRegistry.APPLICATION_JSON);

		log.debug(coapResponse.getResponseText());

		reportThread().start();

		String myMode = ApplicationConfig.getMode();
		switch (myMode) {
			case "pull":
				controlThread().start();
				break;
			case "push":
				observeThread().start();
				break;
			default:
		}
	}
}
