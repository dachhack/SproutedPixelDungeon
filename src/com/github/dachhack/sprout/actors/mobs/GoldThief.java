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
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Terror;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.items.CityKey;
import com.github.dachhack.sprout.items.Gold;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.food.Cloudberry;
import com.github.dachhack.sprout.items.weapon.missiles.Shuriken;
import com.github.dachhack.sprout.sprites.CharSprite;
import com.github.dachhack.sprout.sprites.GoldThiefSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class GoldThief extends Mob {

	protected static final String TXT_STOLE = "%s stole %s gold from you!";
	private static final String TXT_KILLCOUNT = "Gold Thief Kill Count: %s";

	public Item item;

	{
		name = "crazy gold thief";
		spriteClass = GoldThiefSprite.class;

		HP = HT = 10;
		defenseSkill = 8+(Math.round((Dungeon.depth)/2));

		EXP = 1;
		
		loot = new Shuriken(3);
		lootChance = 0.5f;
		
		lootOther = new Cloudberry();
		lootChanceOther = 0.1f; // by default, see die()

		FLEEING = new Fleeing();
	}

	private int goldtodrop = 0;
	
	private static final String ITEM = "item";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(ITEM, item);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		item = (Item) bundle.get(ITEM);
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(1, 7);
	}

	@Override
	protected float attackDelay() {
		return 0.5f;
	}

	@Override
	public void die(Object cause) {
		
		if (!Dungeon.limitedDrops.citykey.dropped()) {
			Dungeon.limitedDrops.citykey.drop();
			Dungeon.level.drop(new CityKey(), pos).sprite.drop();
			explodeDew(pos);				
		} else {
			explodeDew(pos);
		}

		Statistics.goldThievesKilled++;
		GLog.w(TXT_KILLCOUNT, Statistics.goldThievesKilled);
		super.die(cause);

		if (item != null) {
			Dungeon.level.drop(item, pos).sprite.drop();
		}
	}

	@Override
	protected Item createLoot() {
		return new Gold(Random.NormalIntRange(goldtodrop, goldtodrop+100));
	}

	@Override
	public int attackSkill(Char target) {
		return 12;
	}

	@Override
	public int dr() {
		return 3;
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		if (item == null && enemy instanceof Hero && steal((Hero) enemy)) {
			state = FLEEING;
		}

		return damage;
	}

	@Override
	public int defenseProc(Char enemy, int damage) {
		if (state == FLEEING) {
			Dungeon.level.drop(new Gold(), pos).sprite.drop();
		}

		return damage;
	}

	protected boolean steal(Hero hero) {

							
			Gold gold = new Gold(Random.Int(Dungeon.gold / 10, Dungeon.gold / 2));
			if (gold.quantity() > 0) {
				goldtodrop = Math.min((gold.quantity()+100),Dungeon.gold);
				Dungeon.gold -= goldtodrop;
				GLog.w(TXT_STOLE, this.name, gold.quantity());
				return true;
			} else {
			   return false;
		}
	}

	@Override
	public String description() {
		String desc = "This thief has gold dust covering his robes and skin. He has a crazy glint in his eye. ";
		return desc;
	}

	private class Fleeing extends Mob.Fleeing {
		@Override
		protected void nowhereToRun() {
			if (buff(Terror.class) == null) {
				sprite.showStatus(CharSprite.NEGATIVE, TXT_RAGE);
				state = HUNTING;
			} else {
				super.nowhereToRun();
			}
		}
	}
}
