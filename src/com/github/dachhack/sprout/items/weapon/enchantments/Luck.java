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
package com.github.dachhack.sprout.items.weapon.enchantments;

import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.items.weapon.Weapon;
import com.github.dachhack.sprout.items.weapon.melee.relic.RelicMeleeWeapon;
import com.github.dachhack.sprout.sprites.ItemSprite;
import com.github.dachhack.sprout.sprites.ItemSprite.Glowing;

public class Luck extends Weapon.Enchantment {

	private static final String TXT_LUCKY = "Lucky %s";

	private static ItemSprite.Glowing GREEN = new ItemSprite.Glowing(0x00FF00);

	@Override
	public boolean proc(RelicMeleeWeapon weapon, Char attacker, Char defender, int damage) {
		return false;
	}
	
	@Override
	public boolean proc(Weapon weapon, Char attacker, Char defender, int damage) {
		int level = Math.max(0, weapon.level);

		int dmg = damage;
		for (int i = 1; i <= level + 1; i++) {
			dmg = Math.max(dmg, attacker.damageRoll() - i);
		}

		if (dmg > damage) {
			defender.damage(dmg - damage, this);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String name(String weaponName) {
		return String.format(TXT_LUCKY, weaponName);
	}

	@Override
	public Glowing glowing() {
		return GREEN;
	}
}
