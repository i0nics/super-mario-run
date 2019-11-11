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
	private GImage LuigiButton;
	private GImage PrincessButton;
	private GImage LuigiPic;
	private GImage PrincessPic;
	private GImage background;
	private GImage backLabel;
	private int count;
	public Timer bubbleTimer;
	private static int PrincessCost = 110;
	private static int luigiCost = 50;
	private GImage buyLabel;
	private GImage buyLabel2;
	private GLabel coinCount;
	
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
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
		
		bubbleTimer = new Timer(500, this);
	
		LuigiButton = new GImage(IMG_FOLDER + "bubble.png",690,450);
		LuigiButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		
		LuigiPic = new GImage(IMG_FOLDER + "Luigi.png", 675, 200);
		
		PrincessPic = new GImage(IMG_FOLDER + "Princess.png", 325, 200);
		PrincessPic.setSize(185, 250);
		
		PrincessButton = new GImage(IMG_FOLDER + "bubble.png",335,450);
		PrincessButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		
		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		BackPipe.setSize(pipeWidth, pipeHeight);
		
		background = new GImage(IMG_FOLDER + "background.jpg", 0, 0);
		background.setSize(mainWidth, mainHeight);
		
		backLabel = new GImage(IMG_FOLDER + "backLabel.png",170, 75);
		backLabel.setSize(labelWidth*1.2, labelHeight*1.2);
		
		buyLabel = new GImage(IMG_FOLDER + "buyLabel.png",340,490);
		buyLabel.setSize(labelWidth*1.5, labelHeight*1.5);
		
		buyLabel2 = new GImage(IMG_FOLDER + "buyLabel.png",700,490);
		buyLabel2.setSize(labelWidth*1.5, labelHeight*1.5);
		
		coinCount = new GLabel("Coins: " + progress.getNumCoins());
		coinCount.setLocation(400, 100);
		coinCount.setFont(lABEL_FONT);
		
		bubbleTimer.start();
	}
	
	@Override
	public void showContents() {
		coinCount.setLabel("Coins: " + progress.getNumCoins());
		program.add(background);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(LuigiPic);
		program.add(PrincessPic);
		program.add(LuigiButton);
		program.add(PrincessButton);
		program.add(backLabel);
		program.add(buyLabel);
		program.add(buyLabel2);
		program.add(coinCount);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(LuigiPic);
		program.remove(PrincessPic);
		program.remove(LuigiButton);
		program.remove(PrincessButton);
		program.remove(backLabel);
		program.remove(buyLabel);
		program.remove(buyLabel2);
		program.remove(coinCount);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton || obj == backLabel || obj == BackPipe) {
			program.playPipeSound();

			program.switchToShop();
		}
		
		if(obj == LuigiButton || obj == buyLabel2) {
			Luigitransaction();
		}
		
		if(obj == PrincessButton || obj == buyLabel) {
			PrincessTransaction();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			PrincessButton.move(0, 10);
			LuigiButton.move(0, 10);
			backLabel.move(0, 10);
			BackButton.move(0, 10);
			buyLabel.move(0, 10);
			buyLabel2.move(0,10);
		}
		
		if (count == 2) {
			PrincessButton.move(0, -10);
			LuigiButton.move(0, -10);
			backLabel.move(0, -10);
			BackButton.move(0, -10);
			buyLabel.move(0, -10);
			buyLabel2.move(0,-10);
			count = 0;
		}
		count++;
	}
	
	public void Luigitransaction() {
		progress.getNumCoins();
		if(progress.getNumCoins() >= luigiCost) {
			progress.setLuigiUnlocked();
			progress.decreaseCoins(luigiCost);
			System.out.println("Purchase comfirmed");
			System.out.println(progress.getNumCoins());
			System.out.println("Luigi Unlocked");
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
		
		else if (progress.getNumCoins() < luigiCost) {
			System.out.println("not enough money");
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
	}
	
	public void PrincessTransaction() {
		if(progress.getNumCoins() >= PrincessCost) {
			progress.setPrincessUnlocked();
			progress.decreaseCoins(PrincessCost);
			System.out.println("Purchase comfirmed!");
			System.out.println("Princess Unlocked");
			System.out.println(progress.getNumCoins());
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
		
		else if (progress.getNumCoins() < PrincessCost) {
			System.out.println("not enough money");
			System.out.println(progress.getNumCoins());
			coinCount.setLabel("Coins: " + progress.getNumCoins());
		}
	}	
}