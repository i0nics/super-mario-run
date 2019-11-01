package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GObject;
import starter.GButton;

public class TourPane extends GraphicsPane {
	private mainSMR program; 
	private GButton TourButton;
	private GButton backButton;
	
	public TourPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		TourButton = new GButton("Tour", 200, 200, 200, 200);
		TourButton.setFillColor(Color.RED);
		backButton = new GButton("Back", 100, 100, 100, 100);
		backButton.setFillColor(Color.GREEN);
	}

	@Override
	public void showContents() {
		program.add(TourButton);
		program.add(backButton);
	}

	@Override
	public void hideContents() {
		program.remove(TourButton);
		program.remove(backButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == backButton) {
			program.playPipeSound();
			program.stopTourSound();
			program.playMenuSound();
			program.switchToMenu();
		}
	}
}
