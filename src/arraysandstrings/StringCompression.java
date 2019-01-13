package arraysandstrings;

public class StringCompression {
	
	public static void main(String[] args) {
		String input = "aabbbbccd";
		String rle = rle(input);
		System.out.println(rle);
	}

	private static String rle(String input) {
		if (input.length() == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		
		char [] charArray = input.toCharArray();
		char pivot = charArray[0];
		int sequence = 1;
		for (int i = 1; i< charArray.length; i++) {
			char current = charArray[i];
			if (current == pivot) {
				sequence++;
			} else {
				if (sequence > 1) {
					builder.append(sequence);
				}
				builder.append(pivot);
				pivot = current;
				sequence = 1;
			}
		}
		
		if (sequence > 1) {
			builder.append(sequence);
		}
		builder.append(pivot);
		
		
		return builder.toString();
	}

}
