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
	private mainSMR program;
	private GImage background;
	private GImage InstructionsList;
	private GButton BackButton;
	public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public static final int MAX_STEPS = 100;
	private GImage mario;
	private int numTimes;
	private Timer timerT;

	public InstructionsPane(mainSMR mainSMR) {
		super();
		program = mainSMR;

		// InstructionButton = new GButton("Instruction", 200, 200, 200, 200);
		// InstructionButton.setFillColor(Color.RED);

		background = new GImage("../media/Instructions Background.jpg", 0, 0);
		InstructionsList = new GImage("../media/Instructions.png", 300, 50);
		BackButton = new GButton("Back", 100, 100, 100, 100);
		BackButton.setFillColor(Color.GREEN);
		background.setSize(1500, 750);
		InstructionsList.setSize(750, 500);

		background.setSize(mainSMR.getWidth(), mainSMR.getHeight());
		timerT = new Timer(50, this);
		numTimes = 0;
		timerT.start();
		timerT.setInitialDelay(1);
		mario = new GImage("mario1.gif", 0, 480);
		mario.setSize(WINDOW_HEIGHT / 15, WINDOW_WIDTH / 30);
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(mario);
		// program.add(InstructionButton);
		program.add(InstructionsList);
		program.add(BackButton);
	}

	@Override
	public void hideContents() {
		// program.remove(InstructionButton);
		program.remove(InstructionsList);
		program.remove(mario);
		program.remove(background);
		program.remove(BackButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());

		if (obj == BackButton) {
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
        	mario.setLocation(0,460);
		}
		numTimes++;
	}
}