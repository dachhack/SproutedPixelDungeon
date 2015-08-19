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
package com.github.dachhack.sprout.levels.features;

import com.github.dachhack.sprout.Challenges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Barkskin;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.hero.HeroSubClass;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.particles.LeafParticle;
import com.github.dachhack.sprout.items.Dewdrop;
import com.github.dachhack.sprout.items.Generator;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.RedDewdrop;
import com.github.dachhack.sprout.items.VioletDewdrop;
import com.github.dachhack.sprout.items.YellowDewdrop;
import com.github.dachhack.sprout.items.artifacts.SandalsOfNature;
import com.github.dachhack.sprout.items.food.Blackberry;
import com.github.dachhack.sprout.items.food.Blueberry;
import com.github.dachhack.sprout.items.food.Cloudberry;
import com.github.dachhack.sprout.items.food.Moonberry;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.plants.BlandfruitBush;
import com.github.dachhack.sprout.scenes.GameScene;
import com.watabou.utils.Random;

public class HighGrass {

	public static void trample(Level level, int pos, Char ch) {

		Level.set(pos, Terrain.GRASS);
		GameScene.updateMap(pos);

		if (!Dungeon.isChallenged(Challenges.NO_HERBALISM)) {
			int naturalismLevel = 0;

			if (ch != null) {
				SandalsOfNature.Naturalism naturalism = ch
						.buff(SandalsOfNature.Naturalism.class);
				if (naturalism != null) {
					if (!naturalism.isCursed()) {
						naturalismLevel = naturalism.level() + 1;
						naturalism.charge();
					} else {
						naturalismLevel = -1;
					}
				}
			}

			if (naturalismLevel >= 0) {
				// Seed
				if (Random.Int(18 - ((int) (naturalismLevel * 3.34))) == 0) {
					Item seed = Generator.random(Generator.Category.SEED);

					if (seed instanceof BlandfruitBush.Seed) {
						if (Random.Int(15)
								- Dungeon.limitedDrops.blandfruitSeed.count >= 0) {
							level.drop(seed, pos).sprite.drop();
							Dungeon.limitedDrops.blandfruitSeed.count++;
						}
						
					  }	else if (seed instanceof Blackberry
								|| seed instanceof Cloudberry
								|| seed instanceof Blueberry
								|| seed instanceof Moonberry								
								) {
							if (Random.Int(40)- Dungeon.limitedDrops.berries.count >= 0) {
								level.drop(seed, pos).sprite.drop();
								Dungeon.limitedDrops.berries.count++;
							}
					} else
						level.drop(seed, pos).sprite.drop();
				}
				
				// Mushroom
				if (Dungeon.growLevel(Dungeon.depth) && Random.Int(40 - ((int) (naturalismLevel * 3.34))) == 0) {
					Item mushroom = Generator.random(Generator.Category.MUSHROOM);
					level.drop(mushroom, pos).sprite.drop();
				}
				
				// Dew
				if (Random.Int(3 - naturalismLevel) == 0) {
					if (Random.Int(30 - naturalismLevel) == 0 && naturalismLevel>0) {
						level.drop(new YellowDewdrop(), pos).sprite.drop();
					} else if (Random.Int(50 - naturalismLevel) == 0 && naturalismLevel>2) {
						level.drop(new RedDewdrop(), pos).sprite.drop();
					} else if (Random.Int(100 - naturalismLevel) == 0 && naturalismLevel>4){
						level.drop(new VioletDewdrop(), pos).sprite.drop();
					} else {
					   level.drop(new Dewdrop(), pos).sprite.drop();
					}
				}
			}
		}

		int leaves = 4;

		// Barkskin
		if (ch instanceof Hero && ((Hero) ch).subClass == HeroSubClass.WARDEN) {
			Buff.affect(ch, Barkskin.class).level(ch.HT / 3);
			leaves = 8;
		}

		CellEmitter.get(pos).burst(LeafParticle.LEVEL_SPECIFIC, leaves);
		Dungeon.observe();
	}
}
