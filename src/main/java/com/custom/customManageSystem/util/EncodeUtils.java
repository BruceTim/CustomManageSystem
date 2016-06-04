package com.custom.customManageSystem.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * base64、SHA-1、SHA-256、SHA-384、SHA-512
 * @author hui
 *
 */
public class EncodeUtils {

	public static final boolean ENCRYPT = true;
	public static final boolean DECRYPT = false;
	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA";
	public static final String SHA224 = "SHA-224";
	public static final String SHA256 = "SHA-256";
	public static final String SHA384 = "SHA-384";
	public static final String SHA512 = "SHA-512";
	public static final String HMACMD5 = "HmacMD5";
	public static final String HMACSHA1 = "HmacSHA1";
	public static final String HMACSHA224 = "HmacSHA224";
	public static final String HMACSHA256 = "HmacSHA256";
	public static final String HMACSHA384 = "HmacSHA384";
	public static final String HMACSHA512 = "HmacSHA512";
	public static final String PBKDF2 = "PBKDF2";
	public static final String DES = "DES";
	public static final String DESEDE = "DESede";
	public static final String AES = "AES";
	public static final String BLOWFISH = "Blowfish";
	public static final String RC2 = "RC2";
	public static final String RC4 = "RC4";
	
	public static void main(String[] args) {
		String data = "aaa";
		String key = "key";
		try {
			System.out.println("Base64:" + "YWFh");
			System.out.println("Base64:" + encryptBase64(data.getBytes("utf-8"))); 
			System.out.println(decryptBase64("YWFh", "utf-8"));
			System.out.println("MD5:" + "47bce5c74f589f4867dbd57e9ca9f808");
			System.out.println("MD5:" + encryptMD5(data.getBytes()));
			System.out.println("SHA1:" + "7e240de74fb1ed08fa08d38063f6a6a91462a815");
			System.out.println("SHA1:" + encryptSHA1(data.getBytes()));
			System.out.println("SHA1:" + encryptSHA1(data.getBytes("gb2312")));
			System.out.println("SHA1:" + encryptSHA1(data.getBytes("utf-8")));
			System.out.println("SHA256:" + "9834876dcfb05cb167a5c24953eba58c4ac89b1adf57f28f2f9d09af107ee8f0");
			System.out.println("SHA256:" + encryptSHA256(data.getBytes()));
			System.out.println("SHA384:" + "8e07e5bdd64aa37536c1f257a6b44963cc327b7d7dcb2cb47a22073d33414462bfa184487cf372ce0a19dfc83f8336d8");
			System.out.println("SHA384:" + encryptSHA384(data.getBytes()));
			System.out.println("SHA512:" + "d6f644b19812e97b5d871658d6d3400ecd4787faeb9b8990c1e7608288664be77257104a58d033bcf1a0e0945ff06468ebe53e2dff36e248424c7273117dac09");
			System.out.println("SHA512:" + encryptSHA512(data.getBytes()));
			System.out.println("HmacMD5:" + "864a4f42fefbfbef59d899e6a51ed48d");
			System.out.println("HmacMD5:" + encryptHmacMD5(data.getBytes("utf-8"), key.getBytes("utf-8")));
			System.out.println("HmacSHA1:" + "350a7e138eb23612dd6655d77c66956f936de7db");
			System.out.println("HmacSHA1:" + encryptHmacSHA1(data.getBytes("utf-8"), key.getBytes("utf-8")));
			System.out.println("HmacSHA256:" + "2b38b05a32e6ac777926791c3df4a806482468780fa9ce1e540071abdb623f6b");
			System.out.println("HmacSHA256:" + encryptHmacSHA256(data.getBytes("utf-8"), key.getBytes("utf-8")));
			System.out.println("HmacSHA384:" + "ae215cfbd6dabd234e87b7dadf5624c7d5d5952744f91dace87923f90bddc226298806888088e5183dcaf0d36143a6c9");
			System.out.println("HmacSHA384:" + encryptHmacSHA384(data.getBytes("utf-8"), key.getBytes("utf-8")));
			System.out.println("HmacSHA512:" + "c2182ea5cad41cbcd3084cc58f7d2a0f8c67a7243ce235e99bdce973db4ba10a27dcfc453ef93da9d4938d4d2ef31c6fbec18a2b0a6da34c4b4cc5f70fa55694");
			System.out.println("HmacSHA512:" + encryptHmacSHA512(data.getBytes("utf-8"), key.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * encode by base64
	 * @param data: a byte array for the data which you want to encrypt
	 * @return
	 */
	public static String encryptBase64(byte[] data) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
	
	/**
	 * decrypt by base64
	 * @param data: the data which you want to decrypt
	 * @param charset 
	 * @return String
	 * @throws UnsupportedEncodingException 
	 * @throws IOException
	 */
	public static String decryptBase64(String sData, String charsetName) throws UnsupportedEncodingException, IOException {
		return new String(decryptBase64(sData), charsetName);
	}
	
	/**
	 * decrypt by base64
	 * @param sData: the data which you want to decrypt
	 * @return a byte array
	 * @throws IOException
	 */
	public static byte[] decryptBase64(String sData) throws IOException{
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(sData);
	}
	
	/**
	 * get encrypted byte[] according to the algorithm about SHA,default is under MD5
	 * @param data: a byte array for the data which you want to encrypt
	 * @param algorithm: final String(MD5/SHA1/SHA256/SHA384/SHA512)
	 * @return a byte array
	 */
	public static byte[] getEncryptBytes(byte[] data, String algorithm){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.reset();
			messageDigest.update(data);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		}

		return messageDigest.digest();
	}
	
	/**
	 * encrypt by MD5
	 * @param data: a byte array for the data which you want to encrypt
	 * @return
	 */
	public static String encryptMD5(byte[] data){
		return bytes2Hex(getEncryptBytes(data, MD5));
	}
	
	/**
	 * encrypt by SHA-1
	 * @param data: a byte array for the data which you want to encrypt
	 * @return
	 */
	public static String encryptSHA1(byte[] data){
		return bytes2Hex(getEncryptBytes(data, SHA1));
	}
	
	/**
	 * encrypt by SHA-256
	 * @param data: a byte array for the data which you want to encrypt
	 * @return
	 */
	public static String encryptSHA256(byte[] data){
		return bytes2Hex(getEncryptBytes(data, SHA256));
	}
	
	/**
	 * encrypt by SHA-384
	 * @param data: a byte array for the data which you want to encrypt
	 * @return
	 */
	public static String encryptSHA384(byte[] data){
		return bytes2Hex(getEncryptBytes(data, SHA384));
	}
	
	/**
	 * encrypt by SHA-512
	 * @param data: a byte array for the data which you want to encrypt
	 * @return
	 */
	public static String encryptSHA512(byte[] data){
		return bytes2Hex(getEncryptBytes(data, SHA512));
	}
	
	/**
	 * refer the byte array to Hex String
	 * @param byteArray
	 * @return String
	 */
	public static String bytes2Hex(byte[] byteArray){
		StringBuffer sBuffer = new StringBuffer();
		for (byte b:byteArray) {
			sBuffer.append(byte2Hex2(b));
		}
		return sBuffer.toString(); 
	}
	
	/**
	 * refer the byte to Hex String
	 * @param byte
	 * @return String
	 */
	public static String byte2Hex(byte b){
		StringBuffer sBuffer = new StringBuffer();
		if (Integer.toHexString(0xFF & b).length() == 1){
			sBuffer.append("0").append(
					Integer.toHexString(0xFF & b));
		}else {
			sBuffer.append(Integer.toHexString(0xFF & b));
		}
		return sBuffer.toString();
	}
	
	/**
	 * another method for refer the byte to Hex String 
	 * @param byte
	 * @return String
	 */
	public static String byte2Hex2(byte b){
		char[] Digit = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		char[] ob = new char[2];
		ob[0] = Digit[(b>>>4) & 0X0f];
		ob[1] = Digit[b & 0X0f];
		return new String(ob);
	}
	
	/**
	 * get encrypted byte[] according to the algorithm for Hmac
	 * @param algorithm: final String(HMACMD5/HMACSHA1/HMACSHA256/HMACSHA384/HMACSHA512)
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static byte[] getEncryptBytesForMac(byte[] data, String algorithm, byte[] keyData) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(keyData, algorithm);
		Mac	mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}
	
	
	/**
	 * encrypt by HamcMd5
	 * @param data: a byte array for the data which you want to encrypt
	 * @param keyData: a byte array for your key
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String encryptHmacMD5(byte[] data, byte[] keyData) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		byte[] resultByte = getEncryptBytesForMac(data, HMACMD5, keyData);
		return bytes2Hex(resultByte);
	}
	
	/**
	 * encrypt by HamcSHA1
	 * @param data: a byte array for the data which you want to encrypt
	 * @param keyData: a byte array for your key
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String encryptHmacSHA1(byte[] data, byte[] keyData) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		byte[] resultByte = getEncryptBytesForMac(data, HMACSHA1, keyData);
		return bytes2Hex(resultByte);
	}
	
	/**
	 * encrypt by HamcSHA256
	 * @param data: a byte array for the data which you want to encrypt
	 * @param keyData: a byte array for your key
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String encryptHmacSHA256(byte[] data, byte[] keyData) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		byte[] resultByte = getEncryptBytesForMac(data, HMACSHA256, keyData);
		return bytes2Hex(resultByte);
	}
	
	/**
	 * encrypt by HamcSHA384
	 * @param data: a byte array for the data which you want to encrypt
	 * @param keyData: a byte array for your key
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String encryptHmacSHA384(byte[] data, byte[] keyData) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		byte[] resultByte = getEncryptBytesForMac(data, HMACSHA384, keyData);
		return bytes2Hex(resultByte);
	}
	
	/**
	 * encrypt by HamcSHA512
	 * @param data: a byte array for the data which you want to encrypt
	 * @param keyData: a byte array for your key
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String encryptHmacSHA512(byte[] data, byte[] keyData) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		byte[] resultByte = getEncryptBytesForMac(data, HMACSHA512, keyData);
		return bytes2Hex(resultByte);
	}
	
	/**
	 * init the DES key
	 * @param algorithm: final String(DES/DESEDE/AES/BLOWFISH/RC2/RC4)
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String initDESKey(String algorithm) throws NoSuchAlgorithmException, IOException{
		return initDESKey(null, algorithm);
	}
	
	/**
	 * init the DES key
	 * @param sKey : your key which encrypted by base64
	 * @param algorithm: final String(DES/DESEDE/AES/BLOWFISH/RC2/RC4)
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String initDESKey(String sKey, String algorithm) throws NoSuchAlgorithmException, IOException{
		SecureRandom sRandom = null;
		if(sKey !=null){
			sRandom = new SecureRandom(decryptBase64(sKey));
		} else{
			sRandom = new SecureRandom();
		}
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
		keyGenerator.init(sRandom);
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBase64(secretKey.getEncoded());
	}
	
	/**
	 * refer your keyData to SecretKey
	 * @param sKey : your key which from initDESKey method
	 * @param algorithm: final String(DES/DESEDE/AES/BLOWFISH/RC2/RC4)
	 * @return
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	private static SecretKey toKey(byte[] keyData, String algorithm) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException{
		SecretKey secretKey = null;
		if("DES".equals(algorithm) || "DESede".equals(algorithm)){
			DESKeySpec dks = new DESKeySpec(keyData);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
			secretKey = keyFactory.generateSecret(dks);
		} else {
			// AES、Blowfish...
			secretKey = new SecretKeySpec(keyData, algorithm);
		}
		return secretKey;
	}
	
	/**
	 * get encrypted byte[] according to the algorithm for DES/DESEDE/AES/BLOWFISH/RC2/RC4
	 * @param data : your data which you want to encrypt 
	 * @param algorithm: final String(DES/DESEDE/AES/BLOWFISH/RC2/RC4)
	 * @param sKey : your key which from initDESKey method
	 * @param isEncrypt: if isEncrypt is true, will do encrypt;otherwise,will do decrypt
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static byte[] getBytesForDES(byte[] data, String algorithm, String key, boolean isEncrypt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		Cipher cipher = Cipher.getInstance(algorithm);
		SecretKey secretKey = toKey(decryptBase64(key), algorithm);
		if(isEncrypt){
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} else {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
		}
		return cipher.doFinal(data);
	}
	
	/**
	 * encrypt by DES
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String encryptDES(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, DES, key, true));
	}
	
	/**
	 * decrypt by DES
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String decryptDES(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, DES, key, false));
	}
	
	/**
	 * encrypt by DESede
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String encryptDESede(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, DESEDE, key, true));
	}
	
	/**
	 * decrypt by DESede
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String decryptDESede(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, DESEDE, key, false));
	}
	
	/**
	 * encrypt by AES
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String encryptAES(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, AES, key, true));
	}
	
	/**
	 * decrypt by AES
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String decryptAES(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, AES, key, false));
	}
	
	/**
	 * encrypt by Blowfish
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String encryptBlowfish(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, BLOWFISH, key, true));
	}
	
	/**
	 * decrypt by Blowfish
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String decryptBlowfish(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, BLOWFISH, key, false));
	}
	
	/**
	 * encrypt by RC2
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String encryptRC2(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, RC2, key, true));
	}
	
	/**
	 * decrypt by RC2
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String decryptRC2(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, RC2, key, false));
	}
	
	/**
	 * encrypt by RC4
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String encryptRC4(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, RC4, key, true));
	}
	
	/**
	 * decrypt by RC4
	 * @param data : your data which you want to encrypt 
	 * @param sKey : your key which from initDESKey method
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static String decryptRC4(byte[] data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
		return bytes2Hex(getBytesForDES(data, RC4, key, false));
	}
}
