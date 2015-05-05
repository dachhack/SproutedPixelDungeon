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
import com.github.dachhack.sprout.actors.buffs.Poison;
import com.github.dachhack.sprout.items.food.Blackberry;
import com.github.dachhack.sprout.items.potions.PotionOfHealing;
import com.github.dachhack.sprout.items.weapon.enchantments.Leech;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.mechanics.Ballistica;
import com.github.dachhack.sprout.sprites.DwarfLichSprite;
import com.watabou.utils.Random;

public class DwarfLich extends Mob {

	{
		name = "dwarf lich";
		spriteClass = DwarfLichSprite.class;

		HP = HT = 95+(Dungeon.depth*Random.NormalIntRange(1, 3));
		defenseSkill = 24+(Math.round((Dungeon.depth)/2));
	
		EXP = 14;
		
		loot = new PotionOfHealing();
		lootChance = 0.2f;
		
		lootOther = new Blackberry();
		lootChanceOther = 0.333f; // by default, see die()
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(20, 32);
	}

	@Override
	public int attackSkill(Char target) {
		return 36+(Math.round((Dungeon.depth)/2));
	}

	@Override
	public int dr() {
		return 16;
	}

	@Override
	protected boolean canAttack(Char enemy) {
		return !Level.adjacent(pos, enemy.pos)
				&& Ballistica.cast(pos, enemy.pos, false, true) == enemy.pos;
	}

	@Override
	protected boolean getCloser(int target) {
		if (state == HUNTING) {
			return enemySeen && getFurther(target);
		} else {
			return super.getCloser(target);
		}
	}

	

	@Override
	public String description() {
		return "A powerful wizard sacrificed to sustain the undead dwarf king, "
				+ "the wizard calls on the power of death to damage opponents.";
	}

	private static final HashSet<Class<?>> RESISTANCES = new HashSet<Class<?>>();
	static {
		RESISTANCES.add(Leech.class);
		RESISTANCES.add(Poison.class);
	}

	@Override
	public HashSet<Class<?>> resistances() {
		return RESISTANCES;
	}
}
