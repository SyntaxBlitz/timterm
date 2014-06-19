package net.syntaxblitz.TimTerm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;

public class TimTerm {

	public static final int PORT = 49151;	// just before dynamic port range ;)
	
	public static final int SCREEN_WIDTH  = 1600;
	public static final int SCREEN_HEIGHT = 900;
	public static final int BAR_HEIGHT    = 20;
	
	private static final JDialog bar = new JDialog();
	private static final JTextField tf = new JTextField();
	private static final Font dsm = new Font("Droid Sans Mono", Font.PLAIN, 12);
	
	public static boolean showingBar = false;
	
	public static void main(String[] args) {
		// Initialise the magical bar and keep it in memory like a madman.
		// This uses more memory, but should be faster when the bar is actually called upon. I hope.
		bar.setSize(SCREEN_WIDTH, BAR_HEIGHT);
		
		bar.setAlwaysOnTop(true);
		bar.setUndecorated(true);		// really, a JWindow should be used if it shouldn't show up in taskbar and should be undecorated. But JWindows can't have editable components (?), so we do this.
		bar.setLocation(0, SCREEN_HEIGHT - BAR_HEIGHT);
		
		tf.setBackground(Color.BLACK);
		tf.setForeground(Color.WHITE);
		tf.setCaretColor(Color.WHITE);
		tf.setBorder(null);
		tf.setFont(dsm);
		tf.setSize(SCREEN_WIDTH - 50, BAR_HEIGHT);
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// enter key pressed.
				String textEntered = tf.getText();
				TextProcessor.processText(textEntered);		// because that's what it seems like a text processor should do.
				bar.setVisible(false);
				TimTerm.showingBar = false;
				tf.setText("");
			}
		});
		bar.add(tf);
		
		// Start the server thread
		ServerThread t = new ServerThread();
		t.start();
		//gotConnection();		// Used for debugging so we don't have to use the C# program every time
	}

	public static void gotConnection() {
		TimTerm.showingBar = !TimTerm.showingBar;
		bar.setVisible(TimTerm.showingBar);
		bar.requestFocus();
		tf.requestFocusInWindow();
		//TODO: force textfield to steal focus
	}
	
}
