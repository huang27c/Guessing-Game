package UnrestrictedGame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import datastructures.BinaryTreeNode;
import datastructures.DefaultBinaryTreeNode;

/**
 * Create a new frame to let player add options and save the options to the data
 * base
 * 
 * @author Ching2 Huang
 *
 */
public class AddOpt implements ActionListener {

	// two textAreas: answer and question
	private JTextArea aTA, qTA;
	private JButton yes, no;// yes/no button
	private String newA, newQ; // three
	private BinaryTreeNode<String> root;
	private JFrame f;

	/**
	 * pop up a frame for user to add options
	 * 
	 * @param current
	 *            node from rgg or ugg
	 */
	public AddOpt(BinaryTreeNode<String> current) {
		root = current;
		frame();// the frame for adding options
	}

	/**
	 * tell yes/no buttons what to do
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// if the player clicks yes
		if (e.getSource().equals(yes)) {
			// update data
			updateInfo("yes");
		} else {
			// if the player clicks no, up date the data
			updateInfo("no");
		}
		// close the window only when the player enter smth
		if (checkInput()) {
			f.dispose();// dispose the frame
		}
	}

	/**
	 * update the data in the decision tree
	 * 
	 * @param yes
	 *            or no
	 */
	private void updateInfo(String stg) {
		// the original data in the root
		String oldA = root.getData();
		// get the new answer and new question from the text area
		newA = aTA.getText();
		newQ = qTA.getText();
		root.setData(newQ);
		// button decides how to reset the data
		if (stg.equals("yes")) {
			// if it's yes, then new answer should be the left child and old one
			// be the right child
			root.setLeftChild(new DefaultBinaryTreeNode(newA));
			root.setRightChild(new DefaultBinaryTreeNode(oldA));
		} else {
			// if it's no, then new answer should be the right child and old one
			// be the left child
			root.setLeftChild(new DefaultBinaryTreeNode(oldA));
			root.setRightChild(new DefaultBinaryTreeNode(newA));
		}
	}

	/**
	 * make sure the player enter something
	 * 
	 * @return true when the player enter something
	 */
	private boolean checkInput() {
		// two warnings
		String warnA = "you have to put an answer";
		String warnQ = "you have to put a question";
		// if the player didn't enter anything or it's the warning
		if (newA.isEmpty() || newA.equals(warnA)) {
			// show the warning
			aTA.setText(warnA);
			return false;
		} else if (newQ.isEmpty() || newQ.equals(warnQ)) {
			// show the warning
			qTA.setText(warnQ);
			return false;
		} else {
			return true;
		}
	}

	/**
	 * the frame for adding options
	 * 
	 * @return the frame
	 */
	private JFrame frame() {
		// panel to contain three parts of adding options
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(3, 1));
		pan.add(upP()); // the answer
		pan.add(midP()); // the question
		pan.add(btmP()); // the buttons

		// create the frame
		f = new JFrame("Tell me more");
		f.add(pan);// add pan to the frame
		f.setSize(300, 400); // size
		f.setResizable(false); // unable to resize
		f.setUndecorated(true); // unable to drag and close
		f.setAlwaysOnTop(true); // always on top
		f.setLocationRelativeTo(null); // pop up in the middle
		f.setVisible(true); // show the frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// stop running when it's closed

		return f;
	}

	/**
	 * top panel contains a place for the player to enter answer
	 * 
	 * @return panel
	 */
	private JPanel upP() {
		// text area to enter answer
		aTA = new JTextArea();
		// panel to contain text area
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		// label for instruction
		// add everything in panel
		p.add(instruction("What's your answer"));
		p.add(aTA);

		return p;
	}

	/**
	 * middle panel contains a place for player to enter question
	 * 
	 * @return the panel
	 */
	private JPanel midP() {
		// text area to enter question
		qTA = new JTextArea();
		// panel to contain text area and label
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		// add everything to panel
		p.add(instruction("Add a question for it"));
		p.add(qTA);

		return p;
	}

	/**
	 * bottom panel contains two buttons
	 * 
	 * @return the panel
	 */
	private JPanel btmP() {
		// two buttons: yes, no
		yes = button("YES");
		no = button("NO");

		// panel to contain buttons
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		// add things in
		p.add(yes);
		p.add(no);

		return p;
	}

	/**
	 * creates a JLabel and center it
	 * 
	 * @param information
	 * @return a label
	 */
	private JLabel instruction(String stg) {
		// new JLabel
		JLabel lb = new JLabel(stg);
		// set it to center
		lb.setHorizontalAlignment(lb.CENTER);
		return lb;
	}

	/**
	 * creates a JButton
	 * 
	 * @param name
	 *            of the button
	 * @return a button
	 */
	private JButton button(String stg) {
		// a new button
		JButton but = new JButton(stg);
		// tell button to perform
		but.addActionListener(this);
		return but;
	}
}