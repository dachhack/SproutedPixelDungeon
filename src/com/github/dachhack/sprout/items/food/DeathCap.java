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

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.buffs.BerryRegeneration;
import com.github.dachhack.sprout.actors.buffs.Blindness;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Hunger;
import com.github.dachhack.sprout.actors.buffs.MindVision;
import com.github.dachhack.sprout.actors.buffs.Paralysis;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class DeathCap extends Food {

	{
		name = "death cap mushroom";
		image = ItemSpriteSheet.MUSHROOM_DEATHCAP;
		energy = (Hunger.STARVING - Hunger.HUNGRY)/10;
		message = "Munch munch";
		hornValue = 1;
		bones = false;
	}
	
	private static final String TXT_PREVENTING = "Something tells you that wouldn't be a good idea here! ";
	private static final String TXT_EFFECT = "Dark energy fills your veins. All you can see is your bones glowing! ";

	@Override
	public void execute(Hero hero, String action) {
		
		if (action.equals(AC_EAT)) {
			
			if (Dungeon.bossLevel()){
				GLog.w(TXT_PREVENTING);
				return;
			}

		}
		
	   if (action.equals(AC_EAT)) {
		   
		   
		   GLog.w(TXT_EFFECT);
			
		   switch (Random.Int(10)) {
			case 1:
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					mob.damage(Math.max(5,Math.round(mob.HP/2)), this);
				}
				hero.damage(Math.max(1,Math.round(hero.HP/4)), this);
				Buff.prolong(hero, Blindness.class, Random.Int(5, 7));
				break;
			case 0: case 2: case 3: case 4: case 5: 
			case 6: case 7: case 8: case 9: case 10:
				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
					mob.damage(Math.max(3,Math.round(mob.HP/2)), this);
					mob.aggro(hero);
				}
				hero.damage(Math.max(1,Math.round(hero.HP/4)), this);
				Buff.prolong(hero, Blindness.class, Random.Int(6, 9));
				break;
			}
		}
	   
	   super.execute(hero, action);
	}	
	
	@Override
	public String info() {
		return "A deadly-looking fungus found growing in the dungeon. "
				+"Eat at your own risk. ";
	}

	@Override
	public int price() {
		return 20 * quantity;
	}
	
	public DeathCap() {
		this(1);
	}

	public DeathCap(int value) {
		this.quantity = value;
	}
}
