package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GObject;
import starter.GButton;

public class PowerUpPane extends GraphicsPane {
	private mainSMR program; 
	private GButton BackButton;
	private GButton MushroomButton;
	private GButton StarButton;
	
	public PowerUpPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		BackButton = new GButton("Back", 100, 100, 80, 80);
		BackButton.setFillColor(Color.GREEN);
		MushroomButton = new GButton("Mushroom", 200, 500, 150, 100);
		MushroomButton.setFillColor(Color.BLUE);
		StarButton = new GButton("Star", 700, 500, 150, 100);
		StarButton.setFillColor(Color.YELLOW);
	}
	@Override
	public void showContents() {
		program.add(BackButton);
		program.add(MushroomButton);
		program.add(StarButton);
	}

	@Override
	public void hideContents() {
		program.remove(BackButton);
		program.remove(MushroomButton);
		program.remove(StarButton);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton) {

			program.switchToShop();
		}
	}
}

