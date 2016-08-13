package aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

	private final String initializationVector;

	public AES(String initializationVector) {

		this.initializationVector = initializationVector;
	}

	public byte[] encryptAES(String plainText, String encryptionKey) {

		try {

			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");

			SecretKeySpec key =
					new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");

			cipher.init(Cipher.ENCRYPT_MODE, key, 
					new IvParameterSpec(initializationVector.getBytes("UTF-8")));

			return cipher.doFinal(plainText.getBytes("UTF-8"));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String decryptAES(byte[] cipherText, String encryptionKey) {
		
		try {
			
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
			
			SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
			
			cipher.init(Cipher.DECRYPT_MODE, key,
					new IvParameterSpec(initializationVector.getBytes("UTF-8")));
			
			return new String(cipher.doFinal(cipherText), "UTF-8");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
