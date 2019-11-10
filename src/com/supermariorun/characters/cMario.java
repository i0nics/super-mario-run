package com.supermariorun.characters;

import acm.graphics.GImage;

public class cMario {
	private GImage Mario;
	public static final String IMG_FOLDER = "character/";
	
	
	public cMario() {
		Mario = new GImage (IMG_FOLDER + "stand.png", 50, 400);
		
	}
	
	public void run() {
		Mario.setImage(IMG_FOLDER + "run.gif");
	}
	
	public void jump() {
		Mario.setImage(IMG_FOLDER + "jump.gif");
	}
	
	public GImage getMario(){
		return Mario;
	}
	
}
