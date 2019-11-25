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
	private static String IMG_FOLDER;
	private GImage Background;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> Coins;
	private ArrayList <GImage> Plants;
	private ArrayList <GImage> Goombas;
	private ePiranhaPlant ePlant;
	private HashMap<String, ArrayList <Pair<Integer, Integer>>> levelMap;
	private ScanLevel scanLevel;
	
	public Level(String levelNum) throws FileNotFoundException {
		IMG_FOLDER = "level" + levelNum + "/";
		Environment = new ArrayList <GImage> ();
		Coins = new ArrayList <GImage> ();
		Plants = new ArrayList<GImage> ();
		Goombas = new ArrayList<GImage> ();
		ePlant = new ePiranhaPlant(this);
		scanLevel = new ScanLevel();
		
		Background = new GImage (IMG_FOLDER + levelNum + "Background.png", 0, 0);
		levelMap = scanLevel.runScan("level/level"+ levelNum + ".txt");	}
	
	public void setUpLevel() {
		Environment.clear();
		Coins.clear();
		Plants.clear();
		Background.setLocation(0, 0);
		
		if (levelMap.containsKey("PIPE")) {
			for (Pair<Integer, Integer> loop : levelMap.get("PIPE")) {
				Environment.add(new GImage(IMG_FOLDER + "pipe.png", loop.getKey(), loop.getValue()));
			}
		}
		
		if (levelMap.containsKey("GROUNDLONG")) {
			for (Pair<Integer, Integer> loop : levelMap.get("GROUNDLONG")) {
	 			Environment.add(new GImage(IMG_FOLDER + "grassStrip.png", loop.getKey(), loop.getValue()));
	 		}
		}
		
		if (levelMap.containsKey("GROUND")) {
	 		for (Pair<Integer, Integer> loop : levelMap.get("GROUND")) {
	 			Environment.add(new GImage(IMG_FOLDER + "grassStripShort.png", loop.getKey(), loop.getValue()));
	 		}
		}
 		
 		if (levelMap.containsKey("GROUNDSHORT")) {
	 		for (Pair<Integer, Integer> loop : levelMap.get("GROUNDSHORT")) {
	 			Environment.add(new GImage(IMG_FOLDER + "grassStripShorter.png", loop.getKey(), loop.getValue()));
	 		}
 		}
		
 		if (levelMap.containsKey("BRICK"))
			for (Pair<Integer, Integer> loop : levelMap.get("BRICK")) {
				Environment.add(new GImage(IMG_FOLDER + "brick.png", loop.getKey(), loop.getValue()));
		}
		
		if (levelMap.containsKey("COIN")) {
			for (Pair<Integer, Integer> loop : levelMap.get("COIN")) {
				Coins.add(new GImage(IMG_FOLDER + "coin.gif", loop.getKey(), loop.getValue()));
			}
		}
		
		if (levelMap.containsKey("PLANT")) {
			for (Pair<Integer, Integer> loop : levelMap.get("PLANT")) {
				Plants.add(new GImage("enemies/pPlant.png", loop.getKey(), loop.getValue()));
			}
		}
		
		if (levelMap.containsKey("GOOMBA")) {
			for (Pair<Integer, Integer> loop : levelMap.get("GOOMBA")) {
				Goombas.add(new GImage("enemies/goomba.png", loop.getKey(), loop.getValue()));
			}
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
	
	public ArrayList<GImage> getGoombas() {
		return Goombas;
	}
}
