package main;

import aes.AES;

public class Main {

	public static final String text = "0123456789012345";
	
	public static byte[] encText;
	public static String decText;
	
	public static final String encryptionKey = "16-bitkey1234567";
	
	public static void main(String[] args) {

		AES aes = new AES("qwertyuiop123456");
		
		System.out.println(text);
		
		encText = aes.encryptAES(text, encryptionKey);
		
		for(byte b : encText) {
			System.out.print(b);
		}
		System.out.println();
		
		decText = aes.decryptAES(encText, encryptionKey);
		
		System.out.println(decText);
	}

}
