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
package com.github.dachhack.sprout.actors.hero;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Challenges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.ShatteredPixelDungeon;
import com.github.dachhack.sprout.items.CavesKey;
import com.github.dachhack.sprout.items.CityKey;
import com.github.dachhack.sprout.items.HallsKey;
import com.github.dachhack.sprout.items.HolyHandGrenade;
import com.github.dachhack.sprout.items.PrisonKey;
import com.github.dachhack.sprout.items.SewersKey;
import com.github.dachhack.sprout.items.TomeOfMastery;
import com.github.dachhack.sprout.items.armor.ClothArmor;
import com.github.dachhack.sprout.items.artifacts.CloakOfShadows;
import com.github.dachhack.sprout.items.food.Food;
import com.github.dachhack.sprout.items.food.FullMoonberry;
import com.github.dachhack.sprout.items.food.GoldenNut;
import com.github.dachhack.sprout.items.potions.PotionOfMindVision;
import com.github.dachhack.sprout.items.potions.PotionOfStrength;
import com.github.dachhack.sprout.items.scrolls.ScrollOfIdentify;
import com.github.dachhack.sprout.items.scrolls.ScrollOfMagicMapping;
import com.github.dachhack.sprout.items.wands.WandOfMagicMissile;
import com.github.dachhack.sprout.items.weapon.melee.Dagger;
import com.github.dachhack.sprout.items.weapon.melee.Knuckles;
import com.github.dachhack.sprout.items.weapon.melee.ShortSword;
import com.github.dachhack.sprout.items.weapon.missiles.Boomerang;
import com.github.dachhack.sprout.items.weapon.missiles.Dart;
import com.watabou.utils.Bundle;

public enum HeroClass {

	WARRIOR("warrior"), MAGE("mage"), ROGUE("rogue"), HUNTRESS("huntress");

	private String title;

	private HeroClass(String title) {
		this.title = title;
	}

	public static final String[] WAR_PERKS = {
			"Warriors start with 11 points of Strength.",
			"Warriors start with a unique short sword. This sword can be later \"reforged\" to upgrade another melee weapon.",
			"Warriors are less proficient with missile weapons.",
			"Any piece of food restores more health when eaten.",
			"Potions of Strength are identified from the beginning.", };

	public static final String[] MAG_PERKS = {
			"Mages start with a unique Wand of Magic Missile. This wand can be later \"disenchanted\" to upgrade another wand.",
			"Mages recharge their wands faster.",
			"When eaten, any piece of food restores 1 charge for all wands in the inventory.",
			"Mages can use wands as a melee weapon.",
			"Scrolls of Identify are identified from the beginning." };

	public static final String[] ROG_PERKS = {
			"Rogues start with a unique Cloak of Shadows.",
			"Rogues identify a type of a ring on equipping it.",
			"Rogues are proficient with light armor, dodging better while wearing one.",
			"Rogues are proficient in detecting hidden doors and traps.",
			"Rogues can go without food longer.",
			"Scrolls of Magic Mapping are identified from the beginning." };

	public static final String[] HUN_PERKS = {
			"Huntresses start with 15 points of Health.",
			"Huntresses start with a unique upgradeable boomerang.",
			"Huntresses are proficient with missile weapons, getting bonus damage from excess strength.",
			"Huntresses are able to recover a single used missile weapon from each enemy.",
			"Huntresses gain more health from dewdrops.",
			"Huntresses sense neighbouring monsters even if they are hidden behind obstacles.",
			"Potions of Mind Vision are identified from the beginning." };

	public void initHero(Hero hero) {

		hero.heroClass = this;

		initCommon(hero);

		switch (this) {
		case WARRIOR:
			initWarrior(hero);
			break;

		case MAGE:
			initMage(hero);
			break;

		case ROGUE:
			initRogue(hero);
			break;

		case HUNTRESS:
			initHuntress(hero);
			break;
		}

		if (Badges.isUnlocked(masteryBadge())) {
			new TomeOfMastery().collect();
		}

		hero.updateAwareness();
	}

	private static void initCommon(Hero hero) {
		if (!Dungeon.isChallenged(Challenges.NO_ARMOR))
			(hero.belongings.armor = new ClothArmor()).identify();

		if (!Dungeon.isChallenged(Challenges.NO_FOOD))
			new Food().identify().collect();
	}

	public Badges.Badge masteryBadge() {
		switch (this) {
		case WARRIOR:
			return Badges.Badge.MASTERY_WARRIOR;
		case MAGE:
			return Badges.Badge.MASTERY_MAGE;
		case ROGUE:
			return Badges.Badge.MASTERY_ROGUE;
		case HUNTRESS:
			return Badges.Badge.MASTERY_HUNTRESS;
		}
		return null;
	}

	private static void initWarrior(Hero hero) {
		hero.STR = hero.STR + 1;
		

		(hero.belongings.weapon = new ShortSword()).identify();
		Dart darts = new Dart(8);
		darts.identify().collect();
		
		//Playtest
		
		//hero.HT=hero.HP=999;
		//hero.STR = hero.STR + 20;
		/*
		//PotionOfLiquidFlame potion5 = new PotionOfLiquidFlame(); potion5.collect();
		BookOfDead dbook = new BookOfDead(); dbook.collect();
		BookOfLife lbook = new BookOfLife(); lbook.collect();
		BookOfTranscendence tbook = new BookOfTranscendence(); tbook.collect();
		SanChikarah san = new SanChikarah(); san.collect();
		PotionBandolier bag1 = new PotionBandolier(); bag1.collect();
		ScrollHolder bag2 = new ScrollHolder(); bag2.collect();
		
		
		for(int i=0; i<40; i++){
		Scroll scroll = new ScrollOfMagicalInfusion();
        scroll.identify().collect();
        Scroll scroll2 = new ScrollOfUpgrade();
        scroll2.identify().collect();      
       }		
		PlateArmor armor = new PlateArmor();
      armor.identify().collect();
      for(int i=1; i<40; i++){
        PotionOfExperience potion1 = new PotionOfExperience(); potion1.collect();
       PotionOfInvisibility potion2 = new PotionOfInvisibility(); potion2.collect();
       PotionOfHealing potion3 = new PotionOfHealing(); potion3.collect();
       PotionOfMindVision potion4 = new PotionOfMindVision(); potion4.collect();
      PotionOfLevitation potion5 = new PotionOfLevitation(); potion5.collect();
        }
         
        Wand wand = new WandOfDisintegration(); wand.collect();
        */
        //GoldenNut nut = new GoldenNut(); nut.collect();
        //SewersKey key1 = new SewersKey(); key1.collect();
        //PrisonKey key2 = new PrisonKey(); key2.collect();
        //CavesKey key3 = new CavesKey(); key3.collect();
        //CityKey key4 = new CityKey(); key4.collect();
        //HallsKey key5 = new HallsKey(); key5.collect();
        //FullMoonberry berry = new FullMoonberry(); berry.collect();
       
        //HolyHandGrenade bomb = new HolyHandGrenade(10); bomb.collect();
		    	
		Dungeon.quickslot.setSlot(0, darts);

		new PotionOfStrength().setKnown();
	}

	private static void initMage(Hero hero) {
		(hero.belongings.weapon = new Knuckles()).identify();

		WandOfMagicMissile wand = new WandOfMagicMissile();
		wand.identify().collect();

		Dungeon.quickslot.setSlot(0, wand);

		new ScrollOfIdentify().setKnown();
	}

	private static void initRogue(Hero hero) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.misc1 = cloak).identify();
		hero.belongings.misc1.activate(hero);

		Dart darts = new Dart(8);
		darts.identify().collect();

		Dungeon.quickslot.setSlot(0, cloak);
		if (ShatteredPixelDungeon.quickSlots() > 1)
			Dungeon.quickslot.setSlot(1, darts);

		new ScrollOfMagicMapping().setKnown();
	}

	private static void initHuntress(Hero hero) {

		hero.HP = (hero.HT -= 5);

		(hero.belongings.weapon = new Dagger()).identify();
		Boomerang boomerang = new Boomerang();
		boomerang.identify().collect();

		Dungeon.quickslot.setSlot(0, boomerang);

		new PotionOfMindVision().setKnown();
	}

	public String title() {
		return title;
	}

	public String spritesheet() {

		switch (this) {
		case WARRIOR:
			return Assets.WARRIOR;
		case MAGE:
			return Assets.MAGE;
		case ROGUE:
			return Assets.ROGUE;
		case HUNTRESS:
			return Assets.HUNTRESS;
		}

		return null;
	}

	public String[] perks() {

		switch (this) {
		case WARRIOR:
			return WAR_PERKS;
		case MAGE:
			return MAG_PERKS;
		case ROGUE:
			return ROG_PERKS;
		case HUNTRESS:
			return HUN_PERKS;
		}

		return null;
	}

	private static final String CLASS = "class";

	public void storeInBundle(Bundle bundle) {
		bundle.put(CLASS, toString());
	}

	public static HeroClass restoreInBundle(Bundle bundle) {
		String value = bundle.getString(CLASS);
		return value.length() > 0 ? valueOf(value) : ROGUE;
	}
}
