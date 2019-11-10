package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.supermariorun.levels.ILevel;
import com.supermariorun.levels.LevelOne;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class LevelPane extends GraphicsPane{
	private mainSMR program;
	private GImage Background;
	private ILevel level;
	public static final String IMG_FOLDER = "menuScreen/";


	public LevelPane(mainSMR mainSMR, int levelNum) {
		super();
		program = mainSMR;
		level = new LevelOne();
		Background = level.getBackground();
		Background.move(-40, 0);
		Play();
		
	}
	
	public void Play() {
		program.playLvlOneTrack();
		moveEnvironment();
		
	}
	
	public void Stop() {
		program.stopLvlOneTrack();
	}
	
	public void Reset() {
		Background.setLocation(0, 0);
	}
	
	public void moveEnvironment() {
		Background.move(-20, 0);
	}

	@Override
	public void showContents() {
		program.add(Background);
		
	}

	@Override
	public void hideContents() {
		program.add(level.getBackground());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
}