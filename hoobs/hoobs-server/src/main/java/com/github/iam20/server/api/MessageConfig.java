package com.github.iam20.server.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.github.iam20.server.config.ApplicationConfig;
import com.github.iam20.server.model.CoreInformation;

@Controller
public class MessageConfig {
	@MessageMapping("/information")
	@SendTo("/topic/information")
	public CoreInformation sendCoreInfo() throws Exception {
		Thread.sleep(1000);
		return ApplicationConfig.getCoreInformation();
	}
}
