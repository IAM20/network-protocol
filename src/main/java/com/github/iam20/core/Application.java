package com.github.iam20.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

import com.github.iam20.thread.ConnectionListener;
import com.github.iam20.thread.CommandThread;

public class Application {
	private static String recentIpAddr = "";
	private static int recentPortAddr = 0;
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

	public static synchronized void setRecentAddr(String ipAddr, int portAddr) {
		recentIpAddr = ipAddr;
		recentPortAddr = portAddr;
	}

	public static synchronized String[] getRecentAddr() {
		return new String[]{
				recentIpAddr, Integer.toString(recentPortAddr)
		};
	}

	public static int getMyPort() {
		return MY_PORT;
	}
}
