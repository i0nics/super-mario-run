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
	private GImage click;
	private int count = 0;
	public Timer timer;


	public StartPane(mainSMR mainSMR) {
		this.program = mainSMR;
		timer = new Timer(600, this);
		redStripes = new GImage(IMG_FOLDER + "redStripes.png",0, 0);
		marioStart = new GImage(IMG_FOLDER + "title.png", 250, 250);
		click = new GImage(IMG_FOLDER + "click.png", 300, 700);
		timer.start();
	}

	public void actionPerformed(ActionEvent evt){
		if (count == 1) {
			program.add(click);
		}
	
		if(count == 2){
		  timer.stop();
		  program.remove(click);
		  timer.restart();
		  count = 1;
	    }
	   
	   else {
	        count++;
	   }
	}

	@Override
	public void showContents() {
		program.add(redStripes);
		program.add(marioStart);
	}

	@Override
	public void hideContents() {
		program.remove(redStripes);
		program.remove(marioStart);
		program.remove(click);
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
			program.switchToMenu();
	}
}
