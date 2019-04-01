package com.github.iam20.util;

public class UrlValidateChecker {
	private static boolean isValidate(String urlString) {
		return !(urlString == null || urlString.length() == 0);
	}

	public static String makeValidUrl(String urlString) {
		if (!isValidate(urlString)) {
			return "http://localhost";
		}

		return (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString : ("http://" + urlString).intern();
	}

	public static String makeValidElement(String element) {
		return (element == null || element.length() == 0) ? "" : element;
	}
}
