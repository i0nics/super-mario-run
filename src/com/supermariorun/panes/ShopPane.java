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

public class ShopPane extends GraphicsPane implements ActionListener {
	private mainSMR program; 
	public static final String IMG_FOLDER = "shopPane/";
	private GImage PowerUpButton;
	private GImage CharacterButton;
	private GImage BackButton;
	private GImage BackPipe;
	private GImage background;
	private GImage InventoryButton;
	private GImage toad;
	private GImage backLabel;
	private GImage inventoryLabel;
	private GImage characterLabel;
	private GImage powerLabel;
	private int count = 1;
	public Timer bubbleTimer;
	
	
	public ShopPane(mainSMR mainSMR) {
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
		PowerUpButton = new GImage("bubble.png", 770, 200);
		PowerUpButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		CharacterButton = new GImage("bubble.png", 270, 200);
		CharacterButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		BackPipe.setSize(pipeWidth, pipeHeight);
		background = new GImage(IMG_FOLDER + "redStripes.png", 0, 0);
		background.setSize(mainSMR.getWidth(), mainSMR.getHeight());
		InventoryButton = new GImage("bubble.png", 975, 30);
		InventoryButton.setSize(bubbleWidth*1.2, bubbleHeight*1.2);
		toad = new GImage(IMG_FOLDER + "tum.png",300,100);
		toad.setSize(mainSMR.getWidth()/2, mainSMR.getHeight()/1.2);
		backLabel = new GImage(IMG_FOLDER + "backLabel.png", 170, 75);
		backLabel.setSize(labelWidth*1.2, labelHeight*1.2);
		inventoryLabel = new GImage(IMG_FOLDER + "inventoryButton.png",992,75);
		inventoryLabel.setSize(labelWidth*1.2, labelHeight*1.2);
		characterLabel = new GImage(IMG_FOLDER + "charactersButton.png",290,245);
		characterLabel.setSize(labelWidth*1.2, labelHeight*1.2);
		powerLabel = new GImage(IMG_FOLDER + "powerUpsButton.png",790,245);
		powerLabel.setSize(labelWidth*1.2, labelHeight*1.2);
		bubbleTimer.start();
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(PowerUpButton);
		program.add(CharacterButton);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(InventoryButton);
		program.add(toad);
		program.add(backLabel);
		program.add(inventoryLabel);
		program.add(characterLabel);
		program.add(powerLabel);
	}

	@Override
	public void hideContents() {
		program.remove(background);
		program.remove(PowerUpButton);
		program.remove(CharacterButton);
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(InventoryButton);
		program.remove(toad);
		program.remove(backLabel);
		program.remove(inventoryLabel);
		program.remove(characterLabel);
		program.remove(powerLabel);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == PowerUpButton || obj == powerLabel) {
			program.playPipeSound();
			program.switchToPowerUp();
		}
		
		if (obj == CharacterButton || obj == characterLabel) {
			program.playPipeSound();
			program.switchToCharacter();
		}
		
		if (obj == InventoryButton || obj == inventoryLabel) {
			program.playPipeSound();
			program.switchToInventory();
		}
		
		if (obj == BackButton || obj == backLabel || obj == BackPipe) {
			program.menuPane.bubbleTimer.start();
			program.playPipeSound();
			program.stopShopSound();
			program.playMenuSound();
			program.switchToMenu();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			PowerUpButton.move(0, 10);
			powerLabel.move(0, 10);
			CharacterButton.move(0, -10);
			characterLabel.move(0, -10);
			backLabel.move(0, 10);
			BackButton.move(0, 10);
			InventoryButton.move(0,10);
			inventoryLabel.move(0, 10);
		}
		
		if (count == 2) {
			PowerUpButton.move(0, -10);
			powerLabel.move(0, -10);
			CharacterButton.move(0, +10);
			characterLabel.move(0, +10);
			backLabel.move(0, -10);
			BackButton.move(0, -10);
			inventoryLabel.move(0, -10);
			InventoryButton.move(0,-10);
			
			count = 0;
		}
		count++;
	}
}
