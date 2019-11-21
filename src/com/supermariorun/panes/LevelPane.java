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
import com.supermariorun.levels.ILevel;
import com.supermariorun.levels.LevelOne;
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
	private GImage retryButton;
	private GImage resumeButton;
	private GImage pausePane;
	private GImage quitButton;
	private GImage greyBack;
	private GImage levelClear;
	private GImage continueButton;
	
	private LosePane losePane;
	
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> coins;	
	public boolean jumpState;
	public boolean isPause = false;
	private Character Character;
	private ILevel level;
	private Timer timer;
	
	public static final int MS = 70;
	public static final String IMG_FOLDER = "LevelPane/";

	public LevelPane(mainSMR mainSMR, String levelNum) throws FileNotFoundException {
		super();
		this.program = mainSMR;
		
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		
		program = mainSMR;
		timer = new Timer (MS, this);
		
		level = new LevelOne("One");
	
		level.setUpLevel();
		Background = level.getBackground();

		Environment = level.getEnvironment();
		Character = new Character(program, this);
		
		pauseBubble = new GImage(IMG_FOLDER + "bubble.png",30, 10);
		pauseBubble.setSize(100, 100);
		
		pauseButton = new GImage(IMG_FOLDER + "pause.png", 55, 27);
		pauseButton.setSize(50,70);
		
		pausePane = new GImage(IMG_FOLDER + "pausePane.png", 400, 100);
		pausePane.setSize(300, 400);
		
		quitButton = new GImage(IMG_FOLDER + "quitButton.png", 428, 437);
		quitButton.setSize(250,50);
		
		greyBack = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		greyBack.setSize(mainWidth, mainHeight);
		
		resumeButton = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		resumeButton.setSize(190,100);
		
		retryButton = new GImage(IMG_FOLDER + "retryButton.png", 415, 387);
		retryButton.setSize(280, 50);
		
		levelClear = new GImage(IMG_FOLDER + "courseClear.png", 380, 260);
		levelClear.setSize(400, 150);
		
		continueButton = new GImage(IMG_FOLDER + "continueButton.png", 460, 425);
		continueButton.setSize(200, 120);
		
		losePane = new LosePane(program, levelNum);
	}
	
	public void Play() {
		level.setUpLevel();
		Background = level.getBackground();
		Environment = level.getEnvironment();
		Character.reset();
		if (program.getProgress().getCurrentPowerUp() == "star") {
			Character.setStarMode();
		}
		timer.start();
		Character.run();
		program.playLvlOneTrack();
	}
	
	public void Resume() {
		timer.start();
		Character.run();
		program.playLvlOneTrack();
	}
	
	public void Pause() {
	    timer.stop();
		Character.stand();
		program.playPauseSound();
		program.pauseLvlOneTrack();
	}
	
	public void moveEnvironment() {
		Background.move(-10, 0);
		for (GImage move : Environment) {
			move.move(-10, 0);
		}
	}
	
	public void collectCoin() {
		GImage character = Character.getCharacter();
		for (Iterator<GImage> it = level.getCoins().iterator(); it.hasNext(); ) {
			GImage img = it.next();
			if (character.getBounds().intersects(img.getBounds())) {
				program.playCoinEffect();
				Environment.remove(img);
				program.remove(img);
				it.remove();
				program.getProgress().incrementCoins();
			}
		}
	}
	
	public void isGameOver() {
		if (Background.getX() == -4840) {
			timer.stop();
			Character.stand();
			program.add(greyBack);
			program.add(levelClear);
			program.add(continueButton);
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
		collectCoin();
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
	}

	@Override
	public void hideContents() {
		timer.stop();
		program.remove(Background);
		program.removeAll();
	}
	
	public void hideResume() {
		program.remove(greyBack);
		program.remove(pausePane);
		program.remove(quitButton);
		program.remove(resumeButton);
		program.remove(retryButton);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
	    
		if(obj == pauseButton || obj == pauseBubble) {
			Pause();
			isPause = true;
			program.add(greyBack);
			program.add(pausePane);	
			program.add(quitButton);
			program.add(resumeButton);
			program.add(retryButton);
		}
		
		else if(obj == resumeButton) {
			isPause = false;
			program.playResumeSound();
			hideResume();
			Resume();
		}
		
		else if(obj == retryButton){
			isPause = false;
			program.stopLvlOneTrack();
			hideContents();
			showContents();
		}
		
		else if(obj == quitButton) {
			isPause = false;
			program.stopLvlOneTrack();
			program.playTourSound();
			hideResume();
			program.switchToTour();
		}
		
		else if(obj == continueButton) {
			isPause = false;
			program.stopLvlOneTrack();
			hideResume();
			program.playPipeSound();
			program.switchToEndPane();
		}
		
		else {
			if (!jumpState && !isPause) {
				jumpState = true;
				Character.setJumpCount(0);
				Character.jump();
			}
		}
	}
	
	public ArrayList<GImage> getEnvironment() {
		return Environment;
	}
}