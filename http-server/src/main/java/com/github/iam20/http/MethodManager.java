package com.github.iam20.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static com.github.iam20.http.HttpListeningManager.*;
import static com.github.iam20.util.HttpMethodGetter.getContentType;
import static com.github.iam20.util.HttpMethodGetter.sendBytes;

public class MethodManager {
	private static final Logger logger = LoggerFactory.getLogger(MethodManager.class);

	public static void makeGetMethodResponse(String element, DataOutputStream outputStream) {
		element = "." + element;
		FileInputStream fis = null;
		boolean isFileExists = true;
		try {
			fis = new FileInputStream(element);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			isFileExists = false;
		}

		StringBuilder buffer = new StringBuilder();
		if (isFileExists) {
			buffer.append("HTTP/1.1 200 OK\n")
					.append("Content-Type: ").append(getContentType(element)).append("\n")
					.append("\n");
		} else {
			sendPageNotFoundString(outputStream);
		}
		try {
			outputStream.writeBytes(buffer.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			return;
		}

		if (isFileExists) {
			sendBytes(fis, outputStream);
		}
	}

	public static void makePostMethodResponse(String element,
	                                          BufferedReader reader,
	                                          DataOutputStream outputStream) throws IOException {
		if (element.equals("/index.html")) {
			sendBadRequestString(outputStream);
			return;
		}
		String result = writeFile(element, reader);
		sendCreatedString(result, outputStream);
	}

	public static void makePutMethodResponse(String element,
	                                         BufferedReader reader,
	                                         DataOutputStream outputStream) throws IOException {
		if (element.equals("/index.html")) {
			sendBadRequestString(outputStream);
			return;
		}
		File file = new File("." + element);

		if (!file.exists()) {
			sendBadRequestString(outputStream);
			return;
		}

		String result = writeFile(element, reader);
		sendCreatedString("\n" + result + "\n", outputStream);
	}

	public static void makeDeleteMethodResponse(String element,
	                                            DataOutputStream outputStream) {
		File file = new File("." + element);

		if (!file.exists()) {
			sendBadRequestString(outputStream);
			return;
		}

		if (!file.delete()) {
			sendInternalErrorString(outputStream);
		}
		sendOkString(outputStream);
	}

	private static String writeFile(String element, BufferedReader reader) throws IOException {
		element = "." + element;
		FileOutputStream fileOutputStream = new FileOutputStream(element);

		StringBuilder buf = new StringBuilder();
		int ch;
		String inputLine;
		while (true) {
			inputLine = reader.readLine();
			if (inputLine == null || inputLine.equals("")) {
				break;
			}
		}

		while (reader.ready())  {
			ch = reader.read();
			buf.append((char)ch);
		}

		fileOutputStream.write(buf.toString().getBytes());
		fileOutputStream.flush();
		fileOutputStream.close();

		return buf.toString();
	}
}
