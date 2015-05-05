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

import com.github.dachhack.sprout.ui.BuffIndicator;

public class BerryRegeneration extends Buff {
	
	private int level = 0;

	public int level() {
		return level;
	}

	public void level(int value) {
		if (level < value) {
			level = value;
		}
	}

	@Override
	public int icon() {
		return BuffIndicator.REGEN;
	}

	@Override
	public String toString() {
		return "Regenerating";
	}
	@Override
	public boolean act() {
		if (target.isAlive()) {
			   
			if (target.HP < target.HT) {
				target.HP += Math.min(1+Math.round(level/25),(target.HT-target.HP));
			}
			
				spend(TICK);
				if (--level <= 0) {
					detach();
				}

			} else {

				detach();

			}

			return true;
		}
	
}
