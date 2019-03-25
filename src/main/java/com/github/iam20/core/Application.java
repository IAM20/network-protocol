package com.github.iam20.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

import com.github.iam20.thread.ConnectionListener;
import com.github.iam20.thread.CommandThread;

public class Application {
	public static String recentIpAddr;
	public static int recentPortAddr;
	private static int MY_PORT = 18080;
	public final static Scanner scanner = new Scanner(System.in);

	public static void run() throws IOException {
		run(18080);
	}

	public static void run(int port) throws IOException {
		MY_PORT = port;
		ServerSocket server = new ServerSocket(port);
		CommandThread.makeCommandThread(scanner).start();
		ConnectionListener.makeThread(server).start();
	}

	public static int getMyPort() {
		return MY_PORT;
	}
}
