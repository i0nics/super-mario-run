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
	private GImage mario;
	private GImage princess;
	private GImage luigi;
	private GObject leftFoot;
	private GObject rightFoot;
	public static final String IMG_FOLDER = "character/";
	private ArrayList <GImage> Environment;
	public boolean jumpUpState;
	private int jumpCount = 0;
	
	
	public Character(mainSMR mainSMR, LevelPane levelPane, String character) {
		program = mainSMR;
		this.levelPane = levelPane;
		mario = new GImage (IMG_FOLDER + "marioStand.png", 100, 520); 
		mario.setSize(64, 64);
		Environment = levelPane.getEnvironment();
	}
	

	
	public Character(mainSMR mainSMR, LevelPaneDev levelPaneDev) {
		program = mainSMR;
		this.levelPaneDev = levelPaneDev;
		mario = new GImage (IMG_FOLDER + "marioStand.png", 100, 520); 
		mario.setSize(64, 64);
	}



	public void stand() {
		mario.setImage(IMG_FOLDER + "marioStand.png");
		mario.setSize(64, 64);
	}

	public void run() {
		mario.setImage(IMG_FOLDER + "marioRun.gif");
		mario.setSize(64, 64);
	}
	
	public void jump() {
		mario.setImage(IMG_FOLDER + "marioJump.gif");
		mario.setSize(64, 64);
		leftFoot = program.getElementAt(mario.getX() + 20, mario.getY() + 80);
		rightFoot = program.getElementAt(mario.getX() + 30, mario.getY() + 80);
		
		jumpUpState = false;
			
		if (jumpCount >=  0 && jumpCount < 6) {
			jumpUpState = true;
			setJumpCount(jumpCount + 1);
		}
			
		if  (jumpCount > 6) {
			jumpUpState = false;
		}
	
		if (jumpUpState) {		
			mario.move(0, -20);
		}
			
		if (!jumpUpState) {
			mario.move(0, 20);				
			for (GImage obj : Environment) {
				if (leftFoot == obj && rightFoot == obj) {
					
					levelPane.jumpState = false;
					run();
				}
			}
		}
	}
	
	public GImage getCharacter(){
		return mario;
	}

	public void setJumpCount(int jumpCount) {
		this.jumpCount = jumpCount;
	}
		
}
