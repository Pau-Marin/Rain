package com.paumarin.rain.entity;

import java.util.Random;

import com.paumarin.rain.graphics.Screen;
import com.paumarin.rain.level.Level;

public class Entity {

	public int x, y;

	protected Level level;
	protected final Random random = new Random();

	private boolean removed = false;

	public void update() {
	}

	public void render(Screen screen) {
	}

	public void remove() {
		// Remove from level
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void init(Level level) {
		this.level = level;
	}

}
