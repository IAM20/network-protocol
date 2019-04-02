package com.github.iam20.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import static com.github.iam20.util.HttpMethodGetter.parseHttpMethod;
import static com.github.iam20.http.HttpListeningManager.*;
import static com.github.iam20.http.MethodManager.*;

public class ListenThread implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ListenThread.class);

	private Socket socket;
	private BufferedReader reader;
	private DataOutputStream outputStream;

	public ListenThread(Socket socket) throws IOException {
		this.socket = socket;
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		outputStream = new DataOutputStream(socket.getOutputStream());
	}

	public void run() {
		try {
			process();
		} catch (IOException e) {
			for (StackTraceElement stackTraceElement : e.getStackTrace()) {
				logger.error(stackTraceElement.toString());
			}
		}
	}

	private void process() throws IOException {
		String requestMethod = reader.readLine();
		if (requestMethod == null) {
			return;
		}
		logger.debug(requestMethod);

		StringTokenizer tokenizer = new StringTokenizer(requestMethod);
		String method = tokenizer.nextToken();
		String element = tokenizer.nextToken();

		switch (parseHttpMethod(method)) {
			case GET_METHOD:
				makeGetMethodResponse(element, outputStream);
				break;
			case PUT_METHOD:
				makePutMethodResponse(element, reader, outputStream);
				break;
			case POST_METHOD:
				makePostMethodResponse(element, reader, outputStream);
				break;
			case DELETE_METHOD:
				makeDeleteMethodResponse(element, outputStream);
				break;
			default:
				break;
		}

		socket.close();
		outputStream.close();
		reader.close();
	}
}
