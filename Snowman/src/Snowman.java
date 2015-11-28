import java.awt.*;
import javax.swing.*;

public class Snowman {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SnowmanFrame frame = new SnowmanFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
	
	class SnowmanFrame extends JFrame {
		public SnowmanFrame () {
			this.setTitle("Mandelbrot Snowman");
			setPreferredSize(new Dimension(315, 260));
			SnowmanPanel SPanel = new SnowmanPanel();
			SPanel.add(new JLabel("Mandelbrot Hats are Great!"));
			SPanel.setLayout(new BoxLayout(SPanel, BoxLayout.LINE_AXIS));
			SPanel.setAlignmentY(BOTTOM_ALIGNMENT);
			SPanel.setAlignmentX(LEFT_ALIGNMENT);
			add(SPanel);
			//add(new Panel());
			pack();
		}
		
	}
	
	class SnowmanPanel extends JPanel {
		private static final int DEFAULT_WIDTH = 300;
		private static final int DEFAULT_HEIGHT = 200;
		private static final int MID = 150;
		private static final int TOP = 50;
		public void paintComponent(Graphics page) {
		      
		      super.setBackground(Color.cyan);
			  super.setPreferredSize(getPreferedSize());
			  super.paintComponent(page);

		      page.setColor(Color.blue);
		      page.fillRect(0, 175, 300, 50);  // ground

		      page.setColor(Color.yellow);
		      page.fillOval(-40, -40, 80, 80);  // sun

		      page.setColor(Color.white);
		      page.fillOval(MID-20, TOP, 40, 40);      // head
		      page.fillOval(MID-35, TOP+35, 70, 50);   // upper torso
		      page.fillOval(MID-50, TOP+80, 100, 60);  // lower torso

		      page.setColor(Color.black);
		      page.fillOval(MID-10, TOP+10, 5, 5);   // left eye
		      page.fillOval(MID+5, TOP+10, 5, 5);    // right eye

		      page.drawArc(MID-10, TOP+20, 20, 10, 190, 160);   // smile

		      page.drawLine(MID-25, TOP+60, MID-50, TOP+40);  // left arm
		      page.drawLine(MID+25, TOP+60, MID+55, TOP+60);  // right arm

		      //page.drawLine(MID-20, TOP+5, MID+20, TOP+5);  // brim of hat
		      //page.fillRect(MID-15, TOP-20, 30, 25);        // top of hat
		      
		      drawMandlebrot(page); //Mandelbrot Hat
		      
		      han(250, 10, page); //han
		}
		public Dimension getPreferedSize() {
			return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
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
			for (int ix = -10; ix <= 90; ix++) {
				for (int iy = 100; iy <= 200; iy++) {
					in = calcIterNum((ix+10)*4.0/100 - 2, (iy-100)*4.0/100 - 2, 100);
					if (in == 100) {
						G.setColor(Color.BLACK);
						G.drawLine(iy, ix, iy, ix);
					}
					else if (in > 2) {
						G.setColor(Color.getHSBColor(in/30.0f, 1.0f, 1.0f));
						G.drawLine(iy, ix, iy, ix);
					}
					//G.drawLine(iy, ix, iy, ix);
				}
			}
		}
		
		void han(int x, int y, Graphics g) {
			g.setColor(Color.BLACK);
			g.drawLine(x, y, x+3, y+3);
			g.drawLine(x, y+5, x+3, y+8);
			g.drawLine(x+3, y+10, x, y+13);
			g.drawLine(x+5, y, x+10, y);
			g.drawLine(x+5, y, x+10, y+13);
			g.drawLine(x+10, y, x+5, y+13);
		}
		
		public void reload() {
			this.repaint();
		}
	}
	
	/*class Panel extends JPanel {
		
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
			for (int ix = 0; ix <= 130; ix++) {
				for (int iy = 100; iy <= 200; iy++) {
					in = calcIterNum((ix-30)*4.0/100 - 2, (iy-100)*4.0/100 - 2, 100);
					if (in == 100) {
						G.setColor(Color.BLACK);
						G.drawLine(iy, ix, iy, ix);
					}
					else if (in > 0) {
						G.setColor(Color.getHSBColor(in/30.0f, 1.0f, 1.0f));
						G.drawLine(iy, ix, iy, ix);
					}
					//G.drawLine(iy, ix, iy, ix);
				}
			}
		}
		
		public void reload() {
			this.repaint();
		}
		
	}*/
