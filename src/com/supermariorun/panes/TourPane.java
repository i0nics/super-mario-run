package com.supermariorun.panes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Array;

import javax.swing.Timer;

import com.supermariorun.main.GraphicsPane;
import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import starter.GButton;

public class TourPane extends GraphicsPane implements ActionListener {
	public static final String IMG_FOLDER = "tourPane/";
	private mainSMR program; 
	private GImage backLabel;
	private GImage backBubble;
	private GImage backPipe;
	private GImage TBackground;
	private int count = 1;
	public Timer bTimer;
	public Array array[][];
	public TourPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		bTimer = new Timer(500, this);
		final double mainWidth = program.getWidth();
		final double mainHeight = program.getHeight();
		final double pipeWidth = mainWidth/6;
		final double pipeHeight = mainHeight/6;
		final double bubbleWidth = mainWidth/9;
		final double bubbleHeight = mainHeight/5;
		final double labelWidth = mainWidth/12;
		final double labelHeight = mainHeight/12;
		backPipe = new GImage("gPipeR.png", -50, 500);
		backPipe.setSize(pipeWidth, pipeHeight);
		
		backLabel = new GImage("backLabel.png", 177, 520);
		backLabel.setSize(labelWidth, labelHeight);
		
		backBubble = new GImage("bubble.png", 162, 480);
		backBubble.setSize(bubbleWidth, bubbleHeight);
		
		TBackground = new GImage(IMG_FOLDER + "tBackground.jpg",0,0);
		TBackground.setSize(mainWidth, mainHeight);
		bTimer.start();
		for(int y=0;y<16;y++){
		    for(int x=0;x<999;x++){
		        board[x][y] = "?";
		    }
		}
		for (int r = 0; r<16;r++){
		    String line = "";
		    for (int c = 0; c <999;c++){
		        line+="["+board[c][r]+"]";
		    }
		    System.out.println(line);
		}
	}

	@Override
	public void showContents() {
		program.add(TBackground);
		program.add(backLabel);
		program.add(backBubble);
		program.add(backPipe);
	}

	@Override
	public void hideContents() {
		program.remove(TBackground);
		program.remove(backLabel);
		program.remove(backBubble);
		program.remove(backPipe);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == backLabel || obj ==  backBubble || obj == backPipe) {
			program.menuPane.bubbleTimer.start();
			program.playPipeSound();
			program.stopTourSound();
			program.playMenuSound();
			program.switchToMenu();
		}
		//if (obj == TourButton) {
		//	program.playPipeSound();
		//	program.switchToLevel();
		//}
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (count == 1) {
			backBubble.move(10, 0);
			backLabel.move(10, 0);
		}
		
		if (count == 2) {
			backBubble.move(-10, 0);
			backLabel.move(-10,0);
			count = 0;
		}
		count++;
	}
}
