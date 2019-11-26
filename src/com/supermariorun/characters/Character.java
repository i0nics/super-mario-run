package com.supermariorun.characters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import com.supermariorun.main.mainSMR;
import com.supermariorun.panes.LevelPane;
import com.supermariorun.panes.LevelPaneDev;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class Character extends GraphicsProgram implements ActionListener {
	private mainSMR program;
	private LevelPane levelPane;
	private LevelPaneDev levelPaneDev;
	private GImage characImg;
	private GRectangle Feet;
	private GRectangle Body;
	private GRectangle Head;
	private GObject detectGround;
	private int numCoins = 0;
	public static final String IMG_FOLDER = "character/";
	private static String POWERUP_EXT = "";
	private String character = "mario";
	public boolean isDead = false;
	public boolean jumpUpState;
	public boolean fallState = false;
	private int jumpCount = 0;
	private int starCount = 0;
	private int jumpLimit = 10;
	private Timer starTimer;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> Coins;
	private ArrayList <GImage> Goombas;
	 
	public Character(mainSMR mainSMR, LevelPane levelPane) {
		program = mainSMR;
		this.levelPane = levelPane;
		
		characImg = new GImage (IMG_FOLDER + POWERUP_EXT + character + "Stand.png", 100, 520); 
		starTimer = new Timer (1000, this);
				
		character = program.getProgress().getCurrentCharacter();
		
		Environment = levelPane.getLevel().getEnvironment();
		Coins = levelPane.getLevel().getCoins();
		
		Head = new GRectangle(characImg.getX() + 17, characImg.getY() + 3, characImg.getWidth() - 25, 2);
		Feet = new GRectangle(characImg.getX(), characImg.getY() + characImg.getHeight() - 12, characImg.getWidth() - 25, 2);	
		Body = new GRectangle(characImg.getX() + characImg.getWidth() - 9,  characImg.getY() + 8, 2, characImg.getHeight() - 21);
	}

	public void updateBounds() {
		Head.setLocation(characImg.getX() + 17, characImg.getY() + 3);
		Feet.setLocation(characImg.getX(), characImg.getY() + characImg.getHeight() - 12);
		Body.setLocation(characImg.getX() + characImg.getWidth() - 9,  characImg.getY() + 8);
	}
	
	public void stand() {
		characImg.setImage(IMG_FOLDER + POWERUP_EXT + character + "Stand.png");
		characImg.setSize(54, 57);
	}

	public void run() {
		characImg.setImage(IMG_FOLDER + POWERUP_EXT + character + "Run.gif");
	}
	
	public void setJumpImage() {
		characImg.setImage(IMG_FOLDER + POWERUP_EXT + character + "Jump.gif");
	}
	
	public void detectObj(String performAction) {
		for (GImage obj : Environment) {
			if (performAction.equals("Fall") && Feet.intersects(obj.getBounds())) {
				levelPane.setJumpState();
				fallState = false;
				jumpCount = 0;
				characImg.setLocation(characImg.getX(), obj.getY() - 55);
				updateBounds();
				run();
			}
			
			if (performAction.equals("Head") && Head.intersects(obj.getBounds())) {
				fallDown();
			}
		}
	}
	public void fallDown() {
		characImg.move(0, 10);	
		updateBounds();
		detectObj("Fall");
	}
	
	public void jump() {
		jumpUpState = false;
				
		if (jumpCount >=  0 && jumpCount < jumpLimit) {
			jumpUpState = true;
			jumpCount++;
		}

		if (jumpUpState) {		
			characImg.move(0, -10);
			updateBounds();
			detectObj("Head");
		}
			
		if (!jumpUpState) {
			jumpLimit = 10;
			fallDown();
		}
	}
	
	public void setLongJump() {
	    jumpLimit = 15;
	}
	
	public boolean checkCollision() {
		for (GImage obj : Environment) {
			if (Body.intersects(obj.getBounds())) {
				stand();
				return true;
			}
		}
		return false;
	}
	
	public void checkGround() {	
		detectGround = program.getElementAt(characImg.getX() + characImg.getWidth()/2, characImg.getY() + 80);
		
		if (detectGround == levelPane.getLevel().getBackground()) {
			fallState = true;
		}
	
		if (fallState) {
			fallDown();
		}
	}
	
	public void resetPowerUp() {
		POWERUP_EXT = "";
		run();
	}
	
	public void setStarMode() {
		POWERUP_EXT = "star";
		starTimer.start();
		program.stopLvlOneTrack(levelPane.getLevelNum());
		program.playStarTrack();
	}
	
	public void pauseStarMode() {
		starTimer.start();
		program.pauseStarTrack();
	}
	
	public void collectCoin() {
		for (Iterator<GImage> it = Coins.iterator(); it.hasNext(); ) {
			GImage img = it.next();
			if (characImg.getBounds().intersects(img.getBounds())) {
				program.stopCoinEffect();
				program.playCoinEffect();
				program.remove(img);
				it.remove();
				numCoins++;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		starCount++;
		
		if (starCount == 10) {
			starTimer.stop();
			starCount = 0;
			POWERUP_EXT = "";
			program.getProgress().clearCurrentPowerUp();
			program.getProgress().resetStarPurchased();
			program.stopStarTrack();
			program.playLvlOneTrack(levelPane.getLevelNum());
			run();
		}
	}
	
	public int numCoinsCollected() {
		return numCoins;
	}
	
	public void resetCoinsCollected() {
		numCoins = 0;
	}
	
	public void coinsCollected() {
		program.getProgress().increaseCoins(numCoins);
		program.getProgress().sethighScore(numCoins, levelPane.getLevelInt());
	}
	
	public GImage getCharacter(){
		return characImg;
	}
	
	public boolean getFallState() {
		return fallState;
	}

	public void reset() {
		isDead = false;
		characImg.setLocation(100, 520);	
	}
	/*public GRect getRect() {
		return Head;
	}*/
}