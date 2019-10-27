package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GObject;
import starter.GButton;

public class TourPane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton InstructionButton;

	public TourPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		InstructionButton = new GButton("Tour", 200, 200, 200, 200);
		InstructionButton.setFillColor(Color.RED);
	}

	@Override
	public void showContents() {
		program.add(InstructionButton);
	}

	@Override
	public void hideContents() {
		program.remove(InstructionButton);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == InstructionButton) {
			
		}
	}
}
