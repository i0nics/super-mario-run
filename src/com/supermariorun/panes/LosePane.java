package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

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
	
	public LosePane(mainSMR mainSMR) {
		super();
		this.program = mainSMR;
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
			program.stopLvlOneTrack();
			program.playTourSound();
			program.playPipeSound();
			program.switchToTour();
		}
		
		if(obj == retryButton) {
			program.stopLvlOneTrack();
			program.playPipeSound();
			program.switchToLevel(1);
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
		program.remove(background);
		program.remove(quitButton);
		program.remove(retryButton);
	}
}
