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
package com.github.dachhack.sprout.levels.traps;

import com.watabou.noosa.Camera;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.ResultDescriptions;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.Lightning;
import com.github.dachhack.sprout.effects.particles.SparkParticle;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.utils.Random;

public class LightningTrap {

	private static final String name = "lightning trap";

	// 00x66CCEE

	public static void trigger(int pos, Char ch) {

		if (ch != null) {
			ch.damage(Math.max(1, Random.Int(ch.HP / 3, 2 * ch.HP / 3)),
					LIGHTNING);
			if (ch == Dungeon.hero) {

				Camera.main.shake(2, 0.3f);

				if (!ch.isAlive()) {
					Dungeon.fail(Utils.format(ResultDescriptions.TRAP, name));
					GLog.n("You were killed by a discharge of a lightning trap...");
				} else {
					((Hero) ch).belongings.charge(false);
				}
			}

			int[] points = new int[2];

			points[0] = pos - Level.WIDTH;
			points[1] = pos + Level.WIDTH;
			ch.sprite.parent.add(new Lightning(points, 2, null));

			points[0] = pos - 1;
			points[1] = pos + 1;
			ch.sprite.parent.add(new Lightning(points, 2, null));
		}

		CellEmitter.center(pos).burst(SparkParticle.FACTORY,
				Random.IntRange(3, 4));

	}

	public static final Electricity LIGHTNING = new Electricity();

	public static class Electricity {
	}
}
