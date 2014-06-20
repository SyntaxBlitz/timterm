package net.syntaxblitz.TimTerm.commands;

import java.util.List;

import net.syntaxblitz.TimTerm.URLOpener;

public class GMail implements Command {

	@Override
	public void execute(String command, List<String> flags, List<String> arguments) {
		URLOpener.openURL("https://mail.google.com/");
	}

}
