package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.characters.cMario;
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
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> GrassStrips;
	private ArrayList <GImage> Blocks;
	private ArrayList <GImage> qBlocks;
	private cMario Mario;
	private ILevel level;
	private Timer timer;
	public static final int MS = 200;
	public static final String IMG_FOLDER = "LevelPane/";


	public LevelPane(mainSMR mainSMR, int levelNum) {
		super();
		program = mainSMR;
		timer = new Timer (MS, this);
		level = new LevelOne();
		Environment = level.getEnvironment();
		Mario = new cMario();
		DrawLevel();
	}
	
	public void DrawLevel() {
		Background = level.getBackground();
	}
	

	public void Play() {
		timer.start();
		program.playLvlOneTrack();
		moveEnvironment();
	}
	
	public void Pause() {
		program.stopLvlOneTrack();
		timer.stop();

	}
	
	public void Reset() {
		timer.restart();
		Background.setLocation(0, 0);
	}
	
	public void moveEnvironment() {
		Background.move(-10, 0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		moveEnvironment();
	}

	@Override
	public void showContents() {
		Play();
		program.add(Background);
		program.add(Mario.getMario());
		
		for (GImage e: Environment) {
			program.add(e);
		}
	}

	@Override
	public void hideContents() {
		program.add(level.getBackground());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}


}