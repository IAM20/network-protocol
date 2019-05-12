package com.github.iam20.coap.resource;

import static com.github.iam20.coap.core.CoapServerApplication.*;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import com.github.iam20.coap.model.DeviceInfo;

@Slf4j
public class ConnectResource extends CoapResource {
	public ConnectResource() {
		super("connect");
	}

	public ConnectResource(String name) {
		super(name);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		try {
			String id, state, mode;
			String requestText = exchange.getRequestText();
			JSONObject parsedObject = new JSONObject(requestText.toUpperCase());

			id = parsedObject.getString("DEVICEID");
			state = parsedObject.getString("STATE");
			mode = parsedObject.getString("MODE");

			log.info("POST request");
			log.info("Device ID : {}", id);
			log.info("Device State : {}", state);
			log.info("Device mode : {}", mode);

			DeviceInfo deviceInfo = new DeviceInfo(id, state, mode);
			addResource("control", new ControlResource(id));
			addResource("report", new ReportResource(id));

			if (mode.equals("push")) {
				ObserveResource resource = new ObserveResource(id);
				addResource("observe", resource);
				deviceInfo.setObserveResource(resource);
			}
			devicePool.put(Integer.parseInt(id), deviceInfo);

			JSONObject json = new JSONObject();
			json.put("response", "success");
			String payload = json.toString();

			exchange.respond(CoAP.ResponseCode.CONTENT, payload, MediaTypeRegistry.APPLICATION_JSON);
		} catch (Exception e) {
			exchange.respond(CoAP.ResponseCode.BAD_GATEWAY, "SOMETHING GOES WRONG");
		}
	}
}
