package com.paumarin.rain.entity.projectile;

import java.util.Random;

import com.paumarin.rain.entity.Entity;
import com.paumarin.rain.graphics.Screen;
import com.paumarin.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected double nx, ny;
	protected double speed, range, damage;
	protected double x, y;
	protected double distance;

	protected final Random random = new Random();

	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	protected void move() {
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	public void render(Screen screen) {
	}

}
