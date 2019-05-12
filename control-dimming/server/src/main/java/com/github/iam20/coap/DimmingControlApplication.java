package com.github.iam20.coap;

import com.github.iam20.coap.core.CoapServerApplication;
import com.github.iam20.coap.core.CoapServerService;

public class DimmingControlApplication {
	public static void main(String[] args) {
		CoapServerService.makeManagerThread().start();
		CoapServerApplication.run();
	}
}
