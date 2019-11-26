package com.supermariorun.enemies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import com.supermariorun.levels.Level;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import com.supermariorun.main.playerProgress;
import com.supermariorun.panes.LevelPane;


import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRectangle;
import acm.program.GraphicsProgram;

public class ePiranhaPlant implements ActionListener {
	private mainSMR program;
	private LevelPane level;
	double mainWidth ;
	double mainHeight;
	private int count;
	public Timer plantTimer;
	public static final String IMG_FOLDER = "enemies/";
	private static final double PROGRAM_WIDTH = mainSMR.WINDOW_WIDTH;
	private GImage pPlant;
	private ArrayList <GImage> Environment;
	private GRectangle head;
	
	
	public ePiranhaPlant(mainSMR main, LevelPane levelPane, int x, int y) {
		program = main;
		level = levelPane;
	//	level = new LevelOne();
		plantTimer = new Timer(50, this);
		pPlant = new GImage(IMG_FOLDER + "pPlant.png", 100, 470);
		pPlant.setSize(1000, 1000);
		head = new GRectangle (pPlant.getX(), pPlant.getY() + 4, pPlant.getWidth() - 21, 2);
	}
	
	public void updateBounds() {
		head.setLocation(pPlant.getX() + 12, pPlant.getY() + 3);
		
		}
	public void Run() {
		if (level.getBackground().getX() <= pPlant.getX() - PROGRAM_WIDTH) {
			pPlant.move(0, -60);
			updateBounds();
		}
			
		if (head.intersects(level.getCharacter().getCharacter().getBounds())) {
			level.getCharacter().isDead = true;
			
		}
		
	}


	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			pPlant.move(0, -60);
		}
		
		if (count == 2) {
			pPlant.move(0, 60);
			
		}		
		count++;
		
	}

	
		
	public GImage getPlant(){
		return pPlant;
	}
	
}
