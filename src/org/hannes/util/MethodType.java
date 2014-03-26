package org.hannes.util;

import java.util.HashMap;
import java.util.Map;

public enum MethodType {

	TYPE_A("jsqueue", "getStreamKeyFromSongIDEx", "markSongComplete",
			"markSongDownloadedEx", "markStreamKeyOver30Seconds"),

	TYPE_B("htmlshark", "getStreamKeysFromSongIDs", "getCommunicationToken",
			"getResultsFromSearch", "authenticateUser",
			"playlistAddSongToExisting", "playlistAddSongToExisting",
			"popularGetSongs", "playlistGetSongs", "initiateQueue",
			"userAddSongsToLibrary", "userGetPlaylists",
			"userGetSongsInLibrary", "getFavorites", "favorite", "getCountry",
			"albumGetSongs");
	
	/**
	 * The map n stuff
	 */
	private static final Map<String, MethodType> types = new HashMap<>();

	/**
	 * The collection of methods associated with this type
	 */
	private final String[] methods;
	
	/**
	 * The method name
	 */
	private final String name;
	
	private MethodType(String name, String... methods) {
		this.name = name;
		this.methods = methods;
	}

	static {
		for (MethodType type : MethodType.values()) {
			for (String method : type.methods) {
				types.put(method, type);
			}
		}
	}

	/**
	 * Get the MethodType for the given method name
	 * 
	 * @param method
	 * @return
	 */
	public static MethodType get(String method) {
		return types.get(method);
	}

	public String getName() {
		return name;
	}

}