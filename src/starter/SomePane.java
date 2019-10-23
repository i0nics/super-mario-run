package starter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class SomePane extends GraphicsPane {
	private GImage img;
	private GParagraph para;
	public static final int MS_DELAY = 30;
	public static final int START_PARA_X = 150;
	public static final int START_PARA_Y = 300;

	public SomePane(MainApplication program) {
		super(program, MS_DELAY);
		img = new GImage("robot head.jpg", 100, 100);
		para = new GParagraph("", 0, 0);
		reset(); 
		add(img);
		add(para);
	}
	
	/* 
	 * It's good to have a reset function that sets up your variables with
	 * the data that you're looking to have things initialized with
	 * Notice the reset function is called in the constructor.
	 */
	public void reset() {
		para.setText("welcome\nto my\nsecret room!");
		para.setFont("Arial-24");
		para.setLocation(START_PARA_X, START_PARA_Y);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		para.move(5, 0);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		para.setText("you need\nto click\non the eyes\nto go back");
		GObject obj = getElementAt(e.getX(), e.getY());
		if (obj == img) {
			//again, need to make a method in MainApplication to switch to new panes
			program.switchToMenu();
		}
	}
}
