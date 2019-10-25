package com.supermariorun.soundeffects;
import acm.graphics.GLabel;

public class SoundEffect implements Playable{
	private String name;
	
	public SoundEffect(String n) {
		name = n;
	}

	@Override
	public void play(AudioPlayer player, GLabel statusLabel) {
		player.playSound("sounds", name + ".mp3");
		statusLabel.setLabel("playing: " + name);
	}

	@Override
	public String getName() {
		return name;
	}

}
