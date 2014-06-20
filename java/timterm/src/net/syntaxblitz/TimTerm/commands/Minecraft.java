package net.syntaxblitz.TimTerm.commands;

import java.io.IOException;
import java.util.List;

public class Minecraft implements Command {

	@Override
	public void execute(String command, List<String> flags, List<String> arguments) {
		try {
			Runtime.getRuntime().exec(new String[] { "C:\\Users\\Timothy\\Downloads\\Minecraft.exe" });
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
