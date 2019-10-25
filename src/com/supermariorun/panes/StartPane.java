package com.supermariorun.panes;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GParagraph;

public class StartPane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to all of the GraphicsProgram calls
	public static final String IMG_FOLDER = "startScreen/";
	private GImage redStripes;
	private GImage marioStart;
	private GImage click;


	public StartPane(mainSMR mainSMR) {
		this.program = mainSMR;
		redStripes = new GImage(IMG_FOLDER + "redStripes.png",0, 0);
		marioStart = new GImage(IMG_FOLDER + "title.png", 250, 250);
		click = new GImage(IMG_FOLDER + "click.png", 300, 700);
	}


	@Override
	public void showContents() {
		program.add(redStripes);
		program.add(marioStart);
		program.add(click);
	}

	@Override
	public void hideContents() {
		program.remove(redStripes);
		program.remove(marioStart);
		program.remove(click);
	}
	//comment

	@Override
	public void mousePressed(MouseEvent e) {
		//para.setText("you need\nto click\non the eyes\nto go bagck");
			program.switchToMenu();
	}
}
