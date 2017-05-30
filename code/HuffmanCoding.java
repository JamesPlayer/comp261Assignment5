import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */

class Checker implements Comparator<HuffmanNode>
{
   public int compare(HuffmanNode node1, HuffmanNode node2)
   {
       if (node1.probability < node2.probability) {
    	   return -1;
       } else if (node1.probability > node2.probability) {
    	   return 1;
       }
       return 0;
   }
}

public class HuffmanCoding {
	
	protected Map<Character, Integer> charFrequencies = new HashMap<Character, Integer>();
	
	protected HuffmanNode tree;
	
	protected Map<Character, String> encodingValues = new HashMap<Character, String>();
	
	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		
		for (char c : text.toCharArray()) {
			if (charFrequencies.containsKey(c)) {
				charFrequencies.put(c, charFrequencies.get(c) + 1);
			} else {
				charFrequencies.put(c, 1);
			}
		}
		
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<HuffmanNode>(new Checker());
		
		for (Entry<Character, Integer> entrySet : charFrequencies.entrySet()) {
			char value = entrySet.getKey();
			double probability = entrySet.getValue() / (double) text.length();
			queue.offer(new HuffmanNode(value, probability, null, null, null));
		}
		
		while (queue.size() > 1) {
			HuffmanNode node1 = queue.poll();
			HuffmanNode node2 = queue.poll();
			double probability = node1.probability + node2.probability;
			HuffmanNode parent = new HuffmanNode(null, probability, null, node1, node2);
			queue.offer(parent);			
		}
		
		// Final node (root node)
		tree = queue.poll();
		
		// Traverse tree to assign codes
		addCodeToTree(tree, "");
		
	}
	
	protected void addCodeToTree(HuffmanNode node, String code) {	
		node.code = code;
		
		if (node.left != null) {
			addCodeToTree(node.left, code + "0");
		}
		if (node.right != null) {
			addCodeToTree(node.right, code + "1");
		}
		
		if (node.value != null) {
			encodingValues.put(node.value, node.code);
		}
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		
		StringBuilder encodedStr = new StringBuilder();
		
		for (char c : text.toCharArray()) {
			encodedStr.append(encodingValues.get(c));
		}	
		
		return encodedStr.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		
		StringBuilder decodedStr = new StringBuilder();
		HuffmanNode node = tree;
		
		for (char c : encoded.toCharArray()) {
			
			if (c == '0') {
				node = node.left;
			} else {
				node = node.right;
			}
			
			if (node.value != null) {
				decodedStr.append(node.value);
				node = tree;
			}		
		}
		
		return decodedStr.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		System.out.print(tree.toString());
		System.out.print(encodingValues.toString());
		return encodingValues.toString();
	}
}
