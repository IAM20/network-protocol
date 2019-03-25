package com.github.iam20.thread;

import com.github.iam20.core.Application;

import java.net.Socket;
import java.util.Scanner;

public class CommandThread {
	public static Thread makeCommandThread(Scanner scanner) {
		return new Thread(() -> {
			while (true) {
				String command = scanner.nextLine();
				if (command.equals("Q") | command.equals("QUIT")) {
					System.exit(0);
				} else if (command.startsWith("connect") | command.startsWith("c")) {
					String[] commands = command.split(" ");
					Socket socket;
					if (commands.length == 1) {
						System.out.println("Usage : connect IP-address(?:port)");
						continue;
					} else {
						commands = commands[1].split(":");
						try {
							if (commands.length == 1) {
								socket = new Socket(commands[0], 18080);
							} else {
								socket = new Socket(commands[0], Integer.parseInt(commands[1]));
							}
							SocketThreadManager.writeThread(socket).join();
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				} else if (command.equals("reply") || command.equals("r")) {
					try {
						Socket socket = new Socket(Application.recentIpAddr, Application.recentPortAddr);
						SocketThreadManager.writeThread(socket).join();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else if (command.equals("help") || command.equals("h")) {
					String helpMessage =
							"=======================================\n" +
							"h | help : manuals\n" +
							"r | reply: reply to the most recent sender\n" +
							"(c | connect) ip-address(?:port) : connect to ip-address, port default port is 18080\n" +
							"q | quit : close the connection\n" +
							"Q | QUIT : close the application";

					System.out.println(helpMessage);
				}
			}
		});
	}
}
