package net.syntaxblitz.TimTerm.commands;

import java.util.HashMap;
import java.util.List;

import net.syntaxblitz.TimTerm.URLOpener;

public class Steam implements Command {

	// TODO: move this into a separate text file if it gets too big
	private static HashMap<String, Integer> gameIds = new HashMap<String, Integer>();
	static {
		gameIds.put("csgo", 730);
		gameIds.put("go", 730);
		
		gameIds.put("l4d2", 550);
	}
	
	@Override
	public void execute(String command, List<String> flags, List<String> arguments) {
		Integer gameId = gameIds.get(command);
		if (gameId == null) {
			gameId = gameIds.get(arguments.get(0));
		}
		
		if (gameId != null) {
			URLOpener.openURL("steam://run/" + gameId);
		}
	}
	
}
