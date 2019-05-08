package com.github.iam20.service;

import static com.github.iam20.util.TimerTaskWrapper.*;

import java.util.Timer;

import org.json.JSONObject;
import org.eclipse.californium.core.CoapClient;
import lombok.extern.slf4j.Slf4j;

import com.github.iam20.core.MachineController;
import com.github.iam20.config.ApplicationConfig;

/**
 * Polling... class
 */

@Slf4j
public class Control {
	static Thread controlThread() {
		return new Thread(() -> {
			Timer timer = new Timer();
			timer.schedule(wrap(() -> {
				CoapClient client = new CoapClient();
				String uri = ApplicationConfig.getBaseUri() + "control/" + ApplicationConfig.getMySystemId();
				client.setURI(uri);
				String response = client.get().getResponseText();
				log.info("Control response : {}", response);
				JSONObject json = new JSONObject(response);

				String control = json.getString("Control");
				ApplicationConfig.setState(control);

				switch (control) {
					case "ON":
					case "OFF":
						new MachineController(control).start();
					default:
				}
			}), 0, ApplicationConfig.getPollingInterval());
		});
	}
}
