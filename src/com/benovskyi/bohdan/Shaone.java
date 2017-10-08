package com.benovskyi.bohdan;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Shaone {

	public static void main(String[] args) {
		String msg;
		Scanner in = new Scanner(System.in);
        System.out.println("Введіть текст для обробки: ");
        msg = in.nextLine();
		
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
			
			int x1 = (int)Long.parseLong(msgBlock[i-3], 2);
			int x2 = (int)Long.parseLong(msgBlock[i-8], 2);
			int x3 = (int)Long.parseLong(msgBlock[i-14], 2);
			int x4 = (int)Long.parseLong(msgBlock[i-16], 2);
			
			int x =  rotl((x1 ^ x2 ^ x3 ^ x4), 1);
			
			msgBlock[i] = String.format("%32s", Integer.toBinaryString(x)).replace(' ', '0');
		}
		
		
		for(int i = 0; i < 80; i++) {
			System.out.println("Слово[" + i + "] в двійковій системі числення:" + msgBlock[i] + ", в десятковій системі числення: " + Long.parseLong(msgBlock[i], 2));
		}
		
		//ініціалізація 5-ти регістрів
		int h0 = 0x67452301;
		int h1 = 0xEFCDAB89;
		int h2 = 0x98BADCFE;
		int h3 = 0x10325476;
		int h4 = 0xC3D2E1F0;
		
		int a = h0;
		int b = h1;
		int c = h2;
		int d = h3;
		int e = h4;
		
		int k = 0;
		int f = 0;
		
		for(int i = 0; i < 80; i++) {
			if(i >= 0 && i <= 19) {
				f = (b & c) | ((~b) & d);
			    k = 0x5A827999;
			}
			else if(i >= 20 && i <= 39) {
				f = b ^ c ^ d;
			    k = 0x6ED9EBA1;
			}
			else if(i >= 40 && i <= 59) {
				f = (b & c) | (b & d) | (c & d);
			    k = 0x8F1BBCDC;
			}
			else if(i >= 60 && i <= 79) {
				f = b ^ c ^ d;
			    k = 0xCA62C1D6;
			}
			
			int y = (int)Long.parseLong(msgBlock[i], 2);
			int temp = (a << 5) + f + e + k + y;
			e = d;
			d = c;
			c = b << 30;
			b = a;
			a = temp;
		}
		
		h0 += a;
		h1 += b;
		h2 += c;
		h3 += d;
		h4 += e;
		
		String hash = new StringBuilder().append(Integer.toHexString(h0)).append(Integer.toHexString(h1))
				.append(Integer.toHexString(h2)).append(Integer.toHexString(h3)).append(Integer.toHexString(h4)).toString();
		
		System.out.println("Результат: " + hash);
	}
	
	public static int rotl(int x, int y) {
		int q = (x << y) | (x >>> (32 - y));
        return q;
	}

}
