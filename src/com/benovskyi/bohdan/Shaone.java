package com.benovskyi.bohdan;

import java.util.Arrays;

public class Shaone {

	public static void main(String[] args) {
		String msg = "Benovskyi";
		
		byte[] inBits = msg.getBytes();
		System.out.println("Масив байтів: " + Arrays.toString(inBits));
		System.out.println("Довжина повідомлення (символів): " + inBits.length);
		
		String binStr = "";
		for(int i = 0; i<inBits.length; i++) {
			//binStr += Integer.toBinaryString(inBits[i]);
			binStr += String.format("%8s", Integer.toBinaryString(inBits[i])).replace(' ', '0');
		}
		System.out.println("Повідомлення в бітах: " + binStr);
		System.out.println("Довжина повідомлення (бітів): " + binStr.length());
		
		
		if(binStr.length() < 448)
			while(binStr.length() < 448)
				binStr += "0";
		
		System.out.println("Повідомлення в бітах (після додавання нулів): " + binStr);
		System.out.println("Довжина повідомлення (бітів, після додавання нулів): " + binStr.length());
		
		
	}

}
