package com.supermariorun.levels;
import java.util.ArrayList;

import acm.graphics.GImage;

public class LevelOne implements ILevel{
	public static final String IMG_FOLDER = "LevelOne/";
	private GImage Background;
	private ArrayList <GImage> Pipes;
	private ArrayList <GImage> GrassStrips;
	
	public LevelOne() {
		Background = new GImage (IMG_FOLDER + "Background.png", 0 ,0);
		Pipes = new ArrayList <GImage> ();
		Background.move(-100, 0);
	
	}
	
	@Override
	public GImage getBackground() {
		return Background;
	}

	@Override
	public ArrayList<GImage> getPipes() {
		return Pipes;
	}
	
	
	

}
