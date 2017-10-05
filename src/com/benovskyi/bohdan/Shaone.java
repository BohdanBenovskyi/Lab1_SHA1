package com.benovskyi.bohdan;

import java.util.Arrays;

public class Shaone {

	public static void main(String[] args) {
		String msg = "Benovskyi";
		
		byte[] inBits = msg.getBytes();
		System.out.println("����� �����: " + Arrays.toString(inBits));
		System.out.println("������� ����������� (�������): " + inBits.length);
		
		String binStr = "";
		for(int i = 0; i<inBits.length; i++) {
			//binStr += Integer.toBinaryString(inBits[i]);
			binStr += String.format("%8s", Integer.toBinaryString(inBits[i])).replace(' ', '0');
		}
		System.out.println("����������� � ����: " + binStr);
		System.out.println("������� ����������� (���): " + binStr.length());
		
		
		if(binStr.length() < 448)
			while(binStr.length() < 448)
				binStr += "0";
		
		System.out.println("����������� � ���� (���� ��������� ����): " + binStr);
		System.out.println("������� ����������� (���, ���� ��������� ����): " + binStr.length());
		
		
	}

}
