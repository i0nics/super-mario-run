package com.supermariorun.enemies;

import acm.graphics.GImage;

public interface Enemies {
	public void updateBounds();
	public void getEnemySpeed();
	public GImage getEnemyImg();
	public void Run();
}
