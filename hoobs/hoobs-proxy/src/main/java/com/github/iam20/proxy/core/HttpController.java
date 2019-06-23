package com.github.iam20.proxy.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.iam20.proxy.config.ApplicationConfig;
import com.github.iam20.proxy.model.CoreInformation;

@RestController
public class HttpController {
	@GetMapping("info")
	public CoreInformation getInformation() {
		return ApplicationConfig.getCoreInformation();
	}

	@PostMapping("info")
	public CoreInformation coreInformation(@RequestBody CoreInformation coreInformation) {
		ApplicationConfig.handleCoreInformation(coreInformation);
		return ApplicationConfig.getCoreInformation();
	}
}
