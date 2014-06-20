package net.syntaxblitz.TimTerm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(TimTerm.PORT);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				clientSocket.close();
				TimTerm.gotConnection();
			}
		} catch (IOException e) {
			// :(
			// TODO: when there's a better way to report errors, use it
			System.exit(-1);
		}
	}
}