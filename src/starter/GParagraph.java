package starter;

/*
 * File: GParagraph.java
 * ---------------------
 * Simple utility to allow you to create multiple Glabels
 * should you want them to go across multiple lines
 * To have new labels that go directly below older ones, 
 * just use a new line sequence in the string.  
 * 
 * For the sake of simplicity, GParagraph does not 
 * support text wrapping, you will manually have to 
 * split the text using \n.  It does have support for fonts.
 */

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import acm.graphics.GCompound;
import acm.graphics.GLabel;

public class GParagraph extends GCompound {
	public static final String NEWLINE = "\n";
	private double startX;
	private double startY;
	private String allText;
	private double labelHeight;
	private List<GLabel> labels;

	public GParagraph(String label, double x, double y) {
		super();
		startX = x;
		startY = y;
		labels = new ArrayList<GLabel>();
		allText = label;
		labels.add(new GLabel("", startX, startY));
		processLabels();
	}

	public void setColor(Color c) {
		for (GLabel l : labels) {
			l.setColor(c);
		}
	}

	public void setText(String text) {
		allText = text;
		processLabels();
	}

	public void setFont(Font f) {
		labels.get(0).setFont(f);
		processLabels();
	}

	public void setFont(String s) {
		labels.get(0).setFont(s);
		processLabels();
	}

	private void processLabels() {
		labelHeight = labels.get(0).getHeight();
		String[] textLines = breakIntoLines(allText);
		setupLabels(textLines.length);
		assignLabels(textLines);
		removeAll();
		for (GLabel l : labels) {
			add(l);
		}
	}

	private void assignLabels(String[] textLines) {
		for (int i = 0; i < labels.size(); i++) {
			if (i >= textLines.length) {
				labels.get(i).setLabel("");
			} else {
				labels.get(i).setLabel(textLines[i]);
			}
		}
	}

	private void setupLabels(int numLabels) {
		int previousSize = labels.size();
		int i;
		for (i = 1; i < labels.size(); i++) {
			labels.get(i).setFont(labels.get(0).getFont());
			labels.get(i).setLocation(startX, startY + i * labelHeight);
		}
		int numToAdd = numLabels - previousSize;
		for (int j = 0; j < numToAdd; j++) {
			GLabel temp = new GLabel("", startX, startY + ((i + j) * labelHeight));
			temp.setFont(labels.get(0).getFont());
			labels.add(temp);
		}
	}

	private String[] breakIntoLines(String label) {
		return label.split(NEWLINE);
	}
}
