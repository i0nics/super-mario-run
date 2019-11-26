package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import com.supermariorun.main.playerProgress;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class TourPane extends GraphicsPane implements ActionListener {
	public static final String IMG_FOLDER = "tourPane/";
	private static final double PROGRAM_WIDTH = mainSMR.WINDOW_WIDTH;
	private static final double PROGRAM_HEIGHT = mainSMR.WINDOW_HEIGHT;
	private static final double PIPE_WIDTH = PROGRAM_WIDTH/6;
	private static final double PIPE_HEIGHT = PROGRAM_HEIGHT/6;
	private static final double BUBBLE_WIDTH = PROGRAM_WIDTH/9;
	private static final double BUBBLE_HEIGHT = PROGRAM_HEIGHT/5;
	private static final double LABEL_WIDTH = PROGRAM_WIDTH/12;
	private static final double LABEL_HEIGHT = PROGRAM_HEIGHT/12;
	private static final double QBLOCK_WIDTH = PROGRAM_WIDTH/7.2 - 10;
	private static final double QBLOCK_HEIGHT = PROGRAM_HEIGHT/4.409;
	private static final double LEVEL_ICON_WIDTH = PROGRAM_WIDTH/7 + 10;
	private static final double LEVEL_ICON_HEIGHT = PROGRAM_HEIGHT/4.209;
	
	private mainSMR program; 
	private GImage backLabel;
	private GImage backPipe;
	private GImage backBubble;
	private GImage tourBackground;
	private GImage lvlStrip;
	private GImage worldOne;
	private GObject wiggleObj;
	private boolean isWiggle;
	private int wCount = 0;
	private int count = 0;
	private int sizeCount = 0;
	private int lvlCount = 1;
	private Timer bTimer;
	private GButton testLevel;
	private GButton testlevel1;
	private ArrayList <GImage> lockLvl;
	private ArrayList <GImage> levelIcons;
	private playerProgress progress;
	private GImage developerMode;
	
	public TourPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		progress = program.getProgress();
		bTimer = new Timer(100, this);
		lockLvl = new ArrayList <GImage> (2);
		levelIcons = new ArrayList <GImage> (3);
		
		backPipe = new GImage("gPipeR.png", -50, 30);
		backPipe.setSize(PIPE_WIDTH, PIPE_HEIGHT);
		
		backLabel = new GImage("backLabel.png", 177, 60);
		backLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
		
		backBubble = new GImage("bubble.png", 162, 20);
 		backBubble.setSize(BUBBLE_WIDTH, BUBBLE_HEIGHT);
		
		tourBackground = new GImage(IMG_FOLDER + "tourBack.png", 0, 0);
		tourBackground.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		
		lvlStrip = new GImage(IMG_FOLDER + "strip.png", 220 , 410);
		lvlStrip.setSize(PROGRAM_WIDTH - 350, 30);
		
		worldOne = new GImage(IMG_FOLDER + "worldOne.png", 0, 350);
	    worldOne.setSize(150, 150);
	   
	    developerMode = new GImage(IMG_FOLDER + "Builder_Mario.png", 875,300);
	    developerMode.setSize(200, 200);
	    
	    for (int i = 170; i <= 650; i = i + 240) {
	    	levelIcons.add(new GImage(IMG_FOLDER + "lvl" + lvlCount + ".png", i, 345));
	 	    levelIcons.get(lvlCount - 1).setSize(LEVEL_ICON_WIDTH, LEVEL_ICON_HEIGHT);
	 	    lvlCount++;
		}
	    	
		for (int i = 430; i <= 670; i = i + 240) {
			lockLvl.add(new GImage(IMG_FOLDER + "qBlock.png", i, 350));
			lockLvl.get(sizeCount).setSize(QBLOCK_WIDTH, QBLOCK_HEIGHT);
			sizeCount++;
		}
	    
	    testLevel = new GButton ("level two", 600, 550, 100, 100);
		testLevel.setFillColor(Color.RED);

		testlevel1 = new GButton ("level three", 700, 550, 100, 100);
		testlevel1.setFillColor(Color.BLUE);
	}
	
	@Override
	public void showContents() {
		bTimer.start();
		program.add(tourBackground);
		program.add(backLabel);
		program.add(backBubble);
		program.add(backPipe);
		program.add(lvlStrip);
		program.add(worldOne);
		program.add(developerMode);
		for (int i = 1; i <= 3; i++) {
			if (progress.isLevelUnlocked(i)) {
				program.add(levelIcons.get(i - 1));
			}
		}
	
		if (!lockLvl.isEmpty()) {
			for (GImage img1 : lockLvl) {program.add(img1);}
		}
		program.add(testLevel);
		program.add(testlevel1);
	}

	@Override
	public void hideContents() {
		bTimer.stop();
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == backLabel || obj ==  backBubble || obj == backPipe) {
			program.playPipeSound();
			program.stopTourSound();
			program.playMenuSound();
			program.switchToMenu();
		}
		
		if (obj == levelIcons.get(0)) {
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevel("One");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (obj == levelIcons.get(1)) {
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevel("Two");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (obj == levelIcons.get(2)) {
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevel("Three");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (obj == testLevel) {//remove later
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevel("Two");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (obj == testlevel1) {//remove later
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevel("Three");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (obj == developerMode) {
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevelDev("Two");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		for (GImage img : lockLvl) {
			if(isWiggle == false && obj == img){	
				wiggleObj = obj;
				wiggleObj.move(10, 0);
				isWiggle = true;
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			backBubble.move(10, 0);
			backLabel.move(10, 0);
		}
		
		if (count == 6) {
			backBubble.move(-10, 0);
			backLabel.move(-10, 0);
		}
		
		if (count == 12) {
			count = 0;
		}
		
		if (isWiggle == true) {
			if (wCount % 2 != 0) { 
				wiggleObj.move(-20, 0);
			}
			
			else { 
				wiggleObj.move(20, 0);
			}
			wCount++;
		}
	
		if (wCount == 6) {
			wiggleObj.move(-10, 0);
			wCount = 0;
			isWiggle = false;
		}
		
		count++;
	}
	
	public void removeQBlock() {
		if (!lockLvl.isEmpty()) {
			lockLvl.remove(0);
		}
	}
}
