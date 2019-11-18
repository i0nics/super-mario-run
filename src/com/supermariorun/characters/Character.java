package com.supermariorun.characters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.main.mainSMR;
import com.supermariorun.panes.LevelPane;
import com.supermariorun.panes.LevelPaneDev;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class Character extends GraphicsProgram implements ActionListener {
	private mainSMR program;
	private LevelPane levelPane;
	private LevelPaneDev levelPaneDev;
	private GImage characImg;
	private GObject leftFoot;
	private GObject rightFoot;
	public static final String IMG_FOLDER = "character/";
	private ArrayList <GImage> Environment;
	public boolean jumpUpState;
	private int jumpCount = 0;
	
	
	public Character(mainSMR mainSMR, LevelPane levelPane) {
		program = mainSMR;
		this.levelPane = levelPane;
		characImg = new GImage (IMG_FOLDER + "stand.png", 100, 520); 
		characImg.setSize(64, 64);
		Environment = levelPane.getEnvironment();
	}

	
	public Character(mainSMR mainSMR, LevelPaneDev levelPaneDev) {
		program = mainSMR;
		this.levelPaneDev = levelPaneDev;
		characImg = new GImage (IMG_FOLDER + "stand.png", 100, 520); 
		characImg.setSize(64, 64);
	}


	public void stand() {
		characImg.setImage(IMG_FOLDER + "stand.png");
		characImg.setSize(64, 64);
	}

	public void run() {
		characImg.setImage(IMG_FOLDER + "run.gif");
		characImg.setSize(64, 64);
	}
	
	public void jump() {
		characImg.setImage(IMG_FOLDER + "jump.gif");
		characImg.setSize(64, 64);
		leftFoot = program.getElementAt(characImg.getX() + 20, characImg.getY() + 80);
		rightFoot = program.getElementAt(characImg.getX() + 30, characImg.getY() + 80);
		
		jumpUpState = false;
			
		if (jumpCount >=  0 && jumpCount < 6) {
			jumpUpState = true;
			setJumpCount(jumpCount + 1);
		}
			
		if  (jumpCount > 6) {
			jumpUpState = false;
		}
	
		if (jumpUpState) {		
			characImg.move(0, -20);
		}
			
		if (!jumpUpState) {
			characImg.move(0, 20);				
			for (GImage obj : Environment) {
				if (leftFoot == obj && rightFoot == obj) {
					
					levelPane.jumpState = false;
					run();
				}
			}
		}
	}
	
	public GImage getCharacter(){
		return characImg;
	}

	public void setJumpCount(int jumpCount) {
		this.jumpCount = jumpCount;
	}
		
}
