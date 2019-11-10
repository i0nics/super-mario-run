package com.supermariorun.characters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import acm.graphics.GImage;

public class cMario implements ActionListener {
	private GImage Mario;
	public static final String IMG_FOLDER = "character/";
	private Timer timer;
	public boolean jumpState;
	public boolean jumpUpState;
	private int jumpCount = 0;
	
	
	public cMario() {
		timer = new Timer (500, this);
		Mario = new GImage (IMG_FOLDER + "stand.png", 100, 520);
		Mario.setSize(64, 64);
		
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
		isJump = true;
		
	}
	
	public GImage getMario(){
		return Mario;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*if(jumpState == false) {
			jumpCount = 0;
		}
		
		if (jumpState == true ){
			jumpUpState = false;
			
			if (jumpCount >=  0 && jumpCount < 5) {
				jumpUpState = true;
				jumpCount++;
			}
			
			if  (jumpCount >= 5 && jumpCount < JUMPint) {
				//jumpUpState = false;
				jumpCount++;
			}
			
			if (jumpUpState == true) {
				marioRun.move(0, -15);
			}
			
			if (jumpUpState == false) {
				marioRun.move(0, 15);
			}
			
			if (jumpCount == JUMPint) {
				clickState = false;
				jumpState = false;
				run();
			}
		}
		numTimes++;

	}*/
}
