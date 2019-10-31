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
	private GImage shopPipe;
	private GImage tourPipe;
	private GImage instrucPipe;
	private GImage ShopBubble;
	private GImage TourBubble;
	private GImage InstructBubble;

	public MenuPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		tourPipe = new GImage(IMG_FOLDER + "gPipe.png",mainSMR.getWidth()/7, mainSMR.getHeight()/1.3);
		TourBubble = new GImage(IMG_FOLDER + "TourBubble.png", mainSMR.getWidth()/7, mainSMR.getHeight()/1.625);
		tourPipe.setSize(mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		
		shopPipe = new GImage(IMG_FOLDER + "pPipe.png",mainSMR.getWidth()/2.3, mainSMR.getHeight()/1.3);
		ShopBubble = new GImage(IMG_FOLDER + "shopBubble.png", mainSMR.getWidth()/2.3, mainSMR.getHeight()/1.625);
		shopPipe.setSize(mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		
		instrucPipe = new GImage(IMG_FOLDER + "yPipe.png", mainSMR.getWidth()/1.4, mainSMR.getHeight()/1.3);
		InstructBubble = new GImage(IMG_FOLDER + "instrucBubble.png", mainSMR.getWidth()/1.4, mainSMR.getHeight()/1.625);
		instrucPipe.setSize(mainSMR.getWidth()/7, mainSMR.getHeight()/3);
		
		
		menuBackground = new GImage(IMG_FOLDER + "menuBack.png", 0, 0);
		menuBackground.setSize(mainSMR.getWidth(), mainSMR.getHeight());
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
	}

	@Override
	public void hideContents() {
		program.remove(ShopBubble);
		program.remove(TourBubble);
		program.remove(menuBackground);
		program.remove(tourPipe);
		program.remove(shopPipe);
		program.remove(instrucPipe);
		program.remove(InstructBubble);
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == ShopBubble ) {
			program.stopMenuSound();
			program.playPipeSound();
			program.playShopSound();
			program.switchToShop();
		}

		else if(obj == InstructBubble) {
			program.playPipeSound();
			program.switchToInstructions();
		}

		else if(obj == TourBubble) {
			program.playPipeSound();
			program.stopMenuSound();
			program.playTourSound();
			program.switchToTour();
		}
	}
}
