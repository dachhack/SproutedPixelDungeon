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
package com.github.dachhack.sprout.actors.mobs.pets;

import java.util.ArrayList;
import java.util.HashSet;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.hero.HeroSubClass;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.particles.ElmoParticle;
import com.github.dachhack.sprout.items.Heap;
import com.github.dachhack.sprout.items.rings.RingOfHaste;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.sprites.HeroSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public abstract class PET extends Mob {

	{
		HP = HT = 1;
		EXP = 0;

		hostile = false;
		state = HUNTING;
		ally=true;
	}
	
	public int level;
	public int kills;
	public int type;
	public int experience;
	public int cooldown;
	public int goaways = 0;
	public boolean callback = false;
	public boolean stay = false;
	/*
	 type
	 1 = 
	 2 = bee
	 3 = 
	 4 = 
	 5 = 
	  
	  
	 */
	
	private static final String KILLS = "kills";
	private static final String LEVEL = "level";
	private static final String TYPE = "type";
	private static final String EXPERIENCE = "experience";
	private static final String COOLDOWN = "cooldown";
	private static final String GOAWAYS = "goaways";
	private static final String CALLBACK = "callback";
	private static final String STAY = "stay";


	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(KILLS, kills);
		bundle.put(LEVEL, level);
		bundle.put(TYPE, type);
		bundle.put(EXPERIENCE, experience);
		bundle.put(COOLDOWN, cooldown);
		bundle.put(GOAWAYS, goaways);
		bundle.put(CALLBACK, callback);
		bundle.put(STAY, stay);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		kills = bundle.getInt(KILLS);
		level = bundle.getInt(LEVEL);
		type = bundle.getInt(TYPE);
		experience = bundle.getInt(EXPERIENCE);
		cooldown = bundle.getInt(COOLDOWN);
		goaways = bundle.getInt(GOAWAYS);
		callback = bundle.getBoolean(CALLBACK);
		stay = bundle.getBoolean(STAY);
		adjustStats(level);
	}
	
	protected void throwItem() {
		Heap heap = Dungeon.level.heaps.get(pos);
		if (heap != null) {
			int n;
			do {
				n = pos + Level.NEIGHBOURS8[Random.Int(8)];
			} while (!Level.passable[n] && !Level.avoid[n]);
			Dungeon.level.drop(heap.pickUp(), n).sprite.drop(pos);
		}
	}
	
	public void adjustStats(int level) {
	}
	
	public void spawn(int level) {
		this.level = level;
        adjustStats(level);
	}
	
	@Override
	protected boolean act() {
		
		assignPet(this);
		return super.act();
	}
	
	
	@Override
	public void damage(int dmg, Object src) {
		
		if (src instanceof Hero){
			goaways++;
			GLog.n("Keep that up and your %s may leave you!",name);
		}
		
		if (goaways>2){
			flee();
		}
		
		super.damage(dmg,src);
		
	}
	
	@Override
	public void die(Object cause) {
		
		    Dungeon.hero.haspet=false;
		    Dungeon.hero.petCount++;
			GLog.n("Your pet %s dies.",name);			

		super.die(cause);

	}
	
	@Override
	public float speed() {

		float speed = super.speed();
		
		int hasteLevel = Dungeon.petHasteLevel;
		
		if(hasteLevel>10){
			hasteLevel=10;
		}

		if (hasteLevel != 0)
			speed *= Math.pow(1.2, hasteLevel);

		return speed;
	}
	
	public void flee() {
		Dungeon.hero.haspet=false;
	    GLog.n("Your %s knows when it's not wanted!",name);
		destroy();
		sprite.killAndErase();
		CellEmitter.get(pos).burst(ElmoParticle.FACTORY, 6);
	}

	@Override
	protected Char chooseEnemy() {
		
		
		if(enemy != null && !enemy.isAlive() && enemy instanceof Mob){
			kills++;
			experience+=((Mob)enemy).getExp();
		}
		
		if (experience >= level*(level+level) && level < 20){			
			level++;
			GLog.p("Your pet %s gains a level!",name);
			adjustStats(level);
			experience=0;
		}
		
		if (enemy == null || !enemy.isAlive()) {
			HashSet<Mob> enemies = new HashSet<Mob>();
			for (Mob mob : Dungeon.level.mobs) {
				if (mob.hostile && Level.fieldOfView[mob.pos]) {
					enemies.add(mob);
				}
			}

			enemy = enemies.size() > 0 ? Random.element(enemies) : null;
		}

		return enemy;
}
	
	@Override
	protected boolean getCloser(int target) {
		if (enemy != null && !callback) {
		   target = enemy.pos;		
		} else if (checkNearbyHero()) {
		   target = wanderLocation() != -1 ? wanderLocation() : Dungeon.hero.pos;	
		   callback = false;
		} else if(Dungeon.hero.invisible==0){
			target = Dungeon.hero.pos;
		} else {
			target = wanderLocation() != -1 ? wanderLocation() : Dungeon.hero.pos;				
		}
		
		if (stay) {
			
			return false;
			
		}
		
		return super.getCloser(target);
	}

	protected boolean checkNearbyHero(){
		return Level.adjacent(pos, Dungeon.hero.pos);
	}

	public int wanderLocation(){
		  int newPos = -1;
			ArrayList<Integer> candidates = new ArrayList<Integer>();
			boolean[] passable = Level.passable;

			for (int n : Level.NEIGHBOURS8) {
				int c = pos + n;
				if (passable[c] && Actor.findChar(c) == null) {
					candidates.add(c);
				}
			}

			newPos = candidates.size() > 0 ? Random.element(candidates) : -1;
			
		return newPos;
	}
	
	@Override
	public void aggro(Char ch) {		
	}
	
	@Override
	public void beckon(int cell) {
	}


	private void assignPet(PET pet){
		
		  Dungeon.hero.petType=pet.type;
		  Dungeon.hero.petLevel=pet.level;
		  Dungeon.hero.petKills=pet.kills;	
		  Dungeon.hero.petHP=pet.HP;
		  Dungeon.hero.petExperience=pet.experience;
		  Dungeon.hero.petCooldown=pet.cooldown;		
	}
		
	abstract public void interact();
}