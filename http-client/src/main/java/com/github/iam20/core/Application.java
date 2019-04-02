package com.github.iam20.core;

import java.util.Scanner;

public class Application {
	public static final Scanner scanner = new Scanner(System.in);

	public static void run() {
		String method;
		while (true) {
			System.out.print("Input your method(get, post, etc..) : ");
			method = scanner.next();

			if (method.equalsIgnoreCase("q") ||
				method.equalsIgnoreCase("quit")) {
				return;
			}

			HttpRequestHandler.handle(method);
		}
	}
}
