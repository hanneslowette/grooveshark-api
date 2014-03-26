package org.hannes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.hannes.grooveshark.Grooveshark;
import org.hannes.grooveshark.Session;
import org.hannes.grooveshark.node.Playlist;
import org.hannes.grooveshark.node.Song;
import org.hannes.grooveshark.node.SongDownload;

import com.google.gson.JsonSyntaxException;

public class Example {

	public static void main(String[] args) throws IOException, JsonSyntaxException, NoSuchAlgorithmException {
		/*
		 * Create a new session
		 */
		Session session = Grooveshark.createSession();

		/*
		 * Request a single song (provided you already know the song's ID)
		 * 
		 * TODO: This only gets the download information. Not the information about the actual song
		 */
		SongDownload song_download = session.request(new SongDownload(37262423)).getResult();
		System.out.println(song_download.getUrl().toString());
		
		/*
		 * Request a playlist
		 * 
		 * The ID of a playlist is the last part in the URL
		 * e.g. http://grooveshark.com/#!/playlist/Beatles/51574126
		 * 
		 * This means "51574126" is the playlist ID
		 * 
		 * TODO: All of the whitespace in the artist and name have been removed
		 */
		Playlist response = session.request(new Playlist("51574126")).getResult();
		for (Song song : response.getSongs()) {
			System.out.println(song.getArtist() + " - " + song.getName() + " - " + song.getDownloadInformation(session).getUrl().toString());
		}
	}

}