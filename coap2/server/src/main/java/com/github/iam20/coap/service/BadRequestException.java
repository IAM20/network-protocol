package com.github.iam20.coap.service;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class BadRequestException extends RuntimeException {
	@Deprecated
	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException(String msg, CoapExchange exchange) {
		exchange.respond(CoAP.ResponseCode.BAD_REQUEST, msg);
	}
}
