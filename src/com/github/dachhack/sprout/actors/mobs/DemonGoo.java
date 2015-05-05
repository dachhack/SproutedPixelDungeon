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

import java.util.ArrayList;
import java.util.HashSet;

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.blobs.Blob;
import com.github.dachhack.sprout.actors.blobs.ToxicGas;
import com.github.dachhack.sprout.actors.blobs.Web;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Burning;
import com.github.dachhack.sprout.actors.buffs.Light;
import com.github.dachhack.sprout.actors.buffs.Poison;
import com.github.dachhack.sprout.actors.buffs.Roots;
import com.github.dachhack.sprout.actors.buffs.Terror;
import com.github.dachhack.sprout.actors.mobs.Yog.BurningFist;
import com.github.dachhack.sprout.actors.mobs.Yog.Larva;
import com.github.dachhack.sprout.actors.mobs.Yog.RottingFist;
import com.github.dachhack.sprout.effects.Pushing;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.items.Gold;
import com.github.dachhack.sprout.items.keys.SkeletonKey;
import com.github.dachhack.sprout.items.potions.PotionOfMending;
import com.github.dachhack.sprout.items.scrolls.ScrollOfPsionicBlast;
import com.github.dachhack.sprout.items.weapon.enchantments.Death;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.SewerBossLevel;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.levels.features.Door;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.DemonGooSprite;
import com.github.dachhack.sprout.sprites.PoisonGooSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class DemonGoo extends Mob {
	
protected static final float SPAWN_DELAY = 2f;

private int demonGooGeneration = 0;

private static final String DEMONGOOGENERATION = "demonGooGeneration";

	{
		name = "demon goo";
		HP = HT = 100;
		EXP = 10;
		defenseSkill = 20;
		spriteClass = DemonGooSprite.class;
		baseSpeed = 2f;
		viewDistance = Light.DISTANCE;

		loot = new PotionOfMending();
		lootChance = 1f;
	}

	private static final float SPLIT_DELAY = 1f;	
	
	@Override
	protected boolean act() {
		boolean result = super.act();
		
		if (Level.water[pos] && HP < HT) {
			sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
			HP++;
		} else if(Level.water[pos] && HP == HT && HT < 200){
			sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
			HT=HT+5;
			HP=HT;
		}
		return result;
	}

		
	@Override
	public int damageRoll() {
			return Random.NormalIntRange(10, 20);
	}

	@Override
	public int attackSkill(Char target) {
		return 35;
	}

	@Override
	public int dr() {
		return 10;
	}

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(DEMONGOOGENERATION, demonGooGeneration);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		demonGooGeneration = bundle.getInt(DEMONGOOGENERATION);
	}

	@Override
	public int defenseProc(Char enemy, int damage) {
		
		if (HP >= damage + 2) {
			ArrayList<Integer> candidates = new ArrayList<Integer>();
			boolean[] passable = Level.passable;

			int[] neighbours = { pos + 1, pos - 1, pos + Level.WIDTH,
					pos - Level.WIDTH };
			for (int n : neighbours) {
				if (passable[n] && Actor.findChar(n) == null) {
					candidates.add(n);
				}
			}

			if (candidates.size() > 0) {
				GLog.n("Demon Goo divides!");
				DemonGoo clone = split();
				clone.HP = (HP - damage) / 2;
				clone.pos = Random.element(candidates);
				clone.state = clone.HUNTING;

				if (Dungeon.level.map[clone.pos] == Terrain.DOOR) {
					Door.enter(clone.pos);
				}

				GameScene.add(clone, SPLIT_DELAY);
				Actor.addDelayed(new Pushing(clone, pos, clone.pos), -1);

				HP -= clone.HP;
			}
		}

		return damage;
	}


	private DemonGoo split() {
		DemonGoo clone = new DemonGoo();
		clone.demonGooGeneration = demonGooGeneration + 1;
		if (buff(Burning.class) != null) {
			Buff.affect(clone, Burning.class).reignite(clone);
		}
		if (buff(Poison.class) != null) {
			Buff.affect(clone, Poison.class).set(2);
		}
		return clone;
	}
	

	@Override
	public void notice() {
		super.notice();
		yell("GLURP-GLURP-GLUURRRRP!");
	}

	@Override
	public String description() {
		return "Demon Goo is pretty angry you killed its buddy in the sewers. ";
	}

	

	private static final HashSet<Class<?>> RESISTANCES = new HashSet<Class<?>>();
	static {
		RESISTANCES.add(ToxicGas.class);
		RESISTANCES.add(Death.class);
		RESISTANCES.add(ScrollOfPsionicBlast.class);
	}

	@Override
	public HashSet<Class<?>> resistances() {
		return RESISTANCES;
	}
	
	private static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();

	static {
		IMMUNITIES.add(Roots.class);
	}

	@Override
	public HashSet<Class<?>> immunities() {
		return IMMUNITIES;
	}
		
	
}
