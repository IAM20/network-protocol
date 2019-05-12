package com.github.iam20.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;

import com.github.iam20.config.ApplicationConfig;
import com.github.iam20.core.MachineController;

public class Observe {
	static Thread observeThread() {
		return new Thread(() -> {
			CoapClient client = new CoapClient();
			String uri = ApplicationConfig.getBaseUri() + "observe/" + ApplicationConfig.getMySystemId();
			client.setURI(uri);

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			CoapObserveRelation relation = client.observe(new CoapHandler() {
				@Override
				public void onLoad(CoapResponse coapResponse) {
					try {
						String content = coapResponse.getResponseText();
						System.out.println("NOTIFICATION : " + content);
						JSONObject js = new JSONObject(content);
						String control = js.get("Control").toString();
						ApplicationConfig.setState(control);
						if (control.matches("ON")) {
							new MachineController("ON").start();
						} else if (control.matches("OFF")) {
							new MachineController("OFF").start();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onError() {
				}
			});
			try {
				bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			relation.proactiveCancel();
		});
	}
}
