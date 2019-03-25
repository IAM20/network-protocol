package com.github.iam20.thread;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.github.iam20.core.Application;
import com.github.iam20.util.MessageUtil;

public class SocketThreadManager {
	public static Thread writeThread(Socket connectionSocket) {
		return new Thread(() -> {
			try {
				DataOutputStream outputStream = new DataOutputStream(connectionSocket.getOutputStream());
				while (connectionSocket.isConnected()) {
					String sentence = Application.scanner.nextLine();
					if (sentence.equals("q")) {
						String connection = connectionSocket.getInetAddress().getHostAddress();
						connection += ":" + connectionSocket.getPort();
						System.out.println("Quit connection from " + connection + " now your standard input is free!");
						outputStream.writeBytes(Application.getMyPort() + " q\n");
						break;
					}
					outputStream.writeBytes(Application.getMyPort() + " " + sentence + "\n");
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

						System.err.println("Unexpected closed connection T_T from " + ipAddr);
						System.err.println("    Close the connection...");
						break;
					}
					String[] splitedMessage = message.split(" ", 2);

					Application.recentPortAddr = Integer.parseInt(splitedMessage[0]);
					Application.recentIpAddr = connectionSocket.getInetAddress().getHostAddress();
					String senderName = Application.recentIpAddr + ":" + Application.recentPortAddr + " sends a message";
					String realMessage = splitedMessage[1];

					if (splitedMessage[1].equals("q")) {
						System.out.println("Close the listening connection from " + ipAddr + ":" + splitedMessage[0]);
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
