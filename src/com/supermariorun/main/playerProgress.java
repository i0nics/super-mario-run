package com.supermariorun.main;

import java.util.ArrayList;
import java.util.Arrays;
import com.supermariorun.panes.TourPane;

public class playerProgress {
	private static int numCoins = 100;
	private static int tempHigh = 0;
	private static String currentCharacter = "mario";
	private static String currentPowerUp = "";
	private static boolean isYoshiUnlocked = false;
	private static boolean isPrincessUnlocked = false;
	private static boolean isStarPurchased = false;
	private static boolean isMushroomPurchased = false;
	private static ArrayList<Integer> HighScore = new ArrayList <Integer> (Arrays.asList(0, 0, 0));
	private static ArrayList<Boolean> levelUnlocked = new ArrayList <Boolean> (Arrays.asList(true, false, false));
	
	public void unlockLevel(int lvl, TourPane tour) {
		if (lvl <= 2  && !levelUnlocked.get(lvl)) {
			levelUnlocked.set(lvl, true);
			tour.removeQBlock();
		}
	}
	
	public boolean isLevelUnlocked(int lvl) {
		return levelUnlocked.get(lvl - 1);
	}
	
	public void sethighScore(int newHigh, int lvl) {
		tempHigh  = newHigh;

		if (tempHigh > HighScore.get(lvl - 1)) {
			HighScore.set(lvl - 1, tempHigh);
		}
	}
	
	public int getHighScore(int lvl) {
		return HighScore.get(lvl-1);
	}
	
	public int getNumCoins() {
		return numCoins;
	}
	
	public boolean isYoshiUnlocked() {
		return isYoshiUnlocked;
	}
	
	public boolean isPrincessUnlocked() {
		return isPrincessUnlocked;
	}
	
	public boolean isStarPurchased() {
		return isStarPurchased;
	}
	
	public boolean isMushroomPurchased() {
		return isMushroomPurchased;
	}
	
	public void incrementCoins() {
		numCoins++;
	}
	
	public void incrementCoinsRush() {
		numCoins =+ 2;
	}
	
	public void decreaseCoins(int coins) {
		numCoins -= coins;
	}
	
	public void increaseCoins(int coins) {
		numCoins += coins;
	}
	
	public void setYoshiUnlocked() {
		isYoshiUnlocked = true;
	}
	
	public void setPrincessUnlocked() {
		isPrincessUnlocked = true;
	}
	
	public void setStarPurchased() {
		isStarPurchased = true;
	}
	
	public void setMushroomPurchased() {
		isMushroomPurchased = true;
	}
	
	public void resetStarPurchased() {
		isStarPurchased = false;
	}
	
	public void resetMushroomPurchased() {
		isMushroomPurchased = false;
	}
	
	public String getCurrentCharacter() {
		return currentCharacter;
	}
	
	public void setCurrentCharacter(String charac) {
		currentCharacter = charac;
	}
	
	public String getCurrentPowerUp() {
		return currentPowerUp;
	}
	
	public void setCurrentPowerUp(String power) {
		currentPowerUp = power;
	}
	
	public void clearCurrentPowerUp() {
		currentPowerUp = "";
	}
}