package com.supermariorun.levelbuilder;

public class LevelObject {
		
	private LevelObjectType type;
	private int row;
	private int col;

	public LevelObject (LevelObjectType type, int row, int col) {
		this.type = type;
		this.row = row;
		this.col = col;
	}
		
	public void setVehicleType(LevelObjectType type) {
		this.type = type;
	}
	
	public LevelObjectType getVehicleType() {
		return type;
	}
		
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
		
	public void setCol(int col) {
		this.col = col;
	}
			
	public int getCol() {
		return col;
	}
		
	public Space spaceOccupied() {
		return new Space(row, col);
	}
}
