package UnrestrictedGame;

import restrictedGame.RGG;

/**
 * This class extends restricted guessing game. Unrestricted guessing game
 * allows player to enter their answer and update the tree
 * 
 * @author Ching2 Huang
 *
 */
public class UGG extends RGG {

	/**
	 * Constructed changes the title of the frame and hide possible answers
	 */
	public UGG() {
		// change the name
		setTitle("Unrestrcited Guessing Game");
		// hide the possible answers
		answers.setVisible(false);
	}

	/**
	 * override actions for no button
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
			// pop up a new frame for the player to add their option
			new AddOpt(current);
			restart(); // restart the game
		}
	}
}