package com.supermariorun.panes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GParagraph;

public class StartPane extends GraphicsPane implements ActionListener {
	private mainSMR program; 
	public static final String IMG_FOLDER = "startScreen/";
	private GImage redStripes;
	private GImage marioStart;
	private GImage marioImg;
	private GImage goombaImg;
	private GImage lakituImg;
	private GImage clickImg;
	private int count = 1;
	private Timer timer;

	public StartPane(mainSMR main) {
		this.program = main;
		timer = new Timer(600, this);
		redStripes = new GImage(IMG_FOLDER + "redStripes.png",0, 0);
		marioImg = new GImage(IMG_FOLDER + "mario.png", 0, 10);
		goombaImg = new GImage(IMG_FOLDER + "goomba.png", 140, 395);
		lakituImg = new GImage(IMG_FOLDER + "lakitu.png", main.getWidth()/1.4, 0);
		marioStart = new GImage(IMG_FOLDER + "title.png", main.getWidth()/3.36, main.getHeight()/7);
		clickImg = new GImage(IMG_FOLDER + "click.png", main.getWidth()/2.9, main.getHeight() - 100);
		redStripes.setSize(main.getWidth(), main.getHeight());
		goombaImg.setSize(250, 250);
		lakituImg.setSize(250, 250);
	}

	public void actionPerformed(ActionEvent evt){
		if (count == 1) {
		   program.add(clickImg);
		}
	
		if (count == 2){
		  timer.stop();
		  program.remove(clickImg);
		  timer.restart();
		  count = 0;
	    }
	    count++;
	}

	@Override
	public void showContents() {
		timer.start();
		program.add(redStripes);
		program.add(marioStart);
		program.add(marioImg);
		program.add(goombaImg);
		program.add(lakituImg);
	}

	@Override
	public void hideContents() {
		timer.stop();
		program.removeAll();
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		timer.stop();
		program.audio.playSound("sounds", "letsgo.mp3");
		program.stopStartSound();
		program.playMenuSound();
		program.switchToMenu();
	}
}
