package com.github.iam20.rasp.coap;

import static com.github.iam20.rasp.util.TimerTaskWrapper.wrap;

import com.github.iam20.rasp.config.ApplicationConfig;
import com.github.iam20.rasp.core.MachineController;
import com.github.iam20.rasp.model.Device;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

import java.util.Timer;

@Slf4j
public class ConnectionManager {
	private static CoapClient client = new CoapClient();
	private static MachineController controller;
	private static Device device;

	public static void connect() {
		String uri = ApplicationConfig.getServerBaseUri();
		device = ApplicationConfig.getDevice();
		controller = new MachineController(device);
		uri += "device";
		client.setURI(uri);
		log.info(uri);

		JSONObject json = new JSONObject(device);
		log.info(json.toString());
		CoapResponse response = client.post(json.toString(), MediaTypeRegistry.APPLICATION_JSON);
		log.info(response.getResponseText());
		pollingThread().start();
		controller.start();
	}

	private static Thread pollingThread() {
		return new Thread(() -> {
			Timer timer = new Timer();
			timer.schedule(wrap(() -> {
				String uri = ApplicationConfig.getServerBaseUri();
				uri += "device/";
				uri += device.getDeviceName();
				client.setURI(uri);

				String response = client.get().getResponseText();
				JSONObject json = new JSONObject(response.toLowerCase());

				int value = (int)json.get("dimmingvalue");
				device.setDimmingValue(value);
			}), 0, ApplicationConfig.getPollingInterval());
		});
	}
}
