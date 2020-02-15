package com.paumarin.rain.level.tile.spawn_level;

import com.paumarin.rain.graphics.Screen;
import com.paumarin.rain.graphics.Sprite;
import com.paumarin.rain.level.tile.Tile;

public class SpawnGrassTile extends Tile {

	public SpawnGrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, sprite);
	}

}
