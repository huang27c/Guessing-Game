package UnrestrictedGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import restrictedGame.RGG;

/**
 * This class creates a menu for the game. Menu is the first thing to pop up and
 * the players can decide which game they would like to play.
 * 
 * @author Ching2 Huang
 *
 */

public class Menu implements ActionListener {

	private JFrame f; // the frame for menu
	private JButton ugg, rgg; // buttons for two different games

	/**
	 * constructor starts the menu
	 */
	public Menu() {
		// create the frame with game
		frame();
	}

	/**
	 * creates a frame for menu and contains everything
	 * 
	 * @return frame
	 */
	private JFrame frame() {
		f = new JFrame("GUESSING GAME"); // create new frame with name
		f.setSize(400, 200); // set the size of the frame
		f.setResizable(false); // unable to resize the frame
		f.setLocationRelativeTo(null);// default to the middle of the screen
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// shut down the game when the window is closed
		f.add(menuPan());
		f.setVisible(true);// set everything visible
		return f;
	}

	/**
	 * 
	 * @return the panel
	 */
	private JPanel menuPan() {
		// pan is the panel to contain everything
		JPanel pan = new JPanel();
		pan.setLayout(new BorderLayout()); // use border layout

		// welcoming sentence for the beginning of the game
		JLabel info = new JLabel("Welcome to animal guessing game!!");
		info.setHorizontalAlignment(info.CENTER); // set it to the center
		pan.add(info, BorderLayout.NORTH);// add it to the panel

		// creates to button
		rgg = button("Restricted Guessing Game");
		ugg = button("Unrestricted Guessing Game");

		// create a panel for each button, aesthetic purpose
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		// add buttons to panels
		p1.add(rgg);
		p2.add(ugg);

		// another panel to contain p1 and p2
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(2, 1)); // use grid layout
		// add p1 and p2 to p3
		p3.add(p1);
		p3.add(p2);

		// add everything to pan
		pan.add(p3, BorderLayout.CENTER);
		return pan;
	}

	/**
	 * this method creates a button
	 * 
	 * @param name
	 *            of the button
	 * @return a button
	 */
	private JButton button(String stg) {
		// create a button
		JButton but = new JButton(stg);
		// tell the button what to do
		but.addActionListener(this);
		return but;
	}

	/**
	 * tell the buttons what action to performs
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// if click restricted guessing game button
		if (e.getSource().equals(rgg)) {
			// pop up new game
			new RGG();
		} else {// otherwise,
			// pop up the other games
			new UGG();
		}
		// dispose menu frame
		f.dispose();
	}
}
