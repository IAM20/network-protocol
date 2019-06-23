package com.github.iam20.device.core;

import com.github.iam20.device.config.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

@Slf4j
public class CoapSender {
	private static CoapClient client = new CoapClient();

	public static void send() {
		String uri = ApplicationConfig.getProxyServerUri() + "info";
		log.debug("Send CoAP request to {}", uri);
		client.setURI(uri);
		JSONObject json = new JSONObject(ApplicationConfig.getCoreInformation());
		CoapResponse response = client.post(json.toString(), MediaTypeRegistry.APPLICATION_JSON);
		log.info(response.getResponseText());
	}
}
