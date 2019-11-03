package com.supermariorun.panes;

import java.awt.event.MouseEvent;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private mainSMR program; 
	public static final String IMG_FOLDER = "menuScreen/";
	private GImage menuBackground;
	private GImage shopPipe;
	private GImage tourPipe;
	private GImage guidePipe;
	private GImage shopBubble;
	private GImage tourBubble;
	private GImage guideBubble;
	private GImage tourLabel;
	private GImage shopLabel;
	private GImage guideLabel;

	public MenuPane(mainSMR mainSMR) {
		super();
		final double mainWidth = mainSMR.getWidth();
		final double mainHeight = mainSMR.getHeight();
		final double pipeHeight = mainHeight/3;
		final double pipeWidth = mainWidth/8;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		final double pipeY = mainHeight - 130;
		final double bubbleY = mainHeight/1.756;
		final double labelY = mainHeight/1.6;
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
		
		program = mainSMR;
	
		tourPipe = new GImage(IMG_FOLDER + "gPipe.png", mainWidth/7, pipeY);
		tourPipe.setSize(pipeWidth, pipeHeight);
		tourBubble = new GImage(IMG_FOLDER + "bubble.png", mainWidth/6.6, bubbleY);
		tourBubble.setSize(bubbleWidth, bubbleHeight);
		tourLabel = new GImage(IMG_FOLDER + "TourLabel.png", mainWidth/6.16, labelY);
		tourLabel.setSize(labelWidth, labelHeight);
		
		shopPipe = new GImage(IMG_FOLDER + "pPipe.png", mainWidth/2.33, pipeY);
		shopPipe.setSize(pipeWidth, pipeHeight);
		shopBubble = new GImage(IMG_FOLDER + "bubble.png", mainWidth/2.283, bubbleY);
		shopBubble.setSize(bubbleWidth, bubbleHeight);
		shopLabel = new GImage(IMG_FOLDER + "shopLabel.png", mainWidth/2.21, labelY);
		shopLabel.setSize(labelWidth, labelHeight);
		
		guidePipe = new GImage(IMG_FOLDER + "yPipe.png", mainWidth/1.4, pipeY);
		guidePipe.setSize(pipeWidth, pipeHeight);
		guideBubble = new GImage(IMG_FOLDER + "bubble.png", mainWidth/1.3832, bubbleY);
		guideBubble.setSize(bubbleWidth, bubbleHeight);
		guideLabel = new GImage(IMG_FOLDER + "guideLabel.png", mainWidth/1.3557, labelY);
		guideLabel.setSize(labelWidth, labelHeight);
		
		menuBackground = new GImage(IMG_FOLDER + "menuBack.gif", 0, 0);
		menuBackground.setSize(mainWidth, mainHeight);
	}

	@Override
	public void showContents() {
		program.add(menuBackground);
		program.add(shopPipe);
		program.add(shopBubble);
		program.add(tourPipe);
		program.add(guidePipe);
		program.add(tourBubble);
		program.add(guidePipe);
		program.add(guideBubble);
		program.add(tourLabel);
		program.add(shopLabel);
		program.add(guideLabel);
	}

	@Override
	public void hideContents() {
		program.remove(shopBubble);
		program.remove(tourBubble);
		program.remove(menuBackground);
		program.remove(tourPipe);
		program.remove(shopPipe);
		program.remove(guidePipe);
		program.remove(guidePipe);
		program.remove(tourLabel);
		program.remove(shopLabel);
		program.remove(guideLabel);
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == shopBubble ) {
			program.stopMenuSound();
			program.playPipeSound();
			program.playShopSound();
			program.switchToShop();
		}

		else if(obj == guidePipe) {
			program.playPipeSound();
			program.switchToInstructions();
		}

		else if(obj == tourBubble) {
			program.playPipeSound();
			program.stopMenuSound();
			program.playTourSound();
			program.switchToTour();
		}
	}
}
