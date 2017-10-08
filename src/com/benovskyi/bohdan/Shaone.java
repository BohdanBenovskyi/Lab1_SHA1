package com.benovskyi.bohdan;

import java.math.BigInteger;
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
		
		//binStr += "1";
		binStr += String.format("%8s", Integer.toBinaryString(0x80)).replace(' ', '0');
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
		
		for(int i = 16; i < 80; i++) {
			msgBlock[i] = "0";
			for(int l = 0; l < 31; l++)
				msgBlock[i] += "0";
		}
		
		for(int i = 16; i < 80; i++) {
			/*
			int x1 = Integer.parseInt(msgBlock[i-3], 2);
			int x2 = Integer.parseInt(msgBlock[i-8], 2);
			int x3 = Integer.parseInt(msgBlock[i-14], 2);
			int x4 = Integer.parseInt(msgBlock[i-16], 2);
			
			int x =  rotl((x1 ^ x2 ^ x3 ^ x4), 1);
			*/
			
			long x1 = Long.parseLong(msgBlock[i-3], 2);
			long x2 = Long.parseLong(msgBlock[i-8], 2);
			long x3 = Long.parseLong(msgBlock[i-14], 2);
			long x4 = Long.parseLong(msgBlock[i-16], 2);
			
			long x =  rotl((x1 ^ x2 ^ x3 ^ x4), 1);
			
			msgBlock[i] = String.format("%32s", Long.toBinaryString(x)).replace(' ', '0');
		}
		
		
		for(int i = 0; i < 80; i++) {
			System.out.println("Слово[" + i + "] в двійковій системі числення:" + msgBlock[i] + ", в десятковій системі числення: " + Long.parseLong(msgBlock[i], 2));
		}
	}
	
	public static long rotl(long x, int y) {
		long q = (x << y) | (x >>> (32 - y));
        return q;
	}

}
