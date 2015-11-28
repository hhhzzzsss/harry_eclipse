package main;

import javax.swing.JFrame;
import mazeGenerator.ClearPath;
import structure.CellMatrix;

public class Main {
	
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = 600;
	public static final int CELLS_HORIZONTAL = 60;
	public static final int CELLS_VERTICAL = CELLS_HORIZONTAL;
	public static final int CELL_WIDTH = SCREEN_WIDTH / CELLS_HORIZONTAL;
	public static final int CELL_HEIGHT = SCREEN_HEIGHT / CELLS_VERTICAL;
	public static int fxMode = 0;
	public static boolean trail = true;
	public static boolean sound = true;
	public static boolean autoSolve = true;
	public static boolean autoPlay = false;
	public static GraphicsPanel p;		
	
	public static void main(String args[]) {

		CellMatrix m = new CellMatrix(CELLS_HORIZONTAL, CELLS_VERTICAL);
		ClearPath.clear(m);
		p = new GraphicsPanel(m);
		
		JFrame frame = new JFrame("Maze Generator");
		frame.add(p);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		if (autoPlay) {
			p.gameLoop();
		}
		
	}
	
	public static void refresh() {
		p.repaint();
	}
}
