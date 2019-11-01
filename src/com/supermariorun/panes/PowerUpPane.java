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
	
	public PowerUpPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		BackButton = new GButton("Back", 100, 100, 80, 80);
		BackButton.setFillColor(Color.GREEN);
		
	}
	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(BackButton);
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(BackButton);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton) {

			program.switchToShop();
		}
	}
}

