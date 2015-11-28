package character;

import java.util.concurrent.ThreadLocalRandom;

import main.Main;
import main.Sound;
import structure.CellMatrix;
import structure.CellPosition;

public class Player {
	private int x;
	private int y;
	private CellMatrix m;
	Sound sound = new Sound();
	
	public Player(CellMatrix tm) {
		x = 0;
		y = 0;
		m = tm;
	}
	
	public Player(CellMatrix tm, int tx, int ty) {
		x = tx;
		y = ty;
		m = tm;
	}
	
	public void moveUp() {
		if (EdgeDetect.upOpen(m, x, y)) {
			y--;
			sound.playSound(Sound.PING);
		}
		else
			sound.playSound(Sound.BING);
	}
	
	public void moveDown() {
		if (EdgeDetect.downOpen(m, x, y)) {
			y++;
			sound.playSound(Sound.PING);
		}
		else
			sound.playSound(Sound.BING);
	}
	
	public void moveLeft() {
		if (EdgeDetect.leftOpen(m, x, y)) {
			x--;
			sound.playSound(Sound.PING);
		}
		else
			sound.playSound(Sound.BING);
	}
	
	public void moveRight() {
		if (EdgeDetect.rightOpen(m, x, y)) {
			x++;
			sound.playSound(Sound.PING);
		}
		else
			sound.playSound(Sound.BING);
	}
	
	public void autoMove() {
		CellPosition bestMove = new CellPosition(x, y);
		int bestDist = Main.CELLS_HORIZONTAL*Main.CELLS_VERTICAL;
		CellPosition up = new CellPosition(x, y-1);
		CellPosition down = new CellPosition(x, y+1);
		CellPosition left = new CellPosition(x-1, y);
		CellPosition right = new CellPosition(x+1, y);
		if (EdgeDetect.upOpen(m, x, y) && m.getCell(up).getDistance() < bestDist) {
			bestMove = up;
			bestDist = m.getCell(up).getDistance();
			
		}
		if (EdgeDetect.downOpen(m, x, y) && m.getCell(down).getDistance() < bestDist) {
			bestMove = down;
			bestDist = m.getCell(down).getDistance();
			
		}
		if (EdgeDetect.leftOpen(m, x, y) && m.getCell(left).getDistance() < bestDist) {
			bestMove = left;
			bestDist = m.getCell(left).getDistance();
			
		}
		if (EdgeDetect.rightOpen(m, x, y) && m.getCell(right).getDistance() < bestDist) {
			bestMove = right;
			bestDist = m.getCell(right).getDistance();
			
		}
		
		//System.out.println(m.getCell(up).getDistance());
		//System.out.println(m.getCell(down).getDistance());
		//System.out.println(m.getCell(left).getDistance());
		//System.out.println(m.getCell(right).getDistance());
		
		setPos(bestMove.getX(), bestMove.getY());
		
		sound.playSound(Sound.PING);
		
	}
	

	public void setPos(int tx, int ty) {
		x = tx;
		y = ty;
	}
	
	public void changePos(int tx, int ty) {
		x += tx;
		y += ty;
	}
	
	public void randomPos() {
		x = ThreadLocalRandom.current().nextInt(0, Main.CELLS_HORIZONTAL);
		y = ThreadLocalRandom.current().nextInt(0, Main.CELLS_VERTICAL);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public CellPosition getPos() {
		return new CellPosition(x, y);
	}
}
