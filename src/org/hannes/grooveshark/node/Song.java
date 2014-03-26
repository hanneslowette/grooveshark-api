package org.hannes.grooveshark.node;

import java.io.IOException;
import java.net.MalformedURLException;

import org.hannes.grooveshark.Session;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a song
 * 
 * @author Hannes
 *
 */
public class Song {

	@SerializedName("SongID")
	/**
	 * The song id
	 */
	private int id;
	
	@SerializedName("Name")
	/**
	 * The name of the song
	 */
	private String name;

	@SerializedName("ArtistName")
	/**
	 * The artists's name
	 */
	private String artist;

	/**
	 * Immediately get this song's download information
	 * 
	 * @param session
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public SongDownload getDownloadInformation(Session session) throws MalformedURLException, IOException {
		return session.request(new SongDownload(this)).getResult();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}

}