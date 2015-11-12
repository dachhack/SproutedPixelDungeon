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
package com.github.dachhack.sprout.actors.blobs;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Journal;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Shadows;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.effects.BlobEmitter;
import com.github.dachhack.sprout.effects.particles.ShaftParticle;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.scenes.GameScene;

public class Foliage extends Blob {

	@Override
	protected void evolve() {

		int from = WIDTH + 1;
		int to = Level.getLength() - WIDTH - 1;

		int[] map = Dungeon.level.map;
		boolean regrowth = false;

		boolean visible = false;

		for (int pos = from; pos < to; pos++) {
			if (cur[pos] > 0) {

				off[pos] = cur[pos];
				volume += off[pos];

				if (map[pos] == Terrain.EMBERS) {
					map[pos] = Terrain.GRASS;
					regrowth = true;
				}

				visible = visible || Dungeon.visible[pos];

			} else {
				off[pos] = 0;
			}
		}

		Hero hero = Dungeon.hero;
		if (hero.isAlive() && hero.visibleEnemies() == 0 && cur[hero.pos] > 0) {
			Buff.affect(hero, Shadows.class).prolong();
		}

		if (regrowth) {
			GameScene.updateMap();
		}

		if (visible) {
			Journal.add(Journal.Feature.GARDEN);
		}
	}

	@Override
	public void use(BlobEmitter emitter) {
		super.use(emitter);
		emitter.start(ShaftParticle.FACTORY, 0.9f, 0);
	}

	@Override
	public String tileDesc() {
		return "Shafts of light pierce the gloom of the underground garden.";
	}
}
