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
import starter.GButton;

public class CharacterPane extends GraphicsPane implements ActionListener{
	private mainSMR program;
	private playerProgress progress;
	public static final String IMG_FOLDER = "characterPane/";
	public static final String lABEL_FONT = "Arial-Bold-22";
	private GImage BackButton;
	private GImage BackPipe;
	private GImage yoshiBubble;
	private GImage princessBubble;
	private GImage yoshiPic;
	private GImage PrincessPic;
	private GImage background;
	private GImage backLabel;
	private GImage marioPic;
	private GImage marioBubble;
	private GImage marioLabel;
	
	private int count;
	private GImage coin;
	public Timer bubbleTimer;
	private static int PrincessCost = 50;
	private static int yoshiCost = 50;
	private GImage princessLabel;
	private GImage yoshiLabel;
	private GLabel coinCount;
	final double labelWidth;
	final double labelHeight;
	
	public CharacterPane(mainSMR mainSMR) {
		super();
		this.program = mainSMR;
		this.progress = program.getProgress();
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		labelWidth = mainWidth/12;
		labelHeight = mainHeight/12;
		
		bubbleTimer = new Timer(500, this);
		
		background = new GImage(IMG_FOLDER + "background.jpg", 0, 0);
		background.setSize(mainWidth, mainHeight);
		
		backLabel = new GImage(IMG_FOLDER + "backLabel.png",170, 75);
		backLabel.setSize(labelWidth * 1.2, labelHeight*1.2);

		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth * 1.2, bubbleHeight*1.2);
		
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		BackPipe.setSize(pipeWidth, pipeHeight);
		
		marioPic = new GImage(IMG_FOLDER + "mario.png", 835,200);
		marioPic.setSize(230, 250);
		
		PrincessPic = new GImage(IMG_FOLDER + "Princess.png", 225, 200);
		PrincessPic.setSize(185, 250);
		
		yoshiPic = new GImage(IMG_FOLDER + "yoshiPic.png", 535, 200);
		yoshiPic.setSize(185, 250);
		
		marioBubble = new GImage(IMG_FOLDER + "bubble.png", 845,450);
		marioBubble.setSize(bubbleWidth * 1.2, bubbleHeight * 1.2);
		
		marioLabel = new GImage(IMG_FOLDER + "equippedButton.png", 865, 495);
		marioLabel.setSize(labelWidth * 1.2, labelHeight * 1.2);
		
		princessBubble = new GImage(IMG_FOLDER + "bubble.png",235,450);
		princessBubble.setSize(bubbleWidth * 1.2, bubbleHeight*1.2);

		princessLabel = new GImage(IMG_FOLDER + "buyLabel.png",240,490);
		princessLabel.setSize(labelWidth * 1.5, labelHeight * 1.5);
		
		yoshiBubble = new GImage(IMG_FOLDER + "bubble.png",550,450);
		yoshiBubble.setSize(bubbleWidth * 1.2, bubbleHeight * 1.2);
		
		yoshiLabel = new GImage(IMG_FOLDER + "buyLabel.png",560, 490);
		yoshiLabel.setSize(labelWidth * 1.5, labelHeight * 1.5);
		
		coinCount = new GLabel("Coins: " + progress.getNumCoins());
		coinCount.setLocation(900, 100);
		coinCount.setFont(program.marioFont.deriveFont(40f));
		coinCount.setColor(Color.DARK_GRAY);
		
		coin = new GImage(IMG_FOLDER + "coin.gif", 810, 40);
		coin.setSize(100, 100);
	}
	
	@Override
	public void showContents() {
		bubbleTimer.start();
		coinCount.setLabel("Coins: " + progress.getNumCoins());
		program.add(background);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(yoshiPic);
		program.add(PrincessPic);
		program.add(yoshiBubble);
		program.add(princessBubble);
		program.add(backLabel);
		program.add(princessLabel);
		program.add(yoshiLabel);
		program.add(coinCount);
		program.add(coin);
		program.add(marioPic);
		program.add(marioBubble);
		program.add(marioLabel);
	}

	@Override
	public void hideContents() {
		bubbleTimer.stop();
		program.removeAll();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton || obj == backLabel || obj == BackPipe) {
			program.playPipeSound();
			program.switchToShop();
		}
		
		if(progress.isYoshiUnlocked() && (obj == yoshiBubble || obj == yoshiLabel)) {
			program.playButtonEffect();
			if (progress.isPrincessUnlocked()) {
				princessLabel.setImage(IMG_FOLDER + "equipButton.png");
				princessLabel.setSize(labelWidth * 1.3, labelHeight*1.3);
			}
			yoshiLabel.setImage(IMG_FOLDER + "equippedButton.png");
			yoshiLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
			progress.setCurrentCharacter("Yoshi");
			marioLabel.setImage(IMG_FOLDER + "equipButton.png");
			marioLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
		}
		
		if(progress.isPrincessUnlocked() && (obj == princessBubble || obj == princessLabel)) {
			program.playButtonEffect();
			if (progress.isYoshiUnlocked()) {
				yoshiLabel.setImage(IMG_FOLDER + "equipButton.png");
				yoshiLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
			}
			princessLabel.setImage(IMG_FOLDER + "equippedButton.png");
			princessLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
			progress.setCurrentCharacter("Princess");
			marioLabel.setImage(IMG_FOLDER + "equipButton.png");
			marioLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
		}
		
		if (!progress.isYoshiUnlocked() && (progress.getNumCoins() >= yoshiCost) && (obj == yoshiBubble || obj == yoshiLabel)) {
			program.playButtonEffect();
			YoshiTransaction();
			yoshiLabel.setImage(IMG_FOLDER + "equipButton.png");
			yoshiLabel.setSize(labelWidth*1.3, labelHeight*1.3);
		}
		
		if (!progress.isPrincessUnlocked() && (progress.getNumCoins() >= PrincessCost) && (obj == princessBubble || obj == princessLabel)) {
			program.playButtonEffect();
			PrincessTransaction();
			princessLabel.setImage(IMG_FOLDER + "equipButton.png");
			princessLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
		}
		
		if(obj == marioBubble || obj == marioLabel) {
			program.playButtonEffect();
			if (progress.isPrincessUnlocked()) {
				princessLabel.setImage(IMG_FOLDER + "equipButton.png");
				princessLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
			}
			
			if (progress.isYoshiUnlocked()) {
			yoshiLabel.setImage(IMG_FOLDER + "equipButton.png");
			yoshiLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
			}
			progress.setCurrentCharacter("mario");
			marioLabel.setImage(IMG_FOLDER + "equippedButton.png");
			marioLabel.setSize(labelWidth * 1.3, labelHeight * 1.3);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			princessBubble.move(0, 10);
			yoshiBubble.move(0, 10);
			backLabel.move(0, 10);
			BackButton.move(0, 10);
			princessLabel.move(0, 10);
			yoshiLabel.move(0,10);
			marioBubble.move(0,10);
			marioLabel.move(0, 10);
		}
		
		if (count == 2) {
			princessBubble.move(0, -10);
			yoshiBubble.move(0, -10);
			backLabel.move(0, -10);
			BackButton.move(0, -10);
			princessLabel.move(0, -10);
			yoshiLabel.move(0,-10);
			marioBubble.move(0, -10);
			marioLabel.move(0,-10);
			count = 0;
		}
		count++;
	}
	
	public void YoshiTransaction() {
		progress.getNumCoins();
		if(progress.getNumCoins() >= yoshiCost) {
			progress.setYoshiUnlocked();
			progress.decreaseCoins(yoshiCost);
			System.out.println("Purchase comfirmed");
			System.out.println(progress.getNumCoins());
			System.out.println("Yoshi Unlocked");
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
		
		else {
			System.out.println("not enough money");
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
	}
	
	public void PrincessTransaction() {
		if (progress.getNumCoins() >= PrincessCost) {
			progress.setPrincessUnlocked();
			progress.decreaseCoins(PrincessCost);
			System.out.println("Purchase comfirmed!");
			System.out.println("Princess Unlocked");
			System.out.println(progress.getNumCoins());
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
		
		else {
			System.out.println("not enough money");
			System.out.println(progress.getNumCoins());
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
	}	
}