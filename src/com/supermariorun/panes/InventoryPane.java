package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class InventoryPane extends GraphicsPane implements ActionListener{
	private mainSMR program; 
	public static final String IMG_FOLDER = "inverntoryPane/";
	private GButton BackButton;
	private GImage BackPipe;
	private GImage backLabel;
	private int count;
	public Timer bubbleTimer;

	public InventoryPane(mainSMR mainSMR) {
		super();
		this.program = mainSMR;
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
		
		bubbleTimer = new Timer(500, this);
		BackButton = new GButton("Back", 100, 100, 80, 80);
		BackButton.setFillColor(Color.GREEN);
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(BackButton);
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(BackButton);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		if (obj == BackButton) {

			program.switchToShop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			
			backLabel.move(0, 10);
			BackButton.move(0, 10);
		}
		
		if (count == 2) {
			
			backLabel.move(0, -10);
			BackButton.move(0, -10);
			
			count = 0;
		}
		count++;
		
	}
}
