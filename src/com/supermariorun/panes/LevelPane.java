package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.levels.ILevel;
import com.supermariorun.levels.LevelOne;
import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class LevelPane extends GraphicsPane implements ActionListener{
	private mainSMR program;
	private GImage Background;
	private ILevel level;
	private Timer timer;
	public static final String IMG_FOLDER = "menuScreen/";


	public LevelPane(mainSMR mainSMR, int levelNum) {
		super();
		program = mainSMR;
		timer = new Timer (100, this);
		level = new LevelOne();
		Background = level.getBackground();
		Background.move(-4, 0);
		Play();
		
	}
	
	public void Play() {
		program.playLvlOneTrack();
		timer.restart();
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
	public void actionPerformed(ActionEvent e) {
		moveEnvironment();
		
	}

	@Override
	public void showContents() {
		Play();
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