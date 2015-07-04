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
import com.github.dachhack.sprout.actors.buffs.Strength;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class Moonberry extends Food {

	{
		name = "dungeon moon berry";
		image = ItemSpriteSheet.SEED_MOONBERRY;
		energy = (Hunger.STARVING - Hunger.HUNGRY)/10;
		message = "Juicy!";
		hornValue = 1;
		bones = false;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_EAT)) {

				switch (Random.Int(2)) {
				case 0:
					GLog.w("The moon berry fills your body with fearsome strength.");
					Buff.affect(hero, Strength.class);
					break;
				case 1:
					GLog.w("The moon berry fills your body with fearsome strength.");
					Buff.affect(hero, Strength.class);
					Buff.affect(hero, Barkskin.class).level(hero.HT*2);
					break;
				}
			}
	}	
	
	@Override
	public String info() {
		return "A hearty blue-green berry found in the depths of the dungeon. "
				+"This berry has a tough endocarp and radiates powerful magic. "
			    +"Just a whiff of it and you feel a magical fury build within you. ";
	}

	@Override
	public int price() {
		return 20 * quantity;
	}
	
	public Moonberry() {
		this(1);
	}

	public Moonberry(int value) {
		this.quantity = value;
	}
}
