package com.supermariorun.levelbuilder;

public class levelBuilder {
	String[][] board = new String[17][1000];
	
	
	public void board() {
	for(int y=0;y<16;y++){
	    for(int x=0;x<999;x++){
	        board[x][y] = "?";
	    }
	}
	for (int r = 0; r<1;r++){
	    String line = "";
	    for (int c = 0; c <999;c++){
	        line+="["+board[c][r]+"]";
	    }
	    System.out.println(line);
	}
}
}
