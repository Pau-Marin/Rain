package com.paumarin.rain.graphics;

public class Font {

	private static SpriteSheet font = new SpriteSheet("/fonts/aarial.png", 16);
	private static Sprite[] characters = Sprite.split(font);

	public Font() {

	}

}
