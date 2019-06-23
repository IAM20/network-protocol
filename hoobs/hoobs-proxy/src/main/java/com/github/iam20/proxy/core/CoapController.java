package com.github.iam20.proxy.core;

import org.eclipse.californium.core.CoapServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoapController {
	@Autowired
	public CoapController(InformationManager informationManager) {
		CoapServer coapServer = new CoapServer();
		coapServer.add(informationManager);
		coapServer.start();
	}
}
