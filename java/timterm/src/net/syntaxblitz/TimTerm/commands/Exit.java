package net.syntaxblitz.TimTerm.commands;

import java.util.List;

public class Exit implements Command {

	@Override
	public void execute(List<String> flags, List<String> arguments) {
		System.exit(0);
	}

}
