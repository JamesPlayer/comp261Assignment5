import java.util.HashMap;
import java.util.Map;

/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {
	
	Map<Integer, Integer> matchTable = new HashMap<Integer, Integer>();

	public KMP(String pattern, String text) {
		createTable(pattern, text);
	}
	
	protected void createTable(String pattern, String text) {
		// Initialise
		matchTable.put(0, -1);
		matchTable.put(1, 0);
		int j = 0;
		int pos = 2;
		
		while (pos < pattern.length()) {
			if (pattern.charAt(pos-1) == pattern.charAt(j)) {
				matchTable.put(pos, j+1);
				pos++;
				j++;
			} else if (j > 0) {
				j = matchTable.get(j);
			} else { // j == 0
				matchTable.put(pos, 0);
				pos++;
			}
		}
		
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		int m = pattern.length();
		int n = text.length();
		int s = 0;
		int t = 0;
		
		while (t+s < n) {
			if (pattern.charAt(s) == text.charAt(t+s)) {
				s = s + 1;
				if (s == m) {
					return t;
				}
			} else if (matchTable.get(s) == -1) {
				t = t + s + 1;
				s = 0;
			} else {
				t = t + s - matchTable.get(s);
				s = matchTable.get(s);
			}
		}
		
		return -1;
	}
}
