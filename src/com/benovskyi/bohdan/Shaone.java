package com.benovskyi.bohdan;

import java.util.Arrays;

public class Shaone {

	public static void main(String[] args) {
		String msg = "Benovskyi";
		
		String lenOfMsg = String.format("%64s", Integer.toBinaryString(msg.length()*8)).replace(' ', '0');
		System.out.println("Бінарне представлення розміру повідомлення: " + lenOfMsg);
		
		byte[] inBits = msg.getBytes();
		System.out.println("Масив байтів: " + Arrays.toString(inBits));
		System.out.println("Довжина повідомлення (символів): " + inBits.length);
		
		String binStr = "";
		for(int i = 0; i<inBits.length; i++)
			binStr += String.format("%8s", Integer.toBinaryString(inBits[i])).replace(' ', '0');
		
		System.out.println("Повідомлення в бітах: " + binStr);
		System.out.println("Довжина повідомлення (бітів): " + binStr.length());
		
		binStr += "1";
		System.out.println("Одиничний біт був доданий!");
		
		if(binStr.length() < 448)
			while(binStr.length() < 448)
				binStr += "0";
		
		System.out.println("Повідомлення в бітах (після додавання нулів): " + binStr);
		System.out.println("Довжина повідомлення (бітів, після додавання нулів): " + binStr.length());
		
		binStr += lenOfMsg;
		System.out.println("Повідомлення в бітах (після додавання довжини вхідного повідомлення): " + binStr);
		System.out.println("Довжина повідомлення (бітів, після додавання нулів): " + binStr.length());
		
		String tmpBin = binStr;
		String[] msgBlock = new String [80];
		
		//розбив на 16 32-х бітних слова
		int j = 0;
		while(!tmpBin.isEmpty()) {
			String subTmp = "";
			for(int i = 0; i<32; i++) {
				subTmp += tmpBin.charAt(i);
			}
			
			for(int i = 0; i<32; i++)
				tmpBin = new StringBuilder(tmpBin).deleteCharAt(0).toString();
			
			msgBlock[j] = subTmp;
			j++;
		}
		
		
	}

}
