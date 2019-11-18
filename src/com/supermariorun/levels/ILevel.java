package com.supermariorun.levels;
import java.util.ArrayList;

import acm.graphics.GImage;

public interface ILevel {
	public GImage getBackground();
	public ArrayList <GImage> getEnvironment();
	public ArrayList <GImage> getPipes();
	public ArrayList <GImage> getCoins();
	
}
