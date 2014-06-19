package net.syntaxblitz.TimTerm.commands;

import java.util.List;

import net.syntaxblitz.TimTerm.URLOpener;

public class Facebook implements Command {

	@Override
	public void execute(List<String> flags, List<String> arguments) {
		String url = "https://facebook.com/";
		if(flags.contains("i") || flags.contains("-http") || flags.contains("-insecure")) {
			url = "http://facebook.com/";
		}
		URLOpener.openURL(url);
	}

}
