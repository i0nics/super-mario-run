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

public class InventoryPane extends GraphicsPane implements ActionListener{
	private mainSMR program; 
	public static final String IMG_FOLDER = "inventoryPane/";
	private GImage BackButton;
	private GImage BackPipe;
	private GImage backLabel;
	private GImage background;
	private GImage Mushroom;
	private GImage Star;
	private GImage Flower;
	private int count;
	public Timer bubbleTimer;

	public InventoryPane(mainSMR mainSMR) {
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
		backLabel = new GImage(IMG_FOLDER + "backLabel.png",170, 75);
		backLabel.setSize(labelWidth*1.2, labelHeight*1.2);
		background = new GImage(IMG_FOLDER + "Inventory Slots.png", 0, 0);
		background.setSize(mainSMR.getWidth(), mainSMR.getHeight());
		Mushroom = new GImage(IMG_FOLDER + "Mushroom.png", mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		Flower = new GImage(IMG_FOLDER + "Flower.png",mainSMR.getWidth()/2.5, mainSMR.getHeight()/3);
		Star = new GImage(IMG_FOLDER + "Star.png", mainSMR.getWidth()/1.52, mainSMR.getHeight()/3);
		bubbleTimer.start();
	}

	@Override
	public void showContents() {
		program.add(BackButton);
		program.add(BackPipe);
		program.add(backLabel);
		program.add(background);
		program.add(Mushroom);
		program.add(Flower);
		program.add(Star);
	}

	@Override
	public void hideContents() {
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(backLabel);
		program.remove(background);
		program.remove(Mushroom);
		program.remove(Flower);
		program.remove(Star);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton) {

			program.switchToShop();
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
}
