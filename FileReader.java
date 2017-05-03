package restrictedGame;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import datastructures.BinaryTreeNode;
import datastructures.DefaultBinaryTree;
import datastructures.DefaultBinaryTreeNode;

/**
 * Parse xml file and create a decision tree
 * 
 * @author Ching2 Huang
 *
 */
public class FileReader {

	private DefaultBinaryTree<String> tree; // the decision tree
	private DefaultBinaryTreeNode<String> root; // the root of the tree

	/**
	 * Constructor reads a file and parse the file
	 */
	public FileReader() {
		try {
			// Setup XML Document
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File xmlFile = new File("DecisionTree.xml");
			Document document = builder.parse(xmlFile);
			// navigate through the passed in document
			parseTree(document);
		} catch (ParserConfigurationException pce) {
			// print out the exception if this exception happens
			System.out.println("ParserConfigurationException");
		} catch (SAXException saxe) {
			// print out the exception if this exception happens
			System.out.println("SAXException");
		} catch (IOException ioe) {
			// print out the exception if this exception happens
			System.out.println("IOException");
		}
	}

	/**
	 * takes in a document and add data to the tree
	 * 
	 * @param document
	 */
	private void parseTree(Document doc) {
		// create a tree
		tree = new DefaultBinaryTree<String>();
		root = parseNode(doc.getDocumentElement());
		tree.setRoot(root);
	}

	/**
	 * Analyze a node
	 * 
	 * @param node
	 * @return a new binary tree node
	 */
	private DefaultBinaryTreeNode<String> parseNode(Node node) {
		// creates a question node
		DefaultBinaryTreeNode<String> qNode = new DefaultBinaryTreeNode<String>();
		// check if type of node
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element currentElt = (Element) node;
			// if it has statement attribute
			if (currentElt.hasAttribute("statement")) {
				// set the data for the node
				qNode.setData(currentElt.getAttribute("statement"));
			} else {
				// otherwise, get the data and set it to the node
				qNode.setData(node.getTextContent());
			}
		}
		// recursive step: if the node has a child, call the method again to
		// analyze the node
		if (node.hasChildNodes()) {
			// put all the child nodes in a list
			NodeList childList = node.getChildNodes();
			// check every node
			for (int i = 0; i < childList.getLength(); i++) {
				// if it's an element node
				if (childList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					// if there's no left child
					if (qNode.getLeftChild() == null) {
						// set the left child
						qNode.setLeftChild(parseNode(childList.item(i)));
					} else {
						// set the right child
						qNode.setRightChild(parseNode(childList.item(i)));
					}
				}

			}
		}
		return qNode;
	}

	/**
	 * print out the leaves/answers of the tree
	 * 
	 * @return possible answers
	 */
	public String answers(BinaryTreeNode<String> node) {
		return tree.printLeaves(node);
	}

	/**
	 * get decision tree
	 * 
	 * @return the tree
	 */
	public DefaultBinaryTree<String> getTree() {
		return tree;
	}
}