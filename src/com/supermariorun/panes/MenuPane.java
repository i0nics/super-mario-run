package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class MenuPane extends GraphicsPane {
	private mainSMR program; 
	public static final String IMG_FOLDER = "menuScreen/";
	private GButton ShopButton;
	private GButton TourButton;
	private GButton InstructB;
	private GImage menuBackground;
	private GImage menuMario;
	private GImage shopPipe;
	private GImage tourPipe;
	private GImage instrucPipe;
	
	private GImage ShopBubble;
	private GImage TourBubble;
	private GImage InstructBubble;

	public MenuPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		shopPipe = new GImage(IMG_FOLDER + "pipe.png", 380,700);
		ShopButton = new GButton("Shop", 400, 500, 90, 90);
		ShopButton.setFillColor(Color.BLUE);
		ShopBubble = new GImage(IMG_FOLDER + "shopBubble.png",380,595);
		TourButton = new GButton("Tour", 200, 500, 90, 90);
		TourButton.setFillColor(Color.GREEN);
		tourPipe = new GImage(IMG_FOLDER + "pipe.png",175,700);
		TourBubble = new GImage(IMG_FOLDER + "TourBubble.png",180,595);
		InstructB = new GButton("Instructions", 600, 500, 90, 90);
		InstructB.setFillColor(Color.RED);
		instrucPipe = new GImage(IMG_FOLDER + "pipe.png",575,700);
		InstructBubble = new GImage(IMG_FOLDER + "instrucBubble.png",580,595);
		menuBackground = new GImage(IMG_FOLDER + "menuBackground.png", 0, 0);
		menuMario = new GImage(IMG_FOLDER + "menuMario.png",600,15);
		
		
	}

	@Override
	public void showContents() {
		program.add(shopPipe);
		program.add(ShopBubble);
		program.add(tourPipe);
		program.add(instrucPipe);
		program.add(menuBackground);
		program.add(ShopButton);
		program.add(TourButton);
		program.add(TourBubble);
		program.add(InstructB);
		program.add(InstructBubble);
		program.add(menuMario);
		
	}

	@Override
	public void hideContents() {
		program.remove(ShopButton);
		program.remove(ShopBubble);
		program.remove(TourButton);
		program.remove(TourBubble);
		program.remove(InstructB);
		program.remove(menuBackground);
		program.remove(menuMario);
		program.remove(tourPipe);
		program.remove(shopPipe);
		program.remove(instrucPipe);
		program.remove(InstructBubble);
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
		else if(obj == TourButton){
			program.switchToTour();
		}
		
	}
}
