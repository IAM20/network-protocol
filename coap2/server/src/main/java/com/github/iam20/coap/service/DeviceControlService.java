package com.github.iam20.coap.service;

import static com.github.iam20.coap.service.DeviceService.*;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

import com.github.iam20.coap.core.CoapServerApplication;
import com.github.iam20.coap.model.Device;
import com.github.iam20.coap.msg.Message;

public class DeviceControlService extends CoapResource {
	public DeviceControlService(String name) {
		super(name);
	}

	private Device getDevice(String name, CoapExchange exchange) throws DeviceDoesNotExistException {
		Device device = CoapServerApplication.getDevice(name);
		if (device == null) {
			exchange.respond(CoAP.ResponseCode.BAD_REQUEST, Message.errorMsg, MediaTypeRegistry.APPLICATION_JSON);
			throw new DeviceDoesNotExistException();
		}
		return device;
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		String deviceName = getName();
		Device device;
		try {
			device = getDevice(deviceName, exchange);
		} catch (Exception e) {
			return;
		}
		JSONObject json = new JSONObject(device);
		exchange.respond(CoAP.ResponseCode.CONTENT, json.toString(), MediaTypeRegistry.APPLICATION_JSON);
	}

	@Override
	public void handlePUT(CoapExchange exchange) {
		String deviceName = getName();
		try {
			getDevice(deviceName, exchange);
		} catch (Exception e) {
			return;
		}
		try {
			Device payload = getDeviceFromCoapExchange(exchange);
			if (!payload.getDeviceName().equals(deviceName)) {
				exchange.respond(CoAP.ResponseCode.BAD_REQUEST, Message.errorMsg, MediaTypeRegistry.APPLICATION_JSON);
				return;
			}
			CoapServerApplication.addDevice(payload);
			exchange.respond(CoAP.ResponseCode.CONTENT, Message.successMsg, MediaTypeRegistry.APPLICATION_JSON);
		} catch (Exception e) {
			exchange.respond(CoAP.ResponseCode.BAD_REQUEST, Message.errorMsg, MediaTypeRegistry.APPLICATION_JSON);
		}
	}
}