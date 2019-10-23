package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private GButton rect;
	private GButton restart;
	//there is a variable called program, which links back to the program itself

	public MenuPane(MainApplication program) {
		super(program);
		rect = new GButton("Resume", 200, 200, 200, 200);
		rect.setFillColor(Color.RED);
		add(rect);
		restart = new GButton("Restart", 100, 400, 100, 100);
		add(restart);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			//Need to make a new method in MainApplication to switch to other panes
			program.switchToSome();
		}else if(obj == restart) {
			program.restartSome();
		}
	}
}
