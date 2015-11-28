package myfirstprocessing;

import java.util.*;



public class Ellipses {
	public int height;
	public int width;
	public double x;
	public double y;
	public double vx;
	public double vy;
	public double direction;
	public double speed;
	public double mass;
	public int ColorR;
	public int ColorG;
	public int ColorB;
	
	public Ellipses(){
		Random rand=new Random();
		width = rand.nextInt(100) + 80;
		height = width;
		mass = Math.pow(width, 2) / 1000;
		x = rand.nextInt(MyFirstProcessing.ScrWidth - width + (width/2));
		y = rand.nextInt(MyFirstProcessing.ScrHeight - height + (height/2));
		direction = rand.nextInt(360);
		speed = rand.nextDouble()*20+2;
		vx = Math.cos(direction*2*Math.PI/360)*speed;
		vy = Math.sin(direction*2*Math.PI/360)*speed;
		ColorR = rand.nextInt(256);
		ColorG = rand.nextInt(256);
		ColorG = rand.nextInt(256);
		
	}
	public Ellipses(int x,int y,double direction,double speed,int mass){
		Random rand=new Random();
		this.width = mass;
		this.height = width;
		this.mass = width;
		this.x=x;
		this.y=y;
		this.direction = direction;
		this.speed=speed;
		this.vx = Math.cos(direction*2*Math.PI/360)*speed;
		this.vy = Math.sin(direction*2*Math.PI/360)*speed;
	}

	public void update(){
		vx = Math.cos(direction*2*Math.PI/360)*speed;
		vy = Math.sin(direction*2*Math.PI/360)*speed;
		
		if (x <= 0 + (width/2) && vx < 0) {
			while (direction >= 360) {
				direction -= 360;
			}
			direction += 2*(270-direction);
		}
		
		if (x >= MyFirstProcessing.ScrWidth - width + (width/2) && vx > 0) {
			while (direction >= 360) {
				direction -= 360;
			}
			direction += 2*(90-direction);
		}
		
		if (y <= 0 + (height/2) && vy < 0) {
			while (direction >= 360) {
				direction -= 360;
			}
			direction += 2*(180-direction);
		}
		
		if (y >= MyFirstProcessing.ScrHeight - height + (height/2) && vy > 0) {
			while (direction >= 360) {
				direction -= 360;
			}
			direction += 2*(0-direction);
		}
		
		while (direction >= 360) {
			direction -= 360;
		}
		
		x += vx;
		y += vy;
	}
	
	public boolean ballCollision(Ellipses e){
		double ndx1 = x + vx;
		double ndy1 = y + vy;
		double ndx2 = e.x + e.vx;
		double ndy2 = e.y + e.vy;
		double d = Math.sqrt(Math.pow(Math.abs(x-e.x), 2) + Math.pow(Math.abs(y-e.y), 2));
		double nd = Math.sqrt(Math.pow(Math.abs(ndx1-ndx2), 2) + Math.pow(Math.abs(ndy1-ndy2), 2));
		
		double t=width/2+e.width/2;
		
		if (d<=t && nd<d){
			return true;
		}
		else {
			return false;
		}
	}
	public void ballBounce(Ellipses e) {
		
		Collision.collide(this, e);
	}
	
	public void ballBounce2(Ellipses e) {
		Collision c = new Collision();
		c.calc((int) x, (int) e.x, (int) y, (int) e.y, (int) mass, (int) e.mass, (int) vx, (int) e.vx, (int) vy, (int) e.vy);
		direction = c.v1f.heading()/(2*Math.PI)*360;
		speed = c.v1f.mag()/mass;
		e.direction = c.v2f.heading()/(2*Math.PI)*360;
		e.speed = c.v2f.mag()/e.mass;
	}
}
