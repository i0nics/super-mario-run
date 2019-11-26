package com.supermariorun.panes;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import starter.GParagraph;

public class PausePane extends GraphicsPane {
	private mainSMR program; 
	private LevelPane level;
	public static final String IMG_FOLDER = "levelPane/";
	private GImage retryButton;
	private GImage resumeButton;
	private GImage quitButton;
	private GImage greyBack;
	private GImage pausePane;
	private GLabel bestCount;
	
	public PausePane(mainSMR main, LevelPane level) {
		this.program = main;
		this.level = level;
		
		pausePane = new GImage(IMG_FOLDER + "PausePane.png", 400, 100);
		pausePane.setSize(300, 400);
		
		quitButton = new GImage(IMG_FOLDER + "quitButton.png", 428, 437);
		quitButton.setSize(250,50);
		
		greyBack = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		greyBack.setSize(program.getWidth(), program.getHeight());
		
		resumeButton = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		resumeButton.setSize(190,100);
		
		retryButton = new GImage(IMG_FOLDER + "retryButton.png", 415, 387);
		retryButton.setSize(280, 50);
		
		bestCount = new GLabel("" + program.getProgress().getHighScore(level.getLevelInt()), 580, 385);
		bestCount.setFont(program.marioFont.deriveFont(35f));
		bestCount.setColor(Color.GRAY);
		
	}
	
	@Override
	public void showContents() {
		program.setScreen(this);
		program.add(greyBack);
		program.add(pausePane);
		program.add(resumeButton);
		program.add(retryButton);
		program.add(quitButton);
		program.add(bestCount);
	}

	@Override
	public void hideContents() {
		program.remove(greyBack);
		program.remove(pausePane);
		program.remove(resumeButton);
		program.remove(retryButton);
		program.remove(quitButton);
		program.setScreen(level);
		program.remove(bestCount);
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
	    if(obj == resumeButton) {
	    	program.playButtonEffect();
			program.playResumeSound();
			level.Resume();
			hideContents();
		}
		
		else if(obj == retryButton) {
			program.playButtonEffect();
			program.stopLvlOneTrack(level.getLevelNum());
			hideContents();
			level.hideContents();
			level.showContents();
		}
		
		else if(obj == quitButton) {
			program.playButtonEffect();
			program.stopLvlOneTrack(level.getLevelNum());
			program.playTourSound();
			program.switchToTour();
		}
	}
}
