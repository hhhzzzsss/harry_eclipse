package structure;
public class CellPosition {
	protected int x;
	protected int y;
	
	public CellPosition(int tx, int ty) {
		x = tx;
		y = ty;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setPos(int nx, int ny) {
		x = nx;
		y = ny;
	}
	
	public void changePos(int nx, int ny) {
		x += nx;
		y += ny;
	}
	
}
