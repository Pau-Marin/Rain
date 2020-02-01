package com.paumarin.rain.entity.mob;

import com.paumarin.rain.entity.Entity;
import com.paumarin.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;

	protected int dir = 0;
	protected boolean moving = false;

	public void move() {
	}

	public void update() {
	}

	private boolean collision() {
		return false;
	}

}
