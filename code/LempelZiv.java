import java.util.Scanner;

/**
 * A new instance of LempelZiv is created for every run.
 */
public class LempelZiv {
	/**
	 * Take uncompressed input as a text string, compress it, and return it as a
	 * text string.
	 */
	public String compress(String input) {
		StringBuffer result = new StringBuffer();
		int cursor = 1;
		int windowSize = 100;
		
		// Initial value
		result.append("[0|0|" + input.charAt(0) + "]");
		
		while (cursor < input.length()) {
			int lookahead = 0;
			int prevMatch = 0;
			
			while (true) {
				
				int endOfWindow = cursor-1;
				int startOfWindow = (cursor < windowSize) ? 0 : cursor-windowSize;
				String haystack = input.substring(startOfWindow, endOfWindow+1);
				String needle = input.substring(cursor, cursor+lookahead+1);
				int match = haystack.indexOf(needle);
				
				if (match != -1 && (cursor+lookahead) <= input.length()-2) {
					prevMatch = match;
					lookahead = lookahead + 1;
				} else {	
					result.append("[" + (lookahead == 0 ? 0 : cursor - (startOfWindow + prevMatch)) + "|" + lookahead + "|" + input.charAt(cursor+lookahead) + "]");
					cursor = cursor + lookahead + 1;
					break;
				}
			}
		}
		
		
		return result.toString();
	}

	/**
	 * Take compressed input as a text string, decompress it, and return it as a
	 * text string.
	 */
	public String decompress(String compressed) {
		
		Scanner scan = new Scanner(compressed);
		int cursor = 0;
		int offset = 0;
		int length = 0;
		String ch;
		StringBuffer result = new StringBuffer();
		
		scan.useDelimiter("\\[|\\]\\[|\\]|\\|");
		
		while (scan.hasNext()) {
			offset = scan.nextInt();
			length = scan.nextInt();
			ch = scan.next();
			
			for (int j = 0; j < length; j++) {
				result.append(result.charAt(cursor-offset));
				cursor++;
			}
			
			result.append(ch);
			cursor++;
		}
		
		scan.close();
		
		return result.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't want to. It is called on every run and its return
	 * value is displayed on-screen. You can use this to print out any relevant
	 * information from your compression.
	 */
	public String getInformation() {
		return "";
	}
}
