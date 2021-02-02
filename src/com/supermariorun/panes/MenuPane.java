package com.supermariorun.panes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.MainSMR;
import acm.graphics.GImage;
import acm.graphics.GObject;

public class MenuPane extends GraphicsPane implements ActionListener {
	private static final int MOVE_WIDTH = 0;
	private static final int MOVE_HEIGHT = 10;
	private static final String IMG_FOLDER = "menuScreen/";
	private static final double PROGRAM_WIDTH = MainSMR.WINDOW_WIDTH;
	private static final double PROGRAM_HEIGHT = MainSMR.WINDOW_HEIGHT;
	private static final double PIPE_HEIGHT = PROGRAM_HEIGHT/3;
	private static final double PIPE_WIDTH = PROGRAM_WIDTH/8;
	private static final double BUBBLE_WIDTH = PROGRAM_WIDTH/9;
	private static final double BUBBLE_HEIGHT = PROGRAM_HEIGHT/5;
	private static final double PIPE_Y = PROGRAM_HEIGHT - 130;
	private static final double BUBBLE_Y = PROGRAM_HEIGHT/1.756;
	private static final double LABEL_Y = PROGRAM_HEIGHT/1.6;
	private static final double LABEL_WIDTH = PROGRAM_WIDTH/12;
	private static final double LABEL_HEIGHT = PROGRAM_HEIGHT/12;
	
	public Timer bubbleTimer;
	private MainSMR program; 
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
	
	public MenuPane(MainSMR mainSMR) {
		super();
		this.program = mainSMR;
		bubbleTimer = new Timer(500, this);
	
		tourPipe = new GImage(IMG_FOLDER + "gPipe.png", PROGRAM_WIDTH/7, PIPE_Y);
		tourPipe.setSize(PIPE_WIDTH, PIPE_HEIGHT);
		tourBubble = new GImage(IMG_FOLDER + "bubble.png", PROGRAM_WIDTH/6.6, BUBBLE_Y);
		tourBubble.setSize(BUBBLE_WIDTH, BUBBLE_HEIGHT);
		tourLabel = new GImage(IMG_FOLDER + "TourLabel.png", PROGRAM_WIDTH/6.16, LABEL_Y);
		tourLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
		
		shopPipe = new GImage(IMG_FOLDER + "pPipe.png", PROGRAM_WIDTH/2.33, PIPE_Y);
		shopPipe.setSize(PIPE_WIDTH, PIPE_HEIGHT);
		shopBubble = new GImage(IMG_FOLDER + "bubble.png", PROGRAM_WIDTH/2.283, BUBBLE_Y + MOVE_HEIGHT);
		shopBubble.setSize(BUBBLE_WIDTH, BUBBLE_HEIGHT);
		shopLabel = new GImage(IMG_FOLDER + "shopLabel.png", PROGRAM_WIDTH/2.21, LABEL_Y + MOVE_HEIGHT);
		shopLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
		
		guidePipe = new GImage(IMG_FOLDER + "yPipe.png", PROGRAM_WIDTH/1.4, PIPE_Y);
		guidePipe.setSize(PIPE_WIDTH, PIPE_HEIGHT);
		guideBubble = new GImage(IMG_FOLDER + "bubble.png", PROGRAM_WIDTH/1.3832, BUBBLE_Y);
		guideBubble.setSize(BUBBLE_WIDTH, BUBBLE_HEIGHT);
		guideLabel = new GImage(IMG_FOLDER + "guideLabel.png", PROGRAM_WIDTH/1.3557, LABEL_Y);
		guideLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
		
		menuBackground = new GImage(IMG_FOLDER + "menuBack.gif", MOVE_WIDTH, MOVE_WIDTH);
		menuBackground.setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	@Override
	public void showContents() {
		bubbleTimer.start();
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
		bubbleTimer.stop();
		program.removeAll();
	}
	
	void soundAction(){
		program.playPipeSound();
		program.stopMenuSound();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if(obj == tourPipe || obj == tourBubble || obj == tourLabel) {
			soundAction();
			program.playTourSound();
			program.switchToTour();
		}
		
		if (obj == shopPipe || obj == shopBubble || obj == shopLabel) {
			soundAction();
			program.playShopSound();
			program.switchToShop();
		}

		else if(obj == guidePipe || obj == guideBubble || obj == guideLabel) {
			soundAction();
			program.playGuideSound();
			program.switchToInstructions();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			tourBubble.move(MOVE_WIDTH, MOVE_HEIGHT);
			tourLabel.move(MOVE_WIDTH, MOVE_HEIGHT);
			shopBubble.move(MOVE_WIDTH, -MOVE_HEIGHT);
			shopLabel.move(MOVE_WIDTH, -MOVE_HEIGHT);
			guideBubble.move(MOVE_WIDTH, MOVE_HEIGHT);
			guideLabel.move(MOVE_WIDTH, MOVE_HEIGHT);
		}
		
		if (count == 2) {
			tourBubble.move(MOVE_WIDTH, -MOVE_HEIGHT);
			tourLabel.move(MOVE_WIDTH, -MOVE_HEIGHT);
			shopBubble.move(MOVE_WIDTH, +MOVE_HEIGHT);
			shopLabel.move(MOVE_WIDTH, +MOVE_HEIGHT);
			guideBubble.move(MOVE_WIDTH, -MOVE_HEIGHT);
			guideLabel.move(MOVE_WIDTH, -MOVE_HEIGHT);
			count = MOVE_WIDTH;
		}
		count++;
	}
}
