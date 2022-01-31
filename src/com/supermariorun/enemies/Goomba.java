package com.supermariorun.enemies;

import com.supermariorun.main.MainSMR;
import com.supermariorun.panes.LevelPane;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Goomba implements Enemies {
	private MainSMR program;
	private LevelPane level;
	public static final String IMG_FOLDER = "enemies/";
	private static final double PROGRAM_WIDTH = MainSMR.WINDOW_WIDTH;
	private GImage goomba;
	private GRectangle body;
	private GRectangle head;

	public Goomba(MainSMR main, LevelPane levelPane, int x, int y) {
		program = main;
		level = levelPane;
		goomba = new GImage(IMG_FOLDER + "goomba.png", x, y);
		head = new GRectangle (goomba.getX(), goomba.getY() + 2, goomba.getWidth(), 2);
		body = new GRectangle (goomba.getX() - 10,  goomba.getY() + 8, 2, goomba.getHeight() - 10);
	}
	
	@Override
	public void getEnemySpeed() {
		
	}
	
	@Override
	public GImage getEnemyImg() {
		return goomba;
		
	} 

	@Override
	public void updateBounds() {
		head.setLocation(goomba.getX(), goomba.getY() + 2);
		body.setLocation(goomba.getX() - 10,  goomba.getY() + 8);
	}
	
	@Override
	public void Run() {
		GRectangle playerBounds = level.getCharacter().getCharacter().getBounds();
		
		if (goomba.getX() - level.getCharacter().getCharacter().getX() <= 1000) {
			goomba.move(-10, 0);
			updateBounds();
		} 
		
		else {
			goomba.move(-8, 0);
			updateBounds();
		}
		
		if (program.getProgress().getCurrentPowerUp().equals("star")) {
				if (goomba.getBounds().intersects(playerBounds)) {
					program.remove(goomba);
				}
		}
		
		else if (head.intersects(playerBounds)) {
				program.playStompEffect();
				program.remove(goomba);
		} 
			
		else if (body.intersects(playerBounds)) {
				level.getCharacter().isDead = true;
		}
	}
}