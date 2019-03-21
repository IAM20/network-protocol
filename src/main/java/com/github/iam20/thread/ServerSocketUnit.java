package com.github.iam20.thread;

import com.github.iam20.core.Application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerSocketUnit {
	public static Thread writeThread(Socket connectionSocket) {
		return new Thread(() -> {
			try {
				String sentence;
				DataOutputStream outputStream = new DataOutputStream(connectionSocket.getOutputStream());
				while (connectionSocket.isConnected()) {
					sentence = Application.scanner.nextLine();
					outputStream.writeBytes(sentence + "\n");
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
					System.out.println(reader.readLine());
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
