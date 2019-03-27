package com.github.iam20.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionListener.class);
	public static Thread makeThread(ServerSocket socket) {
		return new Thread(() -> {
			while (true) {
				try {
					Socket sock = socket.accept();
					logger.info("Someone connects you..!");
					SocketThreadManager.listenThread(sock).start();
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		});
	}
}
