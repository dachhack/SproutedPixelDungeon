/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.github.dachhack.sprout.sprites;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.items.weapon.missiles.Dart;
import com.github.dachhack.sprout.levels.Level;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Callback;

public class ScorpianSprite extends MobSprite {


	public ScorpianSprite() {
		super();

		texture(Assets.SCORPIO);

		TextureFilm frames = new TextureFilm(texture, 18, 17);

		idle = new Animation(12, true);
		idle.frames(frames, 32, 32, 32, 32, 32, 32, 32, 32, 33, 34, 33, 34, 33, 34);

		run = new Animation(8, true);
		run.frames(frames, 37, 37, 38, 38);

		attack = new Animation(15, false);
		attack.frames(frames, 32, 35, 36);
		
		die = new Animation(12, false);
		die.frames(frames, 32, 39, 40, 41, 42);

		play(idle);
	}

	@Override
	public int blood() {
		return 0xFF44FF22;
	}	
	
}
