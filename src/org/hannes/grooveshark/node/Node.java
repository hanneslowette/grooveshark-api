package org.hannes.grooveshark.node;

import java.lang.reflect.Type;

import org.hannes.grooveshark.Session;
import org.hannes.json.Request;

public interface Node {

	/**
	 * Creates the request to be sent to the server
	 * 
	 * @param session
	 * @return
	 */
	public abstract Request createRequest(Session session);

	/**
	 * The type of Reponse
	 * 
	 * @return
	 */
	public abstract Type getType();

}