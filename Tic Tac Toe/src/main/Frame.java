package main;

import javax.swing.*;

public class Frame extends JFrame {
	
	public Frame() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new Panel());
		pack();
	}
	
}
