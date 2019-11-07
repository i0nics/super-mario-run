package com.supermariorun.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane implements ActionListener {
	public static final String IMG_FOLDER = "menuScreen/";
	public Timer bubbleTimer;
	
	private mainSMR program; 
	private GImage menuBackground;
	private GImage shopPipe;
	private GImage tourPipe;
	private GImage guidePipe;
	private GImage tourBubble;
	private GImage shopBubble;
	private GImage guideBubble;
	private GImage tourLabel;
	private GImage shopLabel;
	private GImage guideLabel;
	private int count = 1;
	
	public MenuPane(mainSMR mainSMR) {
		super();
		this.program = mainSMR;
		bubbleTimer = new Timer(500, this);
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeHeight = mainHeight/3;
		final double pipeWidth = mainWidth/8;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		final double pipeY = mainHeight - 130;
		final double bubbleY = mainHeight/1.756;
		final double labelY = mainHeight/1.6;
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
	
		tourPipe = new GImage(IMG_FOLDER + "gPipe.png", mainWidth/7, pipeY);
		tourPipe.setSize(pipeWidth, pipeHeight);
		tourBubble = new GImage(IMG_FOLDER + "bubble.png", mainWidth/6.6, bubbleY);
		tourBubble.setSize(bubbleWidth, bubbleHeight);
		tourLabel = new GImage(IMG_FOLDER + "TourLabel.png", mainWidth/6.16, labelY);
		tourLabel.setSize(labelWidth, labelHeight);
		
		shopPipe = new GImage(IMG_FOLDER + "pPipe.png", mainWidth/2.33, pipeY);
		shopPipe.setSize(pipeWidth, pipeHeight);
		shopBubble = new GImage(IMG_FOLDER + "bubble.png", mainWidth/2.283, bubbleY + 10);
		shopBubble.setSize(bubbleWidth, bubbleHeight);
		shopLabel = new GImage(IMG_FOLDER + "shopLabel.png", mainWidth/2.21, labelY + 10);
		shopLabel.setSize(labelWidth, labelHeight);
		
		guidePipe = new GImage(IMG_FOLDER + "yPipe.png", mainWidth/1.4, pipeY);
		guidePipe.setSize(pipeWidth, pipeHeight);
		guideBubble = new GImage(IMG_FOLDER + "bubble.png", mainWidth/1.3832, bubbleY);
		guideBubble.setSize(bubbleWidth, bubbleHeight);
		guideLabel = new GImage(IMG_FOLDER + "guideLabel.png", mainWidth/1.3557, labelY);
		guideLabel.setSize(labelWidth, labelHeight);
		
		menuBackground = new GImage(IMG_FOLDER + "menuBack.gif", 0, 0);
		menuBackground.setSize(mainWidth, mainHeight);
		bubbleTimer.start();
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
		program.remove(guideBubble);
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
		
		if(obj == tourPipe || obj == tourBubble || obj == tourLabel) {
			bubbleTimer.stop();
			program.playPipeSound();
			program.stopMenuSound();
			program.playTourSound();
			program.switchToTour();
		}
		
		if (obj == shopPipe || obj == shopBubble || obj == shopLabel) {
			bubbleTimer.stop();
			program.stopMenuSound();
			program.playPipeSound();
			program.playShopSound();
			program.switchToShop();
		}

		else if(obj == guidePipe || obj == guideBubble || obj == guideLabel) {
			bubbleTimer.stop();
			program.guidePane.mTimer.start();
			program.stopMenuSound();
			program.playGuideSound();
			program.playPipeSound();
			program.switchToInstructions();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			tourBubble.move(0, 10);
			tourLabel.move(0, 10);
			shopBubble.move(0, -10);
			shopLabel.move(0, -10);
			guideBubble.move(0, 10);
			guideLabel.move(0, 10);
		}
		
		if (count == 2) {
			tourBubble.move(0, -10);
			tourLabel.move(0, -10);
			shopBubble.move(0, +10);
			shopLabel.move(0, +10);
			guideBubble.move(0, -10);
			guideLabel.move(0, -10);
			count = 0;
		}
		count++;
	}
}
