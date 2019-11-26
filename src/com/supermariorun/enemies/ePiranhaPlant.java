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

public class ePiranhaPlant {
	private mainSMR program;
	private LevelPane level;
	private static final double PROGRAM_WIDTH = mainSMR.WINDOW_WIDTH;
	public static final String IMG_FOLDER = "enemies/";
	private GImage pPlant;
	private GRectangle body;
	//private ArrayList <GImage> Environment;
	
	
	public ePiranhaPlant(mainSMR main, LevelPane levelPane, int x, int y) {
		program = main;
		level = levelPane;
		pPlant = new GImage(IMG_FOLDER + "pPlant.png", x, y);
		
		body = new GRectangle (pPlant.getX(), pPlant.getY() + 4, pPlant.getWidth() - 21, 2);
	}

	public void updateBounds() {
		body.setLocation(pPlant.getX() + 12, pPlant.getY() + 3);
	}
	
	public void Run() {
		
		if (level.getBackground().getX() <= pPlant.getX() - PROGRAM_WIDTH) {
			pPlant.move(0, -10);
			updateBounds();
		} 
		
		else {
			pPlant.move(0, -8);
			updateBounds();
		}

		if (body.intersects(level.getCharacter().getCharacter().getBounds())) {
			level.getCharacter().isDead = true;
		}
	} 
	
		
	public GImage getPlant(){
		return pPlant;
	}
	
}
