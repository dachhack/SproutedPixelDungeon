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
package com.github.dachhack.sprout.items.food;

import com.github.dachhack.sprout.actors.buffs.Barkskin;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Hunger;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class Nut extends Food {

	{
		name = "dungeon nut";
		image = ItemSpriteSheet.SEED_DUNGEONNUT;
		energy = (Hunger.STARVING - Hunger.HUNGRY)/2;
		message = "Crunch Crunch.";
		hornValue = 1;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_EAT)) {

			switch (Random.Int(10)) {
			case 0:
				GLog.w("You feel the dungeon blessing you.");
				Buff.affect(hero, Barkskin.class).level(hero.HT / 4);
				break;
			}
		}
	}	
	
	@Override
	public String info() {
		return "Common dungeon nut.";
	}

	@Override
	public int price() {
		return 20 * quantity;
	}
}
