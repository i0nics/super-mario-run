package starter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* File: GraphicsPane.java
 * -----------------------
 * Like you did with your own graphics programs, simply
 * extend from GraphicsPane and implement
 * as little or as much of the mouse listeners that you need
 * for your own programs.  Notice however that in this situation
 * There is no access to the GraphicsProgram window.
 * Make sure to distinguish between your constructor
 * and using showContents and hideContents
 */

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import acm.graphics.GObject;

public abstract class GraphicsPane implements Interfaceable, ActionListener {
	protected MainApplication program;
	protected List<GObject> allItems;
	protected Timer paneTimer;
	private boolean isActive;
	
	public GraphicsPane(MainApplication app) {
		program = app;
		allItems = new ArrayList<GObject>();
	}
	
	public GraphicsPane(MainApplication app, int ms) {
		this(app);
		setTimer(ms);
	}
	
	public GraphicsPane(MainApplication app, int ms, ActionListener e) {
		this(app);
		setTimer(ms, e);
	}
	
	@Override
	public void activatePane(boolean shouldActivate) {
		isActive = shouldActivate;
		for(GObject item:allItems) {
			if(isActive) {
				program.add(item);
			}else{
				program.remove(item);
			}
		}
		activateTimer(isActive);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void setTimer(int ms) {
		setTimer(ms, this);
	}
	
	public void setTimer(int ms, ActionListener e) {
		paneTimer = new Timer(ms, e);
	}
	
	private void activateTimer(boolean shouldActivate) {
		if(paneTimer == null) {
			return;
		}
		if(shouldActivate) {
			paneTimer.start();
		}else{
			paneTimer.stop();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void add(GObject o) {
		if(isActive) {
			program.add(o);
		}
		if(!allItems.contains(o)) {
			allItems.add(o);
		}
	}
	
	public void remove(GObject o) {
		if(isActive) {
			program.remove(o);
		}
		allItems.remove(o);
	}
	
	public void removeAll() {
		if(isActive) {
			program.removeAll();
		}
		allItems.clear();
	}
	
	public GObject getElementAt(double x, double y) {
		return program.getElementAt(x, y);
	}
}
