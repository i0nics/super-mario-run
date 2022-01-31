package com.supermariorun.enemies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.supermariorun.main.MainSMR;
import com.supermariorun.panes.LevelPane;

import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class KoopaTroopa implements Enemies {
	private MainSMR program;
	private LevelPane level;
	public static final String IMG_FOLDER = "enemies/";
	private static final double PROGRAM_WIDTH = MainSMR.WINDOW_WIDTH;
	private GImage koopaTroopa;
	private GRectangle body;
	private GRectangle head;

	public KoopaTroopa(MainSMR main, LevelPane levelPane, int x, int y) {
		program = main;
		level = levelPane;
		koopaTroopa = new GImage(IMG_FOLDER + "koopa.png", x, y);
		head = new GRectangle (koopaTroopa.getX(), koopaTroopa.getY() + 2, koopaTroopa.getWidth(), 2);
		body = new GRectangle (koopaTroopa.getX() - 10,  koopaTroopa.getY() + 8, 2, koopaTroopa.getHeight() - 10);
	}
	
	@Override
	public void updateBounds() {
		head.setLocation(koopaTroopa.getX(), koopaTroopa.getY() + 2);
		body.setLocation(koopaTroopa.getX() - 10,  koopaTroopa.getY() + 8);
	}
	
	@Override
	public Integer getEnemySpeed() {
		int speed = 0;
		try {
			Scanner readFile = new Scanner(new File("level/koopatroopa_speed.txt"));
			speed = Integer.valueOf(readFile.next());
			readFile.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return speed;
	}
	
	@Override
	public GImage getEnemyImg() {
		return koopaTroopa;
	}

	@Override
	public void Run() {
		GRectangle playerBounds = level.getCharacter().getCharacter().getBounds();
		double playerX = level.getCharacter().getCharacter().getX();
		
		if (koopaTroopa.getX() - playerX <= 1000) {
			koopaTroopa.move(getEnemySpeed(), 0);
			updateBounds();
		} 
		
		else {
			koopaTroopa.move(-8, 0);
			updateBounds();
		}
		
		if (program.getProgress().getCurrentPowerUp().equals("star")) {
				if (koopaTroopa.getBounds().intersects(playerBounds)) {
					program.remove(koopaTroopa);
				}
		}
		
		else if (head.intersects(playerBounds)) {
				program.playStompEffect();
				program.remove(koopaTroopa);
		} 
			
		else if (body.intersects(playerBounds)) {
				level.getCharacter().isDead = true;
		}
		
	}

}
