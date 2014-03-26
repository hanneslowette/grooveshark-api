package org.hannes.grooveshark;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import org.hannes.grooveshark.node.Node;
import org.hannes.json.Request;
import org.hannes.json.Response;
import org.hannes.util.MethodType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * A grooveshark session
 * 
 * @author Hannes
 */
public class Session {
	
	/**
	 * The gson object
	 */
	private static final Gson GSON = new Gson();

	/**
	 * The PHP session id
	 */
	private final String id;
	
	/**
	 * The MD5 encrypted session key
	 */
	private final String key;
	
	/**
	 * The UUID
	 */
	private final String uuid;

	public Session(String id, String key, String uuid) {
		this.id = id;
		this.key = key;
		this.uuid = uuid;
	}

	public <T extends Node> Response<T> request(T node) throws MalformedURLException, IOException {
		/*
		 * Request the communication token
		 */
		Request token_request = new Request("getCommunicationToken").createDefaultHeader(this).put("secretKey", key);
		Response<String> token_response = GSON.fromJson(write(token_request, new URL("https://grooveshark.com/more.php")), new TypeToken<Response<String>>(){}.getType());
		
		/*
		 * Get the node request and add the encrypted token
		 */
		Request resource_request = node.createRequest(this);
		resource_request.getHeader().put("token", Grooveshark.encryptCommunicationToken(token_response.getResult(), resource_request.getMethod()));
		
		/*
		 * Get the object
		 */
		return GSON.fromJson(write(resource_request, new URL("http://grooveshark.com/more.php")), node.getType());
	}

	/**
	 * Writes the request to the given url and returns the response as plain text
	 * 
	 * @param request
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private String write(Request request, URL url) throws IOException {
		String json_data = GSON.toJson(request);
		
		/*
		 * Create the url connection
		 */
		URLConnection connection = (URLConnection) url.openConnection();
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.56 Safari/536.5");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Referer", MethodType.get(request.getMethod()) == MethodType.TYPE_A ? "http://grooveshark.com/JSQueue.swf?20121003.4" : "http://www.grooveshark.com");
		connection.setRequestProperty("Accept-Encoding","gzip");
		connection.setDoOutput(true);
		
		/*
		 * Write the json object
		 */
		ByteBuffer out_buffer = ByteBuffer.wrap(json_data.getBytes());
		WritableByteChannel out = Channels.newChannel(connection.getOutputStream());
		out.write((ByteBuffer) out_buffer);
		out.close();
		
		/*
		 * Read the response from the server
		 */
		StringBuilder builder = new StringBuilder();
		Scanner scanner = new Scanner(new GZIPInputStream(connection.getInputStream()), "UTF-8");
		while (scanner.hasNext()) {
			builder.append(scanner.next());
		}
		scanner.close();
		
		/*
		 * Convert to string
		 */
		return builder.toString();
	}

	public String getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public String getUuid() {
		return uuid;
	}

}