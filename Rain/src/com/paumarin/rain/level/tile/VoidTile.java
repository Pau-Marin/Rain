package com.paumarin.rain.level.tile;

import com.paumarin.rain.graphics.Screen;
import com.paumarin.rain.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x, y, this);
	}

}
