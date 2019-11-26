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

public class CharacterPane extends GraphicsPane implements ActionListener{
	private static final double MULT2 = 1.5;
	private static final double MULT = 1.2;
	private static final int LABEL_HEIGHT = 490;
	private static final int BUBBLE_HEIGHT = 450;
	private static final int CHAR_HEIGHT = 200;
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
	private GLabel costP;
	private GLabel costY;
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
		backLabel.setSize(labelWidth * MULT, labelHeight*MULT);

		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth * MULT, bubbleHeight*MULT);
		
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		BackPipe.setSize(pipeWidth, pipeHeight);
		
		marioPic = new GImage(IMG_FOLDER + "mario.png", 835,CHAR_HEIGHT);
		marioPic.setSize(230, 250);
		
		PrincessPic = new GImage(IMG_FOLDER + "Princess.png", 225, CHAR_HEIGHT);
		PrincessPic.setSize(185, 250);
		
		yoshiPic = new GImage(IMG_FOLDER + "yoshiPic.png", 535, CHAR_HEIGHT);
		yoshiPic.setSize(185, 250);
		
		marioBubble = new GImage(IMG_FOLDER + "bubble.png", 845,BUBBLE_HEIGHT);
		marioBubble.setSize(bubbleWidth * MULT, bubbleHeight * MULT);
		
		marioLabel = new GImage(IMG_FOLDER + "equippedButton.png", 865, 495);
		marioLabel.setSize(labelWidth * MULT, labelHeight * MULT);
		
		princessBubble = new GImage(IMG_FOLDER + "bubble.png",235,BUBBLE_HEIGHT);
		princessBubble.setSize(bubbleWidth * MULT, bubbleHeight*MULT);

		princessLabel = new GImage(IMG_FOLDER + "buyLabel.png",240,LABEL_HEIGHT);
		princessLabel.setSize(labelWidth * MULT2, labelHeight * MULT2);
		
		yoshiBubble = new GImage(IMG_FOLDER + "bubble.png",550,BUBBLE_HEIGHT);
		yoshiBubble.setSize(bubbleWidth * MULT, bubbleHeight * MULT);
		
		yoshiLabel = new GImage(IMG_FOLDER + "buyLabel.png",560, LABEL_HEIGHT);
		yoshiLabel.setSize(labelWidth * MULT2, labelHeight * MULT2);
		
		coinCount = new GLabel("Coins: " + progress.getNumCoins());
		coinCount.setLocation(900, 100);
		coinCount.setFont(program.marioFont.deriveFont(40f));
		coinCount.setColor(Color.DARK_GRAY);
		
		coin = new GImage(IMG_FOLDER + "coin.gif", 800, 35);
		coin.setSize(100, 100);
		
		costP = new GLabel("Cost: 50", mainSMR.getWidth() / 5, mainSMR.getHeight() / 1);
		costP.setFont(program.marioFont.deriveFont(30f));
		costP.setColor(Color.DARK_GRAY);
		
		costY = new GLabel("Cost: 50", mainSMR.getWidth() / 2.07, mainSMR.getHeight() / 1);
		costY.setFont(program.marioFont.deriveFont(30f));
		costY.setColor(Color.DARK_GRAY);
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
		program.add(costY);
		program.add(costP);
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