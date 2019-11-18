package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.characters.Character;
import com.supermariorun.levels.ILevel;
import com.supermariorun.levels.LevelOne;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import starter.GButton;

public class LevelPaneDev extends GraphicsPane {
	private mainSMR program;
	private GImage Background;
	private GImage pauseButton;
	private GImage pauseBubble;
	private GImage retryButton;
	private GImage resumeButton;
	private GImage pausePane;
	private GImage quitButton;
	private GImage gBackground;
	private GLabel CoordLabel;
	private GButton moveLeft;
	private GButton moveRight;
	private GObject obj;
	private GLabel dragCoord;
	private int mouseX = 0;
	private int lastX;
	private int lastY;
	private ArrayList<GImage> Environment;
	private ArrayList<GImage> GrassStrips;
	private ArrayList<GImage> Blocks;
	private ArrayList<GImage> qBlocks;
	private GLabel collison;
	private ILevel level;
	private int spaceWidth = 1150 / 30;
	private int spaceHeight = 650 / 18;
	public static final int MS = 100; // 110
	public static final String IMG_FOLDER = "LevelPane/";
	private Character Character;

	public boolean jumpState = true; // edit
	public boolean jumpUpState;
	private int jumpCount;
	private GObject leftFoot;

	public LevelPaneDev(mainSMR mainSMR, int levelNum) {
		super();
		this.program = mainSMR;

		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();

		collison = new GLabel("collison test", 500, 80);

		Character = new Character(mainSMR, this);
		
		CoordLabel = new GLabel("label ", 500, 40);
		CoordLabel.setColor(Color.red);
		CoordLabel.setFont("Arial-40");

		dragCoord = new GLabel("drag obj", 800, 40);
		dragCoord.setColor(Color.red);
		dragCoord.setFont("Arial-40");

		moveLeft = new GButton("left", 300, 40, 100, 100);
		moveRight = new GButton("right", 400, 40, 100, 100);

		program = mainSMR;

		level = new LevelOne();
		Environment = level.getEnvironment();

		pauseBubble = new GImage(IMG_FOLDER + "bubble.png", 30, 10);
		pauseBubble.setSize(100, 100);

		pauseButton = new GImage(IMG_FOLDER + "pause.png", 55, 27);
		pauseButton.setSize(50, 70);

		pausePane = new GImage(IMG_FOLDER + "pausePane.png", 400, 100);
		pausePane.setSize(300, 400);

		quitButton = new GImage(IMG_FOLDER + "quitButton.png", 428, 437);
		quitButton.setSize(250, 50);

		gBackground = new GImage(IMG_FOLDER + "pauseBack.png", 0, 0);
		gBackground.setSize(mainWidth, mainHeight);

		resumeButton = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		resumeButton.setSize(190, 100);

		retryButton = new GImage(IMG_FOLDER + "retryButton.png", 415, 387);
		retryButton.setSize(280, 50);

		DrawLevel();
	}

	public void DrawLevel() {
		Background = level.getBackground();
	}

	private void drawGridLines() { // DEV

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
		
		program.playLvlOneTrack();
	}

	public void Pause() {
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
			program.stopLvlOneTrack();
		}
	}

	@Override
	public void showContents() {
		Play();
		program.add(Background);
		program.add(pauseButton);
		program.add(pauseBubble);
		program.add(Character.getCharacter());

		for (GImage e : Environment) {
			program.add(e);
		}
		drawGridLines();

		program.add(CoordLabel); // DEV
		program.add(dragCoord);
		program.add(moveLeft);
		program.add(moveRight);
		program.add(collison);
	}

	@Override
	public void hideContents() {
		
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {

		lastX = e.getX();
		lastY = e.getY();
		obj = program.getElementAt(e.getX(), e.getY());

		CoordLabel.setLabel("X: " + (e.getX() + mouseX) + " Y: " + e.getY()); // DEV

		if (obj == moveLeft) {
			Background.move(-40, 0);
			for (GImage move : Environment) { // DEV
				move.move(-40, 0);
			}
			mouseX += 40;
		}

		else if (obj == moveRight) { // DEV
			Background.move(40, 0);
			for (GImage move : Environment) { // DEV
				move.move(40, 0);
			}
			mouseX -= 40; // DEV
		}

		else if (obj == pauseButton || obj == pauseBubble) {
			Pause();
			program.add(gBackground);
			program.add(pausePane);
			program.add(quitButton);
			program.add(resumeButton);
			program.add(retryButton);
		}

		else if (obj == resumeButton) {
			Play();
			program.playResumeSound();
			program.remove(gBackground);
			program.remove(pausePane);
			program.remove(quitButton);
			program.remove(resumeButton);
			program.remove(retryButton);
		}

		else if (obj == retryButton) {
			Restart();
			program.remove(gBackground);
			program.remove(pausePane);
			program.remove(quitButton);
			program.remove(resumeButton);
			program.remove(retryButton);
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
		obj = null;
	}
}