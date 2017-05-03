package UnrestrictedGame;

import restrictedGame.Music;

/**
 * This class starts unrestricted guessing games
 * 
 * @author SUN
 *
 */
public class UnrestrictedGameApp {

	/**
	 * main method start the game with background music
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Music(); // play the music
		new Menu(); // start with the menu
	}
}
