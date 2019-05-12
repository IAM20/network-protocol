package com.github.iam20.coap.core;

import com.github.iam20.coap.model.DeviceInfo;
import com.github.iam20.coap.resource.ConnectResource;
import com.github.iam20.coap.resource.ControlResource;
import com.github.iam20.coap.resource.ObserveResource;
import com.github.iam20.coap.resource.ReportResource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CoapServerApplication {
	private static ConnectResource connectResource = new ConnectResource();
	private static ControlResource controlResource = new ControlResource();
	private static ObserveResource observeResource = new ObserveResource();
	private static ReportResource reportResource = new ReportResource();

	private static Map<String, CoapResource> coapResourceMap = Map.of(
			"connect", connectResource,
			"control", controlResource,
			"observe", observeResource,
			"report", reportResource
	);

	public static Map<Integer, DeviceInfo> devicePool = new HashMap<>();

	public static void run() {
		CoapServer server = new CoapServer();
		for (CoapResource resource : coapResourceMap.values()) {
			server.add(resource);
		}
		server.start();
	}

	public static void addResource(String parentName, CoapResource resource) {
		switch (parentName) {
			case "connect":
				connectResource.add(resource);
				break;
			case "control":
				controlResource.add(resource);
				break;
			case "observe":
				observeResource.add(resource);
				break;
			case "report":
				reportResource.add(resource);
				break;
			default:
				log.error("Unknown resource {}", parentName);
				break;
		}
	}

}
