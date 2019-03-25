package com.github.iam20.util;

public class MessageUtil {
	public static String makeMessage(String senderName, String realMessage) {
		int senderNameLength = senderName.length();
		int realMessageLength = realMessage.length();
		int length = (senderNameLength > realMessageLength) ? senderNameLength : realMessageLength;

		StringBuilder buf = new StringBuilder();
		buf.append("\n|");
		for (int i = 0; i < length; i++) {
			buf.append("=");
		}
		buf.append("|\n");
		buf.append("|");
		buf.append(senderName);
		for (int i = 0; i < length - senderNameLength; i++) {
			buf.append(" ");
		}
		buf.append("|\n");
		buf.append("|").append(realMessage);
		for (int i = 0; i < length - realMessageLength; i++) {
			buf.append(" ");
		}
		buf.append("|\n");
		buf.append("|");
		for (int i = 0; i < length; i++) {
			buf.append("=");
		}
		buf.append("|\n");
		return buf.toString();
	}
}
