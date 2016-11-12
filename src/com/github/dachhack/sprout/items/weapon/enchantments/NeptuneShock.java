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
package com.github.dachhack.sprout.items.weapon.enchantments;

import java.util.ArrayList;
import java.util.HashSet;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.actors.mobs.Shell;
import com.github.dachhack.sprout.effects.Lightning;
import com.github.dachhack.sprout.effects.particles.SparkParticle;
import com.github.dachhack.sprout.items.weapon.Weapon;
import com.github.dachhack.sprout.items.weapon.melee.relic.RelicMeleeWeapon;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.traps.LightningTrap;
import com.github.dachhack.sprout.sprites.ShellSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.utils.Random;

public class NeptuneShock extends Weapon.Enchantment {

	private static final String TXT_SHOCKING = "Grand Shocking %s";
	private int[] points = new int[10];
	private int cost = 10;

	@Override
	public boolean proc(Weapon weapon, Char attacker, Char defender, int damage) {
		
		return false;
	}
	
	@Override
	public boolean proc(RelicMeleeWeapon weapon, Char attacker, Char defender, int damage) {
		// lvl 0 - 25%
		// lvl 1 - 40%
		// lvl 2 - 50%
		 
		
		if (weapon.charge>=cost){
			weapon.charge-=cost;
		} else {
			return false;
		}
		
		int level = Math.max(0, weapon.level);
		boolean procced = false;
		int distance = 1 + level*2;			
		
		for (Mob mob : Dungeon.level.mobs) {
			
			boolean visible = Level.fieldOfView[mob.pos];
		
		if (Level.distance(attacker.pos, mob.pos) < distance && mob.isAlive() && !mob.isPassive() && Random.Int(10)<5){
								
			// int dmg = 20;
			     	points[0] = attacker.pos;
					points[1] = mob.pos;
					attacker.sprite.parent.add(new Lightning(points, 2, null));				
					  
				mob.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
				mob.sprite.flash();
				
				if (Level.water[mob.pos] && !mob.flying) {
					damage *= 2;
				}
				
				if (mob.isAlive()){
				  if(damage<mob.HP){	
				     mob.damage(damage, weapon);
				  }	else {
					  mob.damage(mob.HP-2, weapon); 
					  Buff.prolong(	mob,com.github.dachhack.sprout.actors.buffs.Paralysis.class,Random.Float(1, 1.5f + level));
				  }
				}
			 
              Camera.main.shake(2, 0.3f);
			  procced = true;
			}
	     }
	     
		
		return procced;
	}
	
	private void hit(Mob ch, int mobdamage) {
		if (mobdamage < 1 || !ch.isAlive()) {
			return;
		}
		ch.damage(Level.water[ch.pos] && !ch.flying ? (int) (mobdamage * 2): mobdamage, LightningTrap.LIGHTNING);		
	}
	

	@Override
	public String name(String weaponName) {
		return String.format(TXT_SHOCKING, weaponName);
	}

	

	
	
}
