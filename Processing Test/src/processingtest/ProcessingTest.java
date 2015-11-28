package processingtest;

import java.util.ArrayList;

import javax.swing.JFrame;

import processing.core.PApplet;

public class ProcessingTest extends PApplet {
	
	float tcolor = 0F;
	float color = 0;
	ArrayList<Circle> CircleArray = new ArrayList<Circle>();
	//Circle c1 = new Circle(mouseX, mouseY, 50, 0.05, new boolean[]{true, true, true});
	
	public void settings() {
		size(500, 500);
	}
	
	public void setup() {
		surface.setResizable(true);
		CircleArray.add(new Circle(mouseX, mouseY, 50, 1.0, new boolean[]{true, true, true}));
		CircleArray.add(new Circle(mouseX, mouseY, 45, 0.1, new boolean[]{true, false, true}));
		CircleArray.add(new Circle(mouseX, mouseY, 40, 0.1, new boolean[]{true, true, false}));
		CircleArray.add(new Circle(mouseX, mouseY, 35, 0.1, new boolean[]{false, false, true}));
		CircleArray.add(new Circle(mouseX, mouseY, 30, 0.1, new boolean[]{true, false, false}));
		CircleArray.add(new Circle(mouseX, mouseY, 25, 0.1, new boolean[]{false, true, false}));
		CircleArray.add(new Circle(mouseX, mouseY, 20, 0.05, new boolean[]{false, false, false}));
		
	}

	public void draw() {
		tcolor = tcolor + 1;
		color = sin(tcolor/360) * 255;
		background(100,255,255);
		//fill(color,color,color);
		noStroke();
		
		for (int i = CircleArray.size() - 1; i > 0; i--) {
			Circle c = CircleArray.get(i);
			Circle tc = CircleArray.get(i-1);
			if (c.colorType[0]) {c.color[0] = color;} else {c.color[0] = 255;}
			if (c.colorType[1]) {c.color[1] = color;} else {c.color[1] = 255;}
			if (c.colorType[2]) {c.color[2] = color;} else {c.color[2] = 255;}
			fill(c.color[0], c.color[1], c.color[2]);
			ellipse(c.x,c.y, c.size, c.size);
			c.updateCircle(tc.x, tc.y);
		}
		
		Circle c = CircleArray.get(0);
		if (c.colorType[0] = true) {c.color[0] = color;} else {c.color[0] = 255;}
		if (c.colorType[1] = true) {c.color[1] = color;} else {c.color[1] = 255;}
		if (c.colorType[2] = true) {c.color[2] = color;} else {c.color[2] = 255;}
		fill(c.color[0], c.color[1], c.color[2]);
		ellipse(c.x,c.y, c.size, c.size);
		c.updateCircle(mouseX, mouseY);
		
		//ellipse(c1.x, c1.y, c1.size, c1.size);
		//c1.updateCircle(mouseX, mouseY);
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { processingtest.ProcessingTest.class.getName() });
	}
}
