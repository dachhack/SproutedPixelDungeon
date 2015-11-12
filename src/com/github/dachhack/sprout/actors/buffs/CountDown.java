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
package com.github.dachhack.sprout.actors.buffs;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.ResultDescriptions;
import com.github.dachhack.sprout.actors.mobs.BanditKing;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.actors.mobs.Yog.BurningFist;
import com.github.dachhack.sprout.actors.mobs.Yog.InfectingFist;
import com.github.dachhack.sprout.actors.mobs.Yog.PinningFist;
import com.github.dachhack.sprout.actors.mobs.Yog.RottingFist;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.effects.particles.ShadowParticle;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.ui.BuffIndicator;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class CountDown extends Buff {

	private static final String TXT_HERO_KILLED = "%s killed you...";
	
	private int ticks = 0;

	private static final String TICKS = "ticks";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(TICKS, ticks);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		ticks = bundle.getInt(TICKS);
	}
	
	
	@Override
	public int icon() {
		return BuffIndicator.COUNTDOWN;
	}

	@Override
	public String toString() {
		return "Countdown";
	}

	@Override
	public boolean act() {
		if (target.isAlive()) {
			ticks++;
			GLog.w("countdown: %s ",(6-ticks));
			if (ticks>5){
				
				GLog.w("countdown up!");
				target.sprite.emitter().burst(ShadowParticle.CURSE, 6);
				target.damage(Math.round(target.HT / 4), this);
				for (Mob mob : Dungeon.level.mobs) {
					if (mob instanceof BanditKing) {
						mob.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
						mob.HP = Math.min(mob.HP + (Math.round(target.HT/8)),mob.HT);
					}
				}
				GLog.w("shadow bandits feed on your life force!");
				detach();
			}
		}
		
		if (!target.isAlive() && target == Dungeon.hero) {
				Dungeon.fail(ResultDescriptions.COUNTDOWN);
				GLog.n(TXT_HERO_KILLED, toString());
		}
			
		spend(TICK);	
		
		return true;
	}
}
