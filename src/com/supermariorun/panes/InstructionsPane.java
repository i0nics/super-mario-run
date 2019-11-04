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

public class InstructionsPane extends GraphicsPane implements ActionListener {
	public static final String IMG_FOLDER = "guidePane/";
	public static final int MAX_STEPS = 100;
	private mainSMR program;
	private GImage backImg;
	private GImage InstructionsList;
	private GImage bubbleImg;
	private GImage backLabel;
	private GImage mario;
	private GImage grassImg;
	private GImage bubblePipe;
	private Timer mTimer;
	private int count = 1;
	private int numTimes = 0;
	
	public InstructionsPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
		
		mario = new GImage("mario1.gif", 0, 500);
		mario.setSize(mainWidth/18, mainHeight/8);
		
		backImg = new GImage(IMG_FOLDER + "redStripes.png", 0, 0);
		backImg.setSize(mainWidth, mainHeight);
		
		InstructionsList = new GImage(IMG_FOLDER + "guideLabel.png", 290, 50);	
		InstructionsList.setSize(750, 500);
		
		grassImg = new GImage(IMG_FOLDER + "grassStrip.png", 0, 570);
		grassImg.setSize(mainWidth, mainHeight/8);
		
		bubblePipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		bubblePipe.setSize(pipeWidth, pipeHeight);
		
		backLabel = new GImage(IMG_FOLDER + "backLabel.png", 177, 75);
		backLabel.setSize(labelWidth, labelHeight);
		
		bubbleImg = new GImage(IMG_FOLDER + "bubble.png", 162, 35);
		bubbleImg.setSize(bubbleWidth, bubbleHeight);
		
		mTimer = new Timer(50, this);
		mTimer.start();
	}
	
	@Override
	public void showContents() {
		program.add(backImg);
		program.add(grassImg);
		program.add(mario);
		program.add(InstructionsList);
		program.add(bubblePipe);
		program.add(backLabel);
		program.add(bubbleImg);
	}

	@Override
	public void hideContents() {
		program.remove(InstructionsList);
		program.remove(mario);
		program.remove(backImg);
		program.remove(bubblePipe);
		program.remove(grassImg);
		program.remove(backLabel);
		program.remove(bubbleImg);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());

		if (obj == bubblePipe || obj == backLabel || obj == bubbleImg) {
			program.playPipeSound();
			program.menuPane.bubbleTimer.start();
			program.switchToMenu();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mario.move(10, 0);
		if (numTimes == 120) {
			mTimer.stop();
        	mTimer.restart();
        	numTimes = 0;
        	mario.setLocation(0, 500);
		}
		
		if (count == 10) {
			bubbleImg.move(0, 10);
			backLabel.move(0, 10);
		}
		
		if (count == 20) {
			bubbleImg.move(0, -10);
			backLabel.move(0, -10);
			count = 0;
		}
		numTimes++;
		count++;
	}		
}