package com.github.iam20.coap.service;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class NotFoundException extends RuntimeException {
	NotFoundException(String msg) {
		super(msg);
	}

	NotFoundException(String msg, CoapExchange exchange) {
		exchange.respond(CoAP.ResponseCode.NOT_FOUND, msg);
	}
}
