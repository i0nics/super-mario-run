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
	private GRectangle rightBody;
	private GObject detectGround;
	private GRect Head;
	private int numCoins = 0;
	public static final String IMG_FOLDER = "character/";
	private static String STAR_EXT = "";
	private static String BIG_EXT = "";
	private String character = "mario";
	public boolean jumpUpState;
	public boolean fallState = false;
	private int jumpCount = 0;
	private int starCount = 0;
	private Timer starTimer;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> Coins;
	private ArrayList <GImage> Goombas;
	 
	public Character(mainSMR mainSMR, LevelPane levelPane) {
		program = mainSMR;
		this.levelPane = levelPane;
		
		characImg = new GImage (IMG_FOLDER + BIG_EXT + STAR_EXT + character + "Stand.png", 100, 520); 
		characImg.setSize(64, 64);
		starTimer = new Timer (1000, this);
				
		character = program.getProgress().getCurrentCharacter();
		BIG_EXT = program.getProgress().getCurrentPowerUp();
		
		Environment = levelPane.getLevel().getEnvironment();
		Coins = levelPane.getLevel().getCoins();
		Goombas = levelPane.getLevel().getGoombas();
		
		Feet = new GRectangle(characImg.getX() + 6, characImg.getY() + characImg.getHeight() - 12, characImg.getWidth() - 25, 2);	
		rightBody = new GRectangle(characImg.getX() + characImg.getWidth() - 7,  characImg.getY() + 6, 2, characImg.getHeight() - 21);	
	}

	public Character(mainSMR mainSMR, LevelPaneDev levelPaneDev) {
		program = mainSMR;
		this.levelPaneDev = levelPaneDev;
		characImg = new GImage (IMG_FOLDER + BIG_EXT + STAR_EXT + character + "Stand.png", 100, 520); 
		characImg.setSize(64, 64);
		
		Environment = levelPaneDev.getLevel().getEnvironment();
		Coins = levelPaneDev.getLevel().getCoins();
		Goombas = levelPaneDev.getLevel().getGoombas();
	}
	
	public void stand() {
		characImg.setImage(IMG_FOLDER + BIG_EXT + STAR_EXT + character + "Stand.png");
		characImg.setSize(54, 57);
	}

	public void run() {
		characImg.setImage(IMG_FOLDER + BIG_EXT + STAR_EXT + character + "Run.gif");
	}
	
	public void setJumpImage() {
		characImg.setImage(IMG_FOLDER + BIG_EXT + STAR_EXT + character + "Jump.gif");
	}
	
	public void updateBounds() {
		Feet.setLocation(characImg.getX() + 6, characImg.getY() + characImg.getHeight() - 12);
		rightBody.setLocation(characImg.getX() + characImg.getWidth() - 7,  characImg.getY() + 8);
	}
	
	public void fallDown() {
		characImg.move(0, 10);	
		updateBounds();
		
		for (GImage obj : Environment) {
			if (Feet.intersects(obj.getBounds())) {
				levelPane.jumpState = false;
				fallState = false;
				characImg.setLocation(characImg.getX(), obj.getY() - 55);
				updateBounds();
				run();
			}
		}
	}
	
	public void jump() {
		jumpUpState = false;
			
		if (jumpCount >=  0 && jumpCount < 10) {
			jumpUpState = true;
			jumpCount++;
		}
			
		if  (jumpCount > 10) {
			jumpUpState = false;
		}
	
		if (jumpUpState) {		
			characImg.move(0, -10);
			updateBounds();
		}
			
		if (!jumpUpState) {
			fallDown();
		}
	}
	
	public boolean checkCollision() {
		for (GImage obj : Environment) {
			if (rightBody.intersects(obj.getBounds())) {
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
		BIG_EXT = "";
		run();
	}
	
	public void setStarMode() {
		STAR_EXT = "star";
		starTimer.start();
		program.stopLvlOneTrack();
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
	
	public void checkGoombaCollision() {
		for (Iterator<GImage> it = Goombas.iterator(); it.hasNext(); ) {
			GImage img = it.next();
			GRectangle characBounds = characImg.getBounds();
			GRectangle goombaBounds = img.getBounds();
			if (characBounds.intersects(goombaBounds)) {
				program.remove(img);
				it.remove();
			}
		}
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		starCount++;
		
		if (starCount == 10) {
			starTimer.stop();
			starCount = 0;
			STAR_EXT = "";
			program.getProgress().clearCurrentPowerUp();
			program.getProgress().resetStarPurchased();
			program.stopStarTrack();
			program.playLvlOneTrack();
			run();
		}
	}
	
	public int numCoinsCollected() {
		return numCoins;
	}
	
	public void coinsCollected() {
		program.getProgress().increaseCoins(numCoins);
	}
	
	public GImage getCharacter(){
		return characImg;
	}
	
	public boolean getFallState() {
		return fallState;
	}
	public void setJumpCount(int jumpCount) {
		this.jumpCount = jumpCount;
	}

	public void reset() {
		characImg.setLocation(100, 520);		
	}
	
	//public GRect getRect() {
		//return rightBody;
	//}
}