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

import java.util.HashSet;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.ResultDescriptions;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.blobs.Blob;
import com.github.dachhack.sprout.actors.blobs.Fire;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Frost;
import com.github.dachhack.sprout.actors.buffs.Weakness;
import com.github.dachhack.sprout.actors.mobs.Bee;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.particles.SnowParticle;
import com.github.dachhack.sprout.items.Heap;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.mechanics.Ballistica;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.BlueDragonSprite;
import com.github.dachhack.sprout.sprites.CharSprite;
import com.github.dachhack.sprout.sprites.MirrorSprite;
import com.github.dachhack.sprout.sprites.RedDragonSprite;
import com.github.dachhack.sprout.sprites.SteelBeeSprite;
import com.github.dachhack.sprout.sprites.VelociroosterSprite;
import com.github.dachhack.sprout.sprites.WarlockSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class Velocirooster extends PET {
	
	{
		name = "velocirooster";
		spriteClass = VelociroosterSprite.class;       
		flying=false;
		state = HUNTING;
		level = 1;
		type = 3;
		cooldown=1000;

	}	
			
	protected int regen = 1;	
	protected float regenChance = 0.05f;	
		

	@Override
	public void adjustStats(int level) {
		this.level = level;
		HT = (2 + level) * 5;
		defenseSkill = 1 + level;
	}
	

	@Override
	public int attackSkill(Char target) {
		return defenseSkill;
	}

	@Override
	public int damageRoll() {
		
		int dmg=0;
		if (cooldown==0){
			dmg=Random.NormalIntRange(HT/2, HT); 
			yell("Bwak!");
			cooldown=1000;
		} else {
			dmg=Random.NormalIntRange(HT/5, HT/2) ;
		}
		return dmg;
			
	}

	@Override
	protected boolean act() {
		
		if (cooldown>0){
			cooldown=Math.max(cooldown-(level*level),0);
			if (cooldown==0) {yell("Cock-a-doodle-roar!");}
		}
		
		if (Random.Float()<regenChance && HP<HT){HP+=regen;}

		return super.act();
	}			
	

@Override
public String description() {
	return "The Velocirooster is a vicious cousin of the domesticated rooster." +
            " It races through the dungeon and attacks with razor sharp talons and a vicious beak." +
            " This one has a collar with a tag. It reads, 'To Sprouted from Unleashed. Please enjoy this Velocirooster.' ";
}


@Override
public void interact() {

	int curPos = pos;

	moveSprite(pos, Dungeon.hero.pos);
	move(Dungeon.hero.pos);

	Dungeon.hero.sprite.move(Dungeon.hero.pos, curPos);
	Dungeon.hero.move(curPos);

	Dungeon.hero.spend(1 / Dungeon.hero.speed());
	Dungeon.hero.busy();
}


}