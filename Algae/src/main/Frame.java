package main;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
	
	LayoutPanel l = new LayoutPanel();
	
	public Frame() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(l);
		pack();
		setVisible(true);
		
	}
	
}
