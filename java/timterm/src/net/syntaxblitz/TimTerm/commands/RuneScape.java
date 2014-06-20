package net.syntaxblitz.TimTerm.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RuneScape implements Command {

	@Override
	public void execute(String command, List<String> flags, List<String> arguments) {
		String dir = "C:\\Users\\Timothy\\jagexcache\\jagexlauncher\\bin\\";
		String pathToExec = "C:\\Users\\Timothy\\jagexcache\\jagexlauncher\\bin\\JagexLauncher.exe";
		String execArg = "oldschool";
		if(flags.contains("-eoc") || flags.contains("e")) {
			execArg = "runescape";
		} else if(flags.contains("-oldschool") || flags.contains("o")) {
			execArg = "oldschool";
		}

		String[] cmdArray = {pathToExec, execArg};
		try {
			Runtime.getRuntime().exec( cmdArray, null, new File(dir));	// No idea why I need to use the full exe path AND working dir, but that's just how it goes..
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
