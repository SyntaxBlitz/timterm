package net.syntaxblitz.TimTerm.commands;

import java.util.List;

public class YouTube extends Search {

	@Override
	protected String getURI(String query, List<String> flags) {
		String url = "https://youtube.com/";
		if (flags.contains("i") || flags.contains("-http") || flags.contains("-insecure")) {
			url = "http://youtube.com/";
		}

		if (!query.equals("")) {
			if (flags.contains("v") || flags.contains("-id")) {
				url += "watch?v=" + query;
			} else {
				url += "results?search_query=" + query;
			}
		}
		return url;
	}

}
