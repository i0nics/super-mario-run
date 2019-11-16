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
	private GImage Mario;
	private GObject leftFoot;
	private GObject rightFoot;
	public static final String IMG_FOLDER = "character/";
	private ArrayList <GImage> Environment;
	public boolean jumpUpState;
	private int jumpCount = 0;
	
	
	public Character(mainSMR mainSMR, LevelPane levelPane) {
		program = mainSMR;
		this.levelPane = levelPane;
		Mario = new GImage (IMG_FOLDER + "stand.png", 100, 520); 
		Mario.setSize(64, 64);
		Environment = levelPane.getEnvironment();
	}
	
	public Character(mainSMR mainSMR, LevelPaneDev levelPane) {
		program = mainSMR;
		this.levelPaneDev = levelPane;
		Mario = new GImage (IMG_FOLDER + "stand.png", 100, 520); 
		Mario.setSize(64, 64);
		Environment = levelPaneDev.getEnvironment();
	}
	
	public void stand() {
		Mario.setImage(IMG_FOLDER + "stand.png");
		Mario.setSize(64, 64);
	}

	public void run() {
		Mario.setImage(IMG_FOLDER + "run.gif");
		Mario.setSize(64, 64);
	}
	
	public void jump() {
		Mario.setImage(IMG_FOLDER + "jump.gif");
		Mario.setSize(64, 64);
		leftFoot = program.getElementAt(Mario.getX() + 20, Mario.getY() + 80);
		rightFoot = program.getElementAt(Mario.getX() + 30, Mario.getY() + 80);
		
		jumpUpState = false;
			
		if (jumpCount >=  0 && jumpCount < 6) {
			jumpUpState = true;
			setJumpCount(jumpCount + 1);
		}
			
		if  (jumpCount > 6) {
			jumpUpState = false;
		}
	
		if (jumpUpState) {		
			Mario.move(0, -20);
		}
			
		if (!jumpUpState) {
			Mario.move(0, 20);				
			for (GImage obj : Environment) {
				if (leftFoot == obj && rightFoot == obj) {
					
					levelPane.jumpState = false;
					run();
				}
			}
		}
	}
	
	public GImage getMario(){
		return Mario;
	}

	public void setJumpCount(int jumpCount) {
		this.jumpCount = jumpCount;
	}
		
}
