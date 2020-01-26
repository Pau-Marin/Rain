package com.paumarin.rain.graphics;

import java.util.Random;

public class Screen {

	public final int MAP_SIZE = 8;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	private int width, height;

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

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
			//if (yy < 0 || yy >= height) break;
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset;
				//if (xx < 0 || xx >= width) break;
				pixels[x + y * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}
		}
	}

}
