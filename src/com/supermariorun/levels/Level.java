package com.supermariorun.levels;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.supermariorun.characters.Character;
import com.supermariorun.enemies.ePiranhaPlant;

import acm.graphics.GImage;
import javafx.scene.layout.Background;
import javafx.util.Pair;

public class Level {
	public static final String IMG_FOLDER = "LevelImages/";
	private GImage Background;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> Coins;
	private ArrayList <GImage> Plants;
	private ePiranhaPlant ePlant;
	private HashMap<String, ArrayList <Pair<Integer, Integer>>> levelMap;
	private ScanLevel scanLevel;
	
	public Level(String levelNum) throws FileNotFoundException {
		Environment = new ArrayList <GImage> ();
		Coins = new ArrayList <GImage> ();
		Plants = new ArrayList<GImage> ();
		ePlant = new ePiranhaPlant(this);
		scanLevel = new ScanLevel();
		
		Background = new GImage (IMG_FOLDER + "Background.png", 0, 0);
		levelMap = scanLevel.runScan("level/level"+ levelNum + ".txt");	}
	
	public void setUpLevel() {
		Environment.clear();
		Coins.clear();
		Plants.clear();
		Background.setLocation(0, 0);
		
		for (Pair<Integer, Integer> loop : levelMap.get("PIPE")) {
			Environment.add(new GImage(IMG_FOLDER + "pipe.png", loop.getKey(), loop.getValue()));
		}
		
		for (Pair<Integer, Integer> loop : levelMap.get("GRASSLONG")) {
 			Environment.add(new GImage(IMG_FOLDER + "grassStrip.png", loop.getKey(), loop.getValue()));
 		}
		
 		for (Pair<Integer, Integer> loop : levelMap.get("GRASS")) {
 			Environment.add(new GImage(IMG_FOLDER + "grassStripShort.png", loop.getKey(), loop.getValue()));
 		}
 		
 		for (Pair<Integer, Integer> loop : levelMap.get("GRASSSHORT")) {
 			Environment.add(new GImage(IMG_FOLDER + "grassStripShorter.png", loop.getKey(), loop.getValue()));
 		}
		
		for (Pair<Integer, Integer> loop : levelMap.get("BRICK")) {
			Environment.add(new GImage(IMG_FOLDER + "brick.png", loop.getKey(), loop.getValue()));
		}
		
		for (Pair<Integer, Integer> loop : levelMap.get("COIN")) {
			Coins.add(new GImage(IMG_FOLDER + "coin.gif", loop.getKey(), loop.getValue()));
		}
		
		for (Pair<Integer, Integer> loop : levelMap.get("PLANT")) {
			Plants.add(new GImage(IMG_FOLDER + "pPlant.png", loop.getKey(), loop.getValue()));
		}
	}
		

	public GImage getBackground() {
		return Background;
	}

	public ArrayList<GImage> getEnvironment() {
		return Environment;
	}

	public ArrayList<GImage> getCoins(){
		return Coins;
	}
	
	
	public ArrayList<GImage> getPlant(){
		return Plants;
	}
}
