package com.projectge.utils;

public class HackyTools {
	public static void hackySleep(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
