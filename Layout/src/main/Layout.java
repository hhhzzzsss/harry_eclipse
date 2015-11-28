package main;

import javax.swing.*;
import java.awt.*;

public class Layout extends JTabbedPane{
	
	Boing b = new Boing();
	
	public Layout() {
		
		addTab("Blank", new Panel(Color.DARK_GRAY));
		addTab("Blank2", new Panel(Color.WHITE));
		addTab("Offset", new Offset(Color.CYAN));
		addTab("Boing", b);
		addTab("Snowman", new SnowmanPanel());
		
	}
	
	public void startLoop() {
		b.gameLoop();
	}
}
