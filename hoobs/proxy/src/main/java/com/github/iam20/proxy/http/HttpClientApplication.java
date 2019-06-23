package com.github.iam20.proxy.http;

import static com.github.iam20.proxy.config.ApplicationConfig.getHttpRestServerURI;

import com.github.iam20.proxy.model.CoreInformation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class HttpClientApplication {
	private static RestTemplate restTemplate = new RestTemplate();

	public static int send(CoreInformation coreInformation) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject json = new JSONObject(coreInformation);
		HttpEntity<String> coreInformationHttpEntity = new HttpEntity<>(json.toString(), headers);

		log.info(coreInformationHttpEntity.toString());

		ResponseEntity<String> response =
				restTemplate.exchange(getHttpRestServerURI(), HttpMethod.POST, coreInformationHttpEntity, String.class);

		return response.getStatusCodeValue();
	}
}
