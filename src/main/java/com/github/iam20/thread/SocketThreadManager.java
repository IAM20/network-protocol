package com.github.iam20.thread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.github.iam20.core.Application;
import com.github.iam20.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketThreadManager {
	private static final Logger logger = LoggerFactory.getLogger(SocketThreadManager.class);

	public static Thread writeThread(Socket connectionSocket) {
		return new Thread(() -> {
			try {
				DataOutputStream outputStream = new DataOutputStream(connectionSocket.getOutputStream());
				String connection = connectionSocket.getInetAddress().getHostAddress();
				connection += ":" + connectionSocket.getPort();
				while (connectionSocket.isConnected()) {
					String sentence = Application.scanner.nextLine();
					if (sentence.equals("q")) {
						logger.info("Quit connection from " + connection + " now your standard input is free!");
						outputStream.writeBytes(Application.getMyPort() + " q\n");
						break;
					}
					outputStream.writeBytes(Application.getMyPort() + " " + sentence + "\n");
					System.out.println(MessageUtil.makeWriteMessage(connection, sentence));
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			try {
				connectionSocket.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		});
	}

	public static Thread listenThread(Socket connectionSocket) {
		return new Thread(() -> {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				while (connectionSocket.isConnected()) {
					String message = reader.readLine();
					String ipAddr = connectionSocket.getInetAddress().getHostAddress();

					if (message == null) {
						int port = connectionSocket.getPort();
						ipAddr += ":" + port;

						logger.error("Unexpected closed connection T_T from " + ipAddr);
						logger.error("    Close the connection...");
						break;
					}
					String[] splitedMessage = message.split(" ", 2);

					int receivedPortAddr = Integer.parseInt(splitedMessage[0]);

					Application.setRecentAddr(ipAddr, receivedPortAddr);

					String senderName = ipAddr + ":" + receivedPortAddr + " sends a message";
					String realMessage = splitedMessage[1];

					if (splitedMessage[1].equals("q")) {
						logger.info("Close the listening connection from " + ipAddr + ":" + splitedMessage[0]);
						break;
					}

					System.out.println(MessageUtil.makeMessage(senderName, realMessage));
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			try {
				connectionSocket.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		});
	}
}
