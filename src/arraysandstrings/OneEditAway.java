package arraysandstrings;


public class OneEditAway {

	public static void main (String [] args)  {
		String s1 = "aple";
		String s2 = "apled";
		boolean isOneEditAway = isOneEditAway(s1, s2);
		System.out.println(s1 + " " + s2 + " is one edit away? " + isOneEditAway);
	}

	private static boolean isOneEditAway(String s1, String s2) {
		if (Math.abs(s1.length() - s2.length()) > 1) {
			return false;
		}
		
		if (s1.length() == s2.length()) {
			return isOneReplace(s1, s2);
		} else {
			String longString = null;
			String shortString = null;
			if (s1.length() > s2.length()) {
				longString = s1;
				shortString = s2;
			} else {
				longString = s2;
				shortString = s1;
			}
			
			return isOneInsertAway(longString, shortString);
		}
	}

	private static boolean isOneInsertAway(String longString, String shortString) {
		int diff = 0;
		for (int i = 0, j=0; i < longString.length() && j < shortString.length(); i++, j++) {
			if (longString.charAt(i) != shortString.charAt(j)) {
				diff++;
				i++;
				if (diff > 1) {
					break;
				}
			}
		}
		
		return diff < 2;
	}

	private static boolean isOneReplace(String s1, String s2) {
		int diff = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				diff++;
				if (diff > 1) {
					break;
				}
			}
		}
		
		return diff < 2;
	}

	
}
