package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Boing extends JPanel implements KeyListener{
	
	public boolean gameRunning = true;
	public long lastFpsTime;
	public int fps;
	
	Graphics g;
	
	Sound sound = new Sound();
	
	public int x = 0;
	public double y = 0;
	public int xVel = 5;
	public double yVel = 0;
	public double gravity = 1.5;
	
	public Boing() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 500));
	}
	
	@Override
	protected void paintComponent(Graphics draw) {
		g = draw;
		super.paintComponent(g);
		
		g.setColor(Color.YELLOW);
		g.fillOval(x, (int)y, 20, 20);
		//g.fillOval(100, 100, 100, 100);
		
		x += xVel;
		y -= yVel;
		if (x >= 580 - Math.abs(xVel)) {
			xVel = -Math.abs(xVel);
			sound.playSound(Sound.PING);
		}
		if (x <= 0 + Math.abs(xVel)) {
			xVel = Math.abs(xVel);
			sound.playSound(Sound.PING);
		}
		if (y >= 480 - Math.abs(yVel)) {
			yVel = Math.abs(yVel) + gravity;
			sound.playSound(Sound.PING);
		}
		yVel-= gravity;
		//System.out.println("x: " + x + " y: " + y);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(ABORT);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	public void gameLoop()
	{
	   long lastLoopTime = System.nanoTime();
	   final int TARGET_FPS = 20;
	   final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;   
	   
	   while (gameRunning)
	   {
	      long now = System.nanoTime();
	      long updateLength = now - lastLoopTime;
	      lastLoopTime = now;
	      //double delta = updateLength / ((double)OPTIMAL_TIME);
	      
	      lastFpsTime += updateLength;
	      fps++;
	      
	      if (lastFpsTime >= 1000000000)
	      {
	         System.out.println("(FPS: "+fps+")");
	         lastFpsTime = 0;
	         fps = 0;
	      }
	      
	      update();
	      
	      try{Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000);}
	      catch (IllegalArgumentException|InterruptedException e) {}
	   }
	}
	
	void update() {
		repaint();
	}
}
