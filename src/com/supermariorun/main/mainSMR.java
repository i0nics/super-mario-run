package com.supermariorun.main;
import java.awt.event.ActionListener;
import com.supermariorun.panes.CharacterPane;
import com.supermariorun.panes.InstructionsPane;
import com.supermariorun.panes.InventoryPane;
import com.supermariorun.panes.LevelPane;
import com.supermariorun.panes.LevelPaneDev;
import com.supermariorun.panes.MenuPane;
import com.supermariorun.panes.PowerUpPane;
import com.supermariorun.panes.ShopPane;
import com.supermariorun.panes.StartPane;
import com.supermariorun.panes.TourPane;
import com.supermariorun.soundeffects.AudioPlayer;

public class mainSMR extends GraphicsApplication implements ActionListener{
	public static final String MUSIC_FOLDER = "sounds";
	private static final String START_TRACK = "startTrack.mp3";
	private static final String MENU_TRACK = "menuTrack.mp3";
	private static final String TOUR_TRACK = "tourTrack.mp3";
	private static final String SHOP_TRACK = "shopTrack.mp3";
	private static final String GUIDE_TRACK = "guideTrack.mp3";
	private static final String PIPE_EFFECT = "pipeEffect.wav";
	private static final String JUMP_EFFECT = "jump.wav";
	private static final String PAUSE_EFFECT = "pause.mp3";
	private static final String RESUME_EFFECT = "resume.mp3";
	private static final String LVL1_TRACK = "LevelOne.mp3";
	
	private StartPane startPane;
	public MenuPane menuPane;
	private ShopPane shopPane;
	private PowerUpPane powerUpPane;
	private CharacterPane characterPane;
	public InstructionsPane guidePane;
	private InventoryPane inventoryPane;
	private TourPane tourPane;
	private playerProgress progress;
	public LevelPane levelPane;
	public LevelPaneDev levelPaneDev;
	
    protected static final int FONT_SIZE = 18;
    public static final int WINDOW_WIDTH = 1155;
	public static final int WINDOW_HEIGHT = 650;
	public AudioPlayer audio;

	public void run() {	
		audio = AudioPlayer.getInstance();
		progress = new playerProgress();
		startPane = new StartPane(this);
		menuPane = new MenuPane(this);
		tourPane = new TourPane(this);
		shopPane = new ShopPane(this);
		powerUpPane = new PowerUpPane (this);
		characterPane = new CharacterPane (this);
		guidePane = new InstructionsPane(this);
		inventoryPane = new InventoryPane(this);
		switchToStart();
	}
	
	public void switchToStart() {
		playStartSound();
		switchToScreen(startPane);
	}
	
	public void switchToMenu() {
		switchToScreen(menuPane);
	}
	
	public void switchToLevel(int levelNum) {
		levelPane = new LevelPane (this, levelNum);
		switchToScreen(levelPane);
	}
	
	public void switchToLevelDev(int levelNum) {
		levelPaneDev = new LevelPaneDev (this, levelNum);
		switchToScreen(levelPaneDev);
	}

	public void switchToShop() {
		switchToScreen(shopPane);
	}
	
	public void switchToInstructions() {
		switchToScreen(guidePane);
	}
	
	public void switchToInventory() {
		switchToScreen(inventoryPane);
	}
	
	public void switchToTour() {
		switchToScreen(tourPane);
	}

	public void switchToPowerUp() {
		switchToScreen(powerUpPane);
	}

	public void switchToCharacter() {
		switchToScreen(characterPane);
	}
	
	public void playStartSound() {
		audio.playSound(MUSIC_FOLDER, START_TRACK, true);
	}
	
	public void stopStartSound() {
		audio.stopSound(MUSIC_FOLDER, START_TRACK);
	}
	
	public void playMenuSound() {
		audio.playSound(MUSIC_FOLDER, MENU_TRACK, true);
	}
	
	public void stopMenuSound() {
		audio.stopSound(MUSIC_FOLDER, MENU_TRACK);
	}
	
	public void playTourSound() {
		audio.playSound(MUSIC_FOLDER, TOUR_TRACK, true);
	}
	
	public void stopTourSound() {
		audio.stopSound(MUSIC_FOLDER, TOUR_TRACK);
	}
	
	public void playShopSound() {
		audio.playSound(MUSIC_FOLDER, SHOP_TRACK, true);
	}
	
	public void stopShopSound() {
		audio.stopSound(MUSIC_FOLDER, SHOP_TRACK);
	}
	
	public void playGuideSound() {
		audio.playSound(MUSIC_FOLDER, GUIDE_TRACK, true);
	}
	
	public void stopGuideSound() {
		audio.stopSound(MUSIC_FOLDER, GUIDE_TRACK);
	}
	
	public void playPipeSound() {
		audio.playSound(MUSIC_FOLDER, PIPE_EFFECT);
	}
	
	public void playJumpSound() {
		audio.playSound(MUSIC_FOLDER, JUMP_EFFECT);
	}
	
	public void playPauseSound() {
		audio.playSound(MUSIC_FOLDER, PAUSE_EFFECT);
	}
	
	public void playResumeSound() {
		audio.playSound(MUSIC_FOLDER, RESUME_EFFECT);
	}
	
	public void playLvlOneTrack() {
		audio.playSound(MUSIC_FOLDER, LVL1_TRACK, true);
	}
	
	public void pauseLvlOneTrack() {
		audio.pauseSound(MUSIC_FOLDER, LVL1_TRACK);
	}
	
	public void stopLvlOneTrack() {
		audio.stopSound(MUSIC_FOLDER, LVL1_TRACK);
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}

	public playerProgress getProgress() {
		return progress;
	}
}