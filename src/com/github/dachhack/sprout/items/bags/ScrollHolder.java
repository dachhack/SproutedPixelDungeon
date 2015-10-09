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
package com.github.dachhack.sprout.items.bags;

import com.github.dachhack.sprout.actors.mobs.SeekingBomb;
import com.github.dachhack.sprout.actors.mobs.SeekingClusterBomb;
import com.github.dachhack.sprout.items.ActiveMrDestructo;
import com.github.dachhack.sprout.items.ActiveMrDestructo2;
import com.github.dachhack.sprout.items.Ankh;
import com.github.dachhack.sprout.items.Bomb;
import com.github.dachhack.sprout.items.ClusterBomb;
import com.github.dachhack.sprout.items.DizzyBomb;
import com.github.dachhack.sprout.items.HolyHandGrenade;
import com.github.dachhack.sprout.items.InactiveMrDestructo;
import com.github.dachhack.sprout.items.InactiveMrDestructo2;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.OrbOfZot;
import com.github.dachhack.sprout.items.SeekingBombItem;
import com.github.dachhack.sprout.items.SeekingClusterBombItem;
import com.github.dachhack.sprout.items.SmartBomb;
import com.github.dachhack.sprout.items.rings.Ring;
import com.github.dachhack.sprout.items.scrolls.Scroll;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;

public class ScrollHolder extends Bag {

	{
		name = "scroll holder";
		image = ItemSpriteSheet.HOLDER;

		size = 20;
	}

	@Override
	public boolean grab(Item item) {
		if (item instanceof Scroll 
				||  item instanceof Bomb 
				||  item instanceof DizzyBomb 
				||  item instanceof SmartBomb 
				||  item instanceof SeekingBombItem 
				||  item instanceof ClusterBomb 
				||  item instanceof SeekingClusterBombItem 
				||  item instanceof ActiveMrDestructo
				||  item instanceof ActiveMrDestructo2
				||  item instanceof InactiveMrDestructo
				||  item instanceof InactiveMrDestructo2
				||  item instanceof OrbOfZot
				||  item instanceof HolyHandGrenade
				){
			return true;
			} else {
			return false;
			}
	}

	@Override
	public int price() {
		return 50;
	}

	@Override
	public String info() {
		return "This tubular container looks like it would hold an astronomer's charts, but your scrolls will fit just as well.\n\n"
				+ "The holder doesn't look very flammable, so your scrolls should be safe from fire inside it."
				+ "There is a handy little compartment for your bombs and other explosives too. Nice!";
	}
}
