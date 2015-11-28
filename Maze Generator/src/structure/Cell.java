package structure;

public class Cell {
	
	public static final int UNVISITED_CELL = 0;
	public static final int VISITED_CELL = -1;
	public static final int IN_STACK_CELL = 1;
	
	public static final int TOP = 0;
	public static final int BOTTOM = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public boolean[] walls = new boolean[]{true, true, true, true};
	
	protected int state;
	public int distance;
	public boolean visited;
	
	private CellPosition pos;
	
	public Cell(int x, int y) {
		pos = new CellPosition(x, y);
		state = 0;
		distance = -1;
		visited = false;
	}
	
	public Cell(CellPosition c) {
		pos = c;
		state = 0;
		distance = -1;
		visited = false;
	}
	
	CellPosition getPos() {
		return pos;
	}
	
	public boolean[] getWalls() {
		return walls;
	}
	
	public boolean[] setWalls(boolean top, boolean bottom, boolean left, boolean right) {
		
		walls = new boolean[]{top, bottom, left, right};
		return walls;
		
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int s) {
		state = s;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int i) {
		distance = i;
	}
	
}
