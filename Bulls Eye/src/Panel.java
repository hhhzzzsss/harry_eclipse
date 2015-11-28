
import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel{
	private final int MAX_WIDTH = 300;
	private final int NUM_RINGS = 5;
	private final int RING_WIDTH = 25;
	
	public Panel() {
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(300, 300));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 0, y = 0, diameter = MAX_WIDTH;
		
		g.setColor(Color.WHITE);
		
		for (int i = 0; i < NUM_RINGS; i++) {
			if (g.getColor() == Color.BLACK) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.BLACK);
			}
			
			g.fillOval(x, y, diameter, diameter);
			
			diameter -= (2*RING_WIDTH);
			x += RING_WIDTH;
			y += RING_WIDTH;
		}
		g.setColor(Color.RED);
		g.fillOval(x, y, diameter, diameter);
	}
}
