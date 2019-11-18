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
	private static boolean isYoshiUnlocked = false;
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
	
	public int getNumCoins() {
		return numCoins;
	}
	
	public static int getNumSuperMushrooms() {
		return numSuperMushrooms;
	}
	
	public static int getNumSuperStars() {
		return numSuperStars;
	}
	
	public int getNumCoinRush() {
		return numCoinRush;
	}
	
	public boolean isYoshiUnlocked() {
		return isYoshiUnlocked;
	}
	
	public  boolean isPrincessUnlocked() {
		return isPrincessUnlocked;
	}
	
	public boolean isStarPurchased() {
		return isStarPurchased;
	}
	
	public boolean isFlowerPurchased() {
		return isFlowerPurchased;
	}
	
	public boolean isMushroomPurchased() {
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
	
	public void setYoshiUnlocked() {
		isYoshiUnlocked = true;
	}
	
	public void setPrincessUnlocked() {
		isPrincessUnlocked = true;
	}
	
	public void setStarPurchased() {
		isStarPurchased = true;
	}
	
	public void setFlowerPurchased() {
		isFlowerPurchased = true;
	}
	
	public void setMushroomPurchased() {
		isMushroomPurchased = true;
	}
	
	public boolean isLvlUnlocked(int lvl) {
		return levels.get(lvl);
	}
	
	public String getCurrentCharacter() {
		return currentCharacter;
	}
	
	public void setCurrentCharacter(String charac) {
		currentCharacter = charac;
	}
	
	public void unlockLevel() {
		levels.set(lvl, true);
		lvl++;
	}
}