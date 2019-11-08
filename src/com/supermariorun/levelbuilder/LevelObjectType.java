package com.supermariorun.levelbuilder;

public enum LevelObjectType {
	GRASS_BLOCK, BRICK_BLOCK, QUESTION_BLOCK, PIPE_BLOCK, COIN;
	
	public String toString() {
		switch(this) {
			case GRASS_BLOCK: return "grassBlock";
			case BRICK_BLOCK: return "brickBlock";
			case QUESTION_BLOCK: return "qBlock";
			case PIPE_BLOCK: return "pipeBlock";
			case COIN: return "coin";
		}
		return "n/a";
	}
}
