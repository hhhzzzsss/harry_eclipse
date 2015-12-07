package main;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Panel extends JPanel {
	
	Random rand = new Random();
	Point[][] points = new Point[Main.levels][];
	
	public Panel() {
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		generateTree();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(new Color(20, 10, 10));
		
	}
	
	void generateTree() {
		
		int a = 0;
		int b = 1;
		int c = 1;
		
		for (int i = 0; i < Main.levels; i++) {
			
			points[i] = new Point[b];
			
			for (int i2 = 0; i2 < b; i2++) {
				points[i][i2] = new Point();
			}
			
			c = a + b;
			b = a;
			b = c;
			
		}
		
		a = 0;
		b = 1;
		c = 1;
		
		points[0][0].state = 1;
		
		for (int i = 0; i < Main.levels-1; i++) {
			
			int uIndex = 0;
			
			for (int i2 = 0; i2 < b; i2++) {
				
				Point p = points[i][i2];
				
				if (p.size == 1) {
					
					points[i+1][uIndex].state = 2;
					points[i+1][uIndex].size = p.size - (rand.nextInt(30)+1);
					uIndex++;
					
				}
				else if (p.size == 2) {
					if (rand.nextBoolean()) {
						points[i+1][uIndex].state = 1;
						points[i+1][uIndex].size = p.size - (rand.nextInt(30)+1);
						uIndex++;
						points[i+1][uIndex].state = 2;
						points[i+1][uIndex].size = p.size - (rand.nextInt(30)+1);
						uIndex++;
					}
					else {
						points[i+1][uIndex].state = 2;
						points[i+1][uIndex].size = p.size - (rand.nextInt(30)+1);
						uIndex++;
						points[i+1][uIndex].state = 1;
						points[i+1][uIndex].size = p.size - (rand.nextInt(30)+1);
						uIndex++;
					}
				}
				
			}
			
			c = a + b;
			b = a;
			b = c;
			
		}
		
	}
	
}
