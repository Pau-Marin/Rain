package com.paumarin.rain.graphics;

import java.util.Random;

import com.paumarin.rain.entity.mob.Chaser;
import com.paumarin.rain.entity.mob.Mob;
import com.paumarin.rain.entity.mob.Star;
import com.paumarin.rain.entity.projectile.Projectile;
import com.paumarin.rain.level.tile.Tile;

public class Screen {

	public final int MAP_SIZE = 8;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	private final int ALPHA_COL = 0xffff00ff;

	public int xOffset, yOffset;

	public int width, height;

	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.SPRITE_WIDTH];
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderTextCharacter(int xp, int yp, Sprite sprite, int color, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[x + y * sprite.getWidth()];
				if (col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = color;
			}
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				if (col != ALPHA_COL) pixels[xa + ya * width] = col;
			}
		}
	}

	public void renderMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < mob.getSprite().SIZE; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < mob.getSprite().SIZE; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -mob.getSprite().SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = mob.getSprite().pixels[xs + ys * mob.getSprite().SIZE];
				if ((mob instanceof Chaser) && col == 0xFF472BBF) col = 0xFFBA0015;
				if ((mob instanceof Star) && col == 0xFF472BBF) col = 0xFFE8E83A;
				if (col != ALPHA_COL) pixels[xa + ya * width] = col;
			}
		}
	}

	public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int x = xp; x < xp + width; x++) {
			if (x < 0 || x >= this.width || yp >= this.height) continue;
			if (yp > 0) pixels[x + yp * this.width] = color;
			if (yp + height >= this.height) continue;
			if (yp + height > 0) pixels[x + (yp + height) * this.width] = color;
		}

		for (int y = yp; y <= yp + height; y++) {
			if (xp >= this.width || y < 0 || y >= this.height) continue;
			if (xp > 0) pixels[xp + y * this.width] = color;
			if (xp + width >= this.width) continue;
			if (xp + width > 0) pixels[(xp + width) + y * this.width] = color;
		}

	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
