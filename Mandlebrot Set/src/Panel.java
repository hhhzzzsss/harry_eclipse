
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Panel extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMandlebrot(g);
		
	}
	
	private int calcIterNum(double x, double y, int iterations) {
		int n = 0;
		double rPart = 0;
		double iPart = 0;
		double trPart;
		double tiPart;
		
		while (n < iterations) {
			trPart = rPart;
			tiPart = iPart;
			rPart = (trPart*trPart) - (tiPart*tiPart) + x;
			iPart = 2*trPart*tiPart + y;
			
			if (Math.sqrt((rPart*rPart) + (iPart*iPart)) > 2) {
				break;
			}
			
			n++;
		}
		
		return n;
	}
	
	private void drawMandlebrot(Graphics G) {
		int in;
		for (int ix = 0; ix <= this.getWidth(); ix++) {
			for (int iy = 0; iy <= this.getHeight(); iy++) {
				in = calcIterNum(ix*Main.selectionArea[2]/this.getWidth() + Main.selectionArea[0], iy*Main.selectionArea[2]/this.getHeight() + Main.selectionArea[1], Main.iter);
				if (in == Main.iter) {
					G.setColor(Color.BLACK);
				}
				else {
					G.setColor(Color.getHSBColor(in/30.0f, 1.0f, 1.0f));
				}
				G.drawLine(ix, iy, ix, iy);
			}
		}
	}
	
	public void reload() {
		this.repaint();
	}
	
}
