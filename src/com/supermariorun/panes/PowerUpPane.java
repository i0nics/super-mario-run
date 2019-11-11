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
import acm.graphics.GObject;
import starter.GButton;

public class PowerUpPane extends GraphicsPane implements ActionListener {
	private mainSMR program;
	public static final String IMG_FOLDER = "powerUpPane/";
	private GImage BackButton;
	private GImage BackPipe;
	private GButton MushroomButton;
	private GImage Mushroom;
	private GButton StarButton;
	private GImage Star;
	private GButton FlowerButton;
	private GImage Flower;
	private GImage backLabel;
	private int count;
	public Timer bubbleTimer;
	private static int flowerCost = 50;
	private static int mushroomCost = 50;
	private static int starCost = 50;
	
	public PowerUpPane(mainSMR mainSMR) {
		super();
		this.program = mainSMR;
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
		
		bubbleTimer = new Timer(500, this);
		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		BackPipe.setSize(pipeWidth, pipeHeight);
		MushroomButton = new GButton("Mushroom", 200, 500, 150, 100);
		MushroomButton.setFillColor(Color.BLUE);
		Mushroom = new GImage(IMG_FOLDER + "Mushroom.png", mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		FlowerButton = new GButton("Flower", 500, 500, 150, 100);
		FlowerButton.setFillColor(Color.RED);
		Flower = new GImage(IMG_FOLDER + "Flower.png",mainSMR.getWidth()/2.5, mainSMR.getHeight()/3);		
		StarButton = new GButton("Star", 800, 500, 150, 100);
		StarButton.setFillColor(Color.YELLOW);
		Star = new GImage(IMG_FOLDER + "Star.png", mainSMR.getWidth()/1.52, mainSMR.getHeight()/3);
		backLabel = new GImage(IMG_FOLDER + "backLabel.png",170, 75);
		backLabel.setSize(labelWidth*1.2, labelHeight*1.2);
		bubbleTimer.start();
	}
	@Override
	public void showContents() {
		program.add(BackButton);
		program.add(BackPipe);
		program.add(MushroomButton);
		program.add(Mushroom);
		program.add(FlowerButton);
		program.add(Flower);
		program.add(StarButton);
		program.add(Star);
		program.add(backLabel);
	}

	@Override
	public void hideContents() {
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(MushroomButton);
		program.remove(Mushroom);
		program.remove(FlowerButton);
		program.remove(Flower);
		program.remove(StarButton);
		program.remove(Star);
		program.remove(backLabel);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton) {
			program.switchToShop();
		}
		if(obj == MushroomButton)
		{
			mushroomTransaction();
		}
		if(obj == StarButton)
		{
			starTransaction();
		}
		if(obj == FlowerButton) {
			flowerTransaction();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			
			backLabel.move(0, 10);
			BackButton.move(0, 10);
		}
		
		if (count == 2) {
			
			backLabel.move(0, -10);
			BackButton.move(0, -10);
			
			count = 0;
		}
		count++;
		
	}
	public void starTransaction() {
		playerProgress.getNumCoins();
		if(playerProgress.getNumCoins() >= starCost)
		{
			System.out.println("Purchase comfirmed");
		}
		else if (playerProgress.getNumCoins() < starCost)
		{
			System.out.println("not enough money");
		}
	}
	public void mushroomTransaction() {
		if(playerProgress.getNumCoins() >= mushroomCost)
		{
			System.out.println("Purchase comfirmed");
		}
		else if (playerProgress.getNumCoins() < mushroomCost)
		{
			System.out.println("not enough money");
		}
	}
	public void flowerTransaction() {
		if(playerProgress.getNumCoins() >= flowerCost) {
			System.out.println("Purchase comfirmed");
		}
		else if(playerProgress.getNumCoins() < flowerCost)
		{
			System.out.println("not enough money");
		}
	}	
		
		
	}


