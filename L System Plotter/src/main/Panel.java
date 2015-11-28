package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.util.Stack;
import java.awt.Dimension;

public class Panel extends JPanel{
	
	private StringBuilder s = new StringBuilder(Main.AXIOM);
	
	private Stack<Double> xS = new Stack<Double>();
	private Stack<Double> yS = new Stack<Double>();
	private Stack<Integer> dirS = new Stack<Integer>();
	private double x = Main.START_X;
	private double y = Main.START_Y;
	private int dir = 0;
	
	private static final long serialVersionUID = -6476304087296327285L;
	
	public Panel() {
		setPreferredSize(new Dimension(Main.SCREEN_WIDTH, Main.SCREEN_WIDTH));
		setBackground(Color.WHITE);
		expand();
		//System.out.println(s);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawOval(0, 0, 10, 10);
		drawLSystem(g);
	}
	
	private void drawLSystem(Graphics g) {
		x = Main.START_X;
		y = Main.START_Y;
		dir = 2;
		g.setColor(Color.GREEN);
		for (int i = 0; i < s.length(); i ++) {
			if (s.charAt(i) == 'F') {
				g.drawLine((int)x, (int)y, (int)(x + Math.cos(dir*Main.ANGLE_RADIANS)*Main.STEP), (int)(y - Math.sin(dir*Main.ANGLE_RADIANS)*Main.STEP));
				x += Math.cos(dir*Main.ANGLE_RADIANS)*Main.STEP;
				y -= Math.sin(dir*Main.ANGLE_RADIANS)*Main.STEP;
				//System.out.println("X: " + x + "\tY: " + y);
			}
			else if (s.charAt(i) == '+') {
				dir++;
			}
			else if (s.charAt(i) == '-') {
				dir--;
			}
			else if (s.charAt(i) == '[') {
				xS.push(x);
				yS.push(y);
				dirS.push(dir);
			}
			else if (s.charAt(i) == ']') {
				x = xS.pop();
				y = yS.pop();
				dir = dirS.pop();
			}
		}
	}
	
	void expand2() {
		long tt = System.currentTimeMillis();
		s = new StringBuilder(Main.AXIOM);
		for (int r = 0; r < Main.RECURSION; r++) {
			//s = s.replaceAll("F", "f");
			//s = s.replaceAll("X", "x");
			//s = s.replaceAll("Y", "y");
			//s = s.replaceAll("f", Main.F_RULE);
			//s = s.replaceAll("x", Main.X_RULE);
			//s = s.replaceAll("y", Main.Y_RULE);
			replaceAllChars(s, "F", "f");
			replaceAllChars(s, "X", "x");
			replaceAllChars(s, "Y", "y");
			replaceAll(s, "f", Main.F_RULE);
			replaceAll(s, "x", Main.X_RULE);
			replaceAll(s, "y", Main.Y_RULE);
		}
		System.out.println(System.currentTimeMillis()-tt);
	}
	
	void expand() {
		long tt = System.currentTimeMillis();
		s = new StringBuilder(Main.AXIOM);
		for(int r=0; r<Main.RECURSION; r++){
			StringBuilder t = new StringBuilder();
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)=='F'){
					t.append(Main.F_RULE);
				}
				else if(s.charAt(i)=='X'){
					t.append(Main.X_RULE);
				}
				else if(s.charAt(i)=='Y'){
					t.append(Main.Y_RULE);
				}
				else{
					t.append(s.charAt(i));
				}
			}
			s=t;
		}
		System.out.println(System.currentTimeMillis()-tt);
	}
	
	private void replaceAll(StringBuilder b, String f, String t) {
		int index = b.indexOf(f);
		while (index != -1) {
			b.replace(index, index + 1, t);
			index += t.length();
			index = b.indexOf(f, index);
		}
	}
	
	private void replaceAllChars(StringBuilder b, String f, String t) { //makes a total of a 24 millisecond difference when doing a fractal plant on 8 iterations 
		int index = b.indexOf(f);
		while (index != -1) {
			b.replace(index, index + 1, t);
			index = b.indexOf(f, index + 1);
		}
	}
}
