package com.github.iam20.thread;

import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
