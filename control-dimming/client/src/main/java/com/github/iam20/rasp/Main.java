package com.github.iam20.rasp;

import com.github.iam20.rasp.coap.ConnectionManager;
import com.github.iam20.rasp.config.ApplicationConfig;

public class Main {
	public static void main(String[] args) {
		ApplicationConfig.init();
		ConnectionManager.connect();
	}
}
