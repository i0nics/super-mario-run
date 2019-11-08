package com.supermariorun.levelbuilder;

import java.util.ArrayList;

public class LevelBuilder {
	private Board board;
	private int numMoves;
	ArrayList<LevelObject> vehList;

	public LevelBuilder(int nRows, int nCols) {
		board = new Board (nRows, nCols);
		vehList = new ArrayList <LevelObject> ();
	}
	
	public int getRows() {  
		return board.getNumRows();
	}
	
	public int getColumns() {
		return board.getNumCols();
	}
	
	public int getNumMoves() {
		return numMoves;
	}
	
	public LevelObject getObject(Space space) {
		return board.getObject(space);
	}
	
	public ArrayList <LevelObject> getObjectsOnBoard() {
		vehList = board.getVehiclesOnBoard();
		return vehList;
	}
	
	public void setupLevel(int maxRows, int maxCols) {
	   
	}
}