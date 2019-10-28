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
		shopPipe = new GImage(IMG_FOLDER + "pipe.png", 380,500);
		ShopBubble = new GImage(IMG_FOLDER + "shopBubble.png",380,400);
		tourPipe = new GImage(IMG_FOLDER + "pipe.png",175,500);
		TourBubble = new GImage(IMG_FOLDER + "TourBubble.png",180,400);
		instrucPipe = new GImage(IMG_FOLDER + "pipe.png",575,500);
		InstructBubble = new GImage(IMG_FOLDER + "instrucBubble.png",580,400);
		menuBackground = new GImage(IMG_FOLDER + "menuBack.jpg", 0, 0);
		menuBackground.setSize(mainSMR.getWidth(), mainSMR.getHeight());
		menuMario = new GImage(IMG_FOLDER + "menuMario.png",600,15);
	}

	@Override
	public void showContents() {
		program.add(menuBackground);
		program.add(shopPipe);
		program.add(ShopBubble);
		program.add(tourPipe);
		program.add(instrucPipe);
		program.add(TourBubble);
		program.add(InstructBubble);
		program.add(menuMario);
	}

	@Override
	public void hideContents() {
		program.remove(ShopBubble);
		program.remove(TourBubble);
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
		
		if (obj == ShopBubble ) {
			program.switchToShop();
		}
		
		else if(obj == InstructBubble)
		{
			program.switchToInstructions();
		}
		
		else if(obj == TourBubble){
			program.switchToTour();
		}
	}
}
