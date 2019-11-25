package com.supermariorun.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;

public class playerProgress {
	private static int numCoins = 100;
	private static int numSuperMushrooms = 0;
	private static int numSuperStars = 0;
	private static int numCoinRush = 0;
	private static int highScore = 0;
	private static int tempHigh = 0;
	private static String currentCharacter = "mario";
	private static String currentPowerUp = "";
	private static boolean isYoshiUnlocked = false;
	private static boolean isPrincessUnlocked = false;
	private static boolean isStarPurchased = false;
	private static boolean isMushroomPurchased = false;
	
	List<Boolean> levels = new ArrayList<Boolean>(3);
	
	public playerProgress() {
		for (boolean iterate : levels) {
			iterate = false;
		}
	}
	public int getHighScore() {
		return highScore;
	}
	
	public void sethighScore(int newHigh) {
		tempHigh  = newHigh;
		if(tempHigh > highScore) {
		highScore = tempHigh;
		}
	
	}
	
	public int getNumCoins() {
		return numCoins;
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
	
	public boolean isMushroomPurchased() {
		return isMushroomPurchased;
	}
	public void incrementCoins() {
		numCoins++;
	}
	
	public void incrementCoinsRush() {
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
	public void increaseCoins(int coins) {
		numCoins += coins;
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