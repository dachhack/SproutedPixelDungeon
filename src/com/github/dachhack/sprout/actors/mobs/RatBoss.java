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

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.items.food.Meat;
import com.github.dachhack.sprout.items.potions.PotionOfMending;
import com.github.dachhack.sprout.sprites.RatBossSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class RatBoss extends Rat {
	


	{
		name = "rat boss";
		spriteClass = RatBossSprite.class;

		HP = HT = 12+(Dungeon.depth*Random.NormalIntRange(2, 5));
		defenseSkill = 5+(Dungeon.depth);
		
		loot = new Meat();
		lootChance = 0.5f;
		
		lootOther = new PotionOfMending();
		lootChance = 1f;

		maxLvl = 5;
	}

	@Override
	public void die(Object cause) {
		super.die(cause);
		Badges.validateRare(this);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange(3, 8+(Dungeon.depth));
	}

	@Override
	public int attackSkill(Char target) {
		return 12;
	}

	@Override
	public int dr() {
		return 1;
	}

	@Override
	public void notice() {
		super.notice();
		yell("Scritch Scratch!");
		boolean spawnedRats = false;
		if (!spawnedRats){
	    Rat.spawnAround(pos);
	    GLog.n("Rat pack apears!");
	    spawnedRats = true;
		}
	  }

	
	@Override
	public String description() {
		return "Marsupial rats are aggressive but rather weak denizens "
				+ "of the sewers. They have a nasty bite, but are only life threatening in large numbers.";
	}
}
