package com.github.iam20.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpRequestMessage {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestMessage.class);

	private String method;
	private Map<String, String> header;
	private String body;

	public void send(String url) {
		if (!isValidRequest()) {
			logger.error("This is not valid request check your method and header");
			return;
		}

		try {
			String retMsg = HttpRequestManager.sendRequest(url, method, body, header);
			logger.info("Response msg below\n{}", retMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isValidRequest() {
		return !(method == null || header == null);
	}
}
