package main;

import javax.swing.JPanel;

import character.Player;
import mazeGenerator.ClearPath;
import mazeGenerator.PathFinder;
import structure.*;

import java.awt.event.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GraphicsPanel extends JPanel implements KeyListener{
	
	private static final long serialVersionUID = 6791888953399961746L;
	
	CellMatrix m;
	
	Player p;
	Player t;
	
	Graphics g;
	Graphics2D g2d;
	
	public boolean gameRunning = true;
	public int fps = 0;
	public int lastFpsTime;
	
	public static boolean up = false;
	public static boolean down = false;
	public static boolean left = false;
	public static boolean right = false;
	
	public GraphicsPanel(CellMatrix tm) {
		
		m = tm;
		
		p = new Player(m);
		
		t = new Player(m);
		t.randomPos();
		
		m.getCell(new CellPosition(p.getX(), p.getY())).visited = true;
		
		if (Main.autoSolve)
			PathFinder.findPath(m, t);
		 
		addKeyListener(this);
		setFocusable(true);
		
		this.setPreferredSize(new Dimension(Main.SCREEN_WIDTH + 1, Main.SCREEN_HEIGHT + 1));
		this.setBackground(Color.BLACK);
		
	}
	
	@Override
	protected void paintComponent(Graphics tg) {
		g = tg;
		
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		
		drawMatrix();
		drawPlayer();
		drawTarget();
		if (Main.fxMode == 1) {
			specialFx1();
		}
		else if (Main.fxMode == 2) {
			specialFx2();
		}
		else if (Main.fxMode == 3) {
			specialFx3();
		}
		
	}
	
	private void drawMatrix() {
		
		for (int w = 0; w < Main.CELLS_HORIZONTAL; w++) {
			for (int h = 0; h < Main.CELLS_VERTICAL; h++) {
				Cell c = m.getCell(new CellPosition(w, h));
				
				g.setColor(Color.GRAY);
				
				if (c.visited) {
					g.fillRect(w*Main.CELL_WIDTH, h*Main.CELL_HEIGHT, Main.CELL_HEIGHT, Main.CELL_HEIGHT);
				}
				
				g.setColor(Color.GREEN);
				
				if (c.walls[0])
					g.drawLine(w*Main.CELL_WIDTH, h*Main.CELL_HEIGHT, w*Main.CELL_WIDTH + Main.CELL_WIDTH, h*Main.CELL_HEIGHT);
				if (c.walls[1])
					g.drawLine(w*Main.CELL_WIDTH, h*Main.CELL_HEIGHT + Main.CELL_HEIGHT, w*Main.CELL_WIDTH + Main.CELL_WIDTH, h*Main.CELL_HEIGHT + Main.CELL_WIDTH);
				if (c.walls[2])
					g.drawLine(w*Main.CELL_WIDTH, h*Main.CELL_HEIGHT, w*Main.CELL_WIDTH, h*Main.CELL_HEIGHT + Main.CELL_HEIGHT);
				if (c.walls[3])
					g.drawLine(w*Main.CELL_WIDTH + Main.CELL_WIDTH, h*Main.CELL_HEIGHT, w*Main.CELL_WIDTH + Main.CELL_WIDTH, h*Main.CELL_HEIGHT + Main.CELL_HEIGHT);
			}
		}
		
	}
	
	private void drawPlayer() {
		
		//g.setColor(Color.CYAN);
		
		g.setColor(Color.getHSBColor(m.getCell(p.getPos()).getDistance()/90.0f-0.5f, 1.0f, 1.0f));
		
		g.fillOval(p.getX()*Main.CELL_WIDTH, p.getY()*Main.CELL_HEIGHT, Main.CELL_WIDTH, Main.CELL_HEIGHT);
		
	}
	
	private void specialFx1() {
		
		if (m.getCell(p.getPos()).getDistance() <= 12 && m.getCell(p.getPos()).getDistance() != 0) {
			setBackground(Color.getHSBColor(0.0f, 0.0f, (13.0f-m.getCell(p.getPos()).getDistance())/12.0f));
		}
		else {
			setBackground(Color.BLACK);
		}
		
	}
	
	private void specialFx2() {
		
		if (m.getCell(p.getPos()).getDistance() <= 12 && m.getCell(p.getPos()).getDistance() != 0) {
			g2d.setColor(Color.WHITE);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f/m.getCell(p.getPos()).getDistance()));
			g2d.fillOval((int)(t.getX()-(14.0f-m.getCell(p.getPos()).getDistance())/2)*Main.CELL_WIDTH, (int)(t.getY()-(14.0f-m.getCell(p.getPos()).getDistance())/2)*Main.CELL_HEIGHT, (int)(Main.CELL_WIDTH*((14.0f-m.getCell(p.getPos()).getDistance())+1)), (int)(Main.CELL_HEIGHT*((14.0f-m.getCell(p.getPos()).getDistance())+1)));
		}
		
	}
	
	private void specialFx3() {
		if (m.getCell(p.getPos()).getDistance() <= 12 && m.getCell(p.getPos()).getDistance() != 0) {
			g2d.setColor(Color.WHITE);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f/m.getCell(p.getPos()).getDistance()));
			g2d.setStroke(new BasicStroke(13 - m.getCell(p.getPos()).getDistance(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.drawLine(p.getX()*Main.CELL_WIDTH+Main.CELL_WIDTH/2, p.getY()*Main.CELL_HEIGHT+Main.CELL_HEIGHT/2, t.getX()*Main.CELL_WIDTH+Main.CELL_WIDTH/2, t.getY()*Main.CELL_HEIGHT+Main.CELL_HEIGHT/2);
		}
	}
	
	private void drawTarget() {
		
		g.setColor(Color.RED);
		
		g.fillOval(t.getX()*Main.CELL_WIDTH + (Main.CELL_WIDTH/4), t.getY()*Main.CELL_HEIGHT + (Main.CELL_HEIGHT/4), Main.CELL_WIDTH/2, Main.CELL_HEIGHT/2);
		
	}
	
	private void regenerate() {
		m.reloadMatrix();
		ClearPath.clear(m);
		t.randomPos();
		m.getCell(new CellPosition(p.getX(), p.getY())).visited = true;
		if (Main.autoSolve)
			PathFinder.findPath(m, t);
	}
	
	public void moveUp() {
		p.moveUp();
	}
	
	public void moveDown() {
		p.moveDown();
	}
	
	public void moveLeft() {
		p.moveLeft();
	}
	
	public void moveRight() {
		p.moveRight();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
			moveUp();
			
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
			moveDown();
			
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
			moveLeft();
			
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
			moveRight();
			
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && Main.autoSolve) {
			p.autoMove();
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameRunning = false;
			System.exit(ABORT);
		}
		
		if (p.getX() == t.getX() && p.getY() == t.getY()) {
			regenerate();
		}
		m.getCell(new CellPosition(p.getX(), p.getY())).visited = true;
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
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
		if (p.getX() == t.getX() && p.getY() == t.getY()) {
			regenerate();
		}
		m.getCell(new CellPosition(p.getX(), p.getY())).visited = true;
		p.autoMove();
		repaint();
	}
	
}
