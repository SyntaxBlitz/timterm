package net.syntaxblitz.TimTerm.commands;

import java.util.List;

public interface Command {

	void execute(List<String> flags, List<String> arguments);

}
