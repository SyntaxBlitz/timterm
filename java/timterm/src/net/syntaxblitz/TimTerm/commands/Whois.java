package net.syntaxblitz.TimTerm.commands;

import java.util.List;

public class Whois extends Search {

	@Override
	protected String getURI(String query, List<String> flags) {
		return "http://who.is/whois/" + query.replaceAll("[^A-Za-z0-9.\\-]", "");
	}
	
	@Override
	protected boolean getShouldEncode() {
		return false;
	}
	
}
