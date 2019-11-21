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
import acm.graphics.GObject;
import starter.GButton;

public class TourPane extends GraphicsPane implements ActionListener {
	public static final String IMG_FOLDER = "tourPane/";
	private mainSMR program; 
	private GImage backLabel;
	private GImage backPipe;
	private GImage backBubble;
	private GImage tourBackground;
	private GImage lvlStrip;
	private GImage worldOne;
	private GImage lockLvlTwo;
	private GImage lockLvlThree;
	private GImage lockLvlFour;
	private GImage lvlOne;
	private GObject wiggleObj;
	private GButton DevMode;
	private boolean isWiggle;
	private int wCount = 0;
	private int count = 0;
	private Timer bTimer;
	private GButton testLevel;
	
	public TourPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		bTimer = new Timer(100, this);
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
		final double qBlockWidth = mainWidth/7.2 - 10;
		final double qBlockHeight = mainHeight/4.409;
		
		DevMode = new GButton ("Developer Mode", 500, 550, 100, 100);
		DevMode.setFillColor(Color.BLUE);
		
		backPipe = new GImage("gPipeR.png", -50, 30);
		backPipe.setSize(pipeWidth, pipeHeight);
		
		backLabel = new GImage("backLabel.png", 177, 60);
		backLabel.setSize(labelWidth, labelHeight);
		
		backBubble = new GImage("bubble.png", 162, 20);
 		backBubble.setSize(bubbleWidth, bubbleHeight);
		
		tourBackground = new GImage(IMG_FOLDER + "tourBack.png", 0, 0);
		tourBackground.setSize(mainWidth, mainHeight);
		
		lvlStrip = new GImage(IMG_FOLDER + "strip.png", 220 , 410);
		lvlStrip.setSize(mainWidth - 350, 30);
		
		worldOne = new GImage(IMG_FOLDER + "worldOne.png", 0, 350);
	    worldOne.setSize(150, 150);
			
		lvlOne = new GImage(IMG_FOLDER + "lvlOne.png", 170, 345);
		lvlOne.setSize(mainWidth/7 + 10, mainHeight/4.209 );
	    
	    lockLvlTwo = new GImage(IMG_FOLDER + "qBlock.png", 430, 350);
	    lockLvlTwo.setSize(qBlockWidth, qBlockHeight);
	    
	    lockLvlThree = new GImage(IMG_FOLDER + "qBlock.png", 670, 350);
	    lockLvlThree.setSize(qBlockWidth, qBlockHeight);
	    
	    lockLvlFour= new GImage(IMG_FOLDER + "qBlock.png", 910, 350);
	    lockLvlFour.setSize(qBlockWidth, qBlockHeight);
	    
	    testLevel = new GButton ("level two", 600, 550, 100, 100);
		testLevel.setFillColor(Color.RED);
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
		program.add(lvlOne);
		program.add(lockLvlTwo);
		program.add(lockLvlThree);
		program.add(lockLvlFour);
		program.add(DevMode);
		program.add(testLevel);
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
			program.menuPane.bubbleTimer.start();
			program.playPipeSound();
			program.stopTourSound();
			program.playMenuSound();
			program.switchToMenu();
		}
		
		if (obj == lvlOne) {
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevel("One");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (obj == testLevel) {
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevel("Two");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (obj == DevMode) {
			program.playPipeSound();
			program.stopTourSound();
			try {
				program.switchToLevelDev(1);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if(isWiggle == false && (obj == lockLvlTwo || obj == lockLvlThree || obj == lockLvlFour)){	
				wiggleObj = obj;
				wiggleObj.move(10, 0);
				isWiggle = true;
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
			if (wCount % 2 != 0) { //odd
				wiggleObj.move(-20, 0);
			}
			
			else { //even
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
}
