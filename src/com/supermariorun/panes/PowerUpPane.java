package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class PowerUpPane extends GraphicsPane {
	private mainSMR program;
	public static final String IMG_FOLDER = "menuScreen/";
	private GButton BackButton;
	private GImage BackPipe;
	private GButton MushroomButton;
	private GImage Mushroom;
	private GButton StarButton;
	private GImage Star;
	private GButton FlowerButton;
	private GImage Flower;
	
	public PowerUpPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		BackButton = new GButton("Back", 100, 100, 80, 80);
		BackButton.setFillColor(Color.GREEN);
		BackPipe = new GImage(IMG_FOLDER + "gPipeR.png",mainSMR.getWidth()/55, mainSMR.getHeight()/6.7);
		BackPipe.setSize(mainSMR.getWidth()/14, mainSMR.getHeight()/8);
		MushroomButton = new GButton("Mushroom", 200, 500, 150, 100);
		MushroomButton.setFillColor(Color.BLUE);
		Mushroom = new GImage("../media/Power Ups/Mushroom.png",mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		FlowerButton = new GButton("Flower", 500, 500, 150, 100);
		FlowerButton.setFillColor(Color.RED);
		Flower = new GImage("../media/Power Ups/Flower.png",mainSMR.getWidth()/2.5, mainSMR.getHeight()/3);		
		StarButton = new GButton("Star", 800, 500, 150, 100);
		StarButton.setFillColor(Color.YELLOW);
		Star = new GImage("../media/Power Ups/Star.png",mainSMR.getWidth()/1.52, mainSMR.getHeight()/3);
	}
	@Override
	public void showContents() {
		program.add(BackButton);
		program.add(BackPipe);
		program.add(MushroomButton);
		program.add(Mushroom);
		program.add(FlowerButton);
		program.add(Flower);
		program.add(StarButton);
		program.add(Star);
	}

	@Override
	public void hideContents() {
		program.remove(BackButton);
		program.remove(BackPipe);
		program.remove(MushroomButton);
		program.remove(Mushroom);
		program.remove(FlowerButton);
		program.remove(Flower);
		program.remove(StarButton);
		program.remove(Star);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton) {

			program.switchToShop();
		}
	}
}

