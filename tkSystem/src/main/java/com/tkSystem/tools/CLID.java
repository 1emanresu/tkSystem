package com.tkSystem.tools;

public class  CLID {
	
	/**
	 * 自动生成ID
	 */
	public static String getID() {
		long date = System.currentTimeMillis();
		String hlw = String.valueOf((int) (Math.random() * 90 + 10));
		String sjid = String.valueOf(date) + hlw;
		return sjid;
	}
public static void main(String[] args) {
	System.out.println(getID());
}
}
