package com.github.iam20.core;

import com.github.iam20.thread.InputUnit;
import com.github.iam20.thread.ServerSocketUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Application {
	private final static Logger logger = LoggerFactory.getLogger(Application.class);
	public final static Scanner scanner = new Scanner(System.in);

	public static void run() throws IOException {
		run(18080);
	}

	public static void run(int port) throws IOException {
		ServerSocket server = new ServerSocket(port);
		logger.info("PLEASE INPUT RECEIVER'S HOST IP ADDRESS BELOW");
		String receiverHost = scanner.next();

		InputUnit.makeCommandThread(scanner).start();
		Socket socket = new Socket(receiverHost, 18080);
		Thread writer = ServerSocketUnit.writeThread(socket);
		Thread reader = ServerSocketUnit.listenThread(server.accept());
		writer.run();;
		reader.run();
	}
}
