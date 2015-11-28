package mazeGenerator;

import character.Player;
import structure.*;

public class PathFinder {
	
	public static int dist = -1;
	
	public static void findPath(CellMatrix m, Player target) {
		recursivePathFinder(m, target.getPos());
	}
	
	private static void recursivePathFinder(CellMatrix m, CellPosition p) {
		
		dist++;
		
		Cell c = m.getCell(p);
		int x = p.getX();
		int y = p.getY();
		int up_d = 0, down_d = 0, left_d = 0, right_d = 0;
		if (!c.getWalls()[0]) up_d = m.getCell(new CellPosition(x, y-1)).getDistance();
		if (!c.getWalls()[1]) down_d = m.getCell(new CellPosition(x, y+1)).getDistance();
		if (!c.getWalls()[2]) left_d = m.getCell(new CellPosition(x-1, y)).getDistance();
		if (!c.getWalls()[3]) right_d = m.getCell(new CellPosition(x+1, y)).getDistance();
		
		c.setDistance(dist);
	    
		//System.out.println("dist: "+dist+" x: " + x + " y: " + y +", walls: "+c.getWalls()[0]+", "+c.getWalls()[1]+", "+c.getWalls()[2]+", "+c.getWalls()[3]+", ");
		
		if ((up_d == -1 || up_d > dist)) {
			recursivePathFinder(m, new CellPosition(x, y-1));
		}
		if ((down_d == -1 || down_d>dist)) {
			recursivePathFinder(m, new CellPosition(x, y+1));
		}
		if ((left_d == -1 || left_d > dist)) {
			recursivePathFinder(m, new CellPosition(x-1, y));
		}
		if ((right_d == -1 || right_d > dist)) {
			recursivePathFinder(m, new CellPosition(x+1, y));
		}
		
		dist--;
	}
}
