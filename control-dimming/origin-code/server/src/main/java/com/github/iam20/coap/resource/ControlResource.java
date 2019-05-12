package com.github.iam20.coap.resource;

import static com.github.iam20.coap.core.CoapServerApplication.*;

import com.github.iam20.coap.model.DeviceInfo;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class ControlResource extends CoapResource {
	public ControlResource() {
		super("control");
	}

	public ControlResource(String name) {
		super(name);
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		try {
			String id = getName();
			DeviceInfo device = devicePool.get(Integer.parseInt(id));
			JSONObject json = new JSONObject();

			if (device == null) {
				json.put("Control", "Device not found");
			} else {
				if (device.isEventFlag()) {
					json.put("Control", device.getEvent());
				} else {
					json.put("Control", "NONE");
				}
			}
			String payload = json.toString();
			exchange.respond(CoAP.ResponseCode.CONTENT, payload, MediaTypeRegistry.APPLICATION_JSON);
		} catch (Exception e) {
			exchange.respond(CoAP.ResponseCode.BAD_REQUEST, "Wrong access");
		}
	}
}
