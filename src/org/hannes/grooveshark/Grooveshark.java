package org.hannes.grooveshark;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.hannes.util.MethodType;

public class Grooveshark {
	
	/**
	 * the home url
	 */
	private static final String HOME_URL = "http://www.grooveshark.com/";
	
	/**
	 * The secure random for this class
	 */
	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * Creates a new session
	 * 
	 * @return
	 * @throws IOException
	 */
	public static Session createSession() throws IOException {
		String session_id = Grooveshark.getSessionId();
		String session_key = Grooveshark.getSessionKey(session_id);
		
		return new Session(session_id, session_key, UUID.randomUUID().toString());
	}
	
	/**
	 * Gets the PHP session id from the cookies
	 * 
	 * @return
	 * @throws IOException
	 */
	private static String getSessionId() throws IOException {
		URL url = new URL(HOME_URL);
		URLConnection connection = url.openConnection();
		List<NameValuePair> list = URLEncodedUtils.parse(connection.getHeaderField("Set-Cookie"), Charset.forName("UTF-8"));
		for (NameValuePair pair : list) {
			if (pair.getName().equals("PHPSESSID")) {
				return pair.getValue();
			}
		}
		throw new IllegalStateException("no PHPSESSID found");
	}
	
	/**
	 * Encrypts the session id with md5
	 * 
	 * @param session_id
	 * @return
	 */
	private static String getSessionKey(String session_id) {
		return DigestUtils.md5Hex(session_id);
	}

	/**
	 * Encrypts the communication token
	 * @param key
	 * @param method
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptCommunicationToken(String key, String method) {
		/*
		 * 0123456789abcdef
		 */
		String salt = new BigInteger(130, RANDOM).toString(16).substring(0, 6);
		
		/*
		 * Found this on the internet
		 */
		String password = MethodType.get(method) == MethodType.TYPE_A ? "chickenFingers" : "nuggetsOfBaller";
		
		/*
		 * Encrypt with sha1 and add salt
		 */
		return (salt + DigestUtils.sha1Hex((method + ":" + key + ":" + password + ":" + salt).getBytes()));
	}

}