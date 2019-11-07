package com.supermariorun.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;

public class playerProgress {
	private static int lvl = 0;
	private static int numCoins = 100;
	private static int numSuperMushrooms = 0;
	private static int numSuperStars = 0;
	private static int numCoinRush = 0;
	List<Boolean> levels = new ArrayList<Boolean>(3);
	private static boolean isLuigiUnlocked = false;
	private static boolean isPrincessUnlocked = false;
	
	public playerProgress() {
		for (boolean iterate : levels) {
			iterate = false;
		}
	}
	
	public static int getNumCoins() {
		return numCoins;
	}
	
	public static int getNumSuperMushrooms() {
		return numSuperMushrooms;
	}
	
	public static int getNumSuperStars() {
		return numSuperStars;
	}
	
	public static int getNumCoinRush() {
		return numCoinRush;
	}
	
	public static boolean isLuigiUnlocked() {
		return isLuigiUnlocked;
	}
	
	public static boolean isPrincessUnlocked() {
		return isPrincessUnlocked;
	}
	
	public static void incrementCoins() {
		numCoins++;
	}
	
	public static void incrementCoinsRush() {
		numCoins =+ 2;
	}
	
	public static void incrementNumSuperMushrooms() {
		numSuperMushrooms++;
	}
	
	public static void incrementNumSuperStars() {
		numSuperStars++;
	}
	
	public static void incrementNumCoinRush() {
		numCoinRush++;
	}
	
	public static void decreaseCoins(int coins) {
		numCoins -= coins;
	}
	
	public static void decrementSuperMushrooms() {
		numSuperMushrooms--;
	}
	
	public static void decrementNumSuperStars() {
		numSuperStars--;
	}
	
	public static void decrementNumCoinRush() {
		numCoinRush--;
	}
	
	public static void setLuigiUnlocked() {
		playerProgress.isLuigiUnlocked = true;
	}
	
	public static void setPrincessUnlocked() {
		playerProgress.isPrincessUnlocked = true;
	}
	
	public boolean isLvlUnlocked(int lvl) {
		return levels.get(lvl);
	}
	
	public void unlockLevel() {
		levels.set(lvl, true);
		lvl++;
	}
}