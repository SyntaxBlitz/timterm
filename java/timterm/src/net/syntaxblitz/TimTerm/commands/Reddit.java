package net.syntaxblitz.TimTerm.commands;

import java.util.List;

import net.syntaxblitz.TimTerm.URLOpener;

public class Reddit implements Command {

	@Override
	public void execute(List<String> flags, List<String> arguments) {
		String url = "http://reddit.com/";
		if(arguments.size() > 0) {
			url += "r/" + arguments.get(0);
		}
		URLOpener.openURL(url);
	}

}
