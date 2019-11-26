package com.supermariorun.enemies;

import com.supermariorun.main.mainSMR;
import com.supermariorun.panes.LevelPane;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class eGoomba {
	private mainSMR program;
	private LevelPane level;
	public static final String IMG_FOLDER = "enemies/";
	private static final double PROGRAM_WIDTH = mainSMR.WINDOW_WIDTH;
	private GImage goomba;
	private boolean moveRight;
	private boolean isGoombaDead;
	private GRectangle body;
	private GRectangle head;
	private GRectangle Feet;

	public eGoomba(mainSMR main, LevelPane levelPane, int x, int y) {
		program = main;
		level = levelPane;
		goomba = new GImage(IMG_FOLDER + "goomba.png", x, y);
		
		head = new GRectangle (goomba.getX(), goomba.getY() + 4, goomba.getWidth() - 21, 2);
		body = new GRectangle (goomba.getX() + goomba.getWidth() - 35,  goomba.getY() + 8, 2, goomba.getHeight() - 21);
	}

	public void updateBounds() {
		head.setLocation(goomba.getX(), goomba.getY() + 3);
		body.setLocation(goomba.getX() + goomba.getWidth() - 35,  goomba.getY() + 8);
	}
	
	
	public void Run() {
		
		if (level.getBackground().getX() <= goomba.getX() - PROGRAM_WIDTH) {
			goomba.move(-10, 0);
			updateBounds();
		} 
		
		else {
			goomba.move(-8, 0);
			updateBounds();
		}

		if (head.intersects(level.getCharacter().getCharacter().getBounds())) {
			program.playStompEffect();
			program.remove(goomba);
		} 
		
		if (body.intersects(level.getCharacter().getCharacter().getBounds())) {
			level.getCharacter().isDead = true;
		}
	} 
	
	//public GRect getBody() {
		//return body;
	//}
	//public GRect getHead() {
		//return head;
	//}
	

	public GImage getEnemy() {
		return goomba;
	}
}