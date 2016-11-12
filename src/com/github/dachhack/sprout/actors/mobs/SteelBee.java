/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
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

import java.util.HashSet;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Amok;
import com.github.dachhack.sprout.actors.buffs.Poison;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.sprites.SteelBeeSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class SteelBee extends Mob {

	{
		name = "steel bee";
		spriteClass = SteelBeeSprite.class;

		viewDistance = 8;

		flying = true;
		state = HUNTING;
	}

	private int level;

	// -1 refers to a pot that has gone missing.
	private int potPos;
	// -1 for no owner
	private int potHolder;

	private static final String LEVEL = "level";
	private static final String POTPOS = "potpos";
	private static final String POTHOLDER = "potholder";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(LEVEL, level);
		bundle.put(POTPOS, potPos);
		bundle.put(POTHOLDER, potHolder);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		spawn(bundle.getInt(LEVEL));
		potPos = bundle.getInt(POTPOS);
		potHolder = bundle.getInt(POTHOLDER);
	}

	public void spawn(int level) {
		this.level = level;
        
		HT = (10 + level) * 10;
		defenseSkill = 9 + level*2;
	}

	public void setPotInfo(int potPos, Char potHolder) {
		this.potPos = potPos;
		if (potHolder == null)
			this.potHolder = -1;
		else
			this.potHolder = potHolder.id();
	}

	@Override
	public int attackSkill(Char target) {
		return defenseSkill;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(HT / 10, HT / 4);
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		if (enemy instanceof Mob) {
			((Mob) enemy).aggro(this);
		}
		return damage;
	}

	@Override
	protected Char chooseEnemy() {
					
			if (enemy == null || !enemy.isAlive()) {
				HashSet<Mob> enemies = new HashSet<Mob>();
				for (Mob mob : Dungeon.level.mobs) {
					if (!(mob instanceof Bee) && mob.hostile && Level.fieldOfView[mob.pos]) {
						enemies.add(mob);
					}
				}

				enemy = enemies.size() > 0 ? Random.element(enemies) : null;
			}

			return enemy;
	}


	
	@Override
	protected boolean getCloser(int target) {
		if (enemy != null) {
			target = enemy.pos;
		} else {
			target = Dungeon.hero.pos;
		}
		return super.getCloser(target);
	}
	

	@Override
	public String description() {
		return "Strongly armored in steely plates, this bee is here to fight!";
	}

	private static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
	static {
		IMMUNITIES.add(Poison.class);
		IMMUNITIES.add(Amok.class);
	}

	@Override
	public HashSet<Class<?>> immunities() {
		return IMMUNITIES;
	}
}