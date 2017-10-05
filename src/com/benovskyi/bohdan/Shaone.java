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
		
		
	}

}
