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
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.items.food.Meat;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.RatSprite;
import com.watabou.utils.Random;

public class Rat extends Mob {
	

	private static final float SPAWN_DELAY = 2f;

	{
		name = "marsupial rat";
		spriteClass = RatSprite.class;

		HP = HT = 8+(Dungeon.depth*Random.NormalIntRange(1, 3));
		defenseSkill = 3+(Math.round((Dungeon.depth)/2));
		
		loot = new Meat();
		lootChance = 0.5f;

	}


	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange(1, 5+Dungeon.depth);
	}

	@Override
	public int attackSkill(Char target) {
		return 5+Dungeon.depth;
	}

	@Override
	public int dr() {
		return 1;
	}

	@Override
	public String description() {
		return "Marsupial rats are aggressive but rather weak denizens "
				+ "of the sewers. They have a nasty bite, but are only life threatening in large numbers. "
				+ "Some consider their meat a staple diet item. Maybe try it cooked. ";
	}
	
	public static void spawnAround(int pos) {
		for (int n : Level.NEIGHBOURS4) {
			int cell = pos + n;
			if (Level.passable[cell] && Actor.findChar(cell) == null) {
				spawnAt(cell);
			}
		}
	}
	
	public static Rat spawnAt(int pos) {
		
        Rat b = new Rat();  
    	
			b.pos = pos;
			b.state = b.HUNTING;
			GameScene.add(b, SPAWN_DELAY);

			return b;
     
     }
	
}
