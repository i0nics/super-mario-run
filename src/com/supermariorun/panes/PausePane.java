package com.supermariorun.panes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GParagraph;

public class PausePane extends GraphicsPane {
	private mainSMR program; 
	private LevelPane level;
	public static final String IMG_FOLDER = "LevelPane/";
	private GImage retryButton;
	private GImage resumeButton;
	private GImage quitButton;
	private GImage greyBack;
	private GImage pausePane;
	
	public PausePane(mainSMR main, LevelPane level) {
		this.program = main;
		this.level = level;
		
		pausePane = new GImage(IMG_FOLDER + "pausePane.png", 400, 100);
		pausePane.setSize(300, 400);
		
		quitButton = new GImage(IMG_FOLDER + "quitButton.png", 428, 437);
		quitButton.setSize(250,50);
		
		greyBack = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		greyBack.setSize(program.getWidth(), program.getHeight());
		
		resumeButton = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		resumeButton.setSize(190,100);
		
		retryButton = new GImage(IMG_FOLDER + "retryButton.png", 415, 387);
		retryButton.setSize(280, 50);
	}

	@Override
	public void showContents() {
		program.setScreen(this);
		program.add(greyBack);
		program.add(pausePane);
		program.add(resumeButton);
		program.add(retryButton);
		program.add(quitButton);
	}

	@Override
	public void hideContents() {
		program.remove(greyBack);
		program.remove(pausePane);
		program.remove(resumeButton);
		program.remove(retryButton);
		program.remove(quitButton);
		program.setScreen(level);
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
	    if(obj == resumeButton) {
			program.playResumeSound();
			level.Resume();
		}
		
		else if(obj == retryButton) {
			program.stopLvlOneTrack();
		}
		
		else if(obj == quitButton) {
			program.stopLvlOneTrack();
			program.playTourSound();
			program.switchToTour();
		}
	  
	    hideContents();
	}
}
