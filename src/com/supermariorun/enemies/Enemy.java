package com.supermariorun.enemies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.supermariorun.main.MainSMR;
import com.supermariorun.panes.LevelPane;
import acm.graphics.GImage;
import acm.graphics.GRectangle;

public class Enemy implements Enemies {
	
	private MainSMR program;
	private LevelPane level;
	public static final String IMG_FOLDER = "enemies/";
	private GImage enemyImg;
	private String enemyType;
	private int enemySpeed;
	private GRectangle body;
	private GRectangle head;

	public Enemy(MainSMR main, LevelPane levelPane, String enemyType, int enemyXCoord, int enemyYCoord) {
		program = main;
		level = levelPane;
		this.enemyType = enemyType;
		enemyImg = new GImage(IMG_FOLDER + this.enemyType + ".png", enemyXCoord, enemyYCoord);
		head = new GRectangle (enemyImg.getX(), enemyImg.getY() + 2, enemyImg.getWidth(), 2);
		body = new GRectangle (enemyImg.getX() - 10,  enemyImg.getY() + 8, 2, enemyImg.getHeight() - 10);
		enemySpeed = getEnemySpeed();
	}
	
	
	// Update head and body GRectangles to appropriate locations surrounding the enemy's head and body
	@Override
	public void updateBounds() {
		head.setLocation(enemyImg.getX(), enemyImg.getY() + 2);
		body.setLocation(enemyImg.getX() - 10,  enemyImg.getY() + 8);
	}
	
	
    // Retrieve enemy speed from text file
	@Override
	public Integer getEnemySpeed() {
		int speed = 0;
		try {
			Scanner readFile = new Scanner(new File("level/" + enemyType + "_speed.txt"));
			speed = Integer.valueOf(readFile.next());
			readFile.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return speed;
	}
	
	
	// Return enemy image
	@Override
	public GImage getEnemyImg() {
		return enemyImg;
	}

	
	// Manage enemy movement and track enemy collision with player
	@Override
	public void Run() {
		GRectangle playerBounds = level.getCharacter().getCharacter().getBounds();
		double playerX = level.getCharacter().getCharacter().getX();
		
		if (enemyImg.getX() - playerX <= 1000) {
			enemyImg.move(enemySpeed, 0);
			updateBounds();
		} 
		
		else {
			enemyImg.move(-8, 0);
			updateBounds();
		}
		
		if (program.getProgress().getCurrentPowerUp().equals("star")) {
				if (enemyImg.getBounds().intersects(playerBounds)) {
					program.remove(enemyImg);
				}
		}
		
		else if (head.intersects(playerBounds)) {
				program.playStompEffect();
				program.remove(enemyImg);
		} 
			
		else if (body.intersects(playerBounds)) {
				level.getCharacter().isDead = true;
		}
	}
}
