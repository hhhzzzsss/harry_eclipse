package character;

import structure.CellMatrix;
import structure.CellPosition;

public class EdgeDetect {
	
	public static boolean upOpen(CellMatrix m, int x, int y) {
		return !m.getCell(new CellPosition(x, y)).getWalls()[0];
	}
	
	public static boolean downOpen(CellMatrix m, int x, int y) {
		return !m.getCell(new CellPosition(x, y)).getWalls()[1];
	}
	
	public static boolean leftOpen(CellMatrix m, int x, int y) {
		return !m.getCell(new CellPosition(x, y)).getWalls()[2];
	}
	
	public static boolean rightOpen(CellMatrix m, int x, int y) {
		return !m.getCell(new CellPosition(x, y)).getWalls()[3];
	}
	
}
