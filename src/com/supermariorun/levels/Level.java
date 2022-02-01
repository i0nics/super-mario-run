package com.supermariorun.levels;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import com.supermariorun.enemies.Enemy;
import com.supermariorun.main.MainSMR;
import com.supermariorun.panes.LevelPane;
import acm.graphics.GImage;
import javafx.util.Pair;

public class Level {
	private static String IMG_FOLDER;
	private MainSMR program;
	private GImage Background;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> Coins;
	private ArrayList <GImage> Plants;
	private ArrayList <Enemy> Enemies;
	private HashMap<String, ArrayList <Pair<Integer, Integer>>> levelMap;
	private ScanLevel scanLevel;
	private LevelPane levelPane;
	
	public Level(MainSMR main, String levelNum, LevelPane levelPane) throws FileNotFoundException {
		program = main;
		IMG_FOLDER = "level" + levelNum + "/";
		this.levelPane = levelPane;
		scanLevel = new ScanLevel();
		Environment = new ArrayList <GImage> ();
		Coins = new ArrayList <GImage> ();
		Plants = new ArrayList<GImage> ();
		Enemies = new ArrayList<Enemy> ();
		Background = new GImage (IMG_FOLDER + levelNum + "Background.png", 0, 0);
		levelMap = scanLevel.runScan("level/level"+ levelNum + ".txt");	
	}
	
	public Level(String levelNum) throws FileNotFoundException {
		IMG_FOLDER = "level" + levelNum + "/";
		scanLevel = new ScanLevel();
		Environment = new ArrayList <GImage> ();
		Coins = new ArrayList <GImage> ();
		Plants = new ArrayList<GImage> ();
		Enemies = new ArrayList<Enemy> ();
		Background = new GImage (IMG_FOLDER + levelNum + "Background.png", 0, 0);
		levelMap = scanLevel.runScan("level/level"+ levelNum + ".txt");	
	}

	public void setUpLevel() {
		Environment.clear();
		Coins.clear();
		Plants.clear();
		Enemies.clear();
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
 		
 		if (levelMap.containsKey("PLATFORM"))
			for (Pair<Integer, Integer> loop : levelMap.get("PLATFORM")) {
				Environment.add(new GImage(IMG_FOLDER + "platform.png", loop.getKey(), loop.getValue()));
		}
		
		if (levelMap.containsKey("COIN")) {
			for (Pair<Integer, Integer> loop : levelMap.get("COIN")) {
				Coins.add(new GImage(IMG_FOLDER + "coin.gif", loop.getKey(), loop.getValue()));
			}
		}
		
		if (levelMap.containsKey("GOOMBA")) {
			for (Pair<Integer, Integer> loop : levelMap.get("GOOMBA")) {
				Enemies.add(new Enemy(program, levelPane, "goomba", loop.getKey(), loop.getValue()));
			}
		}
		
		if (levelMap.containsKey("KOOPATROOPA")) {
			for (Pair<Integer, Integer> loop : levelMap.get("KOOPATROOPA")) {
				Enemies.add(new Enemy(program, levelPane, "koopa", loop.getKey(), loop.getValue()));
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
	
	public ArrayList<Enemy> getEnemies() {
		return Enemies;
	}
}
