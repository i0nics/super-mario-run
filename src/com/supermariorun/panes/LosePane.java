package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.Timer;

import com.supermariorun.levels.Level;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import com.supermariorun.main.playerProgress;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class LosePane extends GraphicsPane {
	private mainSMR program; 
	public static final String IMG_FOLDER = "losePane/";
	private GImage quitButton;
	private GImage retryButton;
	private GImage background;
	private LevelPane level;
	
	public LosePane(mainSMR mainSMR, LevelPane level) {
		super();
		this.program = mainSMR;
		this.level = level;
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		background = new GImage(IMG_FOLDER + "gameOver.jpg", 0, 0);
		background.setSize(mainWidth, mainHeight);
		quitButton = new GImage(IMG_FOLDER + "quit.png", 450, 200);
		quitButton.setSize(200, 60);
		retryButton = new GImage(IMG_FOLDER + "retry.png", 450, 250);
		retryButton.setSize(200, 120);
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if(obj == quitButton) {
			program.playButtonEffect();
			program.stopLvlOneTrack(level.getLevelNum());
			program.playTourSound();
			program.switchToTour();
		}
		
		if(obj == retryButton) {
			program.playButtonEffect();
			program.stopLvlOneTrack(level.getLevelNum());
			hideContents();
			program.setScreen(level);
			level.hideContents();
			level.showContents();
		}
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(quitButton);
		program.add(retryButton);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
}
