package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;
import com.supermariorun.panes.LevelPane;
import com.supermariorun.characters.Character;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import com.supermariorun.main.playerProgress;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class EndPane extends GraphicsPane {
	private mainSMR program; 
	public static final String IMG_FOLDER = "endPane/";
	private GImage next;
	private GImage background;
	private GLabel coinCount;
	private Character Character;
	private LevelPane levelPane;
	private GLabel HighScore;
	
	public EndPane(mainSMR mainSMR, LevelPane level) {
		super();
		this.program = mainSMR;
		this.levelPane = level;
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		background = new GImage(IMG_FOLDER + "endBack.png", 0, 0);
		background.setSize(mainWidth, mainHeight);
		next = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		next.setSize(200, 120);
		coinCount = new GLabel("Coins: " + level.getCharacter().numCoinsCollected());
		coinCount.setFont(program.marioFont.deriveFont(40f));
		coinCount.setColor(Color.RED);
		coinCount.setLocation(485, 300);
		HighScore = new GLabel("High Score: " + program.getProgress().getHighScore(level.getLevelInt()));
		HighScore.setFont(program.marioFont.deriveFont(40f));
		HighScore.setColor(Color.RED);
		HighScore.setLocation(485, 400);
		
	}
	
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if(obj == next) {
			program.playButtonEffect();
			program.playTourSound();
			program.switchToTour();
		}
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(next);
		program.add(coinCount);
		program.add(HighScore);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
}