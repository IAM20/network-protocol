package com.github.iam20.thread;

import com.github.iam20.core.Application;

import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener {
	public static Thread makeThread(ServerSocket socket) {
		return new Thread(() -> {
			while (true) {
				try {
					Socket sock = socket.accept();
					Application.recentIpAddr = sock.getLocalAddress().getHostAddress();
					Application.recentPortAddr = sock.getLocalPort();
					Thread thread = SocketThreadManager.listenThread(sock);
					thread.start();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}
}
