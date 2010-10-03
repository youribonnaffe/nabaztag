package com.appspot.ginkotag;

import java.util.ArrayList;
import java.util.List;

public class TextSplitterUtil {

	public static String[] split(String textToSay, int maxTtsLength) {
		if (textToSay == null)
			return new String[0];
		List<String> results = new ArrayList<String>();
		String[] parts = textToSay.split("[ ]");
		String toInsert = "";
		for (int i = 0; i < parts.length; i++) {
			toInsert += parts[i];
			if (toInsert.length() > maxTtsLength) {
				results.add(toInsert);
				toInsert = "";
			}
		}
		if (!toInsert.isEmpty()) {
			results.add(toInsert);
		}
		return results.toArray(new String[0]);
	}

}
