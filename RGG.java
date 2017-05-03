package restrictedGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import UnrestrictedGame.Menu;
import datastructures.BinaryTreeNode;
import datastructures.DefaultBinaryTree;

/**
 * This class maintains the model of the restricted guessing game
 * 
 * @author Ching2 Huang
 *
 */
public class RGG extends JFrame implements ActionListener {

	// two buttons: yes/no and restart
	protected JButton yes, no, restart, menu;
	protected JLabel question;// label for questions
	protected JTextArea answers; // text area for answers
	protected DefaultBinaryTree<String> tree; // tree from file reader
	protected FileReader file; // file reader
	protected BinaryTreeNode<String> current; // current node

	/**
	 * Constructor set up the frame for the game and start the game with root
	 * node
	 */
	public RGG() {
		file = new FileReader();
		tree = file.getTree(); // get the tree
		current = tree.getRoot(); // start from the root
		setTitle("Restrcited Guessing Game"); // name the frame
		setFrame(); // set up the frame
	}

	/**
	 * set up the frame
	 */
	private void setFrame() {
		setSize(400, 400); // set the size of the frame
		setResizable(false); // unable to resize the frame
		setLocationRelativeTo(null);// default to the middle of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// shut down the game when the window is closed
		add(panel());
		setVisible(true);// set everything visible
	}

	/**
	 * big panel to put everyting in
	 * 
	 * @return the panel
	 */
	private JPanel panel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout()); // use border layout
		panel.add(northPanel(), BorderLayout.NORTH);
		// add north panel with element
		panel.add(centerPanel(), BorderLayout.CENTER);
		// add center panel with element
		panel.add(southPanel(), BorderLayout.SOUTH);
		// add south panel with element
		return panel;
	}

	/**
	 * northPanel contains a intro label
	 * 
	 * @return the panel
	 */
	private JPanel northPanel() {
		// intro of the game
		JLabel intro = new JLabel(
				"Pick one animal and I'll guess what's in your mind");
		// panel to put the intro
		JPanel northPan = new JPanel();
		// add panel to the north panel
		northPan.add(intro);
		return northPan;
	}

	/**
	 * center panel contains possible answers and questions
	 * 
	 * @return the panel
	 */
	private JPanel centerPanel() {
		JPanel aPan = new JPanel(); // panel for the answers
		aPan.add(textArea());// add answers to the panel

		// panel for questions
		JPanel qPan = new JPanel();
		question = new JLabel(current.getData().toString()); // get question
		qPan.add(question); // add question to label

		// panel to contain both answers and question
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(2, 1)); // use grid layout
		// add questions and answers to the panel
		center.add(qPan);
		center.add(aPan);
		return center;
	}

	/**
	 * Create a text area to contain all possible answers
	 * 
	 * @return text area
	 */
	protected JTextArea textArea() {
		// the options to choose
		answers = new JTextArea(tree.printLeaves(current));
		// divide to multi line
		answers.setLineWrap(true);
		answers.setWrapStyleWord(true);
		// set size
		answers.setRows(5);
		answers.setColumns(30);
		// unable to edit
		answers.setEditable(false);

		return answers;
	}

	/**
	 * south panel contains yes and no buttons
	 * 
	 * @return the panels
	 */
	public JPanel southPanel() {
		// Create two buttons: yes/no restart
		yes = new JButton("YES");
		no = new JButton("NO");
		restart = new JButton("RESTART");
		menu = new JButton("MENU");
		// tell buttons what to do
		yes.addActionListener(this);
		no.addActionListener(this);
		restart.addActionListener(this);
		menu.addActionListener(this);
		// panel for buttons
		JPanel panel = new JPanel();
		// add buttons to the panel
		panel.add(yes);
		panel.add(no);
		panel.add(restart);
		panel.add(menu);

		return panel;
	}

	/**
	 * tell the buttons what to do
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// if click button yes
		if (e.getSource().equals(yes)) {
			yes(); // go to left subtree
		} else if (e.getSource().equals(no)) {
			no(); // go to right subtree
			// if click button restart
		} else if (e.getSource().equals(restart)) {
			restart(); // restart the game
		} else { // if click menu button
			// go back to the menu
			menu();
		}
		// update answers
		answers.setText(tree.printLeaves(current));
	}

	/**
	 * actions for yes button
	 */
	public void yes() {
		// if the node has left child
		if (current.getLeftChild() != null) {
			// if the node's left child is not a leaf
			if (!current.getLeftChild().isLeaf()) {
				// update current
				current = current.getLeftChild();
				// update question
				question.setText(current.getData());
			} else { // if the node's left child is a leaf, means it's an answer
				// update current
				current = current.getLeftChild();
				// update answer
				question.setText("Is your answer: " + current.getData() + "?");
			}
		} else {
			// guess the right answer
			question.setText("YEAH!!!!");
		}
	}

	/**
	 * actions for no button to perform
	 */
	public void no() {
		// if the node has right child
		if (current.getRightChild() != null) {
			// if the right child is not a lead
			if (!current.getRightChild().isLeaf()) {
				// update current and the question
				current = current.getRightChild();
				question.setText(current.getData());
			} else {// if right child is a leaf, the node is an answer
				// update current
				current = current.getRightChild();
				// print the answer
				question.setText("Is your answer: " + current.getData() + "?");
			}
		} else {
			// update text when guess wrong
			question.setText("oh no");
		}
	}

	/**
	 * action for restart button to perform
	 */
	protected void restart() {
		// reset current as the root
		current = tree.getRoot();
		// reset the question
		question.setText(current.getData());
	}

	/**
	 * actions for menu button to perform
	 */
	protected void menu() {
		// go back to the menu
		new Menu();
		// dispose the game
		dispose();
	}
}