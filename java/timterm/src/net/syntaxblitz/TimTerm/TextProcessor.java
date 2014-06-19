package net.syntaxblitz.TimTerm;

import java.util.ArrayList;

public class TextProcessor {

	public static void processText(String text) {
		String[] bits = text.split(" ");
		
		// First we'll figure out which command they intended to use.
		String command = bits[0];
		
		// Now we'll search for quoted bits. These should be single arguments.
		boolean currentlyQuoted = false;
		ArrayList<String> lstArgs = new ArrayList<String>();	// Could use an array here, but I'd need to loop twice (once for array length, once for population)
		ArrayList<String> lstFlags = new ArrayList<String>();
		for(int i = 1; i < bits.length; i++) {	// This just won't run if there aren't any arguments.		
			if(currentlyQuoted) {
				if(bits[i].endsWith("\"")) {
					currentlyQuoted = false;
					
					bits[i] = bits[i].substring(0, bits[i].length() - 1); // get rid of that nasty end quote
				}
				lstArgs.set(lstArgs.size() - 1, lstArgs.get(lstArgs.size() - 1) + " " + bits[i]);	// Set the last element to its previous text plus space plus this bit
			} else {
				if(bits[i].startsWith("\"") && !bits[i].endsWith("\"")) {	// We're starting a quoted argument, and not ending it at the same time.
					currentlyQuoted = true;									// Keep this in mind: single quoted words will KEEP the quotes around them.
					
					bits[i] = bits[i].substring(1);	// get rid of that nasty start quote
				}
				
				if(!currentlyQuoted && bits[i].startsWith("-")) {
					if(bits[i].charAt(1) == '-') {	// this flag is a double-hyphen flag, and so it is multichar.
						lstFlags.add(bits[i].substring(1));	// add the entire thing to the flags, with a single dash in front.						
					} else {
						if(bits[i].length() > 1) {	// one or more single-character flags here.
							for(int f = 1; f < bits[i].length(); f++) {
								lstFlags.add(Character.toString(bits[i].charAt(f)));
							}
						}							// else do nothing
					}
				} else {
					lstArgs.add(bits[i]);
				}
			}
		}
		
		if(currentlyQuoted) {
			//Quote mismatch error. Maybe we should just... ignore it?
		}
	
		/* System.out.println("Command: " + command);
		for(String f: lstFlags) {
			System.out.println("Flag: " + f);
		}	
		for(String a: lstArgs) {
			System.out.println("Argument: " + a);
		} */
		
		// CommandDispatcher.dispatch(command, lstFlags.toArray(new String[lstFlags.size()]), lstArgs.toArray(new String[lstArgs.size()]));
		// New design decidion: pass everything as a list, NOT as an array, since we're already searching for values in arrays pretty often
		CommandDispatcher.dispatch(command, lstFlags, lstArgs);
		
	}

}
