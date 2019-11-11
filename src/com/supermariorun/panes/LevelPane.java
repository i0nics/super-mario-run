package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.characters.cMario;
import com.supermariorun.levels.ILevel;
import com.supermariorun.levels.LevelOne;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
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
	private GImage gBackground;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> GrassStrips;
	private ArrayList <GImage> Blocks;
	private ArrayList <GImage> qBlocks;
	private cMario Mario;
	private ILevel level;
	private Timer timer;
	private int spaceWidth = 1150/30;
	private int spaceHeight = 650/18;
	public static final int MS = 90; //110
	public static final String IMG_FOLDER = "LevelPane/";


	public LevelPane(mainSMR mainSMR, int levelNum) {
		super();
		this.program = mainSMR;
		
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		
		program = mainSMR;
		timer = new Timer (MS, this);
		level = new LevelOne();
		Mario = new cMario();
		Environment = level.getEnvironment();
		
		pauseBubble = new GImage(IMG_FOLDER + "bubble.png",30, 10);
		pauseBubble.setSize(100, 100);
		
		pauseButton = new GImage(IMG_FOLDER + "pause.png", 55, 27);
		pauseButton.setSize(50,70);
		
		pausePane = new GImage(IMG_FOLDER + "pausePane.png", 400, 100);
		pausePane.setSize(300, 400);
		
		quitButton = new GImage(IMG_FOLDER + "quitButton.png", 428, 437);
		quitButton.setSize(250,50);
		
		gBackground = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		gBackground.setSize(mainWidth,mainHeight);
		
		resumeButton = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		resumeButton.setSize(190,100);
		
		retryButton = new GImage(IMG_FOLDER + "retryButton.png", 415, 387);
		retryButton.setSize(280, 50);
		
		DrawLevel();
	}
	
	public void DrawLevel() {
		Background = level.getBackground();
	}
	
	private void drawGridLines() {

		for (int i = 1; i < 1000; i++) {
			GLine line = new GLine(i * spaceWidth, 0, i * spaceWidth, program.getHeight());
			program.add(line);
		}

		for (int i = 1; i < 18; i++) {
			GLine line = new GLine(0, i * spaceHeight, 6000, i * spaceHeight);
			program.add(line);
		}
	}
	

	public void Play() {
		timer.start();
		Mario.run();
		moveEnvironment();
		program.playLvlOneTrack();
	}
	
	public void Pause() {
		timer.stop();
		Mario.stand();
		program.playPauseSound();
		program.pauseLvlOneTrack();
	}
	
	public void Restart() {
		program.switchToLevel(1);
	}
	
	public void moveEnvironment() {
		Background.move(-10, 0);
		for (GImage move : Environment) {
			move.move(-10, 0);
		}
	}
	
	public void isGameOver() {
		if (Background.getX() == -4840) {
			timer.stop();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		moveEnvironment();
		isGameOver();
	}

	@Override
	public void showContents() {
		Play();
		program.add(Background);
		program.add(Mario.getMario());
		program.add(pauseButton);
		program.add(pauseBubble);

		for (GImage e: Environment) {
			program.add(e);
		}
		drawGridLines();
	}

	@Override
	public void hideContents() {
		program.remove(level.getBackground());
		program.remove(pauseButton);
		program.remove(pauseBubble);
		program.remove(quitButton);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		int x=e.getX();
	    int y=e.getY();
	    System.out.println(x+","+y);
		if(obj == pauseButton || obj == pauseBubble) {
			Pause();
			program.add(gBackground);
			program.add(pausePane);	
			program.add(quitButton);
			program.add(resumeButton);
			program.add(retryButton);
		}
		
		if(obj == resumeButton)	{
			Play();
			program.playResumeSound();
			program.remove(gBackground);
			program.remove(pausePane);
			program.remove(quitButton);
			program.remove(resumeButton);
			program.remove(retryButton);
		}
		
		if(obj == retryButton){
			Restart();
			program.remove(gBackground);
			program.remove(pausePane);
			program.remove(quitButton);
			program.remove(resumeButton);
			program.remove(retryButton);
		}
		
		if(obj == quitButton)	{
			program.playTourSound();
			program.switchToTour();
		}
		
		else {
			Mario.jump();
		}
		
		
	}
}