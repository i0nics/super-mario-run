package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GObject;
import starter.GButton;

public class ShopPane extends GraphicsPane {
	private mainSMR program; 
	private GButton PowerUpButton;
	private GButton CharacterButton;
	private GButton BackButton;

	public ShopPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		PowerUpButton = new GButton("Power Up", 500, 500, 200, 200);
		PowerUpButton.setFillColor(Color.BLUE);
		CharacterButton = new GButton("Character", 200, 500, 200, 200);
		CharacterButton.setFillColor(Color.RED);
		BackButton = new GButton("Back", 100, 100, 100, 100);
		BackButton.setFillColor(Color.GREEN);
	}

	@Override
	public void showContents() {
		program.add(PowerUpButton);
		program.add(CharacterButton);
		program.add(BackButton);
	}

	@Override
	public void hideContents() {
		program.remove(PowerUpButton);
		program.remove(CharacterButton);
		program.remove(BackButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == PowerUpButton) {
			program.switchToPowerUp();
		}
		
		else if(obj == CharacterButton) {
			program.switchToCharacter();
		}
		
		else {
			program.stopShopSound();
			program.playStartSound();
			program.switchToMenu();
		}
	}
}
