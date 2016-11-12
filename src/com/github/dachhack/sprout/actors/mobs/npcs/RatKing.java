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
package com.github.dachhack.sprout.actors.mobs.npcs;


import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.items.Heap;
import com.github.dachhack.sprout.items.weapon.melee.Spork;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.sprites.RatKingSprite;

public class RatKing extends NPC {

	{
		name = "rat king";
		spriteClass = RatKingSprite.class;

		state = SLEEPING;
	}

	@Override
	public int defenseSkill(Char enemy) {
		return 1000;
	}

	@Override
	public float speed() {
		return 2f;
	}

	@Override
	protected Char chooseEnemy() {
		return null;
	}

	@Override
	public void damage(int dmg, Object src) {
	}

	@Override
	public void add(Buff buff) {
	}

	@Override
	public boolean reset() {
		return true;
	}

   
	
	@Override
	public void interact() {
		
		int checkChests = 0;
		int length = Level.getLength();
		for (int i = 0; i < length; i++) {
			Heap chest = Dungeon.level.heaps.get(i);
			if(chest != null && chest.chestCheck()){checkChests++;}
		}
		
		Spork spork = Dungeon.hero.belongings.getItem(Spork.class);
		//RoyalSpork royalspork = Dungeon.hero.belongings.getItem(RoyalSpork.class);
		
		sprite.turnTo(pos, Dungeon.hero.pos);
		if (state == SLEEPING) {
			notice();
			yell("I'm not sleeping!");
			yell("Please don't take my treasures!");
			state = WANDERING;
		//} else if (Statistics.deepestFloor>9 && checkChests >= Dungeon.ratChests && spork==null && royalspork==null){ 
		} else if (Statistics.deepestFloor>10 && checkChests >= Dungeon.ratChests && spork==null){ 
			yell("Thank you for not stealing my treasures! You can have my spork if you can kill the Shadow Bandit who took it from me.");
			Dungeon.sporkAvail = true;
		} else if (checkChests < Dungeon.ratChests){
			Dungeon.sporkAvail = false;
			yell("Why would you steal from me?");
		} else if (spork!=null) {
			//yell("You found my spork! Here, trade me for this old one.");
			yell("You found my spork! Have fun sporking!");
			//if (spork.isEquipped(Dungeon.hero)) {
			//	spork.doUnequip(Dungeon.hero, false);
			//}
			//spork.detach(Dungeon.hero.belongings.backpack);
			//Dungeon.level.drop(new RoyalSpork().enchantNom(), pos).sprite.drop();
			//Dungeon.limitedDrops.royalspork.drop();
			
		} else {
			yell("What is it? I have no time for this nonsense. My kingdom won't rule itself!");
		}
	}

	@Override
	public String description() {
		return ((RatKingSprite) sprite).festive ? "This rat is a little bigger than a regular marsupial rat. "
				+ "It's wearing a tiny festive hat instead of its usual crown. Happy Holidays!"
				: "This rat is a little bigger than a regular marsupial rat "
						+ "and it's wearing a tiny crown on its head.";
	}
}
