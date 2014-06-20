package net.syntaxblitz.TimTerm.commands;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import net.syntaxblitz.TimTerm.URLOpener;

public class Search implements Command {

	protected String searchURI = "https://www.google.com/search?q=%S";
	protected String delimiter = "%20";
	
	@Override
	public void execute(String command, List<String> flags, List<String> arguments) {
		StringBuilder queryBuilder = new StringBuilder();
		for(String s: arguments) {
			queryBuilder.append(s);
			queryBuilder.append(" ");	// spaces are later replaced by the delimiter in getURI(): this allows for more flexible delimiter replacement in broad commands that might search different areas depending on flags
		}
		String query = queryBuilder.toString();
		if(query.endsWith(" ")) {
			query = query.substring(0, query.length() - 1);
		}
		
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// whatevs
			e.printStackTrace();
		}
		
		URLOpener.openURL(this.getURI(query, flags));
	}
	
	protected String getURI(String query, List<String> flags) {
		return searchURI.replace("%S", query).replaceAll(" ", this.delimiter);
	}

}
