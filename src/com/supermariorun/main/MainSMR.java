package com.supermariorun.main;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.supermariorun.panes.CharacterPane;
import com.supermariorun.panes.InstructionsPane;
import com.supermariorun.panes.LevelPane;
import com.supermariorun.panes.LevelPaneDev;
import com.supermariorun.panes.MenuPane;
import com.supermariorun.panes.PowerUpPane;
import com.supermariorun.panes.ShopPane;
import com.supermariorun.panes.StartPane;
import com.supermariorun.panes.TourPane;
import com.supermariorun.soundeffects.AudioPlayer;

public class MainSMR extends GraphicsApplication implements ActionListener {
	private static final String MUSIC_FOLDER = "sounds";
	private static final String BUTTON_EFFECT = "buttonEffect.mp3";
	private static final String COIN_EFFECT = "coin.wav";
	private static final String COURSECLEARED_EFFECT = "courseCleared.mp3";
	private static final String GAMEOVER_TRACK = "gameOver.wav";
	private static final String GUIDE_TRACK = "guideTrack.mp3";
	private static final String JUMP_EFFECT = "jump.wav";
	private static final String MENU_TRACK = "menuTrack.mp3";
	private static final String PAUSE_EFFECT = "pause.mp3";
	private static final String PIPE_EFFECT = "pipeEffect.wav";
	private static final String SHOP_TRACK = "shopTrack.mp3";
	private static final String STAR_TRACK = "starSound.wav";
	private static final String START_TRACK = "startTrack.mp3";
	private static final String RESUME_EFFECT = "resume.mp3";
	private static final String TOUR_TRACK = "tourTrack.mp3";
	private static final String STOMP_EFFECT = "stomp.wav";
	
	private StartPane startPane;
	private MenuPane menuPane;
	private ShopPane shopPane;
	private PowerUpPane powerUpPane;
	private CharacterPane characterPane;
	private InstructionsPane guidePane;
	private TourPane tourPane;
	private playerProgress progress;
	private LevelPane levelPane;
	
    protected static final int FONT_SIZE = 18;
    public static final int WINDOW_WIDTH = 1155; 
	public static final int WINDOW_HEIGHT = 650; 
	public Font marioFont;
	public AudioPlayer audio;

	public void run() {	
		try {
			marioFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("Font/SuperMario256.ttf"));
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (FontFormatException e) {
			e.printStackTrace();
		}
		
		audio = AudioPlayer.getInstance();
		progress = new playerProgress();
		startPane = new StartPane(this);
		menuPane = new MenuPane(this);
		tourPane = new TourPane(this);
		shopPane = new ShopPane(this);
		powerUpPane = new PowerUpPane (this);
		characterPane = new CharacterPane (this);
		guidePane = new InstructionsPane(this);
		
		switchToStart();
	}
	
	public void switchToStart() {
		playStartSound();
		switchToScreen(startPane);
	}
	
	public void switchToMenu() {
		switchToScreen(menuPane);
	}
	
	public void switchToLevel(String levelNum) throws FileNotFoundException {
		levelPane = null;
		levelPane = new LevelPane (this, levelNum);
		switchToScreen(levelPane);
	}
	
	public void switchToLevelDev(String levelNum) throws FileNotFoundException {
		switchToScreen(new LevelPaneDev (this, levelNum));
	}

	public void switchToShop() {
		switchToScreen(shopPane);
	}
	
	public void switchToInstructions() {
		switchToScreen(guidePane);
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
	
	public void playButtonEffect() {
		audio.playSound(MUSIC_FOLDER, BUTTON_EFFECT);
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
	
	public void playLvlOneTrack(String lvl) {
		audio.playSound(MUSIC_FOLDER, "Level" + lvl + ".mp3", true);
	}
	
	public void pauseLvlOneTrack(String lvl) {
		audio.pauseSound(MUSIC_FOLDER, "Level" + lvl + ".mp3");
	}
	
	public void stopLvlOneTrack(String lvl) {
		audio.stopSound(MUSIC_FOLDER, "Level" + lvl + ".mp3");
	}
		
	public void playStarTrack() {
		audio.playSound(MUSIC_FOLDER, STAR_TRACK, true);
	}
	
	public void pauseStarTrack() {
		audio.pauseSound(MUSIC_FOLDER, STAR_TRACK);
	}
	
	public void stopStarTrack() {
		audio.pauseSound(MUSIC_FOLDER, STAR_TRACK);
	}
	
	public void playCoinEffect() {
		audio.playSound(MUSIC_FOLDER, COIN_EFFECT);
	}
	
	public void stopCoinEffect() {
		audio.stopSound(MUSIC_FOLDER, COIN_EFFECT);
	}
	
	public void playCourseClearedTrack() {
		audio.playSound(MUSIC_FOLDER, COURSECLEARED_EFFECT);
	}
	
	public void playStompEffect() {
		audio.playSound(MUSIC_FOLDER, STOMP_EFFECT);
	}
	
	public void playGameOverSound() {
		audio.playSound(MUSIC_FOLDER, GAMEOVER_TRACK);
	}
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		requestFocus();
	}

	public playerProgress getProgress() {
		return progress;
	}
	
	public TourPane getTourPane() {
		return tourPane;
	}
}

