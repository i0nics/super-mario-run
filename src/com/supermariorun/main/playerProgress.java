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
	
	public static void addCoin() {
		numCoins++;
	}
	
	public static void addNumSuperMushrooms() {
		numSuperMushrooms++;
	}
	
	public static void setNumSuperStars() {
		numSuperStars++;
	}
	
	public static void setNumCoinRush() {
		numCoinRush++;
	}
	
	public static void setLuigiUnlocked() {
		playerProgress.isLuigiUnlocked = true;
	}
	
	public static void setPrincessUnlocked() {
		playerProgress.isPrincessUnlocked = true;
	}

}
