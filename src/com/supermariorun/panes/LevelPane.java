package com.supermariorun.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.characters.Character;
import com.supermariorun.enemies.eGoomba;
import com.supermariorun.levels.Level;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class LevelPane extends GraphicsPane implements ActionListener{
	private mainSMR program;
	private GImage Background;
	private GImage pauseButton;
	private GImage pauseBubble;
	private GImage greyBack;
	private GImage levelClear;
	private GImage continueEndButton;
	private LosePane losePane;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> Coins;	
	private ArrayList <GImage> Plants;
	private ArrayList <eGoomba> Goombas;

	private Character Character;
	private Level level;
	private Timer timer;
	private PausePane pausePane;
	private EndPane EndPane;
	private boolean isRestartTimer = false;
	private boolean jumpState = false;
	private boolean isPause = false;
	private boolean isMousePressed = false;
	private int mouseCounter = 0;
	private String lvlNum;

	public static final int MS = 1;//30
	public static final String IMG_FOLDER = "LevelPane/";

	public LevelPane(mainSMR mainSMR, String levelNum) throws FileNotFoundException {
		super();
		this.program = mainSMR;
		program = mainSMR;
		timer = new Timer (MS, this);
		
		pausePane = new PausePane(program, this);
		
		level = new Level(program, levelNum, this);
		this.lvlNum = levelNum;
	
		Character = new Character(program, this);
		
		pauseBubble = new GImage(IMG_FOLDER + "bubble.png", 30, 10);
		pauseBubble.setSize(100, 100);
		
		pauseButton = new GImage(IMG_FOLDER + "pause.png", 55, 27);
		pauseButton.setSize(50,70);
		
		greyBack = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		greyBack.setSize(program.getWidth(), program.getHeight());
		
		levelClear = new GImage(IMG_FOLDER + "courseClear.png", 380, 260);
		levelClear.setSize(400, 150);
		
		continueEndButton = new GImage(IMG_FOLDER + "continueButton.png", 460, 425);
		continueEndButton.setSize(200, 120);
		
		losePane = new LosePane(program, this);
	}
	
	public void Play() {
		level.setUpLevel();
		Background = level.getBackground();
		Coins = level.getCoins();
		Environment = level.getEnvironment();
		Plants = level.getPlant();
		Goombas = level.getGoombas();
		Character.reset();
		Character.resetCoinsCollected();
		isPause = false;

		if (program.getProgress().getCurrentPowerUp() == "star") {
			Character.setStarMode();
		}
		
		timer.start();
		Character.run();
		playTrack();
	}
	
	public void Resume() {
		isPause = false;
		timer.start();
		Character.run();
		playTrack();
	}
	
	public void Pause() {
	    timer.stop();
		Character.stand();
		program.playPauseSound();
		program.pauseLvlOneTrack(lvlNum);
		
		if (program.getProgress().getCurrentPowerUp() == "star") {
			Character.pauseStarMode();
		}
	}
	
	public void moveEnvironment() {
		Background.move(-8, 0);
		for (GImage move : Environment) { move.move(-8, 0); }
		for (GImage move : Coins) { move.move(-8, 0); }
		for (GImage move : Plants) { move.move(-8, 0); }
	}
	
	public void playTrack() {
		if (program.getProgress().getCurrentPowerUp().equals("")) {
			program.playLvlOneTrack(lvlNum);
		}
		
		else {
			program.playStarTrack();
		}
	}
	
	public void isGameOver() {
		if (Background.getX() == -10000) {
			timer.stop();
			Character.stand();
			isPause = true;
			program.add(greyBack);
			program.add(levelClear);
			program.add(continueEndButton);
			program.stopLvlOneTrack(lvlNum);
			program.playCourseClearedTrack();
			Character.numCoinsCollected();
			Character.coinsCollected();
			program.getTourPane().unlockLvl();
		}
		
		if (Character.getCharacter().getY() > 650 || Character.isDead) {
			timer.stop();
			program.stopLvlOneTrack(lvlNum);
			program.playGameOverSound();
			program.switchToScreen(losePane);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		moveEnvironment();
		isGameOver();
		for (eGoomba g : Goombas) { g.Run();}
		Character.collectCoin();
		
		if (isMousePressed) {mouseCounter++;}
		if (mouseCounter == 10) {Character.setLongJump();}

		if (jumpState) { Character.jump(); }
		
		else { 
			Character.checkGround();
			if (Character.checkCollision()) {
				if (!Character.getFallState()) {
					timer.stop();
					isRestartTimer = true;
				}
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		isMousePressed = true;
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (isRestartTimer) {
			timer.start();
			isRestartTimer = false;
		}
	
		if(obj == pauseButton || obj == pauseBubble) {
			Pause();
			isPause = true;
			pausePane.showContents();
		}
		
		else if(obj == continueEndButton) {
			program.removeAll();
			program.stopLvlOneTrack(lvlNum);
			program.playButtonEffect();
			EndPane = new EndPane(program, this);
			program.switchToScreen(EndPane);
		}
		
		else {
			if (!jumpState && !isPause) {
				jumpState = true;
				Character.setJumpImage();
				program.playJumpSound();
				Character.jump();
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		isMousePressed = false;
		mouseCounter = 0;
	}
		
	
	@Override
	public void showContents() {
		Play();
		program.add(Background);
		program.add(Character.getCharacter());
		program.add(pauseButton);
		program.add(pauseBubble);
		//program.add(Character.getRect());
		
		for (GImage e: Plants) { program.add(e); }
		for (eGoomba e: Goombas) { program.add(e.getEnemy()); }
		for (GImage e: Environment) { program.add(e); }
		for (GImage e: Coins) { program.add(e); }
	}

	@Override
	public void hideContents() {
		timer.stop();
		program.removeAll();
	}
	
	public void setJumpState() {
		jumpState = false;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public String getLevelNum() {
		return lvlNum;
	}
	
	public GImage getBackground() {
		return Background;
	}
	
	public Character getCharacter() {
		return Character;
	}
}