package com.github.iam20.server.api;

import java.util.List;
import com.github.iam20.server.config.ApplicationConfig;
import com.github.iam20.server.model.CoreInformation;
import com.github.iam20.server.model.MacAddress;
import com.github.iam20.server.model.TempHumid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class ApiController {
	@GetMapping("info")
	public CoreInformation getInformation() {
		return ApplicationConfig.getCoreInformation();
	}

	@PostMapping("info")
	@ResponseStatus(HttpStatus.CREATED)
	public CoreInformation postInformation(@RequestBody CoreInformation coreInformation) {
		ApplicationConfig.setCoreInformation(coreInformation);
		return ApplicationConfig.getCoreInformation();
	}

	@PutMapping("info")
	public CoreInformation putInformation(@RequestBody CoreInformation coreInformation) {
		return postInformation(coreInformation);
	}

	@PostMapping("air-con")
	@ResponseStatus(HttpStatus.CREATED)
	public CoreInformation postTempHumid(@RequestBody TempHumid tempHumid) {
		ApplicationConfig.setTempHumid(tempHumid);
		return ApplicationConfig.getCoreInformation();
	}

	@PutMapping("air-con")
	public CoreInformation putTempHumid(@RequestBody TempHumid tempHumid) {
		return postTempHumid(tempHumid);
	}

	@PostMapping("mac")
	@ResponseStatus(HttpStatus.CREATED)
	public CoreInformation postMacAddresses(@RequestBody List<MacAddress> macAddresses) {
		ApplicationConfig.setMacAddresses(macAddresses);
		return ApplicationConfig.getCoreInformation();
	}

	@PutMapping("mac")
	public CoreInformation putMacAddresses(@RequestBody List<MacAddress> macAddresses) {
		return postMacAddresses(macAddresses);
	}

}
