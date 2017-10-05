package com.benovskyi.bohdan;

import java.util.Arrays;

public class Shaone {

	public static void main(String[] args) {
		String msg = "Benovskyi";
		
		String lenOfMsg = String.format("%64s", Integer.toBinaryString(msg.length()*8)).replace(' ', '0');
		System.out.println("������� ������������� ������ �����������: " + lenOfMsg);
		
		byte[] inBits = msg.getBytes();
		System.out.println("����� �����: " + Arrays.toString(inBits));
		System.out.println("������� ����������� (�������): " + inBits.length);
		
		String binStr = "";
		for(int i = 0; i<inBits.length; i++)
			binStr += String.format("%8s", Integer.toBinaryString(inBits[i])).replace(' ', '0');
		
		System.out.println("����������� � ����: " + binStr);
		System.out.println("������� ����������� (���): " + binStr.length());
		
		binStr += "1";
		System.out.println("��������� �� ��� �������!");
		
		if(binStr.length() < 448)
			while(binStr.length() < 448)
				binStr += "0";
		
		System.out.println("����������� � ���� (���� ��������� ����): " + binStr);
		System.out.println("������� ����������� (���, ���� ��������� ����): " + binStr.length());
		
		binStr += lenOfMsg;
		System.out.println("����������� � ���� (���� ��������� ������� �������� �����������): " + binStr);
		System.out.println("������� ����������� (���, ���� ��������� ����): " + binStr.length());
		
		String tmpBin = binStr;
		String[] msgBlock = new String [80];
		
		//������ �� 16 32-� ����� �����
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
		
		for(int i = 0; i < 16; i++) {
			System.out.println("�����[" + i + "] � ������� ������ ��������:" + msgBlock[i] + ", � ��������� ������ ��������: " + Integer.parseInt(msgBlock[i], 2));
		}
		
		for(int i = 16; i < 80; i++) {
			int x1 = Integer.parseInt(msgBlock[i-3], 2);
			int x2 = Integer.parseInt(msgBlock[i-8], 2);
			int x3 = Integer.parseInt(msgBlock[i-14], 2);
			int x4 = Integer.parseInt(msgBlock[i-16], 2);
			
			x = (x1 ^ x2 ^ x3 ^ x4) << 1;
		}
	}
	
	public static int rotl(int x, int y) {
		int z = (x << y) | (x >> (32-y));
		return z;
	}

}
