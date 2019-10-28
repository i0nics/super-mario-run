package com.supermariorun.main;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import acm.graphics.*;
import acm.program.*;

import javax.swing.*;

import com.supermariorun.panes.CharacterPane;
import com.supermariorun.panes.InstructionsPane;
import com.supermariorun.panes.MenuPane;
import com.supermariorun.panes.PowerUpPane;
import com.supermariorun.panes.ShopPane;
import com.supermariorun.panes.StartPane;
import com.supermariorun.panes.TourPane;
import com.supermariorun.soundeffects.AudioPlayer;

import java.awt.*;

public class mainSMR extends GraphicsApplication implements ActionListener{
	public static final String MUSIC_FOLDER = "sounds";
	private static final String START_TRACK = "startTrack.mp3";
	private static final String MENU_TRACK = "menuTrack.flac";
	private StartPane startPane;
	private MenuPane menu;
	private ShopPane shop;
	private PowerUpPane powerUp;
	private CharacterPane character;
	private InstructionsPane instruction;
	private TourPane tour;

    protected static final int FONT_SIZE = 18;
	public static final int WINDOW_HEIGHT = 1000;
	public static final int WINDOW_WIDTH = 1000;
	
  
	public void run() {		
		startPane = new StartPane(this);
		menu = new MenuPane(this);
		shop = new ShopPane(this);
		instruction = new InstructionsPane(this);
		tour = new TourPane(this);
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

	public void switchToShop() {
		switchToScreen(shop);
	}
	
	public void switchToInstruct() {
		switchToScreen(instruction);
	}
	public void switchToTour()
	{
		switchToScreen(tour);
	}
	public void switchToPowerUp() {
		switchToScreen(powerUp);
	}

	public void switchToCharacter() {
		switchToScreen(character);
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
