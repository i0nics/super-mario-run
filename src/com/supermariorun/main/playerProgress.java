package com.supermariorun.main;

public class playerProgress {
	private static int numCoins = 100;
	private static int numSuperMushrooms = 0;
	private static int numSuperStars = 0;
	private static int numCoinRush = 0;
	//private static ArrayList <boolean >level = false;
	private static boolean isLuigiUnlocked = false;
	private static boolean isPrincessUnlocked = false;
	
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
	
	public static void decreaseCoins (int coins) {
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

}