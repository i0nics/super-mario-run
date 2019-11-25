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

public class PowerUpPane extends GraphicsPane implements ActionListener {
	private static final int PIPE_WH = 50;
	private static final int BUBBLE_HEIGHT = 450;
	private static final int MOVE_WIDTH = 0;
	private static final int MOVE_HEIGHT = 10;
	private mainSMR program;
	public static final String IMG_FOLDER = "powerUpPane/";
	public static final String lABEL_FONT = "Arial-Bold-22";
	private playerProgress progress;
	private GImage BackButton;
	private GImage BackPipe;
	private GImage MushroomBubble;
	private GImage Mushroom;
	private GImage StarBubble;
	private GImage Star;
	private GImage backLabel;
	private int count;
	private GImage coin;
	private Timer bubbleTimer;
	private GImage buyMushroom;
	private GImage buyStar;
	private GImage background;
	private GLabel coinCount;
	private static int mushroomCost = PIPE_WH;
	private static int starCost = PIPE_WH;
	final double labelWidth;
	final double labelHeight;
	
	public PowerUpPane(mainSMR mainSMR) {
		super();
		this.program = mainSMR;
		this.progress = program.getProgress();
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth / 6;
		final double pipeHeight = mainHeight / 6;
		final double bubbleWidth = mainWidth / 9 * 1.2;
		final double bubbleHeight = mainHeight / 5 *1.2;
		labelWidth = mainWidth / 12;
		labelHeight = mainHeight / 12;

		bubbleTimer = new Timer(500, this);

		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth, bubbleHeight);

		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -PIPE_WH, PIPE_WH);
		BackPipe.setSize(pipeWidth, pipeHeight);

		MushroomBubble = new GImage(IMG_FOLDER + "bubble.png", 200, BUBBLE_HEIGHT);
		MushroomBubble.setSize(bubbleWidth, bubbleHeight);

		Mushroom = new GImage(IMG_FOLDER + "Mushroom.png", mainSMR.getWidth() / 7, mainSMR.getHeight() / 3);

		StarBubble = new GImage(IMG_FOLDER + "bubble.png", 800, BUBBLE_HEIGHT);
		StarBubble.setSize(bubbleWidth, bubbleHeight);

		Star = new GImage(IMG_FOLDER + "Star.png", mainSMR.getWidth() / 1.52, mainSMR.getHeight() / 3);

		backLabel = new GImage(IMG_FOLDER + "backLabel.png", 170, 75);
		backLabel.setSize(labelWidth * 1.2, labelHeight * 1.2);

		background = new GImage(IMG_FOLDER + "background1.png", 0, 0);
		background.setSize(mainWidth, mainHeight);

		coinCount = new GLabel("Coins: " + progress.getNumCoins());
		coinCount.setFont(program.marioFont.deriveFont(40f));
		coinCount.setColor(Color.WHITE);
		coinCount.setLocation(900, 100);

		coin = new GImage(IMG_FOLDER + "coin.gif", 820, 35);
		coin.setSize(100, 100);
	}

	@Override
	public void showContents() {
		coinCount.setLabel("Coins: " + progress.getNumCoins());
		
		if (!program.getProgress().isMushroomPurchased()) {
			buyMushroom = new GImage(IMG_FOLDER + "buyLabel.png", 210, 495);
			buyMushroom.setSize(labelWidth * 1.5, labelHeight * 1.5);
		}
		
		if (!program.getProgress().isStarPurchased()) {
			buyStar = new GImage(IMG_FOLDER + "buyLabel.png", 810, 495);
			buyStar.setSize(labelWidth * 1.5, labelHeight * 1.5);
		}
		bubbleTimer.start();
		program.add(background);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(MushroomBubble);
		program.add(Mushroom);
		program.add(StarBubble);
		program.add(Star);
		program.add(backLabel);
		program.add(buyMushroom);
		program.add(buyStar);
		program.add(coinCount);
		program.add(coin);
	}

	@Override
	public void hideContents() {
		bubbleTimer.stop();
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {

		GObject obj = program.getElementAt(e.getX(), e.getY());

		if (obj == BackButton || obj == backLabel) {
			program.playPipeSound();
			program.switchToShop();
		}
		
		if(progress.isStarPurchased() && (obj == StarBubble || obj == buyStar)){
			program.playButtonEffect();
			if (progress.isMushroomPurchased()) {
				buyMushroom.setImage(IMG_FOLDER + "equipButton.png");
				buyMushroom.setSize(labelWidth*1.3, labelHeight*1.3);
			}
				buyStar.setImage(IMG_FOLDER + "equippedButton.png");
				buyStar.setSize(labelWidth*1.3, labelHeight*1.3);
				progress.setCurrentPowerUp("star");
			
		}
		
		if(progress.isMushroomPurchased() && (obj == MushroomBubble || obj == buyMushroom)){
			program.playButtonEffect();
			if (progress.isStarPurchased()) {
				buyStar.setImage(IMG_FOLDER + "equipButton.png");
				buyStar.setSize(labelWidth*1.3, labelHeight*1.3);
			}
				buyMushroom.setImage(IMG_FOLDER + "equippedButton.png");
				buyMushroom.setSize(labelWidth*1.3, labelHeight*1.3);
				progress.setCurrentPowerUp("big");
		}
		
		if (!progress.isMushroomPurchased() &&(progress.getNumCoins() >= mushroomCost)&& (obj == MushroomBubble || obj == buyMushroom)) {
			program.playButtonEffect();
			mushroomTransaction();
			buyMushroom.setImage(IMG_FOLDER + "equipButton.png");
			buyMushroom.setSize(labelWidth*1.3, labelHeight*1.3);
		}

		if (!progress.isStarPurchased() && (progress.getNumCoins() >= starCost) && (obj == StarBubble || obj == buyStar)){
			program.playButtonEffect();
			starTransaction();
			buyStar.setImage(IMG_FOLDER + "equipButton.png");
			buyStar.setSize(labelWidth*1.3, labelHeight*1.3);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			backLabel.move(MOVE_WIDTH, MOVE_HEIGHT);
			BackButton.move(MOVE_WIDTH, MOVE_HEIGHT);
			buyMushroom.move(MOVE_WIDTH, MOVE_HEIGHT);
			buyStar.move(MOVE_WIDTH, MOVE_HEIGHT);
			MushroomBubble.move(MOVE_WIDTH, MOVE_HEIGHT);
			StarBubble.move(MOVE_WIDTH, MOVE_HEIGHT);
		}

		if (count == 2) {
			backLabel.move(MOVE_WIDTH, -MOVE_HEIGHT);
			BackButton.move(MOVE_WIDTH, -MOVE_HEIGHT);
			buyMushroom.move(MOVE_WIDTH, -MOVE_HEIGHT);
			buyStar.move(MOVE_WIDTH, -MOVE_HEIGHT);
			MushroomBubble.move(MOVE_WIDTH, -MOVE_HEIGHT);
			StarBubble.move(MOVE_WIDTH, -MOVE_HEIGHT);
			count = MOVE_WIDTH;
		}

		count++;
	}

	public void starTransaction() {
		progress.getNumCoins();

		if (progress.getNumCoins() >= starCost) {
			progress.setStarPurchased();
			progress.decreaseCoins(starCost);
			System.out.println("Purchase comfirmed");
			System.out.println(progress.getNumCoins());
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}

		else {
			System.out.println("not enough money!");
			System.out.println(progress.getNumCoins());
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
	}

	public void mushroomTransaction() {

		if (progress.getNumCoins() >= mushroomCost) {
			progress.setMushroomPurchased();
			progress.decreaseCoins(mushroomCost);
			System.out.println("Purchase comfirmed");
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