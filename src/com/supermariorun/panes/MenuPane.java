package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GObject;
import starter.GButton;

public class MenuPane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton ShopButton;
	private GButton TourButton;
	private GButton InstructB;
	

	public MenuPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		ShopButton = new GButton("Shop", 400, 400, 100, 100);
		ShopButton.setFillColor(Color.BLUE);
		TourButton = new GButton("Tour", 200, 400, 100, 100);
		TourButton.setFillColor(Color.GREEN);
		InstructB = new GButton("Instructions", 600, 400, 100, 100);
		InstructB.setFillColor(Color.RED);
	}

	@Override
	public void showContents() {
		program.add(ShopButton);
		program.add(TourButton);
		program.add(InstructB);
	}

	@Override
	public void hideContents() {
		program.remove(ShopButton);
		program.remove(TourButton);
		program.remove(InstructB);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == ShopButton) {
			program.switchToShop();
		}
	}
}
