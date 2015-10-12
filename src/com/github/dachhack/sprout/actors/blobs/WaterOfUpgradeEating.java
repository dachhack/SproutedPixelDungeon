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
package com.github.dachhack.sprout.actors.blobs;

import com.github.dachhack.sprout.Journal;
import com.github.dachhack.sprout.Journal.Feature;
import com.github.dachhack.sprout.effects.BlobEmitter;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.items.ActiveMrDestructo;
import com.github.dachhack.sprout.items.ActiveMrDestructo2;
import com.github.dachhack.sprout.items.Generator;
import com.github.dachhack.sprout.items.Generator.Category;
import com.github.dachhack.sprout.items.Honeypot;
import com.github.dachhack.sprout.items.Honeypot.ShatteredPot;
import com.github.dachhack.sprout.items.InactiveMrDestructo;
import com.github.dachhack.sprout.items.InactiveMrDestructo2;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.SteelHoneypot;
import com.github.dachhack.sprout.items.SteelHoneypot.SteelShatteredPot;
import com.github.dachhack.sprout.items.Stylus;
import com.github.dachhack.sprout.items.UpgradeBlobRed;
import com.github.dachhack.sprout.items.UpgradeBlobViolet;
import com.github.dachhack.sprout.items.UpgradeBlobYellow;
import com.github.dachhack.sprout.items.artifacts.Artifact;
import com.github.dachhack.sprout.items.food.Food;
import com.github.dachhack.sprout.items.food.PotionOfConstitution;
import com.github.dachhack.sprout.items.potions.Potion;
import com.github.dachhack.sprout.items.potions.PotionOfHealing;
import com.github.dachhack.sprout.items.potions.PotionOfMending;
import com.github.dachhack.sprout.items.potions.PotionOfMight;
import com.github.dachhack.sprout.items.potions.PotionOfStrength;
import com.github.dachhack.sprout.items.rings.Ring;
import com.github.dachhack.sprout.items.scrolls.Scroll;
import com.github.dachhack.sprout.items.scrolls.ScrollOfMagicalInfusion;
import com.github.dachhack.sprout.items.scrolls.ScrollOfUpgrade;
import com.github.dachhack.sprout.items.wands.Wand;
import com.github.dachhack.sprout.items.weapon.melee.BattleAxe;
import com.github.dachhack.sprout.items.weapon.melee.Dagger;
import com.github.dachhack.sprout.items.weapon.melee.Glaive;
import com.github.dachhack.sprout.items.weapon.melee.Knuckles;
import com.github.dachhack.sprout.items.weapon.melee.Longsword;
import com.github.dachhack.sprout.items.weapon.melee.Mace;
import com.github.dachhack.sprout.items.weapon.melee.MeleeWeapon;
import com.github.dachhack.sprout.items.weapon.melee.Quarterstaff;
import com.github.dachhack.sprout.items.weapon.melee.Spear;
import com.github.dachhack.sprout.items.weapon.melee.Sword;
import com.github.dachhack.sprout.items.weapon.melee.WarHammer;
import com.github.dachhack.sprout.plants.Plant;
import com.watabou.utils.Random;

public class WaterOfUpgradeEating extends WellWater {

	@Override
	protected Item affectItem(Item item) {

		if (item.isUpgradable()) {
			item = eatUpgradable((Item) item);
		} else if (item instanceof Scroll
				    || item instanceof Potion
				    || item instanceof Stylus) {
			item = eatStandard((Item) item);
		} else {
			item = null;
		}
		
		return item;

	}

	@Override
	public void use(BlobEmitter emitter) {
		super.use(emitter);
		emitter.start(Speck.factory(Speck.CHANGE), 0.2f, 0);
	}

	private Item eatUpgradable(Item w) {

		int ups = w.level;
		
		Item n = null;

		if (Random.Float()<(ups/10)){
			
			n = new UpgradeBlobViolet();
			
		} else if (Random.Float()<(ups/5)) {
			
			n =  new UpgradeBlobRed();
			
        } else if (Random.Float()<(ups/3)) {
			
			n =  new UpgradeBlobYellow();
		
		} else {
			
			n = (Plant.Seed) Generator.random(Category.SEEDRICH);
		}
		
		return n;
	}
	
	private Item eatStandard(Item w) {

		Item n = null;
        
		if (Random.Float()<0.1f){
			n = new UpgradeBlobYellow();
		} else {
			n = (Plant.Seed) Generator.random(Category.SEEDRICH);
		}
		
		return n;
	}
	
	@Override
	public String tileDesc() {
		return "A highly caustic liquid shimmers in a pool. Toss in an item to harvest its power. ";
	}
}
