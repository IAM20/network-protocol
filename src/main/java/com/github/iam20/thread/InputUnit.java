package com.github.iam20.thread;

import java.util.Scanner;

public class InputUnit {
	public static Thread makeCommandThread(Scanner scanner) {
		return new Thread(() -> {
			String command = scanner.next();
			if (command.equals("q")) {
				System.exit(0);
			}
		});
	}
}
