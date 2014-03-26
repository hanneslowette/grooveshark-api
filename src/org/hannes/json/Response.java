package org.hannes.json;

import java.util.Map;

public class Response<T> {

	/**
	 * Usually consists of the session id and the service version
	 */
	private Map<String, Object> header;

	/**
	 * the result
	 */
	private T result;

	public Map<String, Object> getHeader() {
		return header;
	}

	public T getResult() {
		return result;
	}

}