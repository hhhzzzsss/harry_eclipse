package myfirstprocessing;

import java.util.*;

import processing.core.*;

import java.awt.event.*;

public class MyFirstProcessing extends PApplet {

	public static int ScrWidth = 1000;
	public static int ScrHeight = 800;
	long framenum = 0;
	ArrayList<Ellipses> ellipseArray = new ArrayList<Ellipses>();
	ArrayList<LineEffect> LineEffects = new ArrayList<LineEffect>();
	public static int EllipseNum = 12;

	public void setup() {
		size(ScrWidth, ScrHeight);
		frame.setResizable(true);
		// ellipseArray.add(new Ellipses(100,100,0,1,20));
		// ellipseArray.add(new Ellipses(400,100,180,1,50));
		for (int i = 0; i < EllipseNum; i++) {
			AddEllipse();
		}
	}

	public void draw() {
		framenum++;
		clear();
		ScrWidth = frame.getWidth();
		ScrHeight = frame.getHeight();
		background(255, 255, 255);
		int LineIndex = 0;
		for (int i = 0; i < LineEffects.size(); i++) {
			if (LineEffects.get(LineIndex).Fade > 0) {
				LineEffects.get(LineIndex).Fade -= 10;
				for (int i2 = 0; i2 < ellipseArray.size(); i2++) {
					Ellipses e = ellipseArray.get(i2);
					strokeWeight(LineEffects.get(LineIndex).Weight[i2]);
					stroke(LineEffects.get(LineIndex).ColorR, LineEffects.get(LineIndex).ColorG, LineEffects.get(LineIndex).ColorB, LineEffects.get(LineIndex).Fade);
					line((float)LineEffects.get(LineIndex).x, (float)LineEffects.get(LineIndex).y, (float)e.x, (float)e.y);
				}
			}
			else {
				LineEffects.remove(LineIndex);
			}
			LineIndex++;
		}
		stroke(0);
		strokeWeight(0);
		for (int i = 0; i < ellipseArray.size(); i++) {
			Ellipses current = ellipseArray.get(i);
			fill(current.ColorR, current.ColorG, current.ColorB, 100);
			ellipse((float) current.x, (float) current.y,
					(float) current.width, (float) current.height);
			current.update();
		}
		for (int i = 0; i < EllipseNum; i++) {
			for (int i2 = i + 1; i2 < EllipseNum; i2++) {
				Ellipses a = ellipseArray.get(i);
				Ellipses b = ellipseArray.get(i2);
				if (a.ballCollision(b)) {
					a.ballBounce(b);
				}
			}
		}
	}

	void AddEllipse() {
		ellipseArray.add(new Ellipses());
	}

	public void mousePressed() {
		float[] h = new float[EllipseNum];
		PVector d = new PVector();
		PVector v = new PVector();
		PVector nv = new PVector();
		if (mouseButton == LEFT) {
			for (int i = 0; i < ellipseArray.size(); i++) {
				Ellipses e = ellipseArray.get(i);
				d.set((float) ((e.x - mouseX)), (float) ((e.y - mouseY)));
				v.set((float) e.vx, (float) e.vy);
				if (1 / (d.mag() / 250) < 1) {
					d.setMag(1 / (d.mag() / 250) * 12);
				} else {
					d.setMag(12);
				}
				v.add(d);
				e.speed = v.mag();
				e.direction = v.heading() / Math.PI * 180;
				h[i] = d.mag() * (float)1.5;
			}
			LineEffects.add(new LineEffect(255, 0, 0, h, mouseX, mouseY));
		} else if (mouseButton == RIGHT) {
			for (int i = 0; i < ellipseArray.size(); i++) {
				Ellipses e = ellipseArray.get(i);
				d.set((float) ((e.x - mouseX)), (float) ((e.y - mouseY)));
				v.set((float) e.vx, (float) e.vy);
				if (-1 / (d.mag() / 250) > -1) {
					d.setMag(-1 / (d.mag() / 250) * 12);
				} else {
					d.setMag(-12);
				}
				v.add(d);
				e.speed = v.mag();
				e.direction = v.heading() / Math.PI * 180;
				h[i] = d.mag() * (float)1.5;
			}
			LineEffects.add(new LineEffect(0, 0, 255, h, mouseX, mouseY));
		} else if (mouseButton == CENTER) {
			for (int i = 0; i < ellipseArray.size(); i++) {
				Ellipses e = ellipseArray.get(i);
				e.speed = 0;
				h[i] = 12;
			}
			LineEffects.add(new LineEffect(0, 255, 0, h, mouseX, mouseY));
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] {myfirstprocessing.MyFirstProcessing.class.getName() });
	}
	/*
	 * public void MouseEffects() {
	 * 
	 * }
	 */
}
