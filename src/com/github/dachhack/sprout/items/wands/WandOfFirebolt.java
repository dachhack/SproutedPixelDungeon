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
package com.github.dachhack.sprout.items.wands;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.ResultDescriptions;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.blobs.Blob;
import com.github.dachhack.sprout.actors.blobs.Fire;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Burning;
import com.github.dachhack.sprout.actors.buffs.Strength;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.MagicMissile;
import com.github.dachhack.sprout.effects.particles.BlastParticle;
import com.github.dachhack.sprout.effects.particles.FlameParticle;
import com.github.dachhack.sprout.effects.particles.SmokeParticle;
import com.github.dachhack.sprout.items.Bomb;
import com.github.dachhack.sprout.items.Heap;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.mechanics.Ballistica;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class WandOfFirebolt extends Wand {

	{
		name = "Wand of Firebolt";
	}

	@Override
	protected void onZap(int cell) {

		int level = level();

		for (int i = 1; i < Ballistica.distance - 1; i++) {
			int c = Ballistica.trace[i];
			if (Level.flamable[c]) {
				GameScene.add(Blob.seed(c, 1, Fire.class));
			}
		}

		GameScene.add(Blob.seed(cell, 1, Fire.class));

		Char ch = Actor.findChar(cell);
		if (ch != null) {

			int damage= Random.Int(1, 8 + level * level);
	        if (Dungeon.hero.buff(Strength.class) != null){ damage *= (int) 4f; Buff.detach(Dungeon.hero, Strength.class);}
			ch.damage(damage, this);
			
			if (damage>255){
				GLog.n("Your wand of Firebolt is burning your hands!");
			}
			
			float backfireChance = Math.max(((damage-255)/10000),0);
			
			if (Random.Float() < backfireChance){
				backfire(damage);
			}
	
			Buff.affect(ch, Burning.class).reignite(ch);

			ch.sprite.emitter().burst(FlameParticle.FACTORY, 5);

			if (ch == curUser && !ch.isAlive()) {
				Dungeon.fail(Utils.format(ResultDescriptions.ITEM, name));
				GLog.n("You killed yourself with your own Wand of Firebolt...");
			}
		}
	}
	
	public void backfire(int damage){
		wandEmpty();
		explode(curUser.pos, damage);
		GLog.n("Your wand of firebolt backfires!");
	}
	
	public void explode(int cell, int damage) {
		// We're blowing up, so no need for a fuse anymore.
		
		Sample.INSTANCE.play(Assets.SND_BLAST, 2);

		if (Dungeon.visible[cell]) {
			CellEmitter.center(cell).burst(BlastParticle.FACTORY, 30);
		}

		boolean terrainAffected = false;
		for (int n : Level.NEIGHBOURS9) {
			int c = cell + n;
			if (c >= 0 && c < Level.getLength()) {
				if (Dungeon.visible[c]) {
					CellEmitter.get(c).burst(SmokeParticle.FACTORY, 4);
				}

				if (Level.flamable[c]) {
					Level.set(c, Terrain.EMBERS);
					GameScene.updateMap(c);
					terrainAffected = true;
				}

				// destroys items / triggers bombs caught in the blast.
				Heap heap = Dungeon.level.heaps.get(c);
				if (heap != null)
					heap.explode();

				Char ch = Actor.findChar(c);
				if (ch != null) {
					// those not at the center of the blast take damage less
					// consistently.
					int minDamage = c == cell ? Math.round(damage/10) : 1;
					int maxDamage = c == cell ? Math.round(damage/4) : Math.round(damage/10);

					int dmg = Random.NormalIntRange(minDamage, maxDamage)
							- Random.Int(ch.dr());
					if (dmg > 0) {
						ch.damage(dmg, this);
					}

					if (ch == Dungeon.hero && !ch.isAlive())
						// constant is used here in the rare instance a player
						// is killed by a double bomb.
						Dungeon.fail(Utils.format(ResultDescriptions.ITEM,"wand of firebolt"));
				}
			}
		}

		if (terrainAffected) {
			Dungeon.observe();
		}
	}
	
	@Override
	protected void fx(int cell, Callback callback) {
		MagicMissile.fire(curUser.sprite.parent, curUser.pos, cell, callback);
		Sample.INSTANCE.play(Assets.SND_ZAP);
	}

	@Override
	public String desc() {
		return "This wand unleashes bursts of magical fire. It will ignite "
				+ "flammable terrain, and will damage and burn a creature it hits."
				+ "It is very unstable at higher levels. Use with caution.";
	}
}
