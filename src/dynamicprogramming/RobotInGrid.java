package dynamicprogramming;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Cell {
	private int x;
	private int y;
	
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public String toString() {
		return String.format("{%d, %d}", x, y);
	}
}

public class RobotInGrid {
	public static int recursiveCalls = 0;
	public static int memorizationCalls = 0;

	
	public static void main(String[] args) {
		boolean [][] grid = makeGrid();
		LinkedList<Cell> path = findPath(grid);
		System.out.println(path);
		
		path = findPathMemorization(grid);
		System.out.println(path);
		
		System.out.println(recursiveCalls);
		System.out.println(memorizationCalls);
	}

	private static LinkedList<Cell> findPath(boolean[][] grid) {
		return findPathRecursive(new Cell(0,0), grid);
	}
	
	private static LinkedList<Cell> findPathMemorization(boolean[][] grid) {
		return findPathMemorizationRecursive(new Cell(0,0), grid, new HashMap<>());
	}

	private static LinkedList<Cell> findPathRecursive(Cell cell, boolean[][] grid) {
		recursiveCalls++;
		
		LinkedList<Cell> path = new LinkedList<Cell>();
		if (cell.getX() == grid.length - 1 && cell.getY() == grid[cell.getX()].length - 1) {
			path.add(cell);
			
		} else if (cell.getX() + 1 < grid.length && grid[cell.getX()+1][cell.getY()]) {
			Cell nextRightCell = new Cell(cell.getX() + 1, cell.getY());
			LinkedList<Cell> rightPath = findPathRecursive(nextRightCell, grid);
			if (rightPath.size() > 0) {
				path.add(cell);
				path.addAll(rightPath);
			}
		} else if (cell.getY() + 1 < grid[cell.getX()].length && grid[cell.getX()][cell.getY()+1]) {
			Cell nextBottomCell = new Cell(cell.getX(), cell.getY()+ 1);
			LinkedList<Cell> bottomPath = findPathRecursive(nextBottomCell, grid);
			if (bottomPath.size() > 0) {
				path.add(cell);
				path.addAll(bottomPath);
			}
		}
		
		return path;
	}
	
	private static LinkedList<Cell> findPathMemorizationRecursive(Cell cell, boolean[][] grid, Map<Cell, LinkedList<Cell>> cache) {
		memorizationCalls++;
		
		if (cache.containsKey(cell)) {
			return cache.get(cell);
		}
		
		LinkedList<Cell> path = new LinkedList<Cell>();
		if (cell.getX() == grid.length - 1 && cell.getY() == grid[cell.getX()].length - 1) {
			path.add(cell);
			
		} else if (cell.getX() + 1 < grid.length && grid[cell.getX()+1][cell.getY()]) {
			Cell nextRightCell = new Cell(cell.getX() + 1, cell.getY());
			LinkedList<Cell> rightPath = findPathMemorizationRecursive(nextRightCell, grid, cache);
			if (rightPath.size() > 0) {
				path.add(cell);
				path.addAll(rightPath);
			}
		} else if (cell.getY() + 1 < grid[cell.getX()].length && grid[cell.getX()][cell.getY()+1]) {
			Cell nextBottomCell = new Cell(cell.getX(), cell.getY()+ 1);
			LinkedList<Cell> bottomPath = findPathMemorizationRecursive(nextBottomCell, grid, cache);
			if (bottomPath.size() > 0) {
				path.add(cell);
				path.addAll(bottomPath);
			}
		}
		
		cache.put(cell, path);
		return path;
	}

	private static boolean[][] makeGrid() {
		return new boolean [][] {
			{true, 	true},
			{false, true},
			{true, 	true}
		};
	}

}
