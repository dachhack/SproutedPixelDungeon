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

import java.util.HashSet;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.ResultDescriptions;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.blobs.ToxicGas;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Terror;
import com.github.dachhack.sprout.effects.particles.SparkParticle;
import com.github.dachhack.sprout.items.RedDewdrop;
import com.github.dachhack.sprout.items.scrolls.ScrollOfPsionicBlast;
import com.github.dachhack.sprout.items.weapon.enchantments.Death;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.traps.LightningTrap;
import com.github.dachhack.sprout.mechanics.Ballistica;
import com.github.dachhack.sprout.sprites.CharSprite;
import com.github.dachhack.sprout.sprites.ShellSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.noosa.Camera;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Shell extends Mob implements Callback {
	
	private static final float TIME_TO_ZAP = 2f;

	private static final String TXT_LIGHTNING_KILLED = "%s's lightning bolt killed you...";

	{
		name = "lightning shell";
		spriteClass = ShellSprite.class;

		HP = HT = 600;
		defenseSkill = 0;

		EXP = 25;
		
		hostile = false;
		state = PASSIVE;
		
		loot = new RedDewdrop();
		lootChance = 1f;
		
	}
	
	@Override
	public void beckon(int cell) {
		// Do nothing
	}

	@Override
	public int damageRoll() {
		return 0;
	}

	@Override
	public void damage(int dmg, Object src) {
		if(Dungeon.shellCharge>0){zapAround(1);}
		super.damage(dmg, src);
	}
	
	@Override
	public int attackSkill(Char target) {
		return 100;
	}

	@Override
	public int dr() {
		return 10;
	}
	
	@Override
	protected boolean act() {
		if(Random.Int(Dungeon.shellCharge)>20 && Dungeon.hero.isAlive()){
			zapAll(1);
		}
		return super.act();
	}
	
	@Override
	public void call() {
		next();
	}
	

	@Override
	protected boolean canAttack(Char enemy) {
		return Ballistica.cast(pos, enemy.pos, false, true) == enemy.pos;
	}

	@Override
	protected boolean doAttack(Char enemy) {

		if (Level.distance(pos, enemy.pos) <= 1) {

			return super.doAttack(enemy);

		} else {

			boolean visible = Level.fieldOfView[pos]
					|| Level.fieldOfView[enemy.pos];
			if (visible) {
				((ShellSprite) sprite).zap(enemy.pos);
			}			
			zapAll(10);
			spend(TIME_TO_ZAP);

			if (hit(this, enemy, true)) {
				int dmg = Random.Int(Math.round(Dungeon.shellCharge/4), Math.round(Dungeon.shellCharge/2));
				Dungeon.shellCharge-=dmg;
				
				if (Level.water[enemy.pos] && !enemy.flying) {
					dmg *= 1.5f;
				}
				enemy.damage(dmg, LightningTrap.LIGHTNING);

				enemy.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
				enemy.sprite.flash();

				if (enemy == Dungeon.hero) {

					Camera.main.shake(2, 0.3f);

					if (!enemy.isAlive()) {
						Dungeon.fail(Utils.format(ResultDescriptions.MOB,
								Utils.indefinite(name)));
						GLog.n(TXT_LIGHTNING_KILLED, name);
					}
				}
			} else {
				enemy.sprite
						.showStatus(CharSprite.NEUTRAL, enemy.defenseVerb());
			}

			return !visible;
		}
	}


	public void zapAll(int dmg){
		
		yell("ZZZZZAAAAAAPPPPPP!!!!!!");
		
		int heroDmg=0;
		int mobDmg=Random.Int(1, 2+Math.round(dmg/4));
		
		for (Mob mob : Dungeon.level.mobs) {
				
			
		  if (Level.distance(pos, mob.pos) > 1 && mob.isAlive()){
			  boolean visible = Level.fieldOfView[pos]
					|| Level.fieldOfView[mob.pos];
			
			
			  if (visible) {
				((ShellSprite) sprite).zap(mob.pos);
			  }
			
			  if (Level.water[mob.pos] && !mob.flying) {
				  mobDmg *= 1.5f;
			  }
			  mob.damage(mobDmg, LightningTrap.LIGHTNING);

			  mob.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
			  mob.sprite.flash();
			
			  Camera.main.shake(2, 0.3f);	
			}
		}
		
		
		if (Dungeon.hero.isAlive()){
			
		Char hero=Dungeon.hero;
		
		 if (Level.distance(pos, hero.pos) > 1){
		
		boolean visibleHero = Level.fieldOfView[pos]
				|| Level.fieldOfView[hero.pos];
		if (visibleHero) {
			((ShellSprite) sprite).zap(hero.pos);
		}
		
		heroDmg = Random.Int(Math.round(Dungeon.shellCharge/4), Math.round(Dungeon.shellCharge/2));
		Dungeon.shellCharge-=heroDmg;
		
		if (Level.water[hero.pos] && !hero.flying) {
			heroDmg *= 1.5f;
		}
				
		hero.damage(heroDmg, LightningTrap.LIGHTNING);

		hero.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
		hero.sprite.flash();
		
		Camera.main.shake(2, 0.3f);	
		}
		}
		

	}
	
public void zapAround(int dmg){
		
		yell("ZZZZZAAAAAAPPPPPP!!!!!!");
		
		int heroDmg=0;
		int mobDmg=Random.Int(1, 2+Math.round(dmg/4));
		
		for (int n : Level.NEIGHBOURS8) {
			int c = pos + n;
			
			Char ch = Actor.findChar(c);
			if (ch != null && ch != Dungeon.hero && ch.isAlive()) {
				
					 boolean visible = Level.fieldOfView[pos]
							|| Level.fieldOfView[ch.pos];
					  
					  if (visible) {
						((ShellSprite) sprite).zap(ch.pos);
					  }
					
			  if (Level.water[ch.pos] && !ch.flying) {
				  mobDmg *= 1.5f;
			  }
			  ch.damage(mobDmg, LightningTrap.LIGHTNING);

			  ch.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
			  ch.sprite.flash();
			
			  Camera.main.shake(2, 0.3f);	
			  
			}  else if (ch != null && ch == Dungeon.hero && ch.isAlive()){
				
				heroDmg = Random.Int(Dungeon.shellCharge, Dungeon.shellCharge*2);
				
				if(dmg<Dungeon.shellCharge){
				    Dungeon.shellCharge-=dmg;
				} else {
					Dungeon.shellCharge=0;	
				}
				
				
				boolean visible = Level.fieldOfView[pos]
						|| Level.fieldOfView[ch.pos];
				  
				  if (visible) {
					((ShellSprite) sprite).zap(ch.pos);
				  }
				
				if (Level.water[ch.pos] && !ch.flying) {
					heroDmg *= 1.5f;
				}
						
				ch.damage(heroDmg, LightningTrap.LIGHTNING);

				ch.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
				ch.sprite.flash();
				
				Camera.main.shake(2, 0.3f);	
			}
						
		}		

	}
	

	@Override
	public String description() {
		return "The lightning shell crackles with electric power. "
				+ "It's powerful lightning attack is drawn to all living things in the lair. ";
	}
	
	@Override
	public void add(Buff buff) {
	}
	
	@Override
	public void die(Object cause) {
		Dungeon.shellCharge=0;
		super.die(cause);
		
	}

	private static final HashSet<Class<?>> RESISTANCES = new HashSet<Class<?>>();
	static {
		RESISTANCES.add(Death.class);
		RESISTANCES.add(ScrollOfPsionicBlast.class);
		RESISTANCES.add(LightningTrap.Electricity.class);
	}

	@Override
	public HashSet<Class<?>> resistances() {
		return RESISTANCES;
	}

	private static final HashSet<Class<?>> IMMUNITIES = new HashSet<Class<?>>();
	static {
		IMMUNITIES.add(ToxicGas.class);
		IMMUNITIES.add(Terror.class);
	}

	@Override
	public HashSet<Class<?>> immunities() {
		return IMMUNITIES;
	}

	
}
