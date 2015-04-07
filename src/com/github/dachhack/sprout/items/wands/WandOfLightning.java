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
package com.github.dachhack.sprout.items.wands;

import java.util.ArrayList;
import java.util.HashSet;

import com.watabou.noosa.Camera;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.ResultDescriptions;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.Lightning;
import com.github.dachhack.sprout.effects.particles.SparkParticle;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.traps.LightningTrap;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class WandOfLightning extends Wand {

	{
		name = "Wand of Lightning";
	}

	private ArrayList<Char> affected = new ArrayList<Char>();

	private int[] points = new int[20];
	private int nPoints;

	@Override
	protected void onZap(int cell) {
		// Everything is processed in fx() method
		if (!curUser.isAlive()) {
			Dungeon.fail(Utils.format(ResultDescriptions.ITEM, name));
			GLog.n("You killed yourself with your own Wand of Lightning...");
		}
	}

	private void hit(Char ch, int damage) {

		if (damage < 1) {
			return;
		}

		if (ch == Dungeon.hero) {
			Camera.main.shake(2, 0.3f);
		}

		affected.add(ch);
		ch.damage(Level.water[ch.pos] && !ch.flying ? (int) (damage * 2)
				: damage, LightningTrap.LIGHTNING);

		ch.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
		ch.sprite.flash();

		points[nPoints++] = ch.pos;

		HashSet<Char> ns = new HashSet<Char>();
		for (int i = 0; i < Level.NEIGHBOURS8.length; i++) {
			Char n = Actor.findChar(ch.pos + Level.NEIGHBOURS8[i]);
			if (n != null && !affected.contains(n)) {
				ns.add(n);
			}
		}

		if (ns.size() > 0) {
			hit(Random.element(ns), Random.Int(damage / 2, damage));
		}
	}

	@Override
	protected void fx(int cell, Callback callback) {

		nPoints = 0;
		points[nPoints++] = Dungeon.hero.pos;

		Char ch = Actor.findChar(cell);
		if (ch != null) {

			affected.clear();
			int lvl = level();
			hit(ch, Random.Int(5 + lvl / 2, 10 + lvl));

		} else {

			points[nPoints++] = cell;
			CellEmitter.center(cell).burst(SparkParticle.FACTORY, 3);

		}
		curUser.sprite.parent.add(new Lightning(points, nPoints, callback));
	}

	@Override
	public String desc() {
		return "This wand conjures forth deadly arcs of electricity, which deal damage "
				+ "to several creatures standing close to each other.";
	}
}
