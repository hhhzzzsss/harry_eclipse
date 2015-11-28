package structure;

public class CellMatrix {
	
	protected Cell[] matrix;
	
	protected int rows;
	protected int columns;
	
	public CellMatrix(int sizeX, int sizeY) {
		
		rows = sizeY;
		columns = sizeX;
		
		matrix = new Cell[rows * columns];
		
		for (int r = 0; r < rows; r ++) {
			for (int c = 0; c < columns; c++) {
				matrix[r*columns + c] = new Cell(r, c);
			}
		}
		
	}
	
	public Cell getCell(CellPosition p) {
		return matrix[p.getY()*columns + p.getX()];
	}
	
	public boolean posIsInMatrix(CellPosition p) {
		return p.getX() < columns && p.getY() < rows && p.getX() >= 0 && p.getY() >= 0;
	}
	
	public void reloadMatrix() {
		for (int r = 0; r < rows; r ++) {
			for (int c = 0; c < columns; c++) {
				matrix[r*columns + c] = new Cell(r, c);
			}
		}
	}
	
}
