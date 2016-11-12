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
package com.github.dachhack.sprout.items.weapon.melee.relic;

import java.util.ArrayList;

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.DwarfKingTomb;
import com.github.dachhack.sprout.actors.mobs.Gullin;
import com.github.dachhack.sprout.actors.mobs.Kupua;
import com.github.dachhack.sprout.actors.mobs.MineSentinel;
import com.github.dachhack.sprout.actors.mobs.Otiluke;
import com.github.dachhack.sprout.actors.mobs.Zot;
import com.github.dachhack.sprout.actors.mobs.ZotPhase;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.artifacts.Artifact.ArtifactBuff;
import com.github.dachhack.sprout.items.weapon.Weapon;
import com.github.dachhack.sprout.items.weapon.Weapon.Enchantment;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class RelicMeleeWeapon extends Weapon {

	private int tier;
	
	private static final float TIME_TO_EQUIP = 1f;
	
	public Buff passiveBuff;
	protected Buff activeBuff;

	// level is used internally to track upgrades to artifacts, size/logic
	// varies per artifact.
	// already inherited from item superclass
	// exp is used to count progress towards levels for some artifacts
	protected int exp = 0;
	// levelCap is the artifact's maximum level
	protected int levelCap = 0;

	// the current artifact charge
	public int charge = 0;
	
	// the maximum charge, varies per artifact, not all artifacts use this.
	public int chargeCap = 0;

	// used by some artifacts to keep track of duration of effects or cooldowns
	// to use.
	protected int cooldown = 0;
	
	public RelicMeleeWeapon(int tier, float acu, float dly) {
		super();

		this.tier = tier;

		ACU = acu;
		DLY = dly;

		STR = typicalSTR();

		MIN = min();
		MAX = max();
		reinforced = true;
	}

	private int min() {
		return tier;
	}

	private int max() {
		return (int) ((tier * tier - tier + 10) / ACU * DLY);
	}

	@Override
	public boolean doEquip(Hero hero) {

			activate(hero);
			
			return super.doEquip(hero);

	}

		
	@Override
	public void activate(Hero hero) {
		passiveBuff = passiveBuff();
		passiveBuff.attachTo(hero);
	}

	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		
		if (super.doUnequip(hero, collect, single)) {

			if (passiveBuff != null){			
			  passiveBuff.detach();
			  passiveBuff = null;
			}

			hero.belongings.weapon = null;
			return true;

		} else {

			return false;

		}
	}

		
	protected WeaponBuff passiveBuff() {
		return null;
	}

	public class WeaponBuff extends Buff {

		public int level() {
			return level;
		}

		public boolean isCursed() {
			return cursed;
		}

	}
	
	@Override
	public void proc(Char attacker, Char defender, int damage) {
		
		if (defender instanceof Gullin 
        		|| defender instanceof Kupua
        		|| defender instanceof MineSentinel
        		|| defender instanceof Otiluke
        		|| defender instanceof Zot
        		|| defender instanceof ZotPhase){
        	
        	//damage*=2;
			
			defender.damage(Random.Int(damage,damage*2), this);
		}
        
		
		if (enchantment != null) {
			enchantment.proc(this, attacker, defender, damage);		
		}
	}
	
	
	@Override
	public Item upgrade() {
		return upgrade(false);
	}

	@Override
	public Item upgrade(boolean enchant) {
		STR--;
		MIN++;
		MAX += tier;

		if (enchant){
			GLog.i("Your weapon screams, 'What are you trying to do to me!?' Relic weapons cannot be enchanted.");
		}
		return super.upgrade(false);
		
	}

	public Item safeUpgrade() {
		return upgrade(enchantment != null);
	}

	
	@Override
	public Item degrade() {
		STR++;
		MIN--;
		MAX -= tier;
		return super.degrade();
	}

	public int typicalSTR() {
		return 8 + tier * 2;
	}

	@Override
	public String info() {

		final String p = "\n\n";

		StringBuilder info = new StringBuilder(desc());

		String quality = levelKnown && level != 0 ? (level > 0 ? "upgraded"
				: "degraded") : "";
		info.append(p);
		info.append("This " + name + " is " + Utils.indefinite(quality));
		info.append(" tier-" + tier + " melee weapon. ");

		if (levelKnown) {
			info.append("Its average damage is "
					+ Math.round((MIN + (MAX - MIN) / 2)
							* (imbue == Imbue.LIGHT ? 0.75f
									: (imbue == Imbue.HEAVY ? 1.5f : 1)))
					+ " points per hit. ");
		} else {
			info.append("Its typical average damage is "
					+ (min() + (max() - min()) / 2) + " points per hit "
					+ "and usually it requires " + typicalSTR()
					+ " points of strength. ");
			if (typicalSTR() > Dungeon.hero.STR()) {
				info.append("Probably this weapon is too heavy for you. ");
			}
		}

		if (DLY != 1f) {
			info.append("This is a rather " + (DLY < 1f ? "fast" : "slow"));
			if (ACU != 1f) {
				if ((ACU > 1f) == (DLY < 1f)) {
					info.append(" and ");
				} else {
					info.append(" but ");
				}
				info.append(ACU > 1f ? "accurate" : "inaccurate");
			}
			info.append(" weapon. ");
		} else if (ACU != 1f) {
			info.append("This is a rather "
					+ (ACU > 1f ? "accurate" : "inaccurate") + " weapon. ");
		}
		switch (imbue) {
		case LIGHT:
			info.append("It was balanced to be lighter. ");
			break;
		case HEAVY:
			info.append("It was balanced to be heavier. ");
			break;
		case NONE:
		}

		if (enchantment != null) {
			info.append("It is enchanted.");
		}
		
		if (reinforced) {
			info.append("\n\nIt is reinforced.");
		}

		if (charge>=chargeCap) {
			info.append("\n\nIt is fully charged.");
		} else {
			info.append("\n\nIt has " +charge+ " out of "+chargeCap+" charges.");
		}
		if (levelKnown 
				//&& Dungeon.hero.belongings.backpack.items.contains(this)
				) {
			if (STR > Dungeon.hero.STR()) {
				info.append(p);
				info.append("Because of your inadequate strength the accuracy and speed "
						+ "of your attack with this " + name + " is decreased.");
			}
			if (STR < Dungeon.hero.STR()) {
				info.append(p);
				info.append("Because of your excess strength the damage "
						+ "of your attack with this " + name + " is increased.");
			}
		}

		if (isEquipped(Dungeon.hero)) {
			info.append(p);
			info.append("You hold the "
					+ name
					+ " at the ready"
					+ (cursed ? ", and because it is cursed, you are powerless to let go."
							: "."));
		} else {
			if (cursedKnown && cursed) {
				info.append(p);
				info.append("You can feel a malevolent magic lurking within the "
						+ name + ".");
			}
		}

		return info.toString();
	}

	@Override
	public int price() {
		int price = 20 * (1 << (tier - 1));
		if (enchantment != null) {
			price *= 1.5;
		}
		if (cursed && cursedKnown) {
			price /= 2;
		}
		if (levelKnown) {
			if (level > 0) {
				price *= (level + 1);
			} else if (level < 0) {
				price /= (1 - level);
			}
		}
		if (price < 1) {
			price = 1;
		}
		return price;
	}

	@Override
	public Item random() {
		super.random();

		if (Random.Int(10 + level) == 0) {
			enchant();
		}

		return this;
	}
	
	private static final String CHARGE = "charge";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(CHARGE, charge);		
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		charge = bundle.getInt(CHARGE);		
	}
}


