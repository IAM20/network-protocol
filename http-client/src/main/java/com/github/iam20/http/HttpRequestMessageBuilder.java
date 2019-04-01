package com.github.iam20.http;

import java.util.Map;

public class HttpRequestMessageBuilder {
	private HttpRequestMessage httpRequestMessage;

	public HttpRequestMessageBuilder() {
		httpRequestMessage = new HttpRequestMessage();
	}

	public HttpRequestMessageBuilder method(String method) {
		httpRequestMessage.setMethod(method.toUpperCase());
		return this;
	}

	public HttpRequestMessageBuilder header(Map<String, String> header) {
		httpRequestMessage.setHeader(header);
		return this;
	}

	public HttpRequestMessageBuilder body(String body) {
		httpRequestMessage.setBody(body);
		return this;
	}

	public HttpRequestMessage build() {
		return httpRequestMessage;
	}
}
