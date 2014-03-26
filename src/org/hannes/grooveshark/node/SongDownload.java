package org.hannes.grooveshark.node;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

import org.hannes.grooveshark.Session;
import org.hannes.json.Request;
import org.hannes.json.Response;
import org.hannes.util.Country;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class SongDownload implements Node {

	/**
	 * The method name
	 */
	private static final String METHOD_NAME = "getStreamKeyFromSongIDEx";

	@SerializedName("SongID")
	/**
	 * The song id
	 */
	private final int id;
	
	@SerializedName("streamServerID")
	/**
	 * The server id
	 */
	private String serverId;
	
	@SerializedName("ip")
	/**
	 * The server IP
	 */
	private String serverIP;
	
	/**
	 * The streamkey
	 */
	private String streamKey;

	public SongDownload(int id) {
		this.id = id;
	}

	public SongDownload(Song song) {
		this.id = song.getId();
	}

	@Override
	public Request createRequest(Session session) {
		Request request = new Request(METHOD_NAME).createDefaultHeader(session);
		request.put("songID", id);
		request.put("country", new Country());
		request.put("mobile", false);
		request.put("prefetch", false);
		return request;
	}

	@Override
	public Type getType() {
		return new TypeToken<Response<SongDownload>>(){}.getType();
	}

	public URL getUrl() throws MalformedURLException {
		return new URL("http://"+ serverIP + "/stream.php?streamKey=" + streamKey);
	}

	public String getServerId() {
		return serverId;
	}

	public String getServerIP() {
		return serverIP;
	}

	public String getStreamKey() {
		return streamKey;
	}

}
