package com.github.iam20.proxy.core;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.github.iam20.proxy.config.ApplicationConfig;
import com.github.iam20.proxy.model.CoreInformation;
import com.github.iam20.proxy.util.CoreInfoParser;

@Slf4j
@Component
public class InformationManager extends CoapResource {
	public InformationManager() {
		super("info");
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		String requestText = exchange.getRequestText();
		JSONObject json = new JSONObject(requestText);
		CoreInformation receivedInformation = CoreInfoParser.parse(json);
		if (ApplicationConfig.handleCoreInformation(receivedInformation)) {
			json.put("code", 201);
			exchange.respond(CoAP.ResponseCode.CREATED, json.toString(), MediaTypeRegistry.APPLICATION_JSON);
		} else {
			json.put("code", 205);
			exchange.respond(CoAP.ResponseCode.CONTENT, json.toString(), MediaTypeRegistry.APPLICATION_JSON);
		}
	}


	@Override
	public void handlePUT(CoapExchange exchange) {
		handlePOST(exchange);
	}
}
