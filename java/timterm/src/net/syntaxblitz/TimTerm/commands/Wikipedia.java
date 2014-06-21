package net.syntaxblitz.TimTerm.commands;

import java.util.List;

public class Wikipedia extends Search {

	@Override
	protected String getURI(String query, List<String> flags) {
		String baseUrl = "https://en.wikipedia.org/w/index.php?title=Special:Search&search=";
		if (flags.contains("i") || flags.contains("-http") || flags.contains("-insecure")) {
			baseUrl = "https://en.wikipedia.org/w/index.php?title=Special:Search&search=";
		}
		return baseUrl + query;
	}
	
}
