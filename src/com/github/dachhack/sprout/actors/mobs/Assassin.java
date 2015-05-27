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
import com.github.dachhack.sprout.actors.blobs.ToxicGas;
import com.github.dachhack.sprout.actors.buffs.Poison;
import com.github.dachhack.sprout.items.scrolls.ScrollOfPsionicBlast;
import com.github.dachhack.sprout.items.weapon.enchantments.Death;
import com.github.dachhack.sprout.mechanics.Ballistica;
import com.github.dachhack.sprout.sprites.AssassinSprite;
import com.watabou.utils.Random;

public class Assassin extends Mob {
	
	{
		name = "Assassin";
		spriteClass = AssassinSprite.class;
		baseSpeed = 2f;

		HP = HT = 25+(Dungeon.depth*Random.NormalIntRange(2, 5));
		EXP = 10;
		defenseSkill = 10+(Math.round((Dungeon.depth)/2));
	}

	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange(10, 18);
	}

	@Override
	public int attackSkill(Char target) {
		return 20+(Dungeon.depth);
	}

	@Override
	public int dr() {
		return 5;
	}
	@Override
	protected float attackDelay() {
		return 0.75f;
	}

	
	@Override
	protected boolean canAttack(Char enemy) {
		return Ballistica.cast(pos, enemy.pos, false, true) == enemy.pos;
	}

	
	@Override
	public String description() {
		return "Tengu are members of the ancient assassins clan, which is also called Tengu. "
				+ "These assassins are noted for extensive use of shuriken and traps.";
	}

	private static final HashSet<Class<?>> RESISTANCES = new HashSet<Class<?>>();
	static {
		RESISTANCES.add(ToxicGas.class);
		RESISTANCES.add(Poison.class);
		RESISTANCES.add(Death.class);
		RESISTANCES.add(ScrollOfPsionicBlast.class);
	}

	@Override
	public HashSet<Class<?>> resistances() {
		return RESISTANCES;
	}
}
