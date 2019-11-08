package com.supermariorun.enemies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class eGoomba extends GraphicsProgram implements ActionListener {
	private mainSMR program;
	final double mainWidth = program.getWidth();
	final double mainHeight = program.getHeight();
	private GImage goomba;
	private GImage Pipe1;
	private GImage Pipe2;
	private GObject getObj;
	private Timer timer;
	private boolean moveRight;


	// private ArrayList<GImage> Pipe;
	public void run() {
		goomba = new GImage("media/goomba.png", 200, 500);
		goomba.setSize(mainWidth/ 4, mainHeight/ 3);
		timer = new Timer(30, this);
		add(goomba);
		addPipe();
		goomba.move(10, 0);
		timer.start();
		timer.setInitialDelay(2);

	}

	public void actionPerformed(ActionEvent e) {
	
		checkCollision();
	

	}

	private void addPipe() {
		// Pipe = new ArrayList<GImage>();
		Pipe1 = new GImage("media/Pipe1.png", 0, 500);
		Pipe1.setSize(mainWidth / 6, mainHeight / 3);
		Pipe2 = new GImage("media/Pipe2.png", 1000, 500);
		Pipe2.setSize(mainWidth / 6, mainWidth/ 3);
		add(Pipe1);
		add(Pipe2);
	}

	public void checkCollision() {
	 
		if (goomba.getX() - 80 == Pipe1.getX()) {
			moveRight = true;
		}
		if (goomba.getX() + 190 == Pipe2.getX()) {
			moveRight = false;
		} 
		
		if (moveRight == true) {
			goomba.move(10, 0);
		}
		
		else {
			goomba.move(-10, 0);
		}
	}

		
	
}
