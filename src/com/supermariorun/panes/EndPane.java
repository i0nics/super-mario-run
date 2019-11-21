package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;
import com.supermariorun.main.playerProgress;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class EndPane extends GraphicsPane {
	private mainSMR program; 
	public static final String IMG_FOLDER = "endPane/";
	private GImage next;
	private GImage background;
	private GLabel coinCount;
	
	public EndPane(mainSMR mainSMR) {
		super();
		this.program = mainSMR;
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		background = new GImage(IMG_FOLDER + "tourBack.png", 0, 0);
		background.setSize(mainWidth, mainHeight);
		next = new GImage(IMG_FOLDER + "continueButton.png", 450, 500);
		next.setSize(200, 120);
	}
	

	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if(obj == next) {
			program.playTourSound();
			program.playPipeSound();
			program.switchToTour();
		}
	}

	@Override
	public void showContents() {
		program.add(background);
		program.add(next);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}
}