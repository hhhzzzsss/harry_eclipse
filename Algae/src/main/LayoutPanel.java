package main;

import javax.swing.*;
import java.awt.*;

public class LayoutPanel extends JPanel {
	
	Panel p = new Panel();
	
	public LayoutPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(p);
	}
}
