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
package com.github.dachhack.sprout.items.weapon.enchantments;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Paralysis;
import com.github.dachhack.sprout.actors.buffs.Terror;
import com.github.dachhack.sprout.actors.buffs.Vertigo;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.items.weapon.Weapon;
import com.github.dachhack.sprout.items.weapon.melee.relic.RelicMeleeWeapon;
import com.github.dachhack.sprout.items.weapon.missiles.JupitersWraith;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.sprites.ItemSprite;
import com.github.dachhack.sprout.sprites.ItemSprite.Glowing;
import com.watabou.noosa.Camera;
import com.watabou.utils.Random;

public class JupitersHorror extends Weapon.Enchantment {

	private static final String TXT_ELDRITCH = "Horrifying %s";

	private static ItemSprite.Glowing GREY = new ItemSprite.Glowing(0x222222);

	@Override
	public boolean proc(RelicMeleeWeapon weapon, Char attacker, Char defender, int damage) {
		return false;
	}
	
	@Override
	public boolean proc(Weapon weapon, Char attacker, Char defender, int damage) {
		// lvl 0 - 20%
		// lvl 1 - 33%
		// lvl 2 - 43%
		int level = Math.max(0, weapon.level);

		if (Random.Int(level + 5) >= 4) {

			if (defender == Dungeon.hero) {
				Buff.affect(defender, Vertigo.class, Vertigo.duration(defender));
			} else {
				Buff.affect(defender, Terror.class, Terror.DURATION).object = attacker
						.id();
				if (Random.Int(level + 11) >= 10){
					//doExplode(defender.pos);
				}
			}

			return true;
		} else {
			return false;
		}
	}
	
	   public void doExplode(int cell) {
			
			Camera.main.shake(3, 0.7f);
			
					if (Dungeon.visible[cell] && Level.passable[cell]) {
						CellEmitter.center(cell).start(Speck.factory(Speck.ROCK), 0.07f, 10);
					}
					
					Char ch = Actor.findChar(cell);
					if (ch != null && ch!=Dungeon.hero) {
						// those not at the center of the blast take damage less
						// consistently.
						int minDamage = Dungeon.depth + 5;
						int maxDamage = 10 + Dungeon.depth * 3;
						                    
						
						int dmg = Random.NormalIntRange(minDamage, maxDamage) - Random.Int(ch.dr());
						
						
						if (dmg > 0) {
							ch.damage(dmg, this);
							if(Random.Int(15)==1){Buff.prolong(ch, Paralysis.class, 1);}
						}
												
	     			}

		}	


	@Override
	public Glowing glowing() {
		return GREY;
	}

	@Override
	public String name(String weaponName) {
		return String.format(TXT_ELDRITCH, weaponName);
	}

}
