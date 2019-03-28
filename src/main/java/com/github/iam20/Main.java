package com.github.iam20;

import com.github.iam20.core.Application;

public class Main {
	public static void main(String[] args) throws Exception {
		int portNumber = (args.length == 1) ? Integer.parseInt(args[0]) : 18080;
		Application.run(portNumber);
	}
}
