package net.syntaxblitz.TimTerm.commands;

import java.util.List;

import net.syntaxblitz.TimTerm.URLOpener;

public class RuneScapeMap implements Command {

	@Override
	public void execute(List<String> flags, List<String> arguments) {
		URLOpener.openURL("http://www.runescape.com/img/rsp777/gamewin/runescape-map-24-july-07.jpg");
	}

}
