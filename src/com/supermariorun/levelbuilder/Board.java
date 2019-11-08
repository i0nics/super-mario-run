package com.supermariorun.levelbuilder;

import java.util.ArrayList;

public class Board {

	LevelObject[][] grid;
	ArrayList <LevelObject> lvlObjList;

	public Board (int rows, int cols) {
		grid = new LevelObject[rows][cols];
		lvlObjList = new ArrayList <LevelObject> ();
	}
	
	public int getNumRows() {
		return grid.length;
	}

	public int getNumCols() {
		return grid[0].length;
	}

	public boolean isObjectOnSpace(Space s) {
		if (grid[s.getRow()][s.getCol()] != null) {
			return true;
		}
		return false;
	}

	public LevelObject getObject(Space s) {

		if (grid[s.getRow()][s.getCol()] != null) {
			return grid[s.getRow()][s.getCol()];
		}
		return null;
	}

	public ArrayList <LevelObject> getObjectsOnBoard() {
		return lvlObjList;
	}


	public void addLvlObj(LevelObjectType type, int startRow, int startCol, int length) {
		if (startRow >= 0 && startRow < getNumRows() && startCol >= 0 && startCol < getNumCols()) {
			LevelObject addObj = new LevelObject (type, startRow, startCol);
			Space spaceOcc = addObj.spaceOccupied();
			lvlObjList.add(addObj);

			for (int i = 0; i < length; i++) {
				if(grid[spaceOcc.getRow()] [spaceOcc.getCol()] == null) {

					grid[spaceOcc.getRow()] [spaceOcc.getCol()] = addObj;
				}
			}
		}
	}
}