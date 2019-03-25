package com.github.iam20;

import com.github.iam20.core.Application;

public class Main {
	public static void main(String[] args) throws Exception {
		switch (args.length) {
			case 0:
				Application.run();
				break;
			case 1:
				Application.run(Integer.parseInt(args[0]));
				break;
			default:
				System.out.println("Usage : application (?port)");
		}
	}
}
