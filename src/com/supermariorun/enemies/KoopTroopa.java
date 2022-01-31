package com.supermariorun.enemies;

import com.supermariorun.main.MainSMR;
import com.supermariorun.panes.LevelPane;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class KoopTroopa implements Enemies {
	private MainSMR program;
	private LevelPane level;
	public static final String IMG_FOLDER = "enemies/";
	private static final double PROGRAM_WIDTH = MainSMR.WINDOW_WIDTH;
	private GImage koopaTroopa;
	private GRectangle body;
	private GRectangle head;

	public KoopTroopa(MainSMR main, LevelPane levelPane, int x, int y) {
		level = levelPane;
		koopaTroopa = new GImage(IMG_FOLDER + "goomba.png", x, y);
		head = new GRectangle (koopaTroopa.getX(), koopaTroopa.getY() + 2, koopaTroopa.getWidth(), 2);
		body = new GRectangle (koopaTroopa.getX() - 10,  koopaTroopa.getY() + 8, 2, koopaTroopa.getHeight() - 10);
	}
	
	@Override
	public void updateBounds() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getEnemySpeed() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public GImage getEnemyImg() {
		return koopaTroopa;
		
	}

	@Override
	public void Run() {
		// TODO Auto-generated method stub
		
	}




	

}
