package com.github.iam20.coap.resource;

import static com.github.iam20.coap.core.CoapServerApplication.*;

import com.github.iam20.coap.model.DeviceInfo;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONException;
import org.json.JSONObject;

public class ReportResource extends CoapResource {
	public ReportResource() {
		super("report");
	}

	public ReportResource(String name) {
		super(name);
	}

	@Override
	public void handlePUT(CoapExchange exchange) {
		try {
			String deviceId = getName();
			String requestText = exchange.getRequestText();
			DeviceInfo device = devicePool.get(Integer.parseInt(deviceId));
			JSONObject json = new JSONObject(requestText.toUpperCase());
			String state = json.getString("STATE");

			JSONObject respond = new JSONObject();
			if (device == null) {
				json.put("Response", "failure");
			} else {
				device.setState(state);
				json.put("Response", "success");
			}
			exchange.respond(CoAP.ResponseCode.CONTENT, respond.toString(), MediaTypeRegistry.APPLICATION_JSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
