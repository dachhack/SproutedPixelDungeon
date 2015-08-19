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


import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.items.Generator;
import com.github.dachhack.sprout.items.food.Meat;
import com.github.dachhack.sprout.items.scrolls.ScrollOfRegrowth;
import com.github.dachhack.sprout.sprites.RatBossSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class RatBoss extends Rat {
	


	{
		name = "rat boss";
		spriteClass = RatBossSprite.class;

		HP = HT = 12+(Dungeon.depth*Random.NormalIntRange(2, 5));
		defenseSkill = 5+(Dungeon.depth/4);
		
		loot = Generator.Category.BERRY;
		lootChance = 0.5f;
		
		lootOther = new ScrollOfRegrowth();
		lootChanceOther = 0.1f;
	}

	private boolean spawnedRats = false;
			
	@Override
	public int damageRoll() {
		return Random.NormalIntRange(2+Dungeon.depth/2, 8+(Dungeon.depth));
	}

	@Override
	public int attackSkill(Char target) {
		return 11+Dungeon.depth;
	}

	@Override
	public int dr() {
		return Dungeon.depth/2;
	}

	@Override
	public void notice() {
		super.notice();
		yell("Scritch Scratch!");
		if (!spawnedRats){
	    Rat.spawnAround(pos);
	    GLog.n("Rat pack apears!");
	    spawnedRats = true;
		}
	  }

	
	@Override
	public String description() {
		return "Larger and stronger than other Marsupial Rats, the "
				+ "Rat Boss can summon a pack of rats from the shadows of the dungeon.";
	}
}
