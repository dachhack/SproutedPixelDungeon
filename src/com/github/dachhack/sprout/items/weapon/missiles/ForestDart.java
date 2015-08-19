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
package com.github.dachhack.sprout.items.weapon.missiles;

import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.mobs.Assassin;
import com.github.dachhack.sprout.actors.mobs.Bat;
import com.github.dachhack.sprout.actors.mobs.Brute;
import com.github.dachhack.sprout.actors.mobs.Gnoll;
import com.github.dachhack.sprout.actors.mobs.GoldThief;
import com.github.dachhack.sprout.actors.mobs.PoisonGoo;
import com.github.dachhack.sprout.actors.mobs.Rat;
import com.github.dachhack.sprout.actors.mobs.RatBoss;
import com.github.dachhack.sprout.actors.mobs.Shaman;
import com.github.dachhack.sprout.actors.mobs.SpectralRat;
import com.github.dachhack.sprout.actors.mobs.Thief;
import com.github.dachhack.sprout.actors.mobs.npcs.Ghost.GnollArcher;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.sprites.ItemSprite.Glowing;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class ForestDart extends MissileWeapon {

	{
		name = "forest dart";
		image = ItemSpriteSheet.DART;

		MIN = 4;
		MAX = 10;

		bones = false; // Finding them in bones would be semi-frequent and
						// disappointing.
	}

	public ForestDart() {
		this(1);
	}

	public ForestDart(int number) {
		super();
		quantity = number;
	}
	
	@Override
	public void proc(Char attacker, Char defender, int damage) {
		
		
       if (    defender instanceof Gnoll 
    		|| defender instanceof GnollArcher  
    		|| defender instanceof Shaman  
    		|| defender instanceof Brute
    		|| defender instanceof Bat
    		|| defender instanceof Rat
    		|| defender instanceof RatBoss
    		|| defender instanceof Assassin 
    		|| defender instanceof Thief 
    		|| defender instanceof GoldThief 
    		|| defender instanceof PoisonGoo 
    		|| defender instanceof SpectralRat 
    		){
    	   defender.damage(Random.Int(damage*2,damage*5), this);
       } else {
    	   defender.damage(Random.Int(damage,damage*2), this); 
       }


	}

	@Override
	public String desc() {
		return "These legendary hunting darts are specifically designed  "
				+ "to put down beasts that linger in the shadows.";
	}

	@Override
	public Item random() {
		quantity = Random.Int(5, 15);
		return this;
	}

	@Override
	public int price() {
		return quantity * 2;
	}
	
	private static final Glowing GREEN = new Glowing(0x00FF00);
	
	@Override
	public Glowing glowing() {
		return GREEN;
	}
}
