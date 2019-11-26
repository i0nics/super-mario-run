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
	private GImage goomba;;
	private GRectangle body;
	private GRectangle head;
	private boolean isStomp = false;

	public eGoomba(mainSMR main, LevelPane levelPane, int x, int y) {
		program = main;
		level = levelPane;
		goomba = new GImage(IMG_FOLDER + "goomba.png", x, y);
		
		head = new GRectangle (goomba.getX(), goomba.getY() + 2, goomba.getWidth(), 2);
		body = new GRectangle (goomba.getX() - 10,  goomba.getY() + 8, 2, goomba.getHeight() - 10);
	}

	public void updateBounds() {
		head.setLocation(goomba.getX(), goomba.getY() + 2);
		body.setLocation(goomba.getX() - 10,  goomba.getY() + 8);
	}
	
	
	public void Run() {
		isStomp = false;
		
		if (goomba.getX() - level.getCharacter().getCharacter().getX() <= 1000) {
			goomba.move(-10, 0);
			updateBounds();
		} 
		
		else {
			goomba.move(-8, 0);
			updateBounds();
		}
		
		if (program.getProgress().getCurrentPowerUp().equals("star")) {
				if (goomba.getBounds().intersects(level.getCharacter().getCharacter().getBounds())) {
				program.remove(goomba);
				}
		}
		
		else {
			if (head.intersects(level.getCharacter().getCharacter().getBounds())) {
				program.playStompEffect();
				program.remove(goomba);
				isStomp = true;
			} 
			
			if (!isStomp && body.intersects(level.getCharacter().getCharacter().getBounds())) {
				level.getCharacter().isDead = true;
			}
		}
	} 

	public GImage getEnemy() {
		return goomba;
	}
}