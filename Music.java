package restrictedGame;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * this class plays music
 * 
 * @author Ching2 Huang
 *
 */
public class Music {

	/**
	 * constructor takes in music and play the music
	 */
	public Music() {
		try {
			String file = "music.wav"; // file name
			// take in the file and play the music
			InputStream in = new FileInputStream(file);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
			// print out to tell something is wrong
			System.out.println("something is wrong");
		}
	}
}
