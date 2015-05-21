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
import com.github.dachhack.sprout.items.Generator;
import com.github.dachhack.sprout.items.Honeypot.ShatteredPot;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.Generator.Category;
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
import com.github.dachhack.sprout.items.weapon.melee.*;
import com.github.dachhack.sprout.plants.Plant;

public class WaterOfTransmutation extends WellWater {

	@Override
	protected Item affectItem(Item item) {

		if (item instanceof MeleeWeapon) {
			item = changeWeapon((MeleeWeapon) item);
		} else if (item instanceof Scroll) {
			item = changeScroll((Scroll) item);
		} else if (item instanceof Potion) {
			item = changePotion((Potion) item);
		} else if (item instanceof Ring) {
			item = changeRing((Ring) item);
		} else if (item instanceof Wand) {
			item = changeWand((Wand) item);
		} else if (item instanceof Plant.Seed) {
			item = changeSeed((Plant.Seed) item);
		} else if (item instanceof Artifact) {
			item = changeArtifact((Artifact) item);
		} else if (item instanceof ShatteredPot) {
			item = changeHoneypot((ShatteredPot) item);
		} else {
			item = null;
		}

		if (item != null) {
			Journal.remove(Feature.WELL_OF_TRANSMUTATION);
		}

		return item;

	}

	@Override
	public void use(BlobEmitter emitter) {
		super.use(emitter);
		emitter.start(Speck.factory(Speck.CHANGE), 0.2f, 0);
	}

	private MeleeWeapon changeWeapon(MeleeWeapon w) {

		MeleeWeapon n = null;

		if (w instanceof Knuckles) {
			n = new Dagger();
		} else if (w instanceof Dagger) {
			n = new Knuckles();
		}

		else if (w instanceof Spear) {
			n = new Quarterstaff();
		} else if (w instanceof Quarterstaff) {
			n = new Spear();
		}

		else if (w instanceof Sword) {
			n = new Mace();
		} else if (w instanceof Mace) {
			n = new Sword();
		}

		else if (w instanceof Longsword) {
			n = new BattleAxe();
		} else if (w instanceof BattleAxe) {
			n = new Longsword();
		}

		else if (w instanceof Glaive) {
			n = new WarHammer();
		} else if (w instanceof WarHammer) {
			n = new Glaive();
		}

		if (n != null) {

			int level = w.level;
			if (level > 0) {
				n.upgrade(level);
			} else if (level < 0) {
				n.degrade(-level);
			}

			n.enchantment = w.enchantment;
			n.levelKnown = w.levelKnown;
			n.cursedKnown = w.cursedKnown;
			n.cursed = w.cursed;

			return n;
		} else {
			return null;
		}
	}

	private Ring changeRing(Ring r) {
		Ring n;
		do {
			n = (Ring) Generator.random(Category.RING);
		} while (n.getClass() == r.getClass());

		n.level = 0;

		int level = r.level;
		if (level > 0) {
			n.upgrade(level);
		} else if (level < 0) {
			n.degrade(-level);
		}

		n.levelKnown = r.levelKnown;
		n.cursedKnown = r.cursedKnown;
		n.cursed = r.cursed;

		return n;
	}

	private Artifact changeArtifact(Artifact a) {
		Artifact n = Generator.randomArtifact();

		if (n != null) {
			n.cursedKnown = a.cursedKnown;
			n.cursed = a.cursed;
			n.levelKnown = a.levelKnown;
			n.transferUpgrade(a.visiblyUpgraded());
		}

		return n;
	}

	private Wand changeWand(Wand w) {

		Wand n;
		do {
			n = (Wand) Generator.random(Category.WAND);
		} while (n.getClass() == w.getClass());

		n.level = 0;
		n.updateLevel();
		n.upgrade(w.level);

		n.levelKnown = w.levelKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;

		return n;
	}

	private Plant.Seed changeSeed(Plant.Seed s) {

		Plant.Seed n;

		do {
			n = (Plant.Seed) Generator.random(Category.SEED);
		} while (n.getClass() == s.getClass());

		return n;
	}

	private Scroll changeScroll(Scroll s) {
		if (s instanceof ScrollOfUpgrade) {

			return new ScrollOfMagicalInfusion();

		} else if (s instanceof ScrollOfMagicalInfusion) {

			return new ScrollOfUpgrade();

		} else {

			Scroll n;
			do {
				n = (Scroll) Generator.random(Category.SCROLL);
			} while (n.getClass() == s.getClass());
			return n;
		}
	}

	private Potion changePotion(Potion p) {
		if (p instanceof PotionOfStrength) {

			return new PotionOfMight();

		} else if (p instanceof PotionOfMight) {

			return new PotionOfStrength();
		
		} else if (p instanceof PotionOfMending){
		
			return new PotionOfHealing();

		} else {

			Potion n;
			do {
				n = (Potion) Generator.random(Category.POTION);
			} while (n.getClass() == p.getClass());
			return n;
		}
	}

	private Food changeHoneypot(ShatteredPot s) {
		return new PotionOfConstitution();
	}
	
	
	
	@Override
	public String tileDesc() {
		return "Power of change radiates from the water of this well. "
				+ "Throw an item into the well to turn it into something else.";
	}
}
