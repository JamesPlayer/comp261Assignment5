/**
 * A new Search instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class Search {

	public Search(String pattern, String text) {
		// TODO maybe fill this in.
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
		boolean found;
		
		for (int k = 0; k <= (n-m); k++) {
			found = true;
			
			for (int i = 0; i < m; i++) {
				if (pattern.charAt(i) != text.charAt(k+i)) {
					found = false;
					break;
				}
			}
			
			if (found) {
				return k;
			}
		}		
		return -1;
	}
}
