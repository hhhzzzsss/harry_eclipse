import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Panel extends JPanel{
	final int SIZE_X = 600, SIZE_Y = 600;
	
	public Panel() {
		setBackground(Color.CYAN);
		setPreferredSize(new Dimension(600, 600));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 0, y = 0, diameter;
		float[] color = new float[3];
		Random generator = new Random();
		
		for (x = 0; x < SIZE_X/2; x+=5) {
			y = x;
			color[0] = generator.nextFloat();
			color[1] = 1;
			color[2] = 1;
			diameter = SIZE_X - 2*x;
			g.setColor(Color.getHSBColor(color[0], color[1], color[2]));
			g.fillOval(x, y, diameter, diameter);
		}
	}
}
