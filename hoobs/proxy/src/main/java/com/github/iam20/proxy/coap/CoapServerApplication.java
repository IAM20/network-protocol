package com.github.iam20.proxy.coap;

import org.eclipse.californium.core.CoapServer;

public class CoapServerApplication {
	private static CoapServer server = new CoapServer();

	public static void run() {
		server.add(new InformationManager());
		server.start();
	}
}
