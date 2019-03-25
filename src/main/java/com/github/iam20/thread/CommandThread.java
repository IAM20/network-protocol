package com.github.iam20.thread;

import java.net.Socket;
import java.util.Scanner;

import com.github.iam20.core.Application;

public class CommandThread {
	public static Thread makeCommandThread(Scanner scanner) {
		return new Thread(() -> {
			while (true) {
				String command = scanner.nextLine();
				if (command.equals("Q") | command.equals("QUIT")) {
					quitApplication();
				} else if (command.startsWith("connect") | command.startsWith("c")) {
					connect(command);
				} else if (command.equals("reply") || command.equals("r")) {
					reply();
				} else if (command.equals("help") || command.equals("h")) {
					printHelpMessage();
				} else if (command.equals("q") | command.equals("quit")) {
					System.err.println("There's no connection, if you want to close the application please use Q | QUIT");
				} else {
					System.err.println("Unknown command");
					printHelpMessage();
				}
			}
		});
	}

	private static void reply() {
		try {
			Socket socket = new Socket(Application.recentIpAddr, Application.recentPortAddr);
			System.out.println("Successfully connect to " + Application.recentIpAddr + ":" + Application.recentPortAddr);
			System.out.println("Now your standard input will send to " + Application.recentIpAddr + ":" + Application.recentPortAddr);

			Thread thread = SocketThreadManager.writeThread(socket);
			thread.start();
			thread.join();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void connect(String command) {
		String[] commands = command.split(" ");
		Socket socket;
		if (commands.length == 1) {
			System.err.println("Usage : connect IP-address(?:port)");
		} else {
			commands = commands[1].split(":");
			try {
				int portNumber = (commands.length == 1) ? 18080 : Integer.parseInt(commands[1]);
				socket = new Socket(commands[0], portNumber);
				System.out.println("Successfully connect to " + commands[0] + ":" + portNumber);
				System.out.println("Now your standard input will send to " + commands[0] + ":" + portNumber);

				Thread thread = SocketThreadManager.writeThread(socket);
				thread.start();
				thread.join();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private static void quitApplication() {
		System.out.println("BYE!");
		System.exit(0);
	}

	private static void printHelpMessage() {
		String helpMessage =
				"====================================================================================\n" +
				"h | help                         : manuals\n" +
				"r | reply                        : reply to the most recent sender\n" +
				"(c | connect) ip-address(?:port) : connect to ip-address, port default port is 18080\n" +
				"q | quit                         : close the connection\n" +
				"Q | QUIT                         : close the application\n" +
				"====================================================================================";

		System.out.println(helpMessage);
	}
}
