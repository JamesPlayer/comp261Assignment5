
public class HuffmanNode {
	
	public Character value;
	public double probability;
	public String code;
	public HuffmanNode left;
	public HuffmanNode right;
	
	/**
	 * @param value
	 * @param probability
	 * @param code
	 * @param leftChild
	 * @param rightChild
	 */
	public HuffmanNode(Character value, double probability, String code, HuffmanNode leftChild, HuffmanNode rightChild) {
		this.value = value;
		this.probability = probability;
		this.code = code;
		this.left = leftChild;
		this.right = rightChild;
	}
	
	public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
	    if(right!=null) {
	        right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
	    }
	    
	    String val = "";
	    
	    if (value != null) {
	    	val += value + ":";
	    }
	    
	    val += String.valueOf(probability);
	    
	    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(val).append("\n");
	    if(left!=null) {
	        left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
	    }
	    return sb;
	}

	@Override
	public String toString() {
	    return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}
	
	
	
	

}
