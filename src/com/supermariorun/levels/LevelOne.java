package com.supermariorun.levels;
import java.util.ArrayList;
import com.supermariorun.characters.cMario;
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
	
	public LevelOne() {
		Environment = new ArrayList <GImage> ();
		GrassStrips = new ArrayList <GImage> ();
		Blocks = new ArrayList <GImage> ();
		qBlocks = new ArrayList <GImage> ();
		Pipes = new ArrayList <GImage> ();
		Background = new GImage (IMG_FOLDER + "Background.png", 0, 0);
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStrip.png", 0, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png", 2065, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",3300, 578));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShorter.png", 3300, 558));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShorter.png", 3300, 525));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png", 4550, 585));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",815,558));
		GrassStrips.add(new GImage(IMG_FOLDER + "grassStripShort.png",815,525));
		//for (GImage g : GrassStrips) { g.setSize(1900, 73);}
		
		setUpEnvironment();
	
	}
	
	public void setUpEnvironment() {
		for (GImage a : GrassStrips) {
			Environment.add(a);
		}
		for (GImage a : Blocks) {
			Environment.add(a);
		}
		for (GImage a : qBlocks) {
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
