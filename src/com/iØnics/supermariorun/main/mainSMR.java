package com.iØnics.supermariorun.main;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;
import acm.program.*;

import javax.swing.*;

import com.iØnics.supermariorun.panes.MenuPane;
import com.iØnics.supermariorun.panes.StartPane;
import com.iØnics.supermariorun.soundeffects.AudioPlayer;

import java.awt.*;

public class mainSMR extends GraphicsApplication{
	public static final String MUSIC_FOLDER = "sounds";
	private static final String START_TRACK = "startTrack.mp3";
	private StartPane startPane;
	private MenuPane menu;
	private int count;

    protected static final int FONT_SIZE = 18;
	public static final int WINDOW_HEIGHT = 1000;
	public static final int WINDOW_WIDTH = 1000;
	
  
	public void run() {		
		startPane = new StartPane(this);
		menu = new MenuPane(this);
		switchToStart();
	}
	
	public void switchToStart() {
		playStartSound();
		switchToScreen(startPane);
	}
	
	public void switchToMenu() {
		playStartSound();
		switchToScreen(menu);
	}


	private void playStartSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, START_TRACK, true);
	}
	

	
	public void init() {
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
		/*screen = new ScreenManager();
        DisplayMode displayMode =
        screen.findFirstCompatibleMode(POSSIBLE_MODES);
        screen.setFullScreen(displayMode);

        Window window = screen.getFullScreenWindow();
        window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
        window.setBackground(Color.BLACK);
        window.setForeground(Color.WHITE);*/

	}
	
}
