package com.supermariorun.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.MainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class ShopPane extends GraphicsPane implements ActionListener {
	private static final int MOVE_HEIGHT = 10;
	private static final int MOVE_WIDTH = 0;
	private MainSMR program; 
	public static final String IMG_FOLDER = "shopPane/";
	private GImage PowerUpButton;
	private GImage CharacterButton;
	private GImage BackButton;
	private GImage BackPipe;
	private GImage background;
	private GImage toad;
	private GImage backLabel;
	private GImage characterLabel;
	private GImage powerLabel;
	private int count = 1;
	private Timer bubbleTimer;
	
	public ShopPane(MainSMR mainSMR) {
		super();
		this.program = mainSMR;
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9 * 1.2;
		final double bubbleHeight = mainHeight/5 * 1.2;
		final double labelWidth = mainWidth/12 * 1.2;
		final double labelHeight = mainHeight/12 * 1.2;
			
		bubbleTimer = new Timer(500, this);
		
		PowerUpButton = new GImage("bubble.png", 770, 200);
		PowerUpButton.setSize(bubbleWidth, bubbleHeight);
	
		CharacterButton = new GImage("bubble.png", 275, 200);
		CharacterButton.setSize(bubbleWidth, bubbleHeight);
	
		BackButton = new GImage(IMG_FOLDER + "bubble.png", 152, 30);
		BackButton.setSize(bubbleWidth, bubbleHeight);
	
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png", -50, 50);
		BackPipe.setSize(pipeWidth, pipeHeight);
	
		background = new GImage(IMG_FOLDER + "redStripes.png", 0, 0);
		background.setSize(mainSMR.getWidth(), mainSMR.getHeight());
		
		toad = new GImage(IMG_FOLDER + "tum.png",380, 257);
		toad.setSize(400, 450);
		
		backLabel = new GImage(IMG_FOLDER + "backLabel.png", 170, 75);
		backLabel.setSize(labelWidth, labelHeight);
		
		characterLabel = new GImage(IMG_FOLDER + "charactersButton.png", 290, 245);
		characterLabel.setSize(labelWidth*1.12, labelHeight*1.12);
		
		powerLabel = new GImage(IMG_FOLDER + "powerUpsButton.png", 790, 245);
		powerLabel.setSize(labelWidth, labelHeight);
	}

	@Override
	public void showContents() {
		bubbleTimer.start();
		program.add(background);
		program.add(PowerUpButton);
		program.add(CharacterButton);
		program.add(BackButton);
		program.add(BackPipe);
		program.add(backLabel);
		program.add(characterLabel);
		program.add(powerLabel);
		program.add(toad);
	}

	@Override
	public void hideContents() {
		bubbleTimer.stop();
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == PowerUpButton || obj == powerLabel) {
			program.playButtonEffect();
			program.switchToPowerUp();
		}
		
		if (obj == CharacterButton || obj == characterLabel) {
			program.playButtonEffect();
			program.switchToCharacter();
		}
			
		if (obj == BackButton || obj == backLabel || obj == BackPipe) {
			program.playPipeSound();
			program.stopShopSound();
			program.playMenuSound();
			program.switchToMenu();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			PowerUpButton.move(MOVE_WIDTH, MOVE_HEIGHT);
			powerLabel.move(MOVE_WIDTH, MOVE_HEIGHT);
			CharacterButton.move(MOVE_WIDTH, -MOVE_HEIGHT);
			characterLabel.move(MOVE_WIDTH, -MOVE_HEIGHT);
			backLabel.move(MOVE_WIDTH, MOVE_HEIGHT);
			BackButton.move(MOVE_WIDTH, MOVE_HEIGHT);
		}
		
		if (count == 2) {
			PowerUpButton.move(MOVE_WIDTH, -MOVE_HEIGHT);
			powerLabel.move(MOVE_WIDTH, -MOVE_HEIGHT);
			CharacterButton.move(MOVE_WIDTH, +MOVE_HEIGHT);
			characterLabel.move(MOVE_WIDTH, +MOVE_HEIGHT);
			backLabel.move(MOVE_WIDTH, -MOVE_HEIGHT);
			BackButton.move(MOVE_WIDTH, -MOVE_HEIGHT);
			
			count = 0;
		}
		count++;
	}
}
