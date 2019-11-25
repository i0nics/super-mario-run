package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.characters.Character;
import com.supermariorun.levels.Level;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import starter.GButton;

public class LevelPaneDev extends GraphicsPane implements ActionListener {
	private mainSMR program;
	private GImage Background;
	private GImage pauseButton;
	private GImage pauseBubble;
	private GImage retryButton;
	private GImage resumeButton;
	private GImage pausePane;
	private GImage quitButton;
	private GImage greyBack;
	private GLabel CoordLabel;
	private GButton moveLeft;
	private GButton moveRight;
	private GObject obj;
	private GLabel dragCoord;
	private int mouseX = 0;
	private boolean mouseHold = false;
	private int lastX;
	private int lastY;
	private ArrayList<GImage> Environment;
	private ArrayList <GImage> Coins;	
	private Level level;
	private int spaceWidth;
	private int spaceHeight;
	public static final int MS = 100; 
	public static final String IMG_FOLDER = "LevelPane/";
	private Timer mouseTimer;
	private String lvlNum;


	public LevelPaneDev(mainSMR mainSMR, String levelNum) throws FileNotFoundException {
		super();
		program = mainSMR;
		lvlNum = levelNum;
		spaceWidth = program.getWidth() / 30;
		spaceHeight = program.getHeight() / 18;
		
		mouseTimer = new Timer (50, this);

		level = new Level(levelNum);
		
		CoordLabel = new GLabel("label ", 500, 40);
		CoordLabel.setColor(Color.red);
		CoordLabel.setFont("Arial-40");

		dragCoord = new GLabel("drag obj", 800, 40);
		dragCoord.setColor(Color.red);
		dragCoord.setFont("Arial-40");

		moveLeft = new GButton("left", 300, 40, 100, 100);
		moveRight = new GButton("right", 400, 40, 100, 100);

		pausePane = new GImage(IMG_FOLDER + "PausePane.png", 400, 100);
		pausePane.setSize(300, 400);
		
		pauseBubble = new GImage(IMG_FOLDER + "bubble.png", 30, 10);
		pauseBubble.setSize(100, 100);

		pauseButton = new GImage(IMG_FOLDER + "pause.png", 55, 27);
		pauseButton.setSize(50, 70);

		quitButton = new GImage(IMG_FOLDER + "quitButton.png", 428, 437);
		quitButton.setSize(250, 50);

		greyBack = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		greyBack.setSize(program.getWidth(), program.getHeight());

		resumeButton = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		resumeButton.setSize(190, 100);

		retryButton = new GImage(IMG_FOLDER + "retryButton.png", 415, 387);
		retryButton.setSize(280, 50);
	}

	public void Play() {
		level.setUpLevel();
		Background = level.getBackground();
		Coins = level.getCoins();
		Environment = level.getEnvironment();
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

	public void Pause() {
		program.playPauseSound();
		program.pauseLvlOneTrack(lvlNum);
	}

	@Override
	public void showContents() {
		Play();
		program.add(Background);
		program.add(pauseButton);
		program.add(pauseBubble);
		//program.add(Character.getRect());

		for (GImage e : Environment) {
			program.add(e);
		}
		
		for (GImage e: Coins) {
			program.add(e);
		}
		drawGridLines();

		program.add(CoordLabel); // DEV
		program.add(dragCoord);
		program.add(moveLeft);
		program.add(moveRight);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseTimer.start();
		lastX = e.getX();
		lastY = e.getY();
		obj = program.getElementAt(e.getX(), e.getY());
		mouseHold = true;

		CoordLabel.setLabel("X: " + (e.getX() + mouseX) + " Y: " + e.getY()); // DEV


		if (obj == pauseButton || obj == pauseBubble) {
			Pause();
			program.add(greyBack);
			program.add(pausePane);
			program.add(quitButton);
			program.add(resumeButton);
			program.add(retryButton);
		}

		else if (obj == resumeButton) {
			program.playResumeSound();
			program.remove(greyBack);
			program.remove(pausePane);
			program.remove(quitButton);
			program.remove(resumeButton);
			program.remove(retryButton);
		}

		else if (obj == retryButton) {
			mouseX = 0;
			program.remove(greyBack);
			program.remove(pausePane);
			program.remove(quitButton);
			program.remove(resumeButton);
			program.remove(retryButton);
			Play();
		}

		else if (obj == quitButton) {
			program.playTourSound();
			program.switchToTour();
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (obj != null) {
			obj.move(e.getX() - lastX, e.getY() - lastY);
			lastX = e.getX();
			lastY = e.getY();
			dragCoord.setLabel("X: " + (obj.getX() + mouseX) + " Y: " + obj.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseTimer.stop();
		obj = null;
		mouseHold = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (obj == moveLeft && mouseHold) {
			Background.move(-40, 0);
			for (GImage move : Environment) { // DEV
				move.move(-40, 0);
			}
			for (GImage move : Coins) { // DEV
				move.move(-40, 0);
			}
			mouseX += 40;
		}

		if (obj == moveRight && mouseHold) { // DEV
			Background.move(40, 0);
			for (GImage move : Environment) { // DEV
				move.move(40, 0);
			}
			for (GImage move : Coins) { // DEV
				move.move(40, 0);
			}
			mouseX -= 40; // DEV
		}
	}
	
	public Level getLevel() {
		return level;
	}
}