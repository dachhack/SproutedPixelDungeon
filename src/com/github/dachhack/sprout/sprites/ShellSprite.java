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
import com.github.dachhack.sprout.actors.mobs.Shell;
import com.github.dachhack.sprout.effects.Lightning;
import com.watabou.noosa.TextureFilm;

public class ShellSprite extends MobSprite {

	private int[] points = new int[2];

	public ShellSprite() {
		super();

		texture(Assets.SHELL);
		TextureFilm frames = new TextureFilm(texture, 16, 16);

		idle = new Animation(10, true);
		idle.frames(frames, 0, 0, 0, 0, 0, 0, 0, 0, 0);

		run = idle.clone();
		die = idle.clone();
		attack = idle.clone();

		zap = attack.clone();
		
		idle();
	}

	@Override
	public void zap(int pos) {

		points[0] = ch.pos;
		points[1] = pos;
		parent.add(new Lightning(points, 2, (Shell) ch));

		turnTo(ch.pos, pos);
		play(zap);
	}
	
}
