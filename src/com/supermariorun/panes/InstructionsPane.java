package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class InstructionsPane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to
										// all of the GraphicsProgram calls
	//private GButton InstructionButton;
	private GImage background;
	private GImage InstructionsList;
	private GButton BackButton;


	public InstructionsPane(mainSMR mainSMR) {
		super();
		program = mainSMR;

		//InstructionButton = new GButton("Instruction", 200, 200, 200, 200);
		//InstructionButton.setFillColor(Color.RED);
		background = new GImage("../media/Sky background.jpg", 170, 270);
		InstructionsList = new GImage("../media/Instructions.png", 200, 185);
		BackButton = new GButton("Back", 100, 100, 100, 100);
		BackButton.setFillColor(Color.GREEN);
	}
	
	@Override
	public void showContents() {
		program.add(background);
		//program.add(InstructionButton);
		program.add(InstructionsList);
		program.add(BackButton);


	}

	@Override
	public void hideContents() {
		//program.remove(InstructionButton);
		program.remove(InstructionsList);
		program.remove(background);
		program.remove(BackButton);


	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());

		//if (obj == InstructionButton) {

		if (obj == BackButton) {

			program.switchToMenu();
		}
	}
}