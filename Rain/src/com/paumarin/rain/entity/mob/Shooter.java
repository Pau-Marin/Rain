package com.paumarin.rain.entity.mob;

import java.util.List;

import com.paumarin.rain.entity.Entity;
import com.paumarin.rain.graphics.AnimatedSprite;
import com.paumarin.rain.graphics.Screen;
import com.paumarin.rain.graphics.Sprite;
import com.paumarin.rain.graphics.SpriteSheet;
import com.paumarin.rain.util.Debug;
import com.paumarin.rain.util.Vector2i;

public class Shooter extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

	private AnimatedSprite animSprite = down;

	private Entity rand = null;

	private int time = 0;
	private int xa = 0, ya = 0;

	public Shooter(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	public void update() {
		time++;

		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if (random.nextInt(4) == 0) {
				xa = 0;
				ya = 0;
			}
		}

		if (walking) animSprite.update();
		else animSprite.setFrame(0);

		if (ya < 0) {
			dir = Direction.UP;
			animSprite = up;
		}
		if (ya > 0) {
			dir = Direction.DOWN;
			animSprite = down;
		}
		if (xa < 0) {
			dir = Direction.LEFT;
			animSprite = left;
		}
		if (xa > 0) {
			dir = Direction.RIGHT;
			animSprite = right;
		}

		if (xa != 0 || ya != 0) {
			// move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		shootRandom();
	}

	private void shootRandom() {
		if (time % (60 + random.nextInt(61)) == 0) {
			List<Entity> entities = level.getEntities(this, 500);
			entities.add(level.getClientPlayer());

			int index = random.nextInt(entities.size());
			rand = entities.get(index);
		}

		if (rand != null) {
			double dx = rand.getX() - x;
			double dy = rand.getY() - y;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
		}
	}

	private void shootClosest() {
		List<Entity> entities = level.getEntities(this, 500);
		entities.add(level.getClientPlayer());

		double min = 0;
		Entity closest = null;
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			double distance = Vector2i.getDistance(new Vector2i(x, y), new Vector2i(e.getX(), e.getY()));
			if (i == 0 || distance < min) {
				min = distance;
				closest = e;
			}
		}

		if (closest != null) {
			double dx = closest.getX() - x;
			double dy = closest.getY() - y;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
		}
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob(x - 16, y - 16, this);
	}

}
