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
	private static boolean isLuigiUnlocked = false;
	private static boolean isPrincessUnlocked = false;
	List<Boolean> levels = new ArrayList<Boolean>(3);
	
	public playerProgress() {
		for (boolean iterate : levels) {
			iterate = false;
		}
	}
	
	public  int getNumCoins() {
		return numCoins;
	}
	
	public  int getNumSuperMushrooms() {
		return numSuperMushrooms;
	}
	
	public int getNumSuperStars() {
		return numSuperStars;
	}
	
	public  int getNumCoinRush() {
		return numCoinRush;
	}
	
	public boolean isLuigiUnlocked() {
		return isLuigiUnlocked;
	}
	
	public  boolean isPrincessUnlocked() {
		return isPrincessUnlocked;
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
	
	public  void setPrincessUnlocked() {
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