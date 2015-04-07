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
package com.github.dachhack.sprout.items.weapon.missiles;

import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Paralysis;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class CurareShuriken extends MissileWeapon {
	
	public static final float DURATION = 3f;

	{
		name = "shuriken";
		image = ItemSpriteSheet.SHURIKEN;

		STR = 13;

		MIN = 2;
		MAX = 6;

		DLY = 0.5f;
	}

	public CurareShuriken() {
		this(1);
	}

	public CurareShuriken(int number) {
		super();
		quantity = number;
	}
	
	@Override
	public void proc(Char attacker, Char defender, int damage) {
		Buff.prolong(defender, Paralysis.class, DURATION);
		super.proc(attacker, defender, damage);
	}

	@Override
	public String desc() {
		return "Star-shaped pieces of metal with razor-sharp blades do significant damage "
				+ "when they hit a target. They can be thrown at very high rate.";
	}

	@Override
	public Item random() {
		quantity = Random.Int(5, 15);
		return this;
	}

	@Override
	public int price() {
		return 6 * quantity;
	}
}
