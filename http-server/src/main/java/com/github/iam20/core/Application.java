package com.github.iam20.core;

import com.github.iam20.http.ListenThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {
	public static void run(int port) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);

		while (true) {
			Socket connection = serverSocket.accept();
			new ListenThread(connection).run();
		}
	}
}
