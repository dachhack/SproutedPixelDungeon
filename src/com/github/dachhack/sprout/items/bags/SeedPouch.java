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

import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.Rice;
import com.github.dachhack.sprout.items.food.Blackberry;
import com.github.dachhack.sprout.items.food.Blueberry;
import com.github.dachhack.sprout.items.food.Cloudberry;
import com.github.dachhack.sprout.items.food.FullMoonberry;
import com.github.dachhack.sprout.items.food.GoldenNut;
import com.github.dachhack.sprout.items.food.Moonberry;
import com.github.dachhack.sprout.items.food.Nut;
import com.github.dachhack.sprout.items.food.ToastedNut;
import com.github.dachhack.sprout.plants.Plant;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;

public class SeedPouch extends Bag {

	{
		name = "seed pouch";
		image = ItemSpriteSheet.POUCH;

		size = 24;
	}

	@Override
	public boolean grab(Item item) {
		if (item instanceof GoldenNut ||  item instanceof ToastedNut || item instanceof Nut 
			|| item instanceof Blackberry
			|| item instanceof Blueberry
			|| item instanceof Cloudberry
			|| item instanceof Moonberry
			|| item instanceof FullMoonberry
			|| item instanceof Plant.Seed
			|| item instanceof Rice){
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
		return "This small velvet pouch allows you to store any number of seeds in it. Very convenient.";
	}
}
