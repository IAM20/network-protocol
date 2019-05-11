package com.github.iam20.rasp.util;

import java.util.TimerTask;

public class TimerTaskWrapper {
	public static TimerTask wrap(Runnable r) {
		return new TimerTask() {
			@Override
			public void run() {
				r.run();
			}
		};
	}
}
