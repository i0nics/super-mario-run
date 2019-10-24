package com.iØnics.supermariorun.panes;
import java.awt.event.MouseEvent;

import com.iØnics.supermariorun.main.GraphicsPane;
import com.iØnics.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GParagraph;

public class StartPane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to all of the GraphicsProgram calls
	private GImage redStripes;
	private GImage marioStart;


	public StartPane(mainSMR mainSMR) {
		this.program = mainSMR;
		redStripes = new GImage("redStripes.png",0, 0);
		marioStart = new GImage("title.png",250, 250);
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
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//para.setText("you need\nto click\non the eyes\nto go back");
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == marioStart) {
			program.switchToMenu();
		}
	}
}
