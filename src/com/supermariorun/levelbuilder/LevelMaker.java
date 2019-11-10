package com.supermariorun.levelbuilder;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;

import acm.graphics.GImage;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

public class LevelMaker extends GraphicsProgram {
	public static final int PROGRAM_WIDTH = 1150;
	public static final int PROGRAM_HEIGHT = 650;
	public static final int NUM_ROWS = 17;
	public static final int NUM_COLS = 2000;
	private GObject toDrag;
	private GImage grass;
	private GImage qBlock;
	private int lastX;
	private int lastY;

	public void run() {
		drawLevel();
		addMouseListeners();
	}

	private void drawLevel() {
		drawGridLines();

	}

	private void drawGridLines() {

		for (int i = 1; i < NUM_COLS; i++) {
			GLine line = new GLine(i * spaceWidth(), 0, i * spaceWidth(), PROGRAM_HEIGHT);
			add(line);
		}

		for (int i = 1; i < NUM_ROWS; i++) {
			GLine line = new GLine(0, i * spaceHeight(), PROGRAM_WIDTH, i * spaceHeight());
			add(line);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY();
		GObject obj = getElementAt(e.getX(), e.getY());

		if (obj instanceof GImage) {
			toDrag = getElementAt(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (toDrag != null) {
			toDrag.move(e.getX() - lastX, e.getY() - lastY);
			lastX = e.getX();
			lastY = e.getY();
		}
	}

	private double spaceWidth() {
		return PROGRAM_WIDTH / 30;
	}

	private double spaceHeight() {
		return PROGRAM_HEIGHT / NUM_ROWS;
	}

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

}
