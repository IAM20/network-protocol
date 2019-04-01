package com.github.iam20.core;

import com.github.iam20.http.HttpRequestMessage;
import com.github.iam20.http.HttpRequestMessageBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.github.iam20.core.Application.scanner;

public class HttpRequestHandler {
	private static final int GET = 0;
	private static final int POST = 1;
	private static final int PUT = 2;
	private static final int DELETE = 3;

	public static void handle(String method){
		int methodCode = getMethodNumber(method);
		Map<String, String> httpHeader = new HashMap<>(Map.of(
				"User-Agent", "CLIENT_OF_LEE",
				"Accept", "text/html"
		));
		String body = null;

		switch (methodCode) {
			case GET:
				break;
			case POST:
			case PUT:
				body = makePostPutDataBody();
				break;
			case DELETE:
				break;
		}
		System.out.print("Input URL : ");
		String url = scanner.next();

		message(body, httpHeader, method).send(url);
	}

	private static HttpRequestMessage message(String body, Map<String, String> header, String method) {
		return new HttpRequestMessageBuilder()
				.body(body)
				.header(header)
				.method(method)
				.build();
	}

	private static String makePostPutDataBody() {
		System.out.println("Input your file path : ");
		String filePath = scanner.next();

		try {
			FileInputStream inputStream = new FileInputStream("./" + filePath);
			byte[] bytes = inputStream.readAllBytes();
			return new String(bytes);
		} catch (FileNotFoundException e) {
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	private static int getMethodNumber(String method) {
		if (method.equalsIgnoreCase("GET")) {
			return GET;
		} else if (method.equalsIgnoreCase("POST")) {
			return POST;
		} else if (method.equalsIgnoreCase("PUT")) {
			return PUT;
		} else if (method.equalsIgnoreCase("DELETE")) {
			return DELETE;
		} return GET;
	}
}
