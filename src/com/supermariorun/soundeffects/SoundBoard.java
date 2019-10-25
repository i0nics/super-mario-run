package com.supermariorun.soundeffects;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.HashMap;
import acm.graphics.*;
import acm.program.*;

public class SoundBoard extends GraphicsProgram {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int STATUS_LABEL_X = 200;
	public static final int STATUS_LABEL_Y = 550;

	public static final int HORN_LABEL_X = 200;
	public static final int HORN_LABEL_Y = 200;
	public static final int AWW_LABEL_X = 600;
	public static final int AWW_LABEL_Y = HORN_LABEL_Y;

	public static final String FONT_STYLE = "Arial-20-Bold";
	public static final String AHH_YEAH = "Ahh Yeah";
	public static final String AIR_HORN = "Air Horn";
	public static final String EXPLOSION = "Explosion";
	
	private HashMap<GLabel, Playable>  musicMap;
	private AudioPlayer player;
	private GLabel statusLabel;
	private GLabel ahhYeah;
	private GLabel airHorn;
	
	public void run() {
		player = AudioPlayer.getInstance();
		musicMap = new HashMap <GLabel, Playable> ();
		ahhYeah = new GLabel(AHH_YEAH, AWW_LABEL_X, AWW_LABEL_Y);
		airHorn =  new GLabel(AIR_HORN, HORN_LABEL_X, HORN_LABEL_Y);
		statusLabel = new GLabel("", STATUS_LABEL_X, STATUS_LABEL_Y);
		musicMap.put(ahhYeah, new SoundEffect (AHH_YEAH));
		musicMap.put(airHorn, new SoundEffect (AIR_HORN));
		add(ahhYeah);
		add(airHorn);
		add(statusLabel);
		addMouseListeners();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject elem = getElementAt(e.getX(), e.getY());
		if (elem instanceof GLabel) {
			musicMap.get(elem).play(player, (GLabel) elem);
		}
	}

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
}
