package main;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{
	public Panel(Color color) {
		setPreferredSize(new Dimension(600, 500));
		setBackground(color);
	}
}
