package com.supermariorun.levels;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import com.supermariorun.characters.Character;
import acm.graphics.GImage;
import javafx.scene.layout.Background;
import javafx.util.Pair;

public class LevelOne implements ILevel{
	public static final String IMG_FOLDER = "LevelOne/";
	private GImage Background;
	
	private ArrayList <GImage> Pipes;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> Blocks;
	private ArrayList <GImage> qBlocks;
	private ArrayList <GImage> coins;
	private HashMap<String, ArrayList <Pair<Integer, Integer>>> levelMap;
	private ScanLevel scanLevel;
	private GImage coin;
	
	public LevelOne() throws FileNotFoundException {
		Environment = new ArrayList <GImage> ();
		qBlocks = new ArrayList <GImage> ();
		coins = new ArrayList <GImage> ();
		scanLevel = new ScanLevel();
		Background = new GImage (IMG_FOLDER + "Background.png", 0, 0);
		levelMap = scanLevel.runScan("level/levelOne.txt");
	}
	
	@Override
	public void setUpLevel() {
		Environment.clear();
		coins.clear();
		Background.setLocation(0, 0);
		
		Environment.add(new GImage(IMG_FOLDER + "grassStrip.png", -2, 578));
		Environment.add(new GImage(IMG_FOLDER + "grassStripShort.png", 2065, 578));
		Environment.add(new GImage(IMG_FOLDER + "grassStripShort.png",3300, 578));
		Environment.add(new GImage(IMG_FOLDER + "grassStripShorter.png", 3300, 558));
		Environment.add(new GImage(IMG_FOLDER + "grassStripShorter.png", 3300, 525));
		Environment.add(new GImage(IMG_FOLDER + "grassStripShort.png", 4550, 585));
		Environment.add(new GImage(IMG_FOLDER + "grassStripShort.png",814, 541));
		Environment.add(new GImage(IMG_FOLDER + "grassStripShort.png",814, 505));
		
		for (Pair<Integer, Integer> loop : levelMap.get("PIPE")) {
			Environment.add(new GImage(IMG_FOLDER + "pipe.png", loop.getKey(), loop.getValue()));
		}
	
		for (Pair<Integer, Integer> loop : levelMap.get("BRICK")) {
			Environment.add(new GImage(IMG_FOLDER + "brick.png", loop.getKey(), loop.getValue()));
		}
		
		for (Pair<Integer, Integer> loop : levelMap.get("COIN")) {
			coins.add(new GImage(IMG_FOLDER + "coin.gif", loop.getKey(), loop.getValue()));
		}
		setUpEnvironment();
	}
	
	public void setUpEnvironment() {
		for (GImage a : coins) {
			Environment.add(a);
		}
	}
	
	@Override
	public GImage getBackground() {
		return Background;
	}

	@Override
	public ArrayList<GImage> getPipes() {
		return Pipes;
	}

	@Override
	public ArrayList<GImage> getEnvironment() {
		return Environment;
	}
	@Override
	public ArrayList<GImage> getCoins(){
		return coins;
	}
}
