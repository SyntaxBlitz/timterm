package net.syntaxblitz.TimTerm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.syntaxblitz.TimTerm.commands.Command;

// Warning: we're using reflection because reflection is awesome.
public class CommandDispatcher {

	public static void dispatch(String command, List<String> flags, List<String> arguments) {
		// First load the configuration file with the command aliases.
		InputStream configStream = CommandDispatcher.class.getClassLoader().getResourceAsStream("net/syntaxblitz/TimTerm/commands/commands.txt");
		try {
			BufferedReader configReader = new BufferedReader(new InputStreamReader(configStream, "UTF-8"));
			String nextLine = "";
			while((nextLine = configReader.readLine()) != null) {
				String[] bits = nextLine.split(" ");
				if(bits.length != 2) {
					System.out.println("Malformed configuration");
					return;
				}
				
				if(command.equalsIgnoreCase(bits[0])) {
					String classPath = bits[1];
					CommandDispatcher.loadClass(classPath, flags, arguments);
					return;
				}
			}
			
			// Didn't return.
			// We don't have a very good way of telling the user about this sort of thing, so we'll just do nothing.
			System.out.println("Command not found");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Couldn't read configuration");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Couldn't read configuration");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Couldn't read configuration - does the file exist?");
			e.printStackTrace();
		}
		
	}

	private static void loadClass(String fqn, List<String> flags, List<String> arguments) {
		// fqn stands for fully-qualified name
		try {
			Class<?> cmdClass = Class.forName(fqn);
			Command cmd = (Command) cmdClass.newInstance();
			cmd.execute(flags, arguments);
		} catch (ClassNotFoundException e) {
			System.out.println("Malformed configuration");
			return;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
