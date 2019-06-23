package com.github.iam20.proxy.core;

import com.github.iam20.proxy.coap.CoapServerApplication;
import com.github.iam20.proxy.config.ApplicationConfig;

public class ProxyApplication {
	public static void run() {
		ApplicationConfig.init();
		CoapServerApplication.run();
	}
}
