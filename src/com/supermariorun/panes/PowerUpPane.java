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
	private mainSMR program;
	public static final String IMG_FOLDER = "powerUpPane/";
	public static final String lABEL_FONT = "Arial-Bold-22";
	private playerProgress progress;
	private GImage BackButton;
	private GImage BackPipe;
	private GImage MushroomButton;
	private GImage Mushroom;
	private GImage StarButton;
	private GImage Star;
	private GImage backLabel;
	private int count;
	private GImage coin;
	private Timer bubbleTimer;
	private GImage buyLabelm;
	private GImage buyLabels;
	private GImage background;
	private GLabel coinCount;
	private static int mushroomCost = 50;
	private static int starCost = 50;
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
		final double bubbleWidth = mainWidth / 9;
		final double bubbleHeight = mainHeight / 5;
		labelWidth = mainWidth / 12;
		labelHeight = mainHeight / 12;

		bubbleTimer = new Timer(500, this);

		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth * 1.2, bubbleHeight * 1.2);

		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		BackPipe.setSize(pipeWidth, pipeHeight);

		MushroomButton = new GImage(IMG_FOLDER + "bubble.png", 200, 450);
		MushroomButton.setSize(bubbleWidth * 1.2, bubbleHeight * 1.2);

		Mushroom = new GImage(IMG_FOLDER + "Mushroom.png", mainSMR.getWidth() / 7, mainSMR.getHeight() / 3);

		StarButton = new GImage(IMG_FOLDER + "bubble.png", 800, 450);
		StarButton.setSize(bubbleWidth * 1.2, bubbleHeight * 1.2);

		Star = new GImage(IMG_FOLDER + "Star.png", mainSMR.getWidth() / 1.52, mainSMR.getHeight() / 3);

		backLabel = new GImage(IMG_FOLDER + "backLabel.png", 170, 75);
		backLabel.setSize(labelWidth * 1.2, labelHeight * 1.2);

		buyLabelm = new GImage(IMG_FOLDER + "buyLabel.png", 210, 485);
		buyLabelm.setSize(labelWidth * 1.5, labelHeight * 1.5);


		buyLabels = new GImage(IMG_FOLDER + "buyLabel.png", 810, 485);
		buyLabels.setSize(labelWidth * 1.5, labelHeight * 1.5);

		background = new GImage(IMG_FOLDER + "background1.png", 0, 0);
		background.setSize(mainWidth, mainHeight);

		coinCount = new GLabel("Coins: " + progress.getNumCoins());
		coinCount.setFont(lABEL_FONT);
		coinCount.setColor(Color.WHITE);
		coinCount.setLocation(400, 100);

		coin = new GImage(IMG_FOLDER + "coin.gif", 320,50);
		coin.setSize(100, 100);
	}

	@Override
	public void showContents() {
		coinCount.setLabel("Coins: " + progress.getNumCoins());
		bubbleTimer.start();
		program.add(background);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(MushroomButton);
		program.add(Mushroom);
		program.add(StarButton);
		program.add(Star);
		program.add(backLabel);
		program.add(buyLabelm);
		program.add(buyLabels);
		program.add(coinCount);
		program.add(coin);
	}

	@Override
	public void hideContents() {
		bubbleTimer.stop();
		program.remove(background);
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(MushroomButton);
		program.remove(Mushroom);
		program.remove(StarButton);
		program.remove(Star);
		program.remove(backLabel);
		program.remove(buyLabelm);
		program.remove(buyLabels);
		program.remove(coinCount);
		program.remove(coin);
	}

	@Override
	public void mousePressed(MouseEvent e) {

		GObject obj = program.getElementAt(e.getX(), e.getY());

		if (obj == BackButton || obj == backLabel) {
			program.playPipeSound();
			program.switchToShop();
		}
		
		if(progress.isStarPurchased() && (obj == StarButton || obj == buyLabels)){
			if (progress.isMushroomPurchased()) {
				buyLabelm.setImage(IMG_FOLDER + "equipButton.png");
				buyLabelm.setSize(labelWidth*1.3, labelHeight*1.3);
			}
			buyLabels.setImage(IMG_FOLDER + "equippedButton.png");
			buyLabels.setSize(labelWidth*1.3, labelHeight*1.3);
			progress.setCurrentPowerUp("star");
		}
		
		if(progress.isMushroomPurchased() && (obj == MushroomButton || obj == buyLabelm)){
			if (progress.isStarPurchased()) {
				buyLabels.setImage(IMG_FOLDER + "equipButton.png");
				buyLabels.setSize(labelWidth*1.3, labelHeight*1.3);
			}
			buyLabelm.setImage(IMG_FOLDER + "equippedButton.png");
			buyLabelm.setSize(labelWidth*1.3, labelHeight*1.3);
			//progress.setCurrentPowerUp("Mushroom");
		}
		if (!progress.isMushroomPurchased() && (obj == MushroomButton || obj == buyLabelm)) {
			mushroomTransaction();
			buyLabelm.setImage(IMG_FOLDER + "equipButton.png");
			buyLabelm.setSize(labelWidth*1.3, labelHeight*1.3);
		}

		if (!progress.isStarPurchased() && (obj == StarButton || obj == buyLabels)){
			starTransaction();
			buyLabels.setImage(IMG_FOLDER + "equipButton.png");
			buyLabels.setSize(labelWidth*1.3, labelHeight*1.3);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			backLabel.move(0, 10);
			BackButton.move(0, 10);
			buyLabelm.move(0, 10);
			buyLabels.move(0, 10);
			MushroomButton.move(0, 10);
			StarButton.move(0, 10);
		}

		if (count == 2) {
			backLabel.move(0, -10);
			BackButton.move(0, -10);
			buyLabelm.move(0, -10);
			buyLabels.move(0, -10);
			MushroomButton.move(0, -10);
			StarButton.move(0, -10);
			count = 0;
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