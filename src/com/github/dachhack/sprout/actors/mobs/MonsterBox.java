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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.watabou.noosa.audio.Sample;
import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Poison;
import com.github.dachhack.sprout.actors.buffs.Roots;
import com.github.dachhack.sprout.actors.buffs.Terror;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.Pushing;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.items.Gold;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.scrolls.ScrollOfPsionicBlast;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.traps.SummoningTrap;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.MimicSprite;
import com.github.dachhack.sprout.sprites.MonsterBoxSprite;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class MonsterBox extends Mob {

	private int level;

	{
		name = "monster box";
		spriteClass = MonsterBoxSprite.class;
	}

	public ArrayList<Item> items;

	private static final String LEVEL = "level";
	private static final String ITEMS = "items";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(ITEMS, items);
		bundle.put(LEVEL, level);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		items = new ArrayList<Item>(
				(Collection<Item>) ((Collection<?>) bundle.getCollection(ITEMS)));
		adjustStats(bundle.getInt(LEVEL));
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange(HT / 10, HT / 4);
	}

	@Override
	public int attackSkill(Char target) {
		return 9 + level;
	}
	
		
	public void adjustStats(int level) {
		this.level = level;

		HT = (3 + level) * 4;
		EXP = 2 + 2 * (level - 1) / 5;
		defenseSkill = attackSkill(null) / 2;

		enemySeen = true;
	}

	//@Override
	//protected boolean act() {
	//	Char ch = Actor.findChar(pos);
	//	if (Random.Int(5)==0){
	//	SummoningTrap.trigger(pos, ch);
	//	}
	//	return rooted;
	//}
	
	@Override
	public void die(Object cause) {

		super.die(cause);

		if (items != null) {
			for (Item item : items) {
				Dungeon.level.drop(item, pos).sprite.drop();
			}
		}
	}

	@Override
	public boolean reset() {
		state = WANDERING;
		return true;
	}

	@Override
	public String description() {
		return "Mimics are magical creatures which can take any shape they wish. In dungeons they almost always "
				+ "choose a shape of a treasure chest, because they know how to beckon an adventurer.";
	}

	public static MonsterBox spawnAt(int pos, List<Item> items) {
		Char ch = Actor.findChar(pos);
		if (ch != null) {
			ArrayList<Integer> candidates = new ArrayList<Integer>();
			for (int n : Level.NEIGHBOURS8) {
				int cell = pos + n;
				if ((Level.passable[cell] || Level.avoid[cell])
						&& Actor.findChar(cell) == null) {
					candidates.add(cell);
				}
			}
			if (candidates.size() > 0) {
				int newPos = Random.element(candidates);
				Actor.addDelayed(new Pushing(ch, ch.pos, newPos), -1);

				ch.pos = newPos;
				// FIXME
				if (ch instanceof Mob) {
					Dungeon.level.mobPress((Mob) ch);
				} else {
					Dungeon.level.press(newPos, ch);
				}
			} else {
				return null;
			}
		}

		MonsterBox m = new MonsterBox();
		m.items = new ArrayList<Item>(items);
		m.adjustStats(Dungeon.depth);
		m.HP = m.HT;
		m.pos = pos;
		m.state = m.HUNTING;
		GameScene.add(m, 1);

		m.sprite.turnTo(pos, Dungeon.hero.pos);
		//Buff.affect(m, Roots.class, 1000);

		if (Dungeon.visible[m.pos]) {
			CellEmitter.get(pos).burst(Speck.factory(Speck.STAR), 10);
			Sample.INSTANCE.play(Assets.SND_MIMIC);
		}

		return m;
	}

	private static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
	static {
		IMMUNITIES.add(ScrollOfPsionicBlast.class);
	}

	@Override
	public HashSet<Class<?>> immunities() {
		return IMMUNITIES;
	}
}
