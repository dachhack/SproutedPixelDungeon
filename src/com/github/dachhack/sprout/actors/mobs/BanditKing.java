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
import com.github.dachhack.sprout.actors.buffs.Blindness;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Cripple;
import com.github.dachhack.sprout.actors.buffs.Poison;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.weapon.melee.Spork;
import com.github.dachhack.sprout.sprites.BanditKingSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class BanditKing extends Thief {

	public Item item;

	{
		name = "bandit king";
		spriteClass = BanditKingSprite.class;
		HP = HT = 100; //200

		EXP = 50;
		maxLvl = 25;
		
		// 1 in 30 chance to be a crazy bandit, equates to overall 1/90 chance.
		lootChance = 0.333f;
		defenseSkill = 10; //20
		Dungeon.sporkAvail = false;
		
	}
	
	@Override
	public int dr() {
		return 10; //20
	}

	//@Override
	//public float speed() {
	//	return 2f;
   //	}
	
	@Override
	protected boolean steal(Hero hero) {
		if (super.steal(hero)) {

			Buff.prolong(hero, Blindness.class, Random.Int(5, 12));
			Buff.affect(hero, Poison.class).set(
					Random.Int(5, 7) * Poison.durationFactor(enemy));
			Buff.prolong(hero, Cripple.class, Cripple.DURATION);
			Dungeon.observe();

			return true;
		} else {
			return false;
		}
	}

	@Override
	public void die(Object cause) {
		super.die(cause);
		yell("Fine! Take it back!");
		GLog.n("Bandit King dissolves away.");
		if (!Dungeon.limitedDrops.spork.dropped()) {
			Dungeon.level.drop(new Spork(), pos).sprite.drop();
			Dungeon.limitedDrops.spork.drop();
			Dungeon.sporkAvail = false;
		yell("Doh! Dropped my spork!");	
		}
	}
}
