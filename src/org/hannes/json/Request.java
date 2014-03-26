package org.hannes.json;

import java.util.HashMap;
import java.util.Map;

import org.hannes.grooveshark.Session;
import org.hannes.util.Country;
import org.hannes.util.MethodType;

public class Request {

	/**
	 * The JSONHeader
	 */
	private final Map<String, Object> header = new HashMap<>();
	
	/**
	 * The parameters
	 */
	private final Map<String, Object> parameters;

	/**
	 * The method name
	 */
	private final String method;

	public Request(String method) {
		this(method, new HashMap<String, Object>());
	}

	public Request(String method, Map<String, Object> parameters) {
		this.method = method;
		this.parameters = parameters;
	}

	/**
	 * Creates a default header
	 * 
	 * @param session
	 * @return
	 */
	public Request createDefaultHeader(Session session) {
		this.header.put("country", new Country());
		this.header.put("uuid", session.getUuid());
		this.header.put("privacy", 0);
		this.header.put("session", session.getId());
		this.header.put("client", MethodType.get(method).getName());
		this.header.put("clientRevision", "20130520");
		return this;
	}

	public Request put(String key, Object value) {
		parameters.put(key, value);
		return this;
	}

	public String getMethod() {
		return method;
	}

	public Map<String, Object> getHeader() {
		return header;
	}

}