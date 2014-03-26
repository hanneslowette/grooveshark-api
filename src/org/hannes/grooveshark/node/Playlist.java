package org.hannes.grooveshark.node;

import java.lang.reflect.Type;

import org.hannes.grooveshark.Session;
import org.hannes.json.Request;
import org.hannes.json.Response;
import org.hannes.util.Country;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class Playlist implements Node {

	/**
	 * The method name
	 */
	private static final String METHOD_NAME = "playlistGetSongs";
	
	/**
	 * The playlist id
	 */
	private final String id;

	@SerializedName("Songs")
	/**
	 * The songs on this playlist
	 */
	private Song[] songs;

	public Playlist(String id) {
		this.id = id;
	}

	@Override
	public Request createRequest(Session session) {
		Request request = new Request(METHOD_NAME).createDefaultHeader(session);
		request.put("country", new Country());
		request.put("offset", 0);
		request.put("playlistID", id);
		request.put("isVerified", false);
		return request;
	}

	@Override
	public Type getType() {
		return new TypeToken<Response<Playlist>>(){}.getType();
	}

	public Song[] getSongs() {
		return songs;
	}

}
