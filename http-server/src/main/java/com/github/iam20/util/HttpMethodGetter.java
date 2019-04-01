package com.github.iam20.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.github.iam20.http.HttpListeningManager.*;

public class HttpMethodGetter {
	private static Logger logger = LoggerFactory.getLogger(HttpMethodGetter.class);

	public static int parseHttpMethod(String method) {
		if (method.equals("GET")) {
			return GET_METHOD;
		} else if (method.equals("POST")) {
			return POST_METHOD;
		} else if (method.equals("PUT")) {
			return PUT_METHOD;
		} else if (method.equals("DELETE")) {
			return DELETE_METHOD;
		} else {
			return -1;
		}
	}
	public static String getContentType(String fileName) {
		if(fileName.endsWith(".htm") || fileName.endsWith(".html")) {
			return "text/html";
		}
		if(fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
			return "image/jpeg";
		}
		return "application/octet-stream";
	}
	public static void sendBytes(FileInputStream fis, OutputStream os)  {
		// Construct a 1K buffer to hold bytes on their way to the socket.
		byte[] buffer = new byte[1024];
		int bytes;

		// Copy requested file into the socket's output stream.
		try {
			while ((bytes = fis.read(buffer)) != -1) {
				os.write(buffer, 0, bytes);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		try {
			fis.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.exit(-1);
		}
	}
}
