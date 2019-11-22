package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import com.supermariorun.characters.Character;
import com.supermariorun.levels.Level;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import com.supermariorun.main.playerProgress;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import starter.GButton;

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
	public boolean jumpState;
	private boolean isPause = false;
	private Character Character;
	private Level level;
	private Timer timer;
	private PausePane pausePane;
	
	public static final int MS = 70;
	public static final String IMG_FOLDER = "LevelPane/";

	public LevelPane(mainSMR mainSMR, String levelNum) throws FileNotFoundException {
		super();
		this.program = mainSMR;
		
		program = mainSMR;
		timer = new Timer (MS, this);
		
		pausePane = new PausePane(program, this);
		
		level = new Level(levelNum);
	
		level.setUpLevel();
		Background = level.getBackground();

		Environment = level.getEnvironment();
		Coins = level.getCoins();
		Character = new Character(program, this);
		
		pauseBubble = new GImage(IMG_FOLDER + "bubble.png",30, 10);
		pauseBubble.setSize(100, 100);
		
		pauseButton = new GImage(IMG_FOLDER + "pause.png", 55, 27);
		pauseButton.setSize(50,70);
		
		greyBack = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		greyBack.setSize(program.getWidth(), program.getHeight());
		
		levelClear = new GImage(IMG_FOLDER + "courseClear.png", 380, 260);
		levelClear.setSize(400, 150);
		
		continueEndButton = new GImage(IMG_FOLDER + "continueButton.png", 460, 425);
		continueEndButton.setSize(200, 120);
		
		losePane = new LosePane(program, levelNum);
	}
	
	public void Play() {
		level.setUpLevel();
		Background = level.getBackground();
		Environment = level.getEnvironment();
		Character.reset();
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
		program.pauseLvlOneTrack();
		
		if (program.getProgress().getCurrentPowerUp() == "star") {
			Character.pauseStarMode();
		}
	}
	
	public void moveEnvironment() {
		Background.move(-10, 0);
		for (GImage move : Environment) {
			move.move(-10, 0);
		}
		for (GImage move : Coins) {
			move.move(-10, 0);
		}
	}
	
	public void playTrack() {
		if (program.getProgress().getCurrentPowerUp().equals("")) {
			program.playLvlOneTrack();
		}
		
		else {
			program.playStarTrack();
		}
	}
	
	public void isGameOver() {
		if (Background.getX() == -4840) {
			timer.stop();
			Character.stand();
			isPause = true;
			program.add(greyBack);
			program.add(levelClear);
			program.add(continueEndButton);
			program.stopLvlOneTrack();
			program.playCourseClearedTrack();
		}
		
		if (Character.getCharacter().getY() > 650) {
			timer.stop();
			program.stopLvlOneTrack();
			program.switchToScreen(losePane);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		moveEnvironment();
		//Character.checkGround();
		Character.collectCoin();
		isGameOver();
	
		if (jumpState == true) {
			Character.jump();
		}
	}

	@Override
	public void showContents() {
		Play();
		program.add(Background);
		program.add(Character.getCharacter());
		program.add(pauseButton);
		program.add(pauseBubble);

		for (GImage e: Environment) {
			program.add(e);
		}
		
		for (GImage e: Coins) {
			program.add(e);
		}
	}

	@Override
	public void hideContents() {
		timer.stop();
		program.removeAll();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
	    
		if(obj == pauseButton || obj == pauseBubble) {
			Pause();
			isPause = true;
			pausePane.showContents();
		}
		
		else if(obj == continueEndButton) {
			program.removeAll();
			program.stopLvlOneTrack();
			program.playPipeSound();
			program.switchToEndPane();
		}
		
		else {
			if (!jumpState && !isPause) {
				jumpState = true;
				Character.setJumpCount(0);
				Character.setJumpImage();
				Character.jump();
			}
		}
	}
	
	public Level getLevel() {
		return level;
	}
}