package com.github.iam20.coap.service;

import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class BadGatewayException extends RuntimeException {
	BadGatewayException(String msg) {
		super(msg);
	}

	BadGatewayException(String msg, CoapExchange exchange) {
		exchange.respond(CoAP.ResponseCode.BAD_GATEWAY, msg);
	}
}
