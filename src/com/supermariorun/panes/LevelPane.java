package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class LevelPane extends GraphicsPane{
	private mainSMR program;
	public static final String IMG_FOLDER = "menuScreen/";
	private GButton BackButton;
	private GImage BackPipe;
	private GButton Level1Button;
	private GButton Level2Button;
	private GButton Level3Button;
	private GButton Level4Button;


	public LevelPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		Level1Button = new GButton("Level 1", 100, 100, 100, 100);
		Level1Button.setFillColor(Color.BLUE);
		Level2Button = new GButton("Level 2", 200, 200, 200, 200);
		Level2Button.setFillColor(Color.BLUE);
		Level3Button = new GButton("Level 3", 300, 300, 300, 300);
		Level3Button.setFillColor(Color.BLUE);
		Level4Button = new GButton("Level 4", 400, 400, 400, 400);
		Level4Button.setFillColor(Color.BLUE);
		BackButton = new GButton("Back", 100, 100, 80, 80);
		BackButton.setFillColor(Color.GREEN);
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png",mainSMR.getWidth()/55, mainSMR.getHeight()/6.7);
		BackPipe.setSize(mainSMR.getWidth()/14, mainSMR.getHeight()/8);
	}

	@Override
	public void showContents() {
		program.add(BackButton);
		program.add(BackPipe);
		program.add(Level1Button);
		program.add(Level2Button);
		program.add(Level3Button);
		program.add(Level4Button);
	}

	@Override
	public void hideContents() {
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(Level1Button);
		program.remove(Level2Button);
		program.remove(Level3Button);
		program.remove(Level4Button);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		/*if (obj == TourButton) {
		//	program.playPipeSound();
		//	program.switchToLevel();
		}*/
		if (obj == Level1Button) {
			program.playLevelSound();
		}
		
		if (obj == BackButton) {
			program.playPipeSound();
			program.stopShopSound();
			program.playMenuSound();
			program.switchToMenu();
		}
	}
}