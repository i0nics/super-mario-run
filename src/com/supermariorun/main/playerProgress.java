package com.supermariorun.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;

public class playerProgress {
	private  int lvl = 0;
	private static int numCoins = 100;
	private static int numSuperMushrooms = 0;
	private static int numSuperStars = 0;
	private static int numCoinRush = 0;
	private static String currentCharacter = "mario";
	private static boolean isLuigiUnlocked = false;
	private static boolean isPrincessUnlocked = false;
	private static boolean isStarPurchased = false;
	private static boolean isFlowerPurchased = false;
	private static boolean isMushroomPurchased = false;
	
	List<Boolean> levels = new ArrayList<Boolean>(3);
	
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
	
	public static boolean isStarPurchased() {
		return isStarPurchased;
	}
	
	public static boolean isFlowerPurchased() {
		return isFlowerPurchased;
	}
	
	public static boolean isMushroomPurchased() {
		return isMushroomPurchased;
	}
	public static void incrementCoins() {
		numCoins++;
	}
	
	public static void incrementCoinsRush() {
		numCoins =+ 2;
	}
	
	public  void incrementNumSuperMushrooms() {
		numSuperMushrooms++;
	}
	
	public  void incrementNumSuperStars() {
		numSuperStars++;
	}
	
	public  void incrementNumCoinRush() {
		numCoinRush++;
	}
	
	public void decreaseCoins(int coins) {
		numCoins -= coins;
	}
	
	public void decrementSuperMushrooms() {
		numSuperMushrooms--;
	}
	
	public void decrementNumSuperStars() {
		numSuperStars--;
	}
	
	public  void decrementNumCoinRush() {
		numCoinRush--;
	}
	
	public void setLuigiUnlocked() {
		playerProgress.isLuigiUnlocked = true;
	}
	
	public void setPrincessUnlocked() {
		playerProgress.isPrincessUnlocked = true;
	}
	
	public void setStarPurchased() {
		playerProgress.isStarPurchased = true;
	}
	
	public void setFlowerPurchased() {
		playerProgress.isFlowerPurchased = true;
	}
	
	public void setMushroomPurchased() {
		playerProgress.isMushroomPurchased = true;
	}
	
	public boolean isLvlUnlocked(int lvl) {
		return levels.get(lvl);
	}
	
	public String getCurrentCharacter() {
		return currentCharacter;
	}
	
	public static void setCurrentCharacter(String charac) {
		currentCharacter = charac;
	}
	
	public void unlockLevel() {
		levels.set(lvl, true);
		lvl++;
	}
}