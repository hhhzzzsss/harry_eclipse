package main;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LayoutPanel extends JPanel{

	private static final long serialVersionUID = -9123587929531895731L;
	
	public LayoutPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Main.panel);
		add(new SettingsPanel());
	}
	
}
