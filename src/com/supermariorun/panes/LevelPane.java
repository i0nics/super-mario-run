package com.supermariorun.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.characters.Character;
import com.supermariorun.enemies.Enemy;
import com.supermariorun.enemies.Goomba;
import com.supermariorun.enemies.KoopaTroopa;
import com.supermariorun.levels.Level;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.MainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class LevelPane extends GraphicsPane implements ActionListener{
	private static final int ENVI_MOVEW = 8;
	private static final boolean BOOLEAN_FALSE = false;
	private MainSMR program;
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
	private ArrayList <Enemy> Enemies;

	private Character Character;
	private Level level;
	private Timer timer;
	private PausePane pausePane;
	private EndPane EndPane;
	private boolean isRestartTimer = BOOLEAN_FALSE;
	private boolean jumpState = BOOLEAN_FALSE;
	private boolean isPause = BOOLEAN_FALSE;
	private boolean isMousePressed = BOOLEAN_FALSE;
	private int mouseCounter = 0;
	private String lvlNum;

	public static final int MS = 30;
	public static final String IMG_FOLDER = "LevelPane/";

	public LevelPane(MainSMR mainSMR, String levelNum) throws FileNotFoundException {
		super();
		this.program = mainSMR;
		program = mainSMR;
		timer = new Timer (MS, this);
		
		level = new Level(program, levelNum, this);
		this.lvlNum = levelNum;
	
		Character = new Character(program, this);
		pausePane = new PausePane(program, this);
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
		Enemies = level.getEnemies();
		Character.reset();
		Character.resetCoinsCollected();
		isPause = BOOLEAN_FALSE;

		if (program.getProgress().getCurrentPowerUp() == "star") {
			Character.setStarMode();
		}
		if(program.getProgress().getCurrentPowerUp() == "big") {
			Character.setMushroomMode();
		}
		timer.start();
		Character.run();
		playTrack();
	}
	
	public void Resume() {
		isPause = BOOLEAN_FALSE;
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
		Background.move(-ENVI_MOVEW, 0);
		for (GImage move : Environment) { move.move(-ENVI_MOVEW, 0); }
		for (GImage move : Coins) { move.move(-ENVI_MOVEW, 0); }
		for (GImage move : Plants) { move.move(-ENVI_MOVEW, 0); }
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
			program.getProgress().unlockLevel(getLevelInt(), program.getTourPane());
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
		for (Enemy en : Enemies) { en.Run();}
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
			isRestartTimer = BOOLEAN_FALSE;
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
		isMousePressed = BOOLEAN_FALSE;
		mouseCounter = 0;
	}
		
	
	@Override
	public void showContents() {
		Play();
		program.add(Background);
		program.add(Character.getCharacter());
		program.add(pauseButton);
		program.add(pauseBubble);
		
		for (GImage e: Plants) { program.add(e); }
		for (Enemy e: Enemies) { program.add(e.getEnemyImg());}
		for (GImage e: Environment) { program.add(e); }
		for (GImage e: Coins) { program.add(e); }
	}

	@Override
	public void hideContents() {
		timer.stop();
		program.stopLvlOneTrack(lvlNum);
		program.stopStarTrack();
		program.removeAll();
	}
	
	public void setJumpState() {
		jumpState = BOOLEAN_FALSE;
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
	
	
	public boolean getJumpState() {
		return jumpState;
	}
	
	public int getLevelInt() {
		if (lvlNum.contentEquals("One")) {
			return 1;
		}
		
		if (lvlNum.contentEquals("Two")) {
			return 2;
		}
		
		return 3;
	}
}