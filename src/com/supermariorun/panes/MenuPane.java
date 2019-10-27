package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class MenuPane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to
						// all of the GraphicsProgram calls
	public static final String IMG_FOLDER = "startScreen/";
	private GButton ShopButton;
	private GButton TourButton;
	private GButton InstructB;
	private	GImage marioStart;
	private GImage menuBackground;
	private GImage menuMario;
	

	public MenuPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		ShopButton = new GButton("Shop", 400, 500, 100, 100);
		ShopButton.setFillColor(Color.BLUE);
		TourButton = new GButton("Tour", 200, 500, 100, 100);
		TourButton.setFillColor(Color.GREEN);
		InstructB = new GButton("Instructions", 600, 500, 100, 100);
		InstructB.setFillColor(Color.RED);
		marioStart = new GImage(IMG_FOLDER + "title.png", 250, 55);
		menuBackground = new GImage(IMG_FOLDER + "menuBackground.png", 0, 0);
		menuMario = new GImage(IMG_FOLDER + "menuMario.png",600,15);
		
		
	}

	@Override
	public void showContents() {
		program.add(menuBackground);
		program.add(ShopButton);
		program.add(TourButton);
		program.add(InstructB);
		program.add(marioStart);
		program.add(menuMario);
	}

	@Override
	public void hideContents() {
		program.remove(ShopButton);
		program.remove(TourButton);
		program.remove(InstructB);
		program.remove(marioStart);
		program.remove(menuBackground);
		program.remove(menuMario);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == ShopButton) {
			program.switchToShop();
		}
		else if(obj == InstructB)
		{
			program.switchToInstruct();
		}
		else {
			program.switchToTour();
		}
	}
}
