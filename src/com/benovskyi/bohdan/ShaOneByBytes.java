package com.benovskyi.bohdan;

import java.util.Arrays;

public class ShaOneByBytes {

	public static void main(String[] args) {
		String msg = "Benovskiy";
		
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
			workData[l++] = (byte)0x80;
		
		
		
	}

}
