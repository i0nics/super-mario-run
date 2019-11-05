package com.supermariorun.panes;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class InstructionsPane extends GraphicsPane implements ActionListener {
	private static final int JUMPint = 11;
	public static final String IMG_FOLDER = "guidePane/";
	public static final int MAX_STEPS = 100;
	private mainSMR program;
	private GImage backImg;
	private GImage InstructionsList;
	private GImage bubbleImg;
	private GImage backLabel;
	private GImage marioRun;
	private GImage grassImg;
	private GImage bubblePipe;
	private Timer mTimer;
	private double marioWidth;
	private double marioHeight;
	private int count = 1;
	private int numTimes = 0;
    private int jumpCount = 0;
	private boolean jumpState = false;
	private boolean clickState = false;
	private boolean jumpUpState = false;
	public static int x = 0;
    public static int y = 0;
    public static int y2 = 0;
    final double mainWidth;
	final double mainHeight;
	double moveMarioX;
	double moveMarioY;

	public static boolean isLeft;
    public static boolean isRight;
	public static boolean isSpace;
	public static boolean ypositive;
	public static boolean ynegative;

	
	public InstructionsPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
	    mainWidth = program.getWidth();
		mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
		marioWidth = mainWidth/18; //64.16
		marioHeight = mainHeight/8; 
		
		marioRun = new GImage(IMG_FOLDER + "mario1.gif", 0, 500);
		marioRun.setSize(marioWidth, marioHeight);
		
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
		program.add(marioRun);
		program.add(InstructionsList);
		program.add(bubblePipe);
		program.add(backLabel);
		program.add(bubbleImg);
	}

	@Override
	public void hideContents() {
		program.remove(InstructionsList);
		program.remove(marioRun);
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
			program.stopGuideSound();
			program.playMenuSound();
			program.menuPane.bubbleTimer.start();
			program.switchToMenu();
		}
		
		else {
			marioRun.setImage(IMG_FOLDER + "marioJump.png");
			marioRun.setSize(marioWidth, marioHeight);
		    program.playJumpSound();
			jumpState = true;
			clickState = true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		marioRun.move(10, 0);
		
		if (marioRun.getX() >= mainWidth) {
        	numTimes = 0;
        	marioRun.setLocation(0, 500);
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
		
		if(jumpState == false) {
			jumpCount = 0;
		}
		
		if (jumpState == true ){
			clickState = false;
			jumpUpState = false;
			
			if (jumpCount >=  0 && jumpCount < 5) {
				jumpUpState = true;
				jumpCount++;
			}
			
			if  (jumpCount >= 5 && jumpCount < JUMPint) {
				//jumpUpState = false;
				jumpCount++;
			}
			
			if (jumpUpState == true) {
				marioRun.move(15, -15);
			}
			
			if (jumpUpState == false) {
				marioRun.move(15, 15);
			}
			
			if (jumpCount == JUMPint) {
				clickState = false;
				jumpState = false;
				marioRun.setImage(IMG_FOLDER + "mario1.gif");
				marioRun.setSize(marioWidth, marioHeight);
			}
		}
		numTimes++;
		count++;
	}		
}