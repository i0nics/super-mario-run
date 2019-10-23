package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;

import com.ionics.supermariorun.main.GraphicsPane;
import com.ionics.supermariorun.main.mainSMR;

import acm.graphics.GObject;

public class MenuPane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to
										// all of the GraphicsProgram calls
	private GButton rect;

	public MenuPane(mainSMR mainSMR) {
		super();
		program = mainSMR;
		rect = new GButton("Next", 200, 200, 200, 200);
		rect.setFillColor(Color.RED);
	}

	@Override
	public void showContents() {
		program.add(rect);
	}

	@Override
	public void hideContents() {
		program.remove(rect);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect) {
			program.switchToSome();
		}
	}
}
