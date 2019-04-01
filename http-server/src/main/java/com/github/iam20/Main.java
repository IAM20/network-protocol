package com.github.iam20;

import com.github.iam20.core.Application;

public class Main {
	public static void main(String[] args) throws Exception {
		int port = (args.length == 1) ? Integer.parseInt(args[0]) : 8080;
		Application.run(port);
	}
}
