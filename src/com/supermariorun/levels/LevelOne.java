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
	private ArrayList <GImage> GrassStrips;
	private ArrayList <GImage> Blocks;
	private ArrayList <GImage> qBlocks;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> coins;
	private HashMap<String, ArrayList <Pair<Integer, Integer>>> levelMap;
	private ScanLevel scanLevel;
	private GImage coin;
	
	public LevelOne(String levelNum) throws FileNotFoundException {
		Environment = new ArrayList <GImage> ();
		GrassStrips = new ArrayList <GImage> ();
		Blocks = new ArrayList <GImage> ();
		qBlocks = new ArrayList <GImage> ();
		Pipes = new ArrayList <GImage> ();
		coins = new ArrayList <GImage> ();
		scanLevel = new ScanLevel();
		levelMap = scanLevel.runScan("level/level"+ levelNum + ".txt");
		
		Background = new GImage (IMG_FOLDER + "Background.png", 0, 0);
		
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStrip.png", -2, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png", 2065, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",3300, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShorter.png", 3300, 558));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShorter.png", 3300, 525));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png", 4550, 585));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",814, 541));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",814, 505));
		
		
		for (Pair<Integer, Integer> loop : levelMap.get("PIPE")) {
			Pipes.add(new GImage(IMG_FOLDER + "pipe.png", loop.getKey(), loop.getValue()));
		}
	
		for (Pair<Integer, Integer> loop : levelMap.get("BRICK")) {
			Blocks.add(new GImage(IMG_FOLDER + "brick.png", loop.getKey(), loop.getValue()));
		}
		
		for (Pair<Integer, Integer> loop : levelMap.get("COIN")) {
			coins.add(new GImage(IMG_FOLDER + "coin.gif", loop.getKey(), loop.getValue()));
		}
		
		setUpEnvironment();
	
	}
	
	public void setUpEnvironment() {
		for(GImage a : Pipes) {
			Environment.add(a);
		}
		for (GImage a : GrassStrips) {
			Environment.add(a);
		}
		
		for (GImage a : Blocks) {
			Environment.add(a);
		}
		
		for (GImage a : qBlocks) {
			Environment.add(a);
		}
		
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
