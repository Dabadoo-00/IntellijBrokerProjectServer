package com.skills4testing.core.util;

import com.skills4testing.exchange.CVersion;

public final class Util {

	
	private static String kServerName = CVersion.kServerName;
	
	/**
	 * toSystemMessage
	 * 
	 * This method will take a string as parameter and show it this to the
	 * console.
	 * 
	 * @param message
	 *            String type, actual message to display
	 * 
	 *            Example: message = "WICTCE Started." Output : WICTCE
	 *            02-05-2002 10.33.35> WICTCE Started.
	 * @author 
	 */
	@SuppressWarnings("null")
	public static void toSystemMessage(String message, boolean printBlock) {
		if ((message != null) || !message.trim().equals("")) {
			if (!printBlock) {
				System.out.println(kServerName + " > " + CDateTime.toSystemOutDate() + "> " + message);
			}
		}
	}
	
	
	/**
	 * StringSearchReplace
	 * 
	 * @param This
	 *            method takes three String's as input and searches the second
	 *            String in the first String, and replaces it with the third
	 *            String. For example, if the parameters are A,B,C ; this method
	 *            searches B in A, if it gets B in A, it replaces the portion of
	 *            B by C in A. It is case insensitive.
	 * 
	 *            This function recursively looks for B in a and replaces all
	 *            the instances of B by C in A.
	 * 
	 *            Acceptable input : 1. No String can be null. 2. First and
	 *            third String may be empty, that is "". If the second String is
	 *            "" it returns the original String.
	 * 
	 *            Output : 1. Replaced String. 2. If the String is not matched
	 *            and replaced, it returns the original String.
	 * 
	 *            Exceptions : If any of the String is null, it throws
	 *            Exceprion.
	 * 
	 *            Example: Input : asshdssdfgg,ss,d Output : adhdddfgg
	 */
	public static String StringSearchReplace(String originalString,
			String searchString, String replacedString) throws Exception {
		boolean mDone = false;
		int positionIndex = 0;

		String tempString = originalString;
		String tempReplacedString = replacedString;

		if ((originalString == null) | (searchString == null)
				| (replacedString == null)) {
			// throw new Exception("Can not be null, yeah");
			// throwing exc makes the function bound to certain rules.
			return null;
		}

		if (searchString.equalsIgnoreCase("")) {
			return originalString;
		}

		StringBuffer str = null;

		while (!mDone) {
			int lengthOfString = tempString.length();
			tempReplacedString = replacedString;

			if ((positionIndex = tempString.toLowerCase().indexOf(
					searchString.toLowerCase(), positionIndex)) > -1) {
				str = new StringBuffer(tempString);

				if (searchString.equalsIgnoreCase("<URL>")
						|| searchString.equalsIgnoreCase("<EMAIL>")) {
					int preIndex = positionIndex - 1;
					char preChar = '\u0000';

					if (preIndex > -1) {
						preChar = tempString.charAt(preIndex);

						//Character ch = new Character(preChar);

						if (!Character.isWhitespace(preChar)) {
							tempReplacedString = " " + tempReplacedString;
						}
					}

					int postIndex = positionIndex + searchString.length();
					char postChar = '\u0000';

					if (postIndex < lengthOfString) {
						postChar = tempString.charAt(postIndex);

						//Character ch = new Character(postChar);

						if (!Character.isWhitespace(postChar)) {
							tempReplacedString = tempReplacedString + " ";
						}
					}
				}

				str.replace(positionIndex,
						positionIndex + searchString.length(),
						tempReplacedString);
				positionIndex += tempReplacedString.length();
				tempString = str.toString();
			} else {
				return tempString;
			}
		}

		return null;
	}
}
