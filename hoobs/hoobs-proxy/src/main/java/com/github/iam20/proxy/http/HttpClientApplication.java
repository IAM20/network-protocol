package com.github.iam20.proxy.http;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.iam20.proxy.model.CoreInformation;

@Slf4j
@Component
public class HttpClientApplication {
	private static RestTemplate restTemplate = new RestTemplate();
	private static String restServerURI;

	@Autowired
	public HttpClientApplication(@Value("${rest.server.ip}") String ip, @Value("${rest.server.port}") String port) {
		restServerURI = "http://" + ip + ":" + port + "/info";
	}

	public static int send(CoreInformation coreInformation) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject json = new JSONObject(coreInformation);
		HttpEntity<String> coreInformationHttpEntity = new HttpEntity<>(json.toString(), headers);

		log.info(coreInformationHttpEntity.toString());

		ResponseEntity<String> response =
				restTemplate.exchange(restServerURI, HttpMethod.POST, coreInformationHttpEntity, String.class);

		return response.getStatusCodeValue();
	}
}
