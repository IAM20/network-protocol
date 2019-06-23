package com.github.iam20.proxy.config;

import com.github.iam20.proxy.http.HttpClientApplication;
import com.github.iam20.proxy.model.CoreInformation;
import com.github.iam20.proxy.util.VendorNameGetter;

public class ApplicationConfig {
	private static CoreInformation coreInformation = new CoreInformation();

	public static void setCoreInformation(CoreInformation coreInformation) {
		ApplicationConfig.coreInformation = coreInformation;
	}

	public static CoreInformation getCoreInformation() {
		return coreInformation;
	}

	public static boolean handleCoreInformation(CoreInformation receivedInformation) {
		if (ApplicationConfig.coreInformation.equals(receivedInformation)) {
			return false;
		}
		CoreInformation ci = VendorNameGetter.getVendorName(receivedInformation);
		int code = HttpClientApplication.send(ci);
		coreInformation = ci;
		return (code % 200) < 100;
	}
}
