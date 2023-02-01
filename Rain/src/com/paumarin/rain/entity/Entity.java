package com.paumarin.rain.entity;

import java.util.Random;

import com.paumarin.rain.graphics.Screen;
import com.paumarin.rain.graphics.Sprite;
import com.paumarin.rain.level.Level;

public class Entity {

	protected double x, y;

	protected Sprite sprite;
	protected Level level;
	protected final Random random = new Random();

	private boolean removed = false;

	public void update() {
	}

	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
	}

	public void remove() {
		// Remove from level
		removed = true;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}

}
