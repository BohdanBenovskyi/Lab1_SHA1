package com.benovskyi.bohdan;

import java.util.Arrays;
import java.util.Scanner;

public class ShaOneByBytes {

	public static void main(String[] args) {
		String msg;
		
		Scanner in = new Scanner(System.in);
        System.out.println("Введіть текст для обробки: ");
        msg = in.nextLine();
		
		byte[] data = msg.getBytes();
		System.out.println("Вхідне повідомлення: " + Arrays.toString(data));
		
		int len_sym = data.length;
		System.out.println("Довжина вхідного повідомлення (символів): " + len_sym);
		
		int len_bits = data.length * 8;
		System.out.println("Довжина вхідного повідомлення (бітів): " + len_bits);
		
		int zero = (448 - 1 - len_bits)/8;
		
		int l = len_sym;
		
		byte[] workData = new byte[64];
		System.arraycopy(data, 0, workData, 0, len_sym);
		
		workData[l++] = (byte)0x80;
		
		for(int i = 0; i < zero; i++)
			workData[l++] = (byte)0x00;
		
		
		for(int i = 0; i < 7; i++)
			workData[l++] = (byte)0x00;
		
		workData[l++] = (byte)len_bits;
		
        System.out.println("Дайджест: " + Arrays.toString(workData));
        System.out.println("Довжина дайджеста: " + workData.length);
        
        int[] tmpData = new int[80];
        int MCount = workData.length / 64;
 
        //розбиваємо на 16 32х розрядних слова
        for (int pos = 0; pos < MCount; pos++) {
            for (int j = 0; j < 16; j++) {
                tmpData[j] = byteArrayToInt(workData, (pos * 64) + (j * 4));
            }
        }
        
        for(int i = 16; i < 80; i++) {
        	int x1 = tmpData[i-3];
        	int x2 = tmpData[i-8];
        	int x3 = tmpData[i-14];
        	int x4 = tmpData[i-16];
        	
        	int x = rotl((x1 ^ x2 ^ x3 ^ x4), 1);
        	tmpData[i] = x;
        }
        
        int H0 = 0x67452301;
        int H1 = 0xEFCDAB89;
        int H2 = 0x98BADCFE;
        int H3 = 0x10325476;
        int H4 = 0xC3D2E1F0;
        
        int a = H0;
        int b = H1;
        int c = H2;
        int d = H3;
        int e = H4;
        
        int f = 0;
        int k = 0;
        
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
			
			int temp = (a << 5) + f + e + k + tmpData[i];
			e = d;
			d = c;
			c = b << 30;
			b = a;
			a = temp;
		}
        
        H0 += a;
		H1 += b;
		H2 += c;
		H3 += d;
		H4 += e;
        
        String hash = new StringBuilder().append(Long.toHexString(H0)).append(Long.toHexString(H1))
				.append(Long.toHexString(H2)).append(Long.toHexString(H3)).append(Long.toHexString(H4)).toString();
		
		System.out.println("Результат: " + hash);
        
	}
	
	private static int byteArrayToInt(byte[] bytedata, int i) {
        return ((bytedata[i] & 0xff) << 24) | ((bytedata[i + 1] & 0xff) << 16) |
        ((bytedata[i + 2] & 0xff) << 8) | (bytedata[i + 3] & 0xff);
    }
	
	private static int rotl(int x, int y) {
        return (x << y) | x >>> (32 - y);
    }

}
