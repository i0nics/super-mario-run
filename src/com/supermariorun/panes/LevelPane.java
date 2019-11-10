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
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> GrassStrips;
	private ArrayList <GImage> Blocks;
	private ArrayList <GImage> qBlocks;
	private cMario Mario;
	private ILevel level;
	private Timer timer;
	private GImage pauseButton;
	private GImage pauseBubble;
	private GImage retryButton;
	private GImage pausePane;
	private GImage quitButton;
	public static final int MS = 200;
	public static final String IMG_FOLDER = "LevelPane/";


	public LevelPane(mainSMR mainSMR, int levelNum) {
		super();
		program = mainSMR;
		timer = new Timer (MS, this);
		level = new LevelOne();
		Environment = level.getEnvironment();
		Mario = new cMario();
		pauseBubble = new GImage(IMG_FOLDER + "bubble.png",30,10);
		pauseBubble.setSize(100, 100);
		pauseButton = new GImage(IMG_FOLDER + "pause.png", 55,27);
		pauseButton.setSize(50,70);
		pausePane = new GImage(IMG_FOLDER + "pausePane.png", 400,200);
		pausePane.setSize(300, 400);
		DrawLevel();
	}
	
	public void DrawLevel() {
		Background = level.getBackground();
	}
	
	private void drawGridLines() {

		/*for (int i = 1; i < 1000; i++) {
			GLine line = new GLine(i * 64, 0, i * 64, program.HEIGHT);
			program.add(line);
		}

		for (int i = 1; i < 18; i++) {
			GLine line = new GLine(0, i * spaceHeight(), PROGRAM_WIDTH, i * spaceHeight());
			program.add(line);
		}*/
	}
	

	public void Play() {
		timer.start();
		program.playLvlOneTrack();
		moveEnvironment();
	}
	
	public void Pause() {
		program.stopLvlOneTrack();
		timer.stop();

	}
	
	public void Reset() {
		timer.restart();
		Background.setLocation(0, 0);
	}
	
	public void moveEnvironment() {
		Background.move(-10, 0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		moveEnvironment();
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
	}

	@Override
	public void hideContents() {
		program.remove(level.getBackground());
		program.remove(pauseButton);
		program.remove(pauseBubble);

	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Mario.jump();
GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if(obj == pauseButton || obj == pauseBubble)
		{
			Pause();
			program.add(pausePane);			
			
		}
		
	}

		
	}


