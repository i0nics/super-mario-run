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

public class CharacterPane extends GraphicsPane implements ActionListener{
	private mainSMR program;
	public static final String IMG_FOLDER = "characterPane/";
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
	private static int luigiCost = 110;
	
	public CharacterPane(mainSMR mainSMR) {
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
		LuigiButton = new GImage(IMG_FOLDER + "bubble.png",675,450);
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
		bubbleTimer.start();
	}
	@Override
	public void showContents() {
		program.add(background);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(LuigiPic);
		program.add(PrincessPic);
		program.add(LuigiButton);
		program.add(PrincessButton);
		program.add(backLabel);
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
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton || obj == backLabel || obj == BackPipe) {

			program.switchToShop();
		}
		if(obj == LuigiButton)
		{
			Luigitransaction();
		}
		if(obj == PrincessButton)
		{
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
		}
		
		if (count == 2) {
			PrincessButton.move(0, -10);
			LuigiButton.move(0, -10);
			backLabel.move(0, -10);
			BackButton.move(0, -10);
			
			count = 0;
		}
		count++;
		
	}
	public void Luigitransaction() {
		playerProgress.getNumCoins();
		if(playerProgress.getNumCoins() >= luigiCost)
		{
			System.out.println("Purchase comfirmed");
		}
		else if (playerProgress.getNumCoins() < luigiCost)
		{
			System.out.println("not enough money");
		}
	}
	public void PrincessTransaction() {
		if(playerProgress.getNumCoins() >= PrincessCost)
		{
			System.out.println("Purchase comfirmed");
		}
		else if (playerProgress.getNumCoins() < PrincessCost)
		{
			System.out.println("not enough money");
		}
	}
		
	}

