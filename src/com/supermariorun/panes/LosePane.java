package com.supermariorun.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.Timer;

import com.supermariorun.levels.Level;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.MainSMR;
import com.supermariorun.main.PlayerProgress;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class LosePane extends GraphicsPane {
	private static final double PROGRAM_WIDTH = MainSMR.WINDOW_WIDTH;
	private static final double PROGRAM_HEIGHT = MainSMR.WINDOW_HEIGHT;
	
	private static final int BUTTON_HEIGHT = 200;
	private static final int BUTTON_SIZEW = 201;
	private static final int BUTTON_WIDTH = 450;
	private MainSMR program; 
	public static final String IMG_FOLDER = "losePane/";
	private GImage quitButton;
	private GImage retryButton;
	private GImage background;
	private LevelPane level;
	
	public LosePane(MainSMR mainSMR, LevelPane level) {
		super();
		this.program = mainSMR;
		this.level = level;
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		background = new GImage(IMG_FOLDER + "gameOver.jpg", 0, 0);
		background.setSize(mainWidth, mainHeight);
		quitButton = new GImage(IMG_FOLDER + "quit.png", BUTTON_WIDTH, BUTTON_HEIGHT);
		quitButton.setSize(BUTTON_SIZEW, 60);
		retryButton = new GImage(IMG_FOLDER + "retry.png", BUTTON_WIDTH - 20, BUTTON_HEIGHT + 50);
		retryButton.setSize(BUTTON_SIZEW * 1.15, 160);
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
