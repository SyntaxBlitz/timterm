package net.syntaxblitz.TimTerm.commands;

import java.util.List;

public class GitHub extends Search {

	@Override
	protected String getURI(String query, List<String> flags) {
		return "https://github.com/search?q=" + query;
	}
	
}
