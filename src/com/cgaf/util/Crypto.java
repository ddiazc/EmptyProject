package com.cgaf.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class Crypto {

	static String IV = "AAAAAAAAAAAAAAAA";
	static String encryptionKey = "0123456789abcdef";

	private static final Logger log = Logger.getLogger(Crypto.class);

	public static String encrypt(String passwordToEncrypt) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.ENCRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(passwordToEncrypt.getBytes("UTF-8")));
	}

	@SuppressWarnings("static-access")
	public static String decrypt(String passwordToDecrypt) throws Exception {
		log.debug("Decrypting password...");
		byte[] cipherText = new Base64().decodeBase64(passwordToDecrypt);
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"),
				"AES");
		cipher.init(Cipher.DECRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(cipher.doFinal(cipherText), "UTF-8");
	}

}
