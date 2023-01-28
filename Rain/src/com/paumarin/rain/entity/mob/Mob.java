package com.paumarin.rain.entity.mob;

import com.paumarin.rain.entity.Entity;
import com.paumarin.rain.entity.projectile.Projectile;
import com.paumarin.rain.entity.projectile.WizardProjectile;
import com.paumarin.rain.graphics.Screen;
import com.paumarin.rain.graphics.Sprite;

public abstract class Mob extends Entity {

	protected boolean moving = false;
	protected boolean walking = false;

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if (xa > 0) dir = Direction.RIGHT;
		if (xa < 0) dir = Direction.LEFT;
		if (ya > 0) dir = Direction.DOWN;
		if (ya < 0) dir = Direction.UP;

		for (int x = 0; x < Math.abs(xa); x++) {
			if (!collision(abs(xa), ya)) {
				this.x += abs(xa);
			}
		}

		for (int y = 0; y < Math.abs(ya); y++) {
			if (!collision(xa, abs(ya))) {
				this.y += abs(ya);
			}
		}
	}

	private int abs(double value) {
		if (value < 0) return -1;
		return 1;
	}

	public abstract void update();

	public abstract void render(Screen screen);

	protected void shoot(int x, int y, double dir) {
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;

		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			double yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}

		return solid;
	}

}
