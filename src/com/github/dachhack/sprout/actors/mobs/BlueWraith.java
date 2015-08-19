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
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Terror;
import com.github.dachhack.sprout.actors.buffs.Vertigo;
import com.github.dachhack.sprout.effects.particles.ShadowParticle;
import com.github.dachhack.sprout.items.RedDewdrop;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.BlueWraithSprite;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Random;

public class BlueWraith extends Wraith  {
	
	{
		name = "blue wraith";
		spriteClass = BlueWraithSprite.class;

		HP = HT = 195;
		defenseSkill = 24;
		baseSpeed = 4f;

		EXP = 11;
		
		loot = new RedDewdrop();
		lootChance = 1.0f; // by default, see die()
		
	}
	
	@Override
	public String description() {
		return "A Blue Wraith has been dispatched to avenge the dungeon. ";
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		if (Random.Int(10) == 0) {
			Buff.affect(enemy, Vertigo.class, Vertigo.duration(enemy));
			Buff.affect(enemy, Terror.class, Terror.DURATION).object = enemy.id();
		}

		return damage;
	}

	@Override
	public void adjustStats(int level) {
		this.level = level;
		defenseSkill = 24;
		enemySeen = true;
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange(20, 90);
	}

	@Override
	public int attackSkill(Char target) {
		return 46;
	}
	
	@Override
	public int dr() {
		return 25;
	}

	public static BlueWraith spawnAt(int pos) {
		
            BlueWraith b = new BlueWraith();  
        	b.adjustStats(Dungeon.depth);
 			b.pos = pos;
 			b.state = b.HUNTING;
 			GameScene.add(b, SPAWN_DELAY);

 			b.sprite.alpha(0);
 			b.sprite.parent.add(new AlphaTweener(b.sprite, 1, 0.5f));

 			b.sprite.emitter().burst(ShadowParticle.CURSE, 5);

 			return b;
         		
		
	}
	

	/*private int level;
				
	/*private static final float TIME_TO_ZAP = 1f;

	private static final String TXT_WRAITHFIRE_KILLED = "%s's wraith fire killed you...";*/
	
	/*private static final float SPAWN_DELAY = 2f;

	{
		name = "blue wraith";
		spriteClass = BlueWraithSprite.class;

		HP = HT = 70;
		defenseSkill = 18;

		EXP = 11;
		loot = Generator.Category.POTION;
		
	}

	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange(12, 20);
	}

	
	@Override
	public int dr() {
		return 8;
	}
/*
	@Override
	protected boolean canAttack(Char enemy) {
		return Ballistica.cast(pos, enemy.pos, false, true) == enemy.pos;
	}

	protected boolean doAttack(Char enemy) {

		if (Level.adjacent(pos, enemy.pos)) {

			return super.doAttack(enemy);

		} else {

			boolean visible = Level.fieldOfView[pos]
					|| Level.fieldOfView[enemy.pos];
			if (visible) {
				((BlueWraithSprite) sprite).zap(enemy.pos);
			} else {
				zap();
			}

			return !visible;
		}
	}

	private void zap() {
		spend(TIME_TO_ZAP);

		if (hit(this, enemy, true)) {
			if (enemy == Dungeon.hero && Random.Int(2) == 0) {
				Buff.prolong(enemy, Frost.class, Frost.duration(enemy));
			}

			int dmg = Random.Int(12, 18);
			enemy.damage(dmg, this);

			if (!enemy.isAlive() && enemy == Dungeon.hero) {
				Dungeon.fail(Utils.format(ResultDescriptions.MOB,
						Utils.indefinite(name)));
				GLog.n(TXT_WRAITHFIRE_KILLED, name);
			}
		} else {
			enemy.sprite.showStatus(CharSprite.NEUTRAL, enemy.defenseVerb());
		}
	}

	public void onZapComplete() {
		zap();
		next();
	}

	@Override
	public void call() {
		next();
	}
		*/
	



		
}
