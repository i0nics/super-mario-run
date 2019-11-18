package com.supermariorun.levels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javafx.util.Pair;

import acm.graphics.GLabel;

public class ScanLevel {

	private HashMap<String, ArrayList <Pair<Integer, Integer>>> levelMap;
	private ArrayList <Pair<Integer, Integer>> listCoord;
		
	public HashMap<String, ArrayList <Pair<Integer, Integer>>> runScan(String file) throws FileNotFoundException {
			
		Scanner readFile = new Scanner(new File(file));
		String obj, x, y;
		int loop;
		levelMap = new HashMap<String, ArrayList<Pair<Integer, Integer>>> ();
		
		
		while (readFile.hasNextLine()) {
			
			obj = readFile.next();
			loop = Integer.valueOf(readFile.next());
			listCoord = new ArrayList<Pair<Integer, Integer>>();
			
			for (int i = 0; i < loop; i++) {
				x = readFile.next();
				y = readFile.next();
				listCoord.add(new Pair <Integer, Integer> (Integer.valueOf(x), Integer.valueOf(y)));
			}
			
			levelMap.put(obj, listCoord);
		}
		readFile.close();
		return levelMap;
	}
}
