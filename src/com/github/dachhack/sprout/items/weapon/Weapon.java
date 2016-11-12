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
package com.github.dachhack.sprout.items.weapon;

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.hero.HeroClass;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.KindOfWeapon;
import com.github.dachhack.sprout.items.rings.RingOfFuror;
import com.github.dachhack.sprout.items.rings.RingOfSharpshooting;
import com.github.dachhack.sprout.items.weapon.enchantments.AresLeech;
import com.github.dachhack.sprout.items.weapon.enchantments.BuzzSaw;
import com.github.dachhack.sprout.items.weapon.enchantments.CromLuck;
import com.github.dachhack.sprout.items.weapon.enchantments.Death;
import com.github.dachhack.sprout.items.weapon.enchantments.Fire;
import com.github.dachhack.sprout.items.weapon.enchantments.Horror;
import com.github.dachhack.sprout.items.weapon.enchantments.Instability;
import com.github.dachhack.sprout.items.weapon.enchantments.JupitersHorror;
import com.github.dachhack.sprout.items.weapon.enchantments.Leech;
import com.github.dachhack.sprout.items.weapon.enchantments.LokisPoison;
import com.github.dachhack.sprout.items.weapon.enchantments.Luck;
import com.github.dachhack.sprout.items.weapon.enchantments.NeptuneShock;
import com.github.dachhack.sprout.items.weapon.enchantments.Nomnom;
import com.github.dachhack.sprout.items.weapon.enchantments.Paralysis;
import com.github.dachhack.sprout.items.weapon.enchantments.Poison;
import com.github.dachhack.sprout.items.weapon.enchantments.Shock;
import com.github.dachhack.sprout.items.weapon.enchantments.Slow;
import com.github.dachhack.sprout.items.weapon.melee.MeleeWeapon;
import com.github.dachhack.sprout.items.weapon.melee.relic.RelicMeleeWeapon;
import com.github.dachhack.sprout.items.weapon.missiles.MissileWeapon;
import com.github.dachhack.sprout.sprites.ItemSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Weapon extends KindOfWeapon {

	private static final int HITS_TO_KNOW = 20;

	private static final String TXT_IDENTIFY = "You are now familiar enough with your %s to identify it. It is %s.";
	//private static final String TXT_INCOMPATIBLE = "Interaction of different types of magic has negated the enchantment on this weapon!";
	private static final String TXT_TO_STRING = "%s :%d";

	public int STR = 10;
	public float ACU = 1; // Accuracy modifier
	public float DLY = 1f; // Speed modifier

	public enum Imbue {
		NONE, LIGHT, HEAVY
	}

	public Imbue imbue = Imbue.NONE;

	private int hitsToKnow = HITS_TO_KNOW;

	public Enchantment enchantment;

	@Override
	public void proc(Char attacker, Char defender, int damage) {

		if (enchantment != null) {
			enchantment.proc(this, attacker, defender, damage);	
		}

		if (!levelKnown) {
			if (--hitsToKnow <= 0) {
				levelKnown = true;
				GLog.i(TXT_IDENTIFY, name(), toString());
				Badges.validateItemLevelAquired(this);
			}
		}
	}

	private static final String UNFAMILIRIARITY = "unfamiliarity";
	private static final String ENCHANTMENT = "enchantment";
	private static final String IMBUE = "imbue";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(UNFAMILIRIARITY, hitsToKnow);
		bundle.put(ENCHANTMENT, enchantment);
		bundle.put(IMBUE, imbue);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if ((hitsToKnow = bundle.getInt(UNFAMILIRIARITY)) == 0) {
			hitsToKnow = HITS_TO_KNOW;
		}
		enchantment = (Enchantment) bundle.get(ENCHANTMENT);
		imbue = bundle.getEnum(IMBUE, Imbue.class);
	}

	@Override
	public float acuracyFactor(Hero hero) {

		int encumbrance = STR - hero.STR();

		float ACU = this.ACU;

		if (this instanceof MissileWeapon) {
			switch (hero.heroClass) {
			case WARRIOR:
				encumbrance += 3;
				break;
			case HUNTRESS:
				encumbrance -= 2;
				break;
			default:
			}
			int bonus = 0;
			for (Buff buff : hero.buffs(RingOfSharpshooting.Aim.class)) {
				bonus += ((RingOfSharpshooting.Aim) buff).level;
			}
			ACU *= (float) (Math.pow(1.1, bonus));
		}

		return encumbrance > 0 ? (float) (ACU / Math.pow(1.5, encumbrance))
				: ACU;
	}

	@Override
	public float speedFactor(Hero hero) {

		int encumrance = STR - hero.STR();
		if (this instanceof MissileWeapon
				&& hero.heroClass == HeroClass.HUNTRESS) {
			encumrance -= 2;
		}

		float DLY = this.DLY
				* (imbue == Imbue.LIGHT ? 0.667f
						: (imbue == Imbue.HEAVY ? 1.667f : 1.0f));

		int bonus = 0;
		for (Buff buff : hero.buffs(RingOfFuror.Furor.class)) {
			bonus += ((RingOfFuror.Furor) buff).level;
		}

		DLY = (float) (0.25 + (DLY - 0.25) * Math.pow(0.8, bonus));

		return (encumrance > 0 ? (float) (DLY * Math.pow(1.2, encumrance))
				: DLY);
	}

	@Override
	public int damageRoll(Hero hero) {

		int damage = super.damageRoll(hero);

		if (this instanceof MeleeWeapon) {
			int exStr = hero.STR() - STR;
			if (exStr > 0) {
				damage += Random.IntRange(0, exStr);
			}
		}
		if (this instanceof MissileWeapon && hero.heroClass == HeroClass.HUNTRESS) {
			int exStr = Math.max(Math.round((hero.STR() - STR)/5),0);
			int lvlBonus = Math.round(hero.lvl/10);
			int totBonus = exStr+lvlBonus+1;
			if (totBonus > 0) {
				damage += damage*(Random.IntRange(exStr, totBonus)/4);
			}
		}
		if (this instanceof MissileWeapon && hero.heroClass != HeroClass.HUNTRESS) {
			int exStr = Math.max(Math.round((hero.STR() - STR)/5),0);
			int lvlBonus = Math.round(hero.lvl/10);
			int totBonus = exStr+lvlBonus;
			if (totBonus > 0) {
				damage = damage*Random.IntRange(lvlBonus, totBonus);
			}
		}

		return Math.round(damage* (imbue == Imbue.LIGHT ? 0.7f : (imbue == Imbue.HEAVY ? 1.5f: 1f)));
	}

	public Item upgrade(boolean enchant) {
		
		if (enchant){
		   if (enchantment != null) {
				enchantAdv();
		   } else {
				enchant();
		   }
		}

		return super.upgrade();
	}

	@Override
	public String toString() {
		return levelKnown ? Utils.format(TXT_TO_STRING, super.toString(), STR)
				: super.toString();
	}

	@Override
	public String name() {
		return enchantment == null ? super.name() : enchantment.name(super.name());
	}

	@Override
	public Item random() {
		if (Random.Float() < 0.4) {
			int n = 1;
			if (Random.Int(3) == 0) {
				n++;
				if (Random.Int(3) == 0) {
					n++;
				}
			}
			if (Random.Int(2) == 0) {
				upgrade(n);
			} else {
				degrade(n);
				cursed = true;
			}
		}
		return this;
	}

	public Weapon enchant(Enchantment ench) {
		enchantment = ench;
		return this;
	}

	
	public Weapon enchant() {

		Class<? extends Enchantment> oldEnchantment = enchantment != null ? enchantment.getClass() : null;
		Enchantment ench = Enchantment.random();
		while (ench.getClass() == oldEnchantment) {
			ench = Enchantment.random();
		}
		
		return enchant(ench);
	}
	
	public Weapon enchantAdv() {

		Class<? extends Enchantment> oldEnchantment = enchantment != null ? enchantment.getClass() : null;
		Enchantment ench = Enchantment.randomAdv();
		while (ench.getClass() == oldEnchantment) {
			ench = Enchantment.randomAdv();
		}
		
		return enchant(ench);
	}
	
	public Weapon enchantLow() {

		Class<? extends Enchantment> oldEnchantment = enchantment != null ? enchantment.getClass() : null;
		Enchantment ench = Enchantment.randomLow();
		while (ench.getClass() == oldEnchantment) {
			ench = Enchantment.randomLow();
		}
		
		return enchant(ench);
	}

	public Weapon enchantNom() {

		Enchantment ench = Enchantment.randomNom();
		return enchant(ench);
	}
	
	public Weapon enchantLuck() {

		Enchantment ench = Enchantment.randomLuck();
		return enchant(ench);
	}
	
	public Weapon enchantBuzz() {

		Enchantment ench = Enchantment.randomBuzz();
		return enchant(ench);
	}
	
	public Weapon enchantNeptune() {

		Enchantment ench = Enchantment.randomNeptune();
		return enchant(ench);
	}
	
	public Weapon enchantAres() {

		Enchantment ench = Enchantment.Ares();
		return enchant(ench);
	}
	

	public Weapon enchantJupiter() {

		Enchantment ench = Enchantment.Jupiter();
		return enchant(ench);
	}
	public Weapon enchantLoki() {

		Enchantment ench = Enchantment.Loki();
		return enchant(ench);
	}
	
	public boolean isEnchanted() {
		return enchantment != null;
	}

	@Override
	public ItemSprite.Glowing glowing() {
		return enchantment != null ? enchantment.glowing() : null;
	}

	public static abstract class Enchantment implements Bundlable {

		private static final Class<?>[] enchants = new Class<?>[] { Fire.class,
				Poison.class, Death.class, Paralysis.class, Leech.class,
				Slow.class, Shock.class, Instability.class, Horror.class,
				Luck.class, Nomnom.class, BuzzSaw.class, NeptuneShock.class,
				CromLuck.class, AresLeech.class};
		private static final float[] chances = new float[] { 10, 10, 1, 2, 1,
				2, 6, 3, 2, 2, 0, 0, 0, 0, 0 };
		
		private static final float[] chancesLow = new float[] { 10, 10, 0, 0, 1,
			2, 6, 0, 0, 2, 0, 0, 0, 0, 0 };
		
		private static final float[] chancesAdv = new float[] { 2, 2, 2, 2, 2,
			2, 2, 2, 2, 2, 0, 0,0, 0, 0 };
		
		private static final float[] chancesNom = new float[] { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 1, 0,0, 0, 0 };
		
		private static final float[] chancesBuzz = new float[] { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 1,0, 0, 0 };
		
		private static final float[] chancesNeptune = new float[] { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 1, 0, 0 };
		
		private static final float[] chancesLuck = new float[] { 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
		
		private static final Class<?>[] relicenchants = new Class<?>[] {  NeptuneShock.class,
			CromLuck.class, AresLeech.class, JupitersHorror.class, LokisPoison.class};
		
		private static final float[] chancesAres = new float[] { 0, 0, 1, 0, 0 };
		private static final float[] chancesJupiter = new float[] { 0, 0, 0, 1, 0 };
		private static final float[] chancesLoki = new float[] { 0, 0, 0, 0, 1 };
		
		public abstract boolean proc(Weapon weapon, Char attacker,
				Char defender, int damage);
		

		public abstract boolean proc(RelicMeleeWeapon weapon, Char attacker,
				Char defender, int damage);

		public String name(String weaponName) {
			return weaponName;
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
		}

		@Override
		public void storeInBundle(Bundle bundle) {
		}

		public ItemSprite.Glowing glowing() {
			return ItemSprite.Glowing.WHITE;
		}

		@SuppressWarnings("unchecked")
		public static Enchantment random() {
			try {
				return ((Class<Enchantment>) enchants[Random.chances(chances)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		@SuppressWarnings("unchecked")
		public static Enchantment randomAdv() {
			try {
				return ((Class<Enchantment>) enchants[Random.chances(chancesAdv)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		@SuppressWarnings("unchecked")
		public static Enchantment randomLow() {
			try {
				return ((Class<Enchantment>) enchants[Random.chances(chancesLow)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		@SuppressWarnings("unchecked")
		public static Enchantment randomNom() {
			try {
				return ((Class<Enchantment>) enchants[Random.chances(chancesNom)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		@SuppressWarnings("unchecked")
		public static Enchantment randomBuzz() {
			try {
				return ((Class<Enchantment>) enchants[Random.chances(chancesBuzz)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomLuck() {
			try {
				return ((Class<Enchantment>) enchants[Random.chances(chancesLuck)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}

		@SuppressWarnings("unchecked")
		public static Enchantment Ares() {
			try {
				return ((Class<Enchantment>) relicenchants[Random.chances(chancesAres)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment Jupiter() {
			try {
				return ((Class<Enchantment>) relicenchants[Random.chances(chancesJupiter)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		
		@SuppressWarnings("unchecked")
		public static Enchantment Loki() {
			try {
				return ((Class<Enchantment>) relicenchants[Random.chances(chancesLoki)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		
		
		@SuppressWarnings("unchecked")
		public static Enchantment randomNeptune() {
			try {
				return ((Class<Enchantment>) enchants[Random.chances(chancesNeptune)])
						.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
	}
}
