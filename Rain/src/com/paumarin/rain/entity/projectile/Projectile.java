package com.paumarin.rain.entity.projectile;

import com.paumarin.rain.entity.Entity;
import com.paumarin.rain.graphics.Screen;
import com.paumarin.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;

	protected Sprite sprite;

	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	protected void move() {
	}

	public void render(Screen screen) {
		screen.renderTile(x, y, sprite);
	}

}
