package net.syntaxblitz.TimTerm.commands;

import java.util.List;

public interface Command {

	void execute(String command, List<String> flags, List<String> arguments);

}
