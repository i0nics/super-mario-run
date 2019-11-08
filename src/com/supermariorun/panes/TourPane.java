package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

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
	private GImage backBubble;
	private GImage backPipe;
	private GImage tourBackground;
	private GImage lvlStrip;
	private GImage worldOne;
	private GImage lockLvlTwo;
	private GImage lockLvlThree;
	private GImage lockLvlFour;
	private GImage lvlOne;
	private double currX;
	private int count = 1;
	public Timer bTimer;
	
	public TourPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		bTimer = new Timer(500, this);
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
	    
	    lockLvlTwo = new GImage(IMG_FOLDER + "?Block.png", 430, 350);
	    lockLvlTwo.setSize(qBlockWidth, qBlockHeight);
	    
	    lockLvlThree = new GImage(IMG_FOLDER + "?Block.png", 670, 350);
	    lockLvlThree.setSize(qBlockWidth, qBlockHeight);
	    
	    lockLvlFour= new GImage(IMG_FOLDER + "?Block.png", 910, 350);
	    lockLvlFour.setSize(qBlockWidth, qBlockHeight);
	    
		bTimer.start();
	}

	@Override
	public void showContents() {
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
	}

	@Override
	public void hideContents() {
		program.remove(tourBackground);
		program.remove(backLabel);
		program.remove(backBubble);
		program.remove(backPipe);
		program.remove(lvlStrip);
		program.remove(worldOne);
		program.remove(lvlOne);
		program.remove(lockLvlTwo);
		program.remove(lockLvlThree);
		program.remove(lockLvlFour);
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
			System.out.println("mouse presssed");
		}else if(obj == lockLvlTwo)
			{		
				lockLvlTwo.move(10,0);
				System.out.println("mouse presssed");
			}
		}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			backBubble.move(10, 0);
			backLabel.move(10, 0);
		}
		
		if (count == 2) {
			backBubble.move(-10, 0);
			backLabel.move(-10,0);
			count = 0;
		}
		count++;
	}
}
