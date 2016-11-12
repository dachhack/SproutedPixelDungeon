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
import com.github.dachhack.sprout.actors.buffs.Blindness;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.items.Generator;
import com.github.dachhack.sprout.items.food.Meat;
import com.github.dachhack.sprout.sprites.BrownBatSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class BrownBat extends Mob {

	{
		name = "brown bat";
		spriteClass = BrownBatSprite.class;

		HP = HT = 4;
		defenseSkill = 1;
		baseSpeed = 2f;

		EXP = 1;
		maxLvl = 15;

		flying = true;

		loot = new Meat();
		lootChance = 0.5f; // by default, see die()
		
		lootOther = Generator.Category.BERRY;
		lootChanceOther = 0.05f; // by default, see die()
		
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(1, 4);
	}

	@Override
	public int attackSkill(Char target) {
		return 5+Dungeon.depth;
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		if (Random.Int(10) == 0) {
			Buff.prolong(enemy, Blindness.class, Random.Int(3, 10));
			GLog.n("The brown bat scratches your eyes!");
			Dungeon.observe();
			state = FLEEING;
		}
		
		return damage;
	}
	@Override
	public int dr() {
		return 1;
	}

	@Override
	public String defenseVerb() {
		return "evaded";
	}
	
	@Override
	public void die(Object cause) {
		
		if (Random.Int(5) == 0) {
			  for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
				  if (Random.Int(2) == 0 && enemy!=null){mob.beckon(enemy.pos);}
			      }
			  GLog.n("The brown bat's shrieks alert nearby enemies!");
			}

		super.die(cause);

	}

	
	@Override
	public String description() {
		return "Basically small, flying rats. Careful they don't get tangled in your hair.";
	}

	
}
