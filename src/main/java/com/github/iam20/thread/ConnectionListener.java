package com.github.iam20.thread;

import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener {
	public static Thread makeThread(ServerSocket socket) {
		return new Thread(() -> {
			while (true) {
				try {
					Socket sock = socket.accept();
					System.out.println("Someone connects you..!");
					SocketThreadManager.listenThread(sock).start();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}
}
