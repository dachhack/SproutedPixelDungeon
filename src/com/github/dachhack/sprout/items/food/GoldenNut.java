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

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.actors.buffs.Hunger;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.sprites.CharSprite;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class GoldenNut extends Nut {

	{
		name = "golden dungeon nut";
		image = ItemSpriteSheet.SEED_GOLDENDUNGEONNUT;
		energy = Hunger.STARVING;
		message = "Melts in your mouth. Tastes like Nutella.";
		hornValue = 2;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_EAT)) {

			switch (Random.Int(2)) {
			case 0:
				GLog.w("You have recieved the dungeon's blessing.");
				
				hero.HT+=20;
				hero.STR+=2;
				hero.sprite.showStatus(CharSprite.POSITIVE, "+2 str, +20 ht");
				GLog.p("Newfound strength surges through your body.");

				Badges.validateStrengthAttained();
				break;
			case 1:
				GLog.w("You have recieved the dungeon's highest blessing.");
				
				hero.HT+=50;
				hero.STR+=5;
				hero.sprite.showStatus(CharSprite.POSITIVE, "+5 str, +50 ht");
				GLog.p("Newfound strength surges through your body.");

				Badges.validateStrengthAttained();
				break;
			}
		}
	}	
	
	@Override
	public String info() {
		return "Unique dungeon nut gilded with enchantment.";
	}

	@Override
	public int price() {
		return 20 * quantity;
	}

}


