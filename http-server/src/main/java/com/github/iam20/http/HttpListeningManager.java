package com.github.iam20.http;

import static com.github.iam20.msg.HttpResponseMessage.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;

public class HttpListeningManager {
	private static final Logger logger = LoggerFactory.getLogger(HttpListeningManager.class);
	public static final int GET_METHOD = 1;
	public static final int PUT_METHOD = 2;
	public static final int POST_METHOD = 3;
	public static final int DELETE_METHOD = 4;

	public static void sendPageNotFoundString(DataOutputStream outputStream) {
		try {
			outputStream.writeBytes(MSG_404_NOT_FOUND);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public static void sendBadRequestString(DataOutputStream outputStream) {
		try {
			outputStream.writeBytes(MSG_400_BAD_REQUEST);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public static void sendCreatedString(String element, DataOutputStream outputStream) {
		StringBuilder buf = new StringBuilder();
		buf.append("HTTP/1.1 201 Created\n")
				.append("Content-Type: text/html\n")
				.append("\n")
				.append("<HTML>\n")
				.append("   <HEAD><TITLE>201 Created</TITLE></HEAD>\n")
				.append("   <BODY>201 Created\n").append(element)
				.append("   </BODY>\n")
				.append("</HTML>\n");
		try {
			outputStream.writeBytes(buf.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public static void sendInternalErrorString(DataOutputStream outputStream) {
		try {
			outputStream.writeBytes(MSG_500_NOT_FOUND);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	public static void sendOkString(DataOutputStream outputStream) {
		try {
			outputStream.writeBytes(MSG_200_OK);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
