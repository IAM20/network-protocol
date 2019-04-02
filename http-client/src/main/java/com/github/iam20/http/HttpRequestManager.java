package com.github.iam20.http;

import com.github.iam20.util.UrlValidateChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class HttpRequestManager {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequestManager.class);
	private static final int TIMEOUT_DURATION = 30000;

	public static String sendRequest(String urlString, String method, Map<String, String> header) throws Exception {
		return sendRequest(urlString, method, "", header);
	}

	public static String sendRequest(String urlString, String method, String element, Map<String, String> header) throws Exception {
		urlString = UrlValidateChecker.makeValidUrl(urlString);
		URL url = new URL(urlString);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(method);

		Set<Map.Entry<String, String>> entrySet = header.entrySet();
		for (Map.Entry<String, String> entry : entrySet) {
			urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
		}
		urlConnection.setConnectTimeout(TIMEOUT_DURATION);

		if (method.equals("POST") || method.equals("PUT")) {
			urlConnection.setDoOutput(true);
			DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
			outputStream.writeBytes(UrlValidateChecker.makeValidElement(element));
		}
		urlConnection.connect();

		logger.debug(method);
		logger.info("Sending " + urlConnection.getRequestMethod() + " request to specified URL : " + urlString);
		int responseCode = urlConnection.getResponseCode();
		logger.debug("{}", responseCode);
		try {
			if (HttpURLConnection.HTTP_OK != responseCode &&
				HttpURLConnection.HTTP_CREATED != responseCode &&
				HttpURLConnection.HTTP_ACCEPTED != responseCode) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		StringBuilder buf = new StringBuilder();

		String inputLine;
		while ((inputLine = inputStream.readLine()) != null) {
			buf.append(inputLine).append('\n');
		}

		inputStream.close();
		urlConnection.disconnect();
		return buf.toString();
	}
}
