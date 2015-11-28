package mazeGenerator;

import structure.*;

//import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ClearPath {
	
	public static final int[][] PERMUTATIONS = new int[][]{
		{0, 1, 2, 3}, {0, 1, 3, 2}, {0, 2, 1, 3}, {0, 2, 3, 1}, {0, 3, 1, 2}, {0, 3, 2, 1},
		{1, 0, 2, 3}, {1, 0, 3, 2}, {1, 2, 0, 3}, {1, 2, 3, 0}, {1, 3, 0, 2}, {1, 3, 2, 0},
		{2, 0, 1, 3}, {2, 0, 3, 1}, {2, 1, 0, 3}, {2, 1, 3, 0}, {2, 3, 0, 1}, {2, 3, 1, 0},
		{3, 0, 1, 2}, {3, 0, 2, 1}, {3, 1, 0, 2}, {3, 1, 2, 0}, {3, 2, 0, 1}, {3, 2, 1, 0}};
	
	public static void clear(CellMatrix m) {
		recursiveClearer(m, new CellPosition(0, 0));
	}
	
	private static void recursiveClearer(CellMatrix m, CellPosition p) {
		
		m.getCell(p).setState(Cell.IN_STACK_CELL);
		
		int[] ia = PERMUTATIONS[ThreadLocalRandom.current().nextInt(0, 24)];
		
		CellPosition down = new CellPosition(p.getX(), p.getY());
		down.changePos(0, 1);
		
		CellPosition up = new CellPosition(p.getX(), p.getY());
		up.changePos(0, -1);
		
		CellPosition left = new CellPosition(p.getX(), p.getY());
		left.changePos(-1, 0);
		
		CellPosition right = new CellPosition(p.getX(), p.getY());
		right.changePos(1, 0);
		
		for (int i = 0; i < 4; i ++) {
			if (ia[i] == 0) {
				if (m.posIsInMatrix(down)) {
					if (m.getCell(down).getState() == Cell.UNVISITED_CELL) {
						m.getCell(p).walls[1] = false;
						m.getCell(down).walls[0] = false;
						recursiveClearer(m, down);
					}
				}
			}
			else if (ia[i] == 1) {
				if (m.posIsInMatrix(up)) {
					if (m.getCell(up).getState() == Cell.UNVISITED_CELL) {
						m.getCell(p).walls[0] = false;
						m.getCell(up).walls[1] = false;
						recursiveClearer(m, up);
					}
				}
			}
			else if (ia[i] == 2) {
				if (m.posIsInMatrix(left)) {
					if (m.getCell(left).getState() == Cell.UNVISITED_CELL) {
						m.getCell(p).walls[2] = false;
						m.getCell(left).walls[3] = false;
						recursiveClearer(m, left);
					}
				}
			}
			else {
				if (m.posIsInMatrix(right)) {
					if (m.getCell(right).getState() == Cell.UNVISITED_CELL) {
						m.getCell(p).walls[3] = false;
						m.getCell(right).walls[2] = false;
						recursiveClearer(m, right);
					}
				}
			}
		}
		
		if (m.posIsInMatrix(down)) {
			if (m.getCell(down).getState() == Cell.UNVISITED_CELL) {
				recursiveClearer(m, down);
			}
		}
		m.getCell(p).setState(Cell.VISITED_CELL);
	}
	
}
