package com.patel.pradeep.utility;

import java.security.SecureRandom;

public class Random {
	public static int randomNoWithThreadName(int no){
		int randomNo = new SecureRandom().nextInt(no);
		return randomNo;
	}
}
