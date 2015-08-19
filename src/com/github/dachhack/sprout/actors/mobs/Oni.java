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
package com.github.dachhack.sprout.actors.mobs;

import java.util.HashSet;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Amok;
import com.github.dachhack.sprout.actors.buffs.Terror;
import com.github.dachhack.sprout.sprites.OniSprite;
import com.watabou.utils.Random;

public class Oni extends Mob {

	{
		name = "oni";
		spriteClass = OniSprite.class;
		state = SLEEPING;

		HP = HT = 200+(Dungeon.depth*10);
		defenseSkill = 18+(Math.round((Dungeon.depth)/2));

		EXP = 22;
		maxLvl = 40;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(50, 90);
	}

	@Override
	public int attackSkill(Char target) {
		return 35;
	}

	@Override
	protected float attackDelay() {
		return 1.5f;
	}

	@Override
	public int dr() {
		return 32;
	}

	@Override
	public String defenseVerb() {
		return "blocked";
	}

	@Override
	public String description() {
		return "Oni are wicked trolls who dwell in places of great power. "
				+"They are quite lazy unless there is food near by... ";
	}

	private static final HashSet<Class<?>> RESISTANCES = new HashSet<Class<?>>();
	static {
	}

	@Override
	public HashSet<Class<?>> resistances() {
		return RESISTANCES;
	}

	private static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
	static {
		IMMUNITIES.add(Amok.class);
		IMMUNITIES.add(Terror.class);
	}

	@Override
	public HashSet<Class<?>> immunities() {
		return IMMUNITIES;
	}
}
