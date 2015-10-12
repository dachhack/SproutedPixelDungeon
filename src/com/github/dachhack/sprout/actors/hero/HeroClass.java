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
import com.github.dachhack.sprout.actors.mobs.Lichen;
import com.github.dachhack.sprout.items.ActiveMrDestructo;
import com.github.dachhack.sprout.items.ActiveMrDestructo2;
import com.github.dachhack.sprout.items.AdamantArmor;
import com.github.dachhack.sprout.items.AdamantRing;
import com.github.dachhack.sprout.items.AdamantWand;
import com.github.dachhack.sprout.items.AdamantWeapon;
import com.github.dachhack.sprout.items.AncientCoin;
import com.github.dachhack.sprout.items.Bomb;
import com.github.dachhack.sprout.items.Bone;
import com.github.dachhack.sprout.items.BookOfDead;
import com.github.dachhack.sprout.items.BookOfLife;
import com.github.dachhack.sprout.items.BookOfTranscendence;
import com.github.dachhack.sprout.items.CavesKey;
import com.github.dachhack.sprout.items.CityKey;
import com.github.dachhack.sprout.items.ClusterBomb;
import com.github.dachhack.sprout.items.ConchShell;
import com.github.dachhack.sprout.items.DizzyBomb;
import com.github.dachhack.sprout.items.DwarfHammer;
import com.github.dachhack.sprout.items.HallsKey;
import com.github.dachhack.sprout.items.HolyHandGrenade;
import com.github.dachhack.sprout.items.OrbOfZot;
import com.github.dachhack.sprout.items.PrisonKey;
import com.github.dachhack.sprout.items.ReturnBeacon;
import com.github.dachhack.sprout.items.SanChikarah;
import com.github.dachhack.sprout.items.SeekingBombItem;
import com.github.dachhack.sprout.items.SeekingClusterBombItem;
import com.github.dachhack.sprout.items.SewersKey;
import com.github.dachhack.sprout.items.SmartBomb;
import com.github.dachhack.sprout.items.SteelHoneypot;
import com.github.dachhack.sprout.items.TenguKey;
import com.github.dachhack.sprout.items.TomeOfMastery;
import com.github.dachhack.sprout.items.armor.ClothArmor;
import com.github.dachhack.sprout.items.armor.PlateArmor;
import com.github.dachhack.sprout.items.artifacts.CloakOfShadows;
import com.github.dachhack.sprout.items.bags.AnkhChain;
import com.github.dachhack.sprout.items.bags.KeyRing;
import com.github.dachhack.sprout.items.bags.PotionBandolier;
import com.github.dachhack.sprout.items.bags.ScrollHolder;
import com.github.dachhack.sprout.items.bags.WandHolster;
import com.github.dachhack.sprout.items.food.BlueMilk;
import com.github.dachhack.sprout.items.food.Blueberry;
import com.github.dachhack.sprout.items.food.DeathCap;
import com.github.dachhack.sprout.items.food.Earthstar;
import com.github.dachhack.sprout.items.food.Food;
import com.github.dachhack.sprout.items.food.FullMoonberry;
import com.github.dachhack.sprout.items.food.GoldenJelly;
import com.github.dachhack.sprout.items.food.GoldenNut;
import com.github.dachhack.sprout.items.food.JackOLantern;
import com.github.dachhack.sprout.items.food.PixieParasol;
import com.github.dachhack.sprout.items.potions.PotionOfExperience;
import com.github.dachhack.sprout.items.potions.PotionOfFrost;
import com.github.dachhack.sprout.items.potions.PotionOfHealing;
import com.github.dachhack.sprout.items.potions.PotionOfInvisibility;
import com.github.dachhack.sprout.items.potions.PotionOfLevitation;
import com.github.dachhack.sprout.items.potions.PotionOfMindVision;
import com.github.dachhack.sprout.items.potions.PotionOfStrength;
import com.github.dachhack.sprout.items.quest.DarkGold;
import com.github.dachhack.sprout.items.rings.Ring;
import com.github.dachhack.sprout.items.rings.RingOfMagic;
import com.github.dachhack.sprout.items.scrolls.Scroll;
import com.github.dachhack.sprout.items.scrolls.ScrollOfIdentify;
import com.github.dachhack.sprout.items.scrolls.ScrollOfMagicMapping;
import com.github.dachhack.sprout.items.scrolls.ScrollOfMagicalInfusion;
import com.github.dachhack.sprout.items.scrolls.ScrollOfRemoveCurse;
import com.github.dachhack.sprout.items.scrolls.ScrollOfUpgrade;
import com.github.dachhack.sprout.items.wands.Wand;
import com.github.dachhack.sprout.items.wands.WandOfDisintegration;
import com.github.dachhack.sprout.items.wands.WandOfFirebolt;
import com.github.dachhack.sprout.items.wands.WandOfMagicMissile;
import com.github.dachhack.sprout.items.weapon.melee.Chainsaw;
import com.github.dachhack.sprout.items.weapon.melee.Dagger;
import com.github.dachhack.sprout.items.weapon.melee.Knuckles;
import com.github.dachhack.sprout.items.weapon.melee.ShortSword;
import com.github.dachhack.sprout.items.weapon.melee.WarHammer;
import com.github.dachhack.sprout.items.weapon.missiles.Boomerang;
import com.github.dachhack.sprout.items.weapon.missiles.Dart;
import com.github.dachhack.sprout.plants.BlandfruitBush;
import com.github.dachhack.sprout.plants.Flytrap;
import com.github.dachhack.sprout.plants.Phaseshift;
import com.github.dachhack.sprout.plants.Plant;
import com.github.dachhack.sprout.plants.Starflower;
import com.github.dachhack.sprout.utils.GLog;
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
			"Dew blessings are more effective for mages.",
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
		    	
		Dungeon.quickslot.setSlot(0, darts);
		
		KeyRing keyring = new KeyRing(); keyring.collect();

		new PotionOfStrength().setKnown();
		
		//playtest(hero);
	}

	private static void initMage(Hero hero) {
		(hero.belongings.weapon = new Knuckles()).identify();

		WandOfMagicMissile wand = new WandOfMagicMissile();
		wand.identify().collect();
		
		KeyRing keyring = new KeyRing(); keyring.collect();
		
		Dungeon.quickslot.setSlot(0, wand);

		new ScrollOfIdentify().setKnown();
		
		//playtest(hero);
	}

	private static void initRogue(Hero hero) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.misc1 = cloak).identify();
		hero.belongings.misc1.activate(hero);

		Dart darts = new Dart(10);
		darts.identify().collect();
		
		KeyRing keyring = new KeyRing(); keyring.collect();

		Dungeon.quickslot.setSlot(0, cloak);
		if (ShatteredPixelDungeon.quickSlots() > 1)
			Dungeon.quickslot.setSlot(1, darts);
		
		Bomb bomb = new Bomb(); bomb.collect();
		new ScrollOfMagicMapping().setKnown();
	}

	private static void initHuntress(Hero hero) {

		hero.HP = (hero.HT -= 5);

		(hero.belongings.weapon = new Dagger()).identify();
		Boomerang boomerang = new Boomerang();
		boomerang.identify().collect();
		
		KeyRing keyring = new KeyRing(); keyring.collect();

		Dungeon.quickslot.setSlot(0, boomerang);

		new PotionOfMindVision().setKnown();
	}

	public void playtest(Hero hero) {
		if (!Dungeon.playtest){
		//Playtest
		//TomeOfMastery tome = new TomeOfMastery(); tome.collect();
				
				hero.HT=hero.HP=999;
				hero.STR = hero.STR + 20;
				
				/*
				PotionBandolier bag1 = new PotionBandolier(); bag1.collect();
				ScrollHolder bag2 = new ScrollHolder(); bag2.collect();
				PlateArmor armor = new PlateArmor();
			    armor.identify().collect();
			    WarHammer hammer = new WarHammer();
			    hammer.identify().collect();
			    Wand wand = new WandOfDisintegration(); wand.upgrade(15); wand.collect();
				//Wand wand2 = new WandOfFirebolt(); wand2.collect();
				Ring ring = new RingOfMagic(); ring.upgrade(4); ring.collect();
				ConchShell conch = new ConchShell(); conch.collect();
				AncientCoin coin = new AncientCoin(); coin.collect();
				TenguKey key = new TenguKey(); key.collect();
				*/
				//Bone bone = new Bone(); bone.collect();
				//ConchShell conch = new ConchShell(); conch.collect();
				//AncientCoin coin = new AncientCoin(); coin.collect();
				//TenguKey key = new TenguKey(); key.collect();
			    //SanChikarah san = new SanChikarah(); san.collect();	
			    ReturnBeacon beacon = new ReturnBeacon(); beacon.collect();
				
				//PotionOfFrost pot = new PotionOfFrost(); pot.collect();
				//SteelHoneypot hpot = new SteelHoneypot(); hpot.collect();
				//Flytrap.Seed seed1 = new Flytrap.Seed(); seed1.collect();
				//Phaseshift.Seed seed2 = new Phaseshift.Seed(); seed2.collect();
				//Starflower.Seed seed3 = new Starflower.Seed(); seed3.collect();
				//BlandfruitBush.Seed seed4 = new BlandfruitBush.Seed(); seed4.collect();
				
				//Chainsaw saw = new Chainsaw(); saw.enchantBuzz(); saw.collect();
				//PotionBandolier bag1 = new PotionBandolier(); bag1.collect();
				//ScrollHolder bag2 = new ScrollHolder(); bag2.collect();
				//AnkhChain chain = new AnkhChain(); chain.collect();
				//WandHolster holster = new WandHolster(); holster.collect();
				
				//AdamantArmor aArmor = new AdamantArmor(); aArmor.collect();
				//AdamantWand aWand = new AdamantWand(); aWand.collect();
				//AdamantRing aRing = new AdamantRing(); aRing.collect();
				//AdamantWeapon aWeapon = new AdamantWeapon(); aWeapon.collect();
				
				Dungeon.playtest=true;
				GLog.i("Playtest Activated");
				
		
		/*
				for(int i=0; i<61; i++){
					Scroll scroll = new ScrollOfMagicalInfusion();
			        scroll.identify().collect();
			        Scroll scroll2 = new ScrollOfUpgrade();
			        scroll2.identify().collect();  
			        Scroll scroll3 = new ScrollOfIdentify();
			        scroll3.identify().collect();  
			        Scroll scroll4 = new ScrollOfRemoveCurse();
			        scroll4.identify().collect();  
			       }	
			       */
				
		}
		
				/*
							 			      
			      for(int i=1; i<61; i++){
				        PotionOfExperience potion1 = new PotionOfExperience(); potion1.collect();
				       PotionOfInvisibility potion2 = new PotionOfInvisibility(); potion2.collect();
				       PotionOfHealing potion3 = new PotionOfHealing(); potion3.collect();
				       PotionOfMindVision potion4 = new PotionOfMindVision(); potion4.collect();
				      PotionOfLevitation potion5 = new PotionOfLevitation(); potion5.collect();
				      Bomb bomb = new Bomb(); bomb.collect();
				      DarkGold darkgold = new DarkGold(); darkgold.collect();
				        }
				         
				
				       
				  Blueberry berry = new Blueberry(10); berry.collect();
				  ClusterBomb cbomb = new ClusterBomb(); cbomb.collect();
				  DizzyBomb dbomb = new DizzyBomb(); dbomb.collect();
				  SmartBomb smbomb = new SmartBomb(); smbomb.collect();
				  SeekingBombItem sbomb = new SeekingBombItem(); sbomb.collect();
				  SeekingClusterBombItem scbomb = new SeekingClusterBombItem(); scbomb.collect();
				  
				  
				//  Bomb bomb = new Bomb(); bomb.collect();
				//DeathCap mush1 = new DeathCap(); mush1.collect();
				//GoldenJelly mush2 = new GoldenJelly(); mush2.collect();
				//BlueMilk mush3 = new BlueMilk(); mush3.collect();
				//JackOLantern mush4 = new JackOLantern(); mush4.collect();
				//Earthstar mush5 = new Earthstar(); mush5.collect();
				//Lichen mush6 = new Lichen(); mush6.collect();
				//PixieParasol mush7 = new PixieParasol(); mush7.collect();
				
				//ActiveMrDestructo mrd = new ActiveMrDestructo(); mrd.collect();
				//OrbOfZot orb = new OrbOfZot(); orb.collect();
				        
				//Phaseshift.Seed seed = new Phaseshift.Seed(); seed.collect();
				//Phaseshift.Seed seed2 = new Phaseshift.Seed(); seed2.collect();
				//Starflower.Seed seed3 = new Starflower.Seed(); seed3.collect();
				
								
				//PotionOfLiquidFlame potion5 = new PotionOfLiquidFlame(); potion5.collect();
				//BookOfDead dbook = new BookOfDead(); dbook.collect();
				///BookOfLife lbook = new BookOfLife(); lbook.collect();
				//BookOfTranscendence tbook = new BookOfTranscendence(); tbook.collect();
				//SanChikarah san = new SanChikarah(); san.collect();	
				
		        //SewersKey key1 = new SewersKey(); key1.collect();
		        //PrisonKey key2 = new PrisonKey(); key2.collect();
		        //CavesKey key3 = new CavesKey(); key3.collect();
		        //CityKey key4 = new CityKey(); key4.collect();
		      // HallsKey key5 = new HallsKey(); key5.collect();
		        FullMoonberry berry2 = new FullMoonberry(10); berry2.collect();		
*/
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
