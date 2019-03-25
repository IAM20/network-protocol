package com.github.iam20.core;

import com.github.iam20.thread.ConnectionListener;
import com.github.iam20.thread.CommandThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Application {
	private final static Logger logger = LoggerFactory.getLogger(Application.class);
	public static String recentIpAddr;
	public static int recentPortAddr;
	public final static Scanner scanner = new Scanner(System.in);

	public static void run() throws IOException {
		run(18080);
	}

	public static void run(int port) throws IOException {
		ServerSocket server = new ServerSocket(port);

		CommandThread.makeCommandThread(scanner).run();
		ConnectionListener.makeThread(server).run();
	}
}
