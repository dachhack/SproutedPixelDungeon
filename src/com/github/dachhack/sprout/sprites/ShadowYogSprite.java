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
import com.github.dachhack.sprout.actors.mobs.ShadowYog;
import com.github.dachhack.sprout.effects.Lightning;
import com.github.dachhack.sprout.effects.Splash;
import com.watabou.noosa.TextureFilm;

public class ShadowYogSprite extends MobSprite {
	
	private int[] points = new int[2];

	public ShadowYogSprite() {
		super();

		texture(Assets.SHADOWYOG);

		TextureFilm frames = new TextureFilm(texture, 20, 19);

		idle = new Animation(10, true);
		idle.frames(frames, 0, 1, 2, 2, 1, 0, 3, 4, 4, 3, 0, 5, 6, 6, 5);

		run = new Animation(12, true);
		run.frames(frames, 0);

		attack = new Animation(12, false);
		attack.frames(frames, 0);

		zap = attack.clone();
		
		die = new Animation(10, false);
		die.frames(frames, 0, 7, 8, 9);

		play(idle);
	}

	@Override
	public void zap(int pos) {

		points[0] = ch.pos;
		points[1] = pos;
		parent.add(new Lightning(points, 2, (ShadowYog) ch));

		turnTo(ch.pos, pos);
		play(zap);
	}

		
	@Override
	public void die() {
		super.die();

		Splash.at(center(), blood(), 12);
	}
}
