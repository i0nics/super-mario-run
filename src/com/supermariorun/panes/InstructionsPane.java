package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class InstructionsPane extends GraphicsPane implements ActionListener {
	public static final String IMG_FOLDER = "guidePane/";
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final int MAX_STEPS = 100;
	private mainSMR program;
	private GImage background;
	private GImage InstructionsList;
	private GImage bubble;
	private GImage back;
	private GImage mario;
	private GImage grass;
	private int numTimes;
	private Timer timerT;
	private GImage bPipe;
	private int count = 1;
	private Timer timerB;
	

	public InstructionsPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		background = new GImage(IMG_FOLDER + "redStripes.png", 0, 0);
		background.setSize(WINDOW_HEIGHT,WINDOW_WIDTH);
		InstructionsList = new GImage(IMG_FOLDER + "instructions.png", 200, 50);	
		InstructionsList.setSize(750, 500);
		grass = new GImage(IMG_FOLDER + "grassStrip.png",0,600);
		grass.setSize(WINDOW_WIDTH,WINDOW_HEIGHT/8);
		bPipe = new GImage(IMG_FOLDER + "gPipeR.png",0,100);
		bPipe.setSize(WINDOW_WIDTH/15,WINDOW_HEIGHT/8);
		back = new GImage(IMG_FOLDER + "backLabel.png",95,120);
		back.setSize(WINDOW_WIDTH/22, WINDOW_HEIGHT/15);
		bubble = new GImage(IMG_FOLDER + "bubble.png",85,100);
		bubble.setSize(WINDOW_WIDTH/15,WINDOW_HEIGHT/8);

		background.setSize(mainSMR.getWidth(), mainSMR.getHeight());
		timerT = new Timer(50, this);
		timerB = new Timer(500, this);
		numTimes = 0;
		timerT.start();
		timerT.setInitialDelay(1);
		mario = new GImage("mario1.gif", 0, 528);
		mario.setSize(WINDOW_WIDTH/18,WINDOW_HEIGHT/8);
		timerB.start();
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(grass);
		program.add(mario);
		program.add(InstructionsList);
		program.add(bPipe);
		program.add(back);
		program.add(bubble);

	}

	@Override
	public void hideContents() {
		program.remove(InstructionsList);
		program.remove(mario);
		program.remove(background);
		program.remove(bPipe);
		program.remove(grass);
		program.remove(back);
		program.remove(bubble);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());

		if (obj == bPipe || obj == back || obj == bubble) {
			program.playPipeSound();
			program.switchToMenu();
		}
	}

	public void actionPerformed(ActionEvent e) {
		mario.move(10, 0);
		if (numTimes == 120) {
			timerT.stop();
			timerT.stop();
        	timerT.restart();
        	numTimes = 0;
        	mario.setLocation(0,528);
		}
		numTimes++;
	}

	public void actionPerformed1(ActionEvent e) {
		if (count == 1) {
			bubble.move(0, 10);
			
		}
		
		if (count == 2) {
			bubble.move(0, -10);
		
			count = 0;
		}
		count++;
	}
}

