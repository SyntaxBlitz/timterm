package net.syntaxblitz.TimTerm.commands;

import java.util.List;

public class GoogleSearch extends Search {
	
	@Override
	protected String getURI(String query, List<String> flags) {
		if(flags.contains("i") || flags.contains("-image")) {
			this.searchURI = "https://www.google.com/search?q=%S&tbm=isch";
		} else if (flags.contains("-ngram") || flags.contains("-ngrams")) {
			this.searchURI = "http://books.google.com/ngrams/graph?content=%S";
			this.delimiter = "+";
		} else if (flags.contains("-books") || flags.contains("b")) {
			this.searchURI = "https://www.google.com/search?q=%S&tbm=bks&tbo=1";
			this.delimiter = "+";
		} else if (flags.contains("l") || flags.contains("-ifm") || flags.contains("-feelinglucky") || flags.contains("-lucky")) {
			this.searchURI = "https://www.google.com/search?q=%S&btnI=I%27m+Feeling+Lucky";
		}

		if(!flags.contains("r") && !flags.contains("-raw")) {
			if(query.equalsIgnoreCase("drive")) {
				return "https://drive.google.com/";
			} else if (query.equalsIgnoreCase("play")) {
				return "https://play.google.com/";
			} else if (query.equalsIgnoreCase("pb") || query.equalsIgnoreCase("playbooks")) {
				return "https://play.google.com/books";
			} else if (query.equalsIgnoreCase("pm") || query.equalsIgnoreCase("playmusic") || query.equalsIgnoreCase("music")) {
				return "https://play.google.com/music";
			} else if (query.equalsIgnoreCase("mail") || query.equalsIgnoreCase("gmail") || query.equalsIgnoreCase("m")) {
				return "https://mail.google.com/";
			}
		}
		return this.searchURI.replace("%S", query).replaceAll(" ", this.delimiter);
	}
	
}
