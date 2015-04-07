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

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.effects.particles.ShadowParticle;
import com.github.dachhack.sprout.items.weapon.Weapon;
import com.github.dachhack.sprout.sprites.ItemSprite;
import com.github.dachhack.sprout.sprites.ItemSprite.Glowing;
import com.watabou.utils.Random;

public class Death extends Weapon.Enchantment {

	private static final String TXT_GRIM = "Grim %s";

	private static ItemSprite.Glowing BLACK = new ItemSprite.Glowing(0x000000);

	@Override
	public boolean proc(Weapon weapon, Char attacker, Char defender, int damage) {
		// lvl 0 - 8%
		// lvl 1 ~ 9%
		// lvl 2 ~ 10%
		int level = Math.max(0, weapon.level);

		if (Random.Int(level + 100) >= 92) {

			defender.damage(defender.HP, this);
			defender.sprite.emitter().burst(ShadowParticle.UP, 5);

			if (!defender.isAlive() && attacker instanceof Hero) {
				Badges.validateGrimWeapon();
			}

			return true;

		} else {

			return false;

		}
	}

	@Override
	public Glowing glowing() {
		return BLACK;
	}

	@Override
	public String name(String weaponName) {
		return String.format(TXT_GRIM, weaponName);
	}

}
