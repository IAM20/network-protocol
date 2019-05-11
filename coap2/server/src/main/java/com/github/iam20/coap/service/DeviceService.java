package com.github.iam20.coap.service;

import com.github.iam20.coap.core.CoapServerApplication;
import com.github.iam20.coap.model.Device;
import com.github.iam20.coap.msg.Message;
import com.github.iam20.coap.util.ParseDeviceInfo;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

@Slf4j
public class DeviceService extends CoapResource {
	public DeviceService(String name) {
		super(name);
	}

	public DeviceService() {
		this("device");
	}

	static Device getDeviceFromCoapExchange(CoapExchange exchange) {
		String requestText = exchange.getRequestText();
		JSONObject json = new JSONObject(requestText.toLowerCase());
		return ParseDeviceInfo.parse(json);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		try {
			Device connectedDevice = getDeviceFromCoapExchange(exchange);
			CoapServerApplication.addDevice(connectedDevice);
			add(new DeviceControlService(connectedDevice.getDeviceName()));
			log.info(connectedDevice.toString());
			/* Add service via device name. */
			exchange.respond(CoAP.ResponseCode.CONTENT, Message.successMsg, MediaTypeRegistry.APPLICATION_JSON);
		} catch (Exception e) {
			exchange.respond(CoAP.ResponseCode.BAD_REQUEST, Message.errorMsg, MediaTypeRegistry.APPLICATION_JSON);
		}
	}
}
