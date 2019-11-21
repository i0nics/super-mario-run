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

public class eGoomba extends GraphicsPane implements ActionListener {
	private mainSMR program;
	double mainWidth ;
	double mainHeight;
	private Level level;
	public static final String IMG_FOLDER = "enemies/";
	private GImage goomba;
	private boolean moveRight;
	private boolean isGoombaDead;
	private ArrayList <GImage> Environment;
	

	public void goomba(mainSMR mainSMR, LevelPane levelPane) {
		program = mainSMR;
		mainWidth = program.getWidth();
		mainHeight = program.getHeight();
	//	level = new LevelOne();
		goomba = new GImage(IMG_FOLDER + "goomba.png", 200, 500);
		goomba.setSize(mainWidth/4, mainHeight/3);
		Environment = levelPane.getEnvironment();
	}


	public void actionPerformed(ActionEvent e) {
	
		checkCollisionEnvironment();

	}

	public void checkCollisionEnvironment() {
		for (Iterator<GImage> it = level.getEnvironment().iterator(); it.hasNext(); ) {
			GImage img = it.next();
		if (goomba.getBounds().intersection(img.getBounds()) != null) {
		
			goomba.move(0,-100);

		}
		
		}

		
		}
	
	public void goombaDead() {
		if(isGoombaDead = true)
		{
		}
	}
	
	@Override
	public void showContents() {
		program.add(goomba);	
	}

	@Override
	public void hideContents() {
		program.remove(goomba);
	}
}
