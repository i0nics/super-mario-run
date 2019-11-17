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
	
	public LevelOne() {
		Environment = new ArrayList <GImage> ();
		GrassStrips = new ArrayList <GImage> ();
		Blocks = new ArrayList <GImage> ();
		qBlocks = new ArrayList <GImage> ();
		Pipes = new ArrayList <GImage> ();
		
		
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
