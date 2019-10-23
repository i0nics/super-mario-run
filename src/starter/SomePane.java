package starter;
import java.awt.event.MouseEvent;

import com.ionics.supermariorun.main.GraphicsPane;
import com.ionics.supermariorun.main.mainSMR;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
	private mainSMR program; // you will use program to get access to all of the GraphicsProgram calls
	private GImage img;
	private GParagraph para;

	public SomePane(mainSMR mainSMR) {
		this.program = mainSMR;
		img = new GImage("robot head.jpg", 100, 100);
		para = new GParagraph("welcome\nto my\nsecret room!", 150, 300);
		para.setFont("Arial-24");
	}

	@Override
	public void showContents() {
		program.add(img);
		program.add(para);
	}

	@Override
	public void hideContents() {
		program.remove(img);
		program.remove(para);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		para.setText("you need\nto click\non the eyes\nto go back");
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == img) {
			program.switchToMenu();
		}
	}
}
