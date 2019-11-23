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
	double mainWidth ;
	double mainHeight;
	private int count;
	public Timer plantTimer;
	public static final String IMG_FOLDER = "enemies/";
	private GImage pPlant;
	private ArrayList <GImage> Environment;
	

	public ePiranhaPlant(Level level) {
		
	//	level = new LevelOne();
		plantTimer = new Timer(500, this);
		pPlant = new GImage(IMG_FOLDER + "pPlant.png", 100, 470);
		pPlant.setSize(1000, 1000);

		Environment = level.getEnvironment();
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
