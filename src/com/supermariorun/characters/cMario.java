package com.supermariorun.characters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class cMario extends GraphicsProgram implements ActionListener {
	private mainSMR program;
	private GImage Mario;
	private GObject leftFoot;
	public static final String IMG_FOLDER = "character/";
	private ArrayList <GImage> Environment;
	private Timer timer;
	public boolean jumpState;
	public boolean jumpUpState;
	private int jumpCount = 0;
	
	
	public cMario(mainSMR mainSMR) {
		program = mainSMR;
		timer = new Timer (25, this);
		Mario = new GImage (IMG_FOLDER + "stand.png", 100, 520); //520
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
	}
	
	public GImage getMario(){
		return Mario;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
