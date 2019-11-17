package com.supermariorun.levels;
import java.util.ArrayList;
import com.supermariorun.characters.Character;
import acm.graphics.GImage;
import javafx.scene.layout.Background;

public class LevelOne implements ILevel{
	public static final String IMG_FOLDER = "LevelOne/";
	private GImage Background;
	
	private ArrayList <GImage> Pipes;
	private ArrayList <GImage> GrassStrips;
	private ArrayList <GImage> Blocks;
	private ArrayList <GImage> qBlocks;
	private ArrayList <GImage> Environment;
	private ArrayList <GImage> coins;
	private GImage coin;
	public LevelOne() {
		Environment = new ArrayList <GImage> ();
		GrassStrips = new ArrayList <GImage> ();
		Blocks = new ArrayList <GImage> ();
		qBlocks = new ArrayList <GImage> ();
		Pipes = new ArrayList <GImage> ();
		coins = new ArrayList <GImage> ();
		
		
		Background = new GImage (IMG_FOLDER + "Background.png", 0, 0);
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStrip.png", -2, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png", 2065, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",3300, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShorter.png", 3300, 558));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShorter.png", 3300, 525));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png", 4550, 585));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",814, 541));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",814, 505));
		
		Pipes.add(new GImage(IMG_FOLDER + "pipe.png",1063, 470));

		Pipes.add(new GImage(IMG_FOLDER + "pipe.png",1855, 453));

		Pipes.add(new GImage(IMG_FOLDER + "pipe.png",2293, 540));

		Pipes.add(new GImage(IMG_FOLDER + "pipe.png",2598, 540));
		
		Pipes.add(new GImage(IMG_FOLDER + "pipe.png",3736, 490));
		
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 2905,454));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 3016,454));
		
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 3867,420));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 3892,420));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 3920,420));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 3948,420));
		
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4055,390));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4083,390));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4111,390));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4139,390));
		
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4219,360));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4239,360));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4259,360));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4279,360));
		Blocks.add(new GImage(IMG_FOLDER + "brick.png", 4299,360));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",1033,470));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",1085,440));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",1140,470));
		
		coins.add(new GImage(IMG_FOLDER + "coin.gif",350,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",400,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",450,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",500,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",1706,470));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",1750,470));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",1780,470));
		
		coins.add(new GImage(IMG_FOLDER + "coin.gif",1810,470));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",1865,425));
		
		coins.add(new GImage(IMG_FOLDER + "coin.gif",2260,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",2300,505));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",2370,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",2560,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",2625,505));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",2671,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",3891,390));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",3921,390));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",3951,390));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4051,355));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4081,355));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4111,355));
		
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4241,325));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4291,325));
		
		coins.add(new GImage(IMG_FOLDER + "coin.gif",2671,535));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",2671,535));
		
		
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4660,540));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4690,540));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4720,540));
		coins.add(new GImage(IMG_FOLDER + "coin.gif",4760,540));
		for (GImage g : coins) { g.setSize(35,35);}
	
		
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
		for(GImage a : coins)
		{
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


}
