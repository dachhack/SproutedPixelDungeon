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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Bones;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.GamesInProgress;
import com.github.dachhack.sprout.ResultDescriptions;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Barkskin;
import com.github.dachhack.sprout.actors.buffs.Bleeding;
import com.github.dachhack.sprout.actors.buffs.Blindness;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Burning;
import com.github.dachhack.sprout.actors.buffs.Charm;
import com.github.dachhack.sprout.actors.buffs.Combo;
import com.github.dachhack.sprout.actors.buffs.Cripple;
import com.github.dachhack.sprout.actors.buffs.Dewcharge;
import com.github.dachhack.sprout.actors.buffs.Drowsy;
import com.github.dachhack.sprout.actors.buffs.Fury;
import com.github.dachhack.sprout.actors.buffs.Hunger;
import com.github.dachhack.sprout.actors.buffs.Invisibility;
import com.github.dachhack.sprout.actors.buffs.LichenDrop;
import com.github.dachhack.sprout.actors.buffs.Light;
import com.github.dachhack.sprout.actors.buffs.Ooze;
import com.github.dachhack.sprout.actors.buffs.Paralysis;
import com.github.dachhack.sprout.actors.buffs.Poison;
import com.github.dachhack.sprout.actors.buffs.Regeneration;
import com.github.dachhack.sprout.actors.buffs.Roots;
import com.github.dachhack.sprout.actors.buffs.SnipersMark;
import com.github.dachhack.sprout.actors.buffs.Strength;
import com.github.dachhack.sprout.actors.buffs.Vertigo;
import com.github.dachhack.sprout.actors.buffs.Weakness;
import com.github.dachhack.sprout.actors.mobs.Lichen;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.actors.mobs.npcs.NPC;
import com.github.dachhack.sprout.actors.mobs.pets.PET;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.CheckedCell;
import com.github.dachhack.sprout.effects.Flare;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.items.Amulet;
import com.github.dachhack.sprout.items.Ankh;
import com.github.dachhack.sprout.items.DewVial;
import com.github.dachhack.sprout.items.Dewdrop;
import com.github.dachhack.sprout.items.EasterEgg;
import com.github.dachhack.sprout.items.Egg;
import com.github.dachhack.sprout.items.Heap;
import com.github.dachhack.sprout.items.Heap.Type;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.KindOfWeapon;
import com.github.dachhack.sprout.items.OtilukesJournal;
import com.github.dachhack.sprout.items.ShadowDragonEgg;
import com.github.dachhack.sprout.items.armor.glyphs.Viscosity;
import com.github.dachhack.sprout.items.artifacts.CapeOfThorns;
import com.github.dachhack.sprout.items.artifacts.DriedRose;
import com.github.dachhack.sprout.items.artifacts.TalismanOfForesight;
import com.github.dachhack.sprout.items.artifacts.TimekeepersHourglass;
import com.github.dachhack.sprout.items.keys.GoldenKey;
import com.github.dachhack.sprout.items.keys.GoldenSkeletonKey;
import com.github.dachhack.sprout.items.keys.IronKey;
import com.github.dachhack.sprout.items.keys.Key;
import com.github.dachhack.sprout.items.keys.SkeletonKey;
import com.github.dachhack.sprout.items.misc.AutoPotion.AutoHealPotion;
import com.github.dachhack.sprout.items.potions.Potion;
import com.github.dachhack.sprout.items.potions.PotionOfHealing;
import com.github.dachhack.sprout.items.potions.PotionOfMight;
import com.github.dachhack.sprout.items.potions.PotionOfStrength;
import com.github.dachhack.sprout.items.quest.DarkGold;
import com.github.dachhack.sprout.items.rings.RingOfElements;
import com.github.dachhack.sprout.items.rings.RingOfEvasion;
import com.github.dachhack.sprout.items.rings.RingOfForce;
import com.github.dachhack.sprout.items.rings.RingOfFuror;
import com.github.dachhack.sprout.items.rings.RingOfHaste;
import com.github.dachhack.sprout.items.rings.RingOfMight;
import com.github.dachhack.sprout.items.rings.RingOfTenacity;
import com.github.dachhack.sprout.items.scrolls.Scroll;
import com.github.dachhack.sprout.items.scrolls.ScrollOfMagicMapping;
import com.github.dachhack.sprout.items.scrolls.ScrollOfMagicalInfusion;
import com.github.dachhack.sprout.items.scrolls.ScrollOfRecharging;
import com.github.dachhack.sprout.items.scrolls.ScrollOfUpgrade;
import com.github.dachhack.sprout.items.wands.Wand;
import com.github.dachhack.sprout.items.weapon.melee.MeleeWeapon;
import com.github.dachhack.sprout.items.weapon.melee.relic.RelicMeleeWeapon;
import com.github.dachhack.sprout.items.weapon.missiles.MissileWeapon;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.levels.features.AlchemyPot;
import com.github.dachhack.sprout.levels.features.Chasm;
import com.github.dachhack.sprout.levels.features.Sign;
import com.github.dachhack.sprout.plants.Earthroot;
import com.github.dachhack.sprout.plants.Sungrass;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.scenes.InterlevelScene;
import com.github.dachhack.sprout.scenes.SurfaceScene;
import com.github.dachhack.sprout.sprites.CharSprite;
import com.github.dachhack.sprout.sprites.HeroSprite;
import com.github.dachhack.sprout.ui.AttackIndicator;
import com.github.dachhack.sprout.ui.BuffIndicator;
import com.github.dachhack.sprout.ui.QuickSlotButton;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.windows.WndAscend;
import com.github.dachhack.sprout.windows.WndDescend;
import com.github.dachhack.sprout.windows.WndDewVial;
import com.github.dachhack.sprout.windows.WndMessage;
import com.github.dachhack.sprout.windows.WndResurrect;
import com.github.dachhack.sprout.windows.WndTradeItem;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Hero extends Char {

	private static final String TXT_LEAVE = "One does not simply leave Pixel Dungeon.";
	private static final String TXT_OVERFILL = "HP Overfilled by %s";

	private static final String TXT_LEVEL_UP = "level up!";
	private static final String TXT_NEW_LEVEL = "Welcome to level %d! Now you are healthier and more focused. "
			+ "It's easier for you to hit enemies and dodge their attacks.";

	public static final String TXT_YOU_NOW_HAVE = "You now have %s";

	private static final String TXT_SOMETHING_ELSE = "There is something else here";
	private static final String TXT_LOCKED_CHEST = "This chest is locked and you don't have matching key";
	private static final String TXT_LOCKED_DOOR = "You don't have a matching key";
	private static final String TXT_NOTICED_SMTH = "You noticed something";

	private static final String TXT_WAIT = "...";
	private static final String TXT_SEARCH = "search";

	public static final int STARTING_STR = 10;

	private static final float TIME_TO_REST = 1f;
	private static final float TIME_TO_SEARCH = 2f;

	public HeroClass heroClass = HeroClass.ROGUE;
	public HeroSubClass subClass = HeroSubClass.NONE;

	private int attackSkill = 10;
	private int defenseSkill = 5;

	public boolean ready = false;
	
	public boolean haspet = false;
	public boolean petfollow = false;
	public int petType = 0;
	public int petLevel = 0;
	public int petKills = 0;
	public int petHP = 0;
	public int petExperience = 0;
	public int petCooldown = 0;
	
	public int petCount = 0;
	
	private boolean damageInterrupt = true;
	public HeroAction curAction = null;
	public HeroAction lastAction = null;

	private Char enemy;

	private Item theKey;
	private Item theSkeletonKey;

	public boolean restoreHealth = false;

	public MissileWeapon rangedWeapon = null;
	public Belongings belongings;

	public int STR;
	public boolean weakened = false;

	public float awareness;

	public int lvl = 1;
	public int exp = 0;

	private ArrayList<Mob> visibleEnemies;

	public Hero() {
		super();
		name = "you";

		HP = HT = 20;
		STR = STARTING_STR;
		awareness = 0.1f;

		belongings = new Belongings(this);

		visibleEnemies = new ArrayList<Mob>();
	}

	public int STR() {
		int STR = this.STR;

		for (Buff buff : buffs(RingOfMight.Might.class)) {
			STR += ((RingOfMight.Might) buff).level;
		}

		return weakened ? STR - 2 : STR;
	}

	private static final String ATTACK = "attackSkill";
	private static final String DEFENSE = "defenseSkill";
	private static final String STRENGTH = "STR";
	private static final String LEVEL = "lvl";
	private static final String EXPERIENCE = "exp";
	private static final String HASPET = "haspet";
	private static final String PETFOLLOW = "petfollow";
	private static final String PETTYPE = "petType";
	private static final String PETLEVEL = "petLevel";
	private static final String PETKILLS = "petKills";
	private static final String PETHP = "petHP";
	private static final String PETEXP = "petExperience";
	private static final String PETCOOLDOWN = "petCooldown";
	private static final String PETCOUNT = "petCount";

	@Override
	public void storeInBundle(Bundle bundle) {

		super.storeInBundle(bundle);

		heroClass.storeInBundle(bundle);
		subClass.storeInBundle(bundle);

		bundle.put(ATTACK, attackSkill);
		bundle.put(DEFENSE, defenseSkill);

		bundle.put(STRENGTH, STR);

		bundle.put(LEVEL, lvl);
		bundle.put(EXPERIENCE, exp);
		bundle.put(HASPET, haspet);
		bundle.put(PETFOLLOW, petfollow);
		bundle.put(PETTYPE, petType);
		bundle.put(PETLEVEL, petLevel);
		bundle.put(PETKILLS, petKills);
		bundle.put(PETHP, petHP);
		bundle.put(PETEXP, petExperience);
		bundle.put(PETCOOLDOWN, petCooldown);
		bundle.put(PETCOUNT, petCount);

		belongings.storeInBundle(bundle);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);

		heroClass = HeroClass.restoreInBundle(bundle);
		subClass = HeroSubClass.restoreInBundle(bundle);

		attackSkill = bundle.getInt(ATTACK);
		defenseSkill = bundle.getInt(DEFENSE);

		STR = bundle.getInt(STRENGTH);
		updateAwareness();

		lvl = bundle.getInt(LEVEL);
		exp = bundle.getInt(EXPERIENCE);
		haspet = bundle.getBoolean(HASPET);
		petfollow = bundle.getBoolean(PETFOLLOW);
		petType = bundle.getInt(PETTYPE);
		petLevel = bundle.getInt(PETLEVEL);
		petKills = bundle.getInt(PETKILLS);
		petHP = bundle.getInt(PETHP);
		petExperience = bundle.getInt(PETEXP);
		petCooldown = bundle.getInt(PETCOOLDOWN);
		petCount = bundle.getInt(PETCOUNT);
		
		belongings.restoreFromBundle(bundle);
	}

	public static void preview(GamesInProgress.Info info, Bundle bundle) {
		info.level = bundle.getInt(LEVEL);
	}

	public String className() {
		return subClass == null || subClass == HeroSubClass.NONE ? heroClass
				.title() : subClass.title();
	}

	public String givenName() {
		return name.equals("you") ? className() : name;
	}

	public void live() {
		Buff.affect(this, Regeneration.class);
		Buff.affect(this, Hunger.class);
	}

	public int tier() {
		return belongings.armor == null ? 0 : belongings.armor.tier;
	}

	public boolean shoot(Char enemy, MissileWeapon wep) {

		rangedWeapon = wep;
		boolean result = attack(enemy);
		Invisibility.dispel();
		rangedWeapon = null;

		return result;
	}

	@Override
	public int attackSkill(Char target) {
		float accuracy = 1;
		if (rangedWeapon != null && Level.distance(pos, target.pos) == 1) {
			accuracy *= 0.5f;
		}

		KindOfWeapon wep = rangedWeapon != null ? rangedWeapon
				: belongings.weapon;
		if (wep != null) {
			return (int) (attackSkill * accuracy * wep.acuracyFactor(this));
		} else {
			return (int) (attackSkill * accuracy);
		}
	}

	@Override
	public int defenseSkill(Char enemy) {

		int bonus = 0;
		for (Buff buff : buffs(RingOfEvasion.Evasion.class)) {
			bonus += ((RingOfEvasion.Evasion) buff).effectiveLevel;
		}

		float evasion = (float) Math.pow(1.15, bonus);
		if (paralysed) {
			evasion /= 2;
		}

		int aEnc = belongings.armor != null ? belongings.armor.STR - STR()
				: 9 - STR();

		if (aEnc > 0) {
			return (int) (defenseSkill * evasion / Math.pow(1.5, aEnc));
		} else {

			if (heroClass == HeroClass.ROGUE) {
				return (int) ((defenseSkill - aEnc) * evasion);
			} else {
				return (int) (defenseSkill * evasion);
			}
		}
	}

	@Override
	public int dr() {
		int dr = belongings.armor != null ? Math.max(belongings.armor.DR, 0)
				: 0;
		Barkskin barkskin = buff(Barkskin.class);
		if (barkskin != null) {
			dr += barkskin.level();
		}
		return dr;
	}

	@Override
	public int damageRoll() {
		KindOfWeapon wep = rangedWeapon != null ? rangedWeapon
				: belongings.weapon;
		int dmg;
		int bonus = 0;
		for (Buff buff : buffs(RingOfForce.Force.class)) {
			bonus += ((RingOfForce.Force) buff).level;
		}

		if (wep != null) {
			dmg = wep.damageRoll(this) + bonus;
		} else {
			int str = STR() - 8;
			dmg = bonus == 0 ? str > 1 ? Random.NormalIntRange(1, str) : 1
					: bonus > 0 ? str > 0 ? Random.NormalIntRange(str / 2
							+ bonus, (int) (str * 0.5f * bonus) + str * 2) : 1
							: 0;
		}
		if (dmg < 0)
			dmg = 0;
		
		if (buff(Fury.class) != null){ dmg *= 1.5f; }
		
		if (buff(Strength.class) != null){ dmg *= 4f; Buff.detach(this, Strength.class);}
		
		return (int) dmg;
		
	}
	

	@Override
	public float speed() {

		float speed = super.speed();

		int hasteLevel = 0;
		for (Buff buff : buffs(RingOfHaste.Haste.class)) {
			hasteLevel += ((RingOfHaste.Haste) buff).level;
		}

		if(haspet){
		  int pethaste=Dungeon.petHasteLevel;	
		  PET heropet = checkpet();	
		  
		   if(pethaste>0 && hasteLevel>10 && heropet!=null){
			 hasteLevel=10;
		   }
		   
		}
		
		
		
		if (hasteLevel != 0)
			speed *= Math.pow(1.2, hasteLevel);

		int aEnc = belongings.armor != null ? belongings.armor.STR - STR() : 0;
		if (aEnc > 0) {

			return (float) (speed * Math.pow(1.3, -aEnc));

		} else {

			return ((HeroSprite) sprite)
					.sprint(subClass == HeroSubClass.FREERUNNER
							&& !isStarving()) ? invisible > 0 ? 4f * speed
					: 1.5f * speed : speed;

		}
	}

	public float attackDelay() {
		KindOfWeapon wep = rangedWeapon != null ? rangedWeapon
				: belongings.weapon;
		if (wep != null) {

			return wep.speedFactor(this);

		} else {
			// Normally putting furor speed on unarmed attacks would be
			// unnecessary
			// But there's going to be that one guy who gets a furor+force ring
			// combo
			// This is for that one guy, you shall get your fists of fury!
			int bonus = 0;
			for (Buff buff : buffs(RingOfFuror.Furor.class)) {
				bonus += ((RingOfFuror.Furor) buff).level;
			}
			return (float) (0.25 + (1 - 0.25) * Math.pow(0.8, bonus));
		}
	}

	@Override
	public void spend(float time) {
		TimekeepersHourglass.timeFreeze buff = buff(TimekeepersHourglass.timeFreeze.class);
		if (!(buff != null && buff.processTime(time)))
			super.spend(time);
	};

	public void spendAndNext(float time) {
		busy();
		spend(time);
		next();
	}

	@Override
	public boolean act() {

		super.act();		
		
		Statistics.moves++;
		
		if(Dungeon.dewDraw){Dungeon.level.currentmoves++;}

		if (paralysed) {

			curAction = null;

			spendAndNext(TICK);
			return false;
		}
	
		Egg egg = belongings.getItem(Egg.class);
		if (egg!=null){
			egg.moves++;
		}
		
		EasterEgg egg2 = belongings.getItem(EasterEgg.class);
		if (egg2!=null){
			egg2.moves++;
		}
		
		ShadowDragonEgg egg3 = belongings.getItem(ShadowDragonEgg.class);
		if (egg3!=null){
			egg3.moves++;
		}
		
		OtilukesJournal journal = belongings.getItem(OtilukesJournal.class);
		if (journal!=null && (Dungeon.depth < 26 || Dungeon.depth==55) 
				&& (journal.level>1 || journal.rooms[0]) 
				&& journal.charge<journal.fullCharge){
			journal.charge++;
			if (journal.charge>=journal.fullCharge){
				GLog.p("Otilike's Journal is fully charged!");
			}
		}
		
		/*
		Heap heap = Dungeon.level.heaps.get(pos);
		if (heap != null){
			heap.dewcollect();
		}
		*/

		checkVisibleMobs();

		if (curAction == null) {

			if (restoreHealth) {
				if (isStarving() || HP >= HT) {
					restoreHealth = false;
				} else {
					spend(TIME_TO_REST);
					next();
					return false;
				}
			}

			ready();
			return false;

		} else {

			restoreHealth = false;

			ready = false;
			
			if (curAction instanceof HeroAction.Move) {

				return actMove((HeroAction.Move) curAction);

			} else if (curAction instanceof HeroAction.Interact) {

				return actInteract((HeroAction.Interact) curAction);
				
			} else if (curAction instanceof HeroAction.InteractPet) {

				return actInteractPet((HeroAction.InteractPet) curAction);

			} else if (curAction instanceof HeroAction.Buy) {

				return actBuy((HeroAction.Buy) curAction);

			} else if (curAction instanceof HeroAction.PickUp) {

				return actPickUp((HeroAction.PickUp) curAction);

			} else if (curAction instanceof HeroAction.OpenChest) {

				return actOpenChest((HeroAction.OpenChest) curAction);

			} else if (curAction instanceof HeroAction.Unlock) {

				return actUnlock((HeroAction.Unlock) curAction);

			} else if (curAction instanceof HeroAction.Descend) {

				return actDescend((HeroAction.Descend) curAction);

			} else if (curAction instanceof HeroAction.Ascend) {

				return actAscend((HeroAction.Ascend) curAction);

			} else if (curAction instanceof HeroAction.Attack) {

				return actAttack((HeroAction.Attack) curAction);

			} else if (curAction instanceof HeroAction.Cook) {

				return actCook((HeroAction.Cook) curAction);

			}
		}
		
		return false;
	}

	public void busy() {
		ready = false;
	}

	private void ready() {
		sprite.idle();
		curAction = null;
		damageInterrupt = true;
		ready = true;

		AttackIndicator.updateState();

		GameScene.ready();
	}

	public void interrupt() {
		if (isAlive() && curAction != null
				&& curAction instanceof HeroAction.Move && curAction.dst != pos) {
			lastAction = curAction;
		}
		curAction = null;
	}

	public void resume() {
		curAction = lastAction;
		lastAction = null;
		damageInterrupt = false;
		act();
	}

	

	
	private boolean actMove(HeroAction.Move action) {

		if (getCloser(action.dst)) {

			return true;

		} else {
			if (Dungeon.level.map[pos] == Terrain.SIGN && pos != Dungeon.level.pitSign) {
				Sign.read(pos);
			} else if (Dungeon.level.map[pos] == Terrain.SIGN && pos == Dungeon.level.pitSign){
				Sign.readPit(pos);
			}
			ready();

			return false;
		}
	}
	
	private boolean actInteract(HeroAction.Interact action) {

		NPC npc = action.npc;

		if (Level.adjacent(pos, npc.pos)) {

			ready();
			sprite.turnTo(pos, npc.pos);
			npc.interact();
			return false;

		} else {

			if (Level.fieldOfView[npc.pos] && getCloser(npc.pos)) {

				return true;

			} else {
				ready();
				return false;
			}

		}
	}

	private boolean actInteractPet(HeroAction.InteractPet action) {

		PET pet = action.pet;

		if (Level.adjacent(pos, pet.pos)) {

			ready();
			sprite.turnTo(pos, pet.pos);
			pet.interact();
			return false;

		} else {

			if (Level.fieldOfView[pet.pos] && getCloser(pet.pos)) {

				return true;

			} else {
				ready();
				return false;
			}

		}
	}
	
	private boolean actBuy(HeroAction.Buy action) {
		int dst = action.dst;
		if (pos == dst || Level.adjacent(pos, dst)) {

			ready();

			Heap heap = Dungeon.level.heaps.get(dst);
			if (heap != null && heap.type == Type.FOR_SALE && heap.size() == 1) {
				GameScene.show(new WndTradeItem(heap, true));
			}

			return false;

		} else if (getCloser(dst)) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actCook(HeroAction.Cook action) {
		int dst = action.dst;
		if (Dungeon.visible[dst]) {

			ready();
			AlchemyPot.operate(this, dst);
			return false;

		} else if (getCloser(dst)) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actPickUp(HeroAction.PickUp action) {
		int dst = action.dst;
		if (pos == dst) {

			Heap heap = Dungeon.level.heaps.get(pos);
			if (heap != null) {
				Item item = heap.pickUp();
				if (item.doPickUp(this)) {

					if (item instanceof Dewdrop
							|| item instanceof TimekeepersHourglass.sandBag
							|| item instanceof DriedRose.Petal) {
						// Do Nothing
					} else {

						boolean important = ((item instanceof ScrollOfUpgrade || item instanceof ScrollOfMagicalInfusion) && ((Scroll) item)
								.isKnown())
								|| ((item instanceof PotionOfStrength || item instanceof PotionOfMight) && ((Potion) item)
										.isKnown());
						if (important) {
							GLog.p(TXT_YOU_NOW_HAVE, item.name());
						} else {
							GLog.i(TXT_YOU_NOW_HAVE, item.name());
						}

						// Alright, if anyone complains about not knowing the
						// vial doesn't revive
						// after this... I'm done, I'm just done.
						if (item instanceof DewVial) {
							GLog.w("Its revival power seems to have faded.");
							GameScene.show(new WndDewVial(item));
						}
					}

					if (!heap.isEmpty()) {
						GLog.i(TXT_SOMETHING_ELSE);
					}
					curAction = null;
				} else {
					Dungeon.level.drop(item, pos).sprite.drop();
					ready();
				}
			} else {
				ready();
			}

			return false;

		} else if (getCloser(dst)) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actOpenChest(HeroAction.OpenChest action) {
		int dst = action.dst;
		if (Level.adjacent(pos, dst) || pos == dst) {

			Heap heap = Dungeon.level.heaps.get(dst);
			if (heap != null
					&& (heap.type != Type.HEAP && heap.type != Type.FOR_SALE)) {

				theKey = null;
				theSkeletonKey = null;

				if (heap.type == Type.LOCKED_CHEST
						|| heap.type == Type.CRYSTAL_CHEST 
						//|| heap.type == Type.MONSTERBOX
						) {

					theKey = belongings.getKey(GoldenKey.class, Dungeon.depth);
					theSkeletonKey = belongings.getKey(GoldenSkeletonKey.class, 0);

					if (theKey == null && theSkeletonKey == null) {
						GLog.w(TXT_LOCKED_CHEST);
						ready();
						return false;
					}
				}

				switch (heap.type) {
				case TOMB:
					Sample.INSTANCE.play(Assets.SND_TOMB);
					Camera.main.shake(1, 0.5f);
					break;
				case SKELETON:
				case REMAINS:
					break;
				default:
					Sample.INSTANCE.play(Assets.SND_UNLOCK);
				}

				spend(Key.TIME_TO_UNLOCK);
				sprite.operate(dst);

			} else {
				ready();
			}

			return false;

		} else if (getCloser(dst)) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actUnlock(HeroAction.Unlock action) {
		int doorCell = action.dst;
		if (Level.adjacent(pos, doorCell)) {

			theKey = null;
			int door = Dungeon.level.map[doorCell];

			if (door == Terrain.LOCKED_DOOR) {

				theKey = belongings.getKey(IronKey.class, Dungeon.depth);

			} else if (door == Terrain.LOCKED_EXIT) {

				theKey = belongings.getKey(SkeletonKey.class, Dungeon.depth);

			}

			if (theKey != null) {

				spend(Key.TIME_TO_UNLOCK);
				sprite.operate(doorCell);

				Sample.INSTANCE.play(Assets.SND_UNLOCK);

			} else {
				GLog.w(TXT_LOCKED_DOOR);
				ready();
			}

			return false;

		} else if (getCloser(doorCell)) {

			return true;

		} else {
			ready();
			return false;
		}
	}
	
	private PET checkpet(){
		for (Mob mob : Dungeon.level.mobs) {
			if(mob instanceof PET) {
				return (PET) mob;
			}
		}	
		return null;
	}
	
	private boolean checkpetNear(){
		for (int n : Level.NEIGHBOURS8) {
			int c = pos + n;
			if (Actor.findChar(c) instanceof PET) {
				return true;
			}
		}
		return false;
	}

	private boolean actDescend(HeroAction.Descend action) {
		int stairs = action.dst;
		
		if (!Dungeon.level.forcedone && 
			 Dungeon.dewDraw && (					 
					 Dungeon.level.checkdew()>0 
				     || Dungeon.hero.buff(Dewcharge.class) != null)
				    ) {
			
			GameScene.show(new WndDescend());
			ready();
			return false;
		}
		
		if (!Dungeon.level.forcedone && 
				 Dungeon.dewDraw && 
				 !Dungeon.level.cleared &&
				 !Dungeon.notClearableLevel(Dungeon.depth)
				 ) {
				
				GameScene.show(new WndDescend());
				ready();
				return false;
			}
		
		
		if (pos == stairs && pos == Dungeon.level.exit && !Dungeon.level.sealedlevel){

			curAction = null;
			
			if(Dungeon.dewDraw){
			 for (int i = 0; i < Level.LENGTH; i++) {
				Heap heap = Dungeon.level.heaps.get(i);
				if (heap != null)
					heap.dryup();
			  }	
			 
			 if (!Dungeon.level.cleared && Dungeon.dewDraw && !Dungeon.notClearableLevel(Dungeon.depth) ){
				 Dungeon.level.cleared=true;
				 Statistics.prevfloormoves=0;
			 }
			 
			}	
				
			
			PET pet = checkpet();
			if(pet!=null && checkpetNear()){
			  Dungeon.hero.petType=pet.type;
			  Dungeon.hero.petLevel=pet.level;
			  Dungeon.hero.petKills=pet.kills;	
			  Dungeon.hero.petHP=pet.HP;
			  Dungeon.hero.petExperience=pet.experience;
			  Dungeon.hero.petCooldown=pet.cooldown;
			  pet.destroy();
			  petfollow=true;
			} else if (Dungeon.hero.haspet && Dungeon.hero.petfollow) {
				petfollow=true;
			} else {
				petfollow=false;
			}
						
			Buff buff = buff(TimekeepersHourglass.timeFreeze.class);
			if (buff != null) buff.detach();

			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0]))
				if (mob instanceof DriedRose.GhostHero)
					mob.destroy();

			InterlevelScene.mode = InterlevelScene.Mode.DESCEND;
			Game.switchScene(InterlevelScene.class);

			return false;
			
		} else if (getCloser(stairs)) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actAscend(HeroAction.Ascend action) {
		int stairs = action.dst;
		if (pos == stairs && pos == Dungeon.level.entrance) {
			
			if (Dungeon.depth == 1) {

				if (belongings.getItem(Amulet.class) == null) {
					GameScene.show(new WndMessage(TXT_LEAVE));
					ready();
							
				} else if (Dungeon.level.forcedone){
					Dungeon.win(ResultDescriptions.WIN);
					Dungeon.deleteGame(Dungeon.hero.heroClass, true);
					Game.switchScene(SurfaceScene.class);
				} else {
					GameScene.show(new WndAscend());
					ready();
				}
				
			} else if (Dungeon.depth == 34) {
				curAction = null;

				Hunger hunger = buff(Hunger.class);
				if (hunger != null && !hunger.isStarving()) {
					hunger.satisfy(-Hunger.STARVING / 10);
				}
				
				PET pet = checkpet();
				if(pet!=null && checkpetNear()){
				  Dungeon.hero.petType=pet.type;
				  Dungeon.hero.petLevel=pet.level;
				  Dungeon.hero.petKills=pet.kills;	
				  Dungeon.hero.petHP=pet.HP;
				  Dungeon.hero.petExperience=pet.experience;
				  Dungeon.hero.petCooldown=pet.cooldown;
				  pet.destroy();
				  petfollow=true;
				} else if (Dungeon.hero.haspet && Dungeon.hero.petfollow) {
					petfollow=true;
				} else {
					petfollow=false;
				}
				
				Buff buff = buff(TimekeepersHourglass.timeFreeze.class);
				if (buff != null)
					buff.detach();

				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0]))
					if (mob instanceof DriedRose.GhostHero)
						mob.destroy();
                
				InterlevelScene.mode = InterlevelScene.Mode.ASCEND;
				Game.switchScene(InterlevelScene.class);
				
			
		    } else if (Dungeon.depth == 41) {
			curAction = null;

			Hunger hunger = buff(Hunger.class);
			if (hunger != null && !hunger.isStarving()) {
				hunger.satisfy(-Hunger.STARVING / 10);
			}

			PET pet = checkpet();
			if(pet!=null && checkpetNear()){
			  Dungeon.hero.petType=pet.type;
			  Dungeon.hero.petLevel=pet.level;
			  Dungeon.hero.petKills=pet.kills;	
			  Dungeon.hero.petHP=pet.HP;
			  Dungeon.hero.petExperience=pet.experience;
			  Dungeon.hero.petCooldown=pet.cooldown;
			  pet.destroy();
			  petfollow=true;
			} else if (Dungeon.hero.haspet && Dungeon.hero.petfollow) {
				petfollow=true;
			} else {
				petfollow=false;
			}
			
			Buff buff = buff(TimekeepersHourglass.timeFreeze.class);
			if (buff != null)
				buff.detach();

			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0]))
				if (mob instanceof DriedRose.GhostHero)
					mob.destroy();
            
			InterlevelScene.mode = InterlevelScene.Mode.ASCEND;
			Game.switchScene(InterlevelScene.class);
			
		   } else if (Dungeon.depth > 26 && !Dungeon.townCheck(Dungeon.depth)){
				ready();
			} else if (Dungeon.depth == 25 || Dungeon.depth == 55 || Dungeon.depth == 99){
				ready();
			} else if (Dungeon.depth > 55 && Dungeon.level.locked){
				ready();
			} else {

				curAction = null;

				Hunger hunger = buff(Hunger.class);
				if (hunger != null && !hunger.isStarving()) {
					hunger.satisfy(-Hunger.STARVING / 10);
				}

				PET pet = checkpet();
				if(pet!=null && checkpetNear()){
				  Dungeon.hero.petType=pet.type;
				  Dungeon.hero.petLevel=pet.level;
				  Dungeon.hero.petKills=pet.kills;	
				  Dungeon.hero.petHP=pet.HP;
				  Dungeon.hero.petExperience=pet.experience;
				  Dungeon.hero.petCooldown=pet.cooldown;
				  pet.destroy();
				  petfollow=true;
				} else if (Dungeon.hero.haspet && Dungeon.hero.petfollow) {
					petfollow=true;
				} else {
					petfollow=false;
				}
				
				Buff buff = buff(TimekeepersHourglass.timeFreeze.class);
				if (buff != null)
					buff.detach();

				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0]))
					if (mob instanceof DriedRose.GhostHero)
						mob.destroy();
                
				InterlevelScene.mode = InterlevelScene.Mode.ASCEND;
				Game.switchScene(InterlevelScene.class);
			}

			return false;

		} else if (getCloser(stairs)) {

			return true;

		} else {
			ready();
			return false;
		}
	}

	private boolean actAttack(HeroAction.Attack action) {

		enemy = action.target;

		if (Level.adjacent(pos, enemy.pos) && enemy.isAlive()
				&& !isCharmedBy(enemy)) {

			spend(attackDelay());
			sprite.attack(enemy.pos);

			return false;

		} else {

			if (Level.fieldOfView[enemy.pos] && getCloser(enemy.pos)) {

				return true;

			} else {
				ready();
				return false;
			}

		}
	}

	public void rest(boolean tillHealthy) {
		//search(true);
		spendAndNext(TIME_TO_REST);
		if (!tillHealthy) {
			sprite.showStatus(CharSprite.DEFAULT, TXT_WAIT);
		}
		restoreHealth = tillHealthy;
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		KindOfWeapon wep = rangedWeapon != null ? rangedWeapon
				: belongings.weapon;

		if (wep != null)
			wep.proc(this, enemy, damage);

		switch (subClass) {
		case GLADIATOR:
			if (wep instanceof MeleeWeapon || wep == null) {
				damage += Buff.affect(this, Combo.class).hit(enemy, damage);
			}
			break;
		case BATTLEMAGE:
			if (wep instanceof Wand) {
				Wand wand = (Wand) wep;
				if (wand.curCharges < wand.maxCharges && damage > 0) {

					wand.curCharges++;
					if (Dungeon.quickslot.contains(wand)) {
						QuickSlotButton.refresh();
					}

					ScrollOfRecharging.charge(this);
				}
				damage += wand.curCharges;
			}
			break;
		case SNIPER:
			if (rangedWeapon != null) {
				Buff.prolong(this, SnipersMark.class, attackDelay() * 1.1f).object = enemy
						.id();
			}
			break;
		default:
		}

		return damage;
	}

	@Override
	public int defenseProc(Char enemy, int damage) {

		CapeOfThorns.Thorns thorns = buff(CapeOfThorns.Thorns.class);
		if (thorns != null) {
			damage = thorns.proc(damage, enemy);
		}

		Earthroot.Armor armor = buff(Earthroot.Armor.class);
		if (armor != null) {
			damage = armor.absorb(damage);
		}

		Sungrass.Health health = buff(Sungrass.Health.class);
		if (health != null) {
			health.absorb(damage);
		}

		if (belongings.armor != null) {
			damage = belongings.armor.proc(enemy, this, damage);
		}

		return damage;
	}

	@Override
	public void damage(int dmg, Object src) {
		if (buff(TimekeepersHourglass.timeStasis.class) != null)
			return;

		restoreHealth = false;

		if (!(src instanceof Hunger || src instanceof Viscosity.DeferedDamage)
				&& damageInterrupt)
			interrupt();

		if (this.buff(Drowsy.class) != null) {
			Buff.detach(this, Drowsy.class);
			GLog.w("The pain helps you resist the urge to sleep.");
		}

		int tenacity = 0;
		for (Buff buff : buffs(RingOfTenacity.Tenacity.class)) {
			tenacity += ((RingOfTenacity.Tenacity) buff).level;
		}
		if (tenacity != 0) // (HT - HP)/HT = heroes current % missing health.
			dmg = (int) Math.ceil(dmg
					* Math.pow(0.9, tenacity * ((float) (HT - HP) / HT)));

		super.damage(dmg, src);

		if (subClass == HeroSubClass.BERSERKER && 0 < HP
				&& HP <= HT * Fury.LEVEL) {
			Buff.affect(this, Fury.class);
		}
		
		if (this.buff(AutoHealPotion.class) != null && ((float) HP / HT)<.1) {
			PotionOfHealing pot = Dungeon.hero.belongings.getItem(PotionOfHealing.class);
			if (pot != null) {
				pot.detach(Dungeon.hero.belongings.backpack,1);	
				/*
				if(!(Dungeon.hero.belongings.getItem(PotionOfHealing.class).quantity() > 0)){
					pot.detachAll(Dungeon.hero.belongings.backpack);
				}
				*/
				GLog.w("AutoPotion Triggered!");
				pot.apply(this);				
			}	
			else if (pot==null){
				GLog.w("AutoPotion triggered but you are not carrying any potions of healing!");
			}
			
		}
		
	}

	private void checkVisibleMobs() {
		ArrayList<Mob> visible = new ArrayList<Mob>();

		boolean newMob = false;
		Mob closest = null;

		for (Mob m : Dungeon.level.mobs) {
			if (Level.fieldOfView[m.pos] && m.hostile) {
				visible.add(m);
				if (!visibleEnemies.contains(m)) {
					newMob = true;
				}
				if (closest == null){
									closest = m;
								} else if (distance(closest) > distance(m)) {
									closest = m;
								}
			}
		}
			
			if (closest != null && (QuickSlotButton.lastTarget == null ||
												!QuickSlotButton.lastTarget.isAlive() ||
												!Dungeon.visible[QuickSlotButton.lastTarget.pos])){
								QuickSlotButton.target(closest);
		}

		if (newMob) {
			interrupt();
			restoreHealth = false;
		}

		visibleEnemies = visible;
	}

	public int visibleEnemies() {
		return visibleEnemies.size();
	}

	public Mob visibleEnemy(int index) {
		return visibleEnemies.get(index % visibleEnemies.size());
	}

	private boolean getCloser(final int target) {

		if (rooted) {
			Camera.main.shake(1, 1f);
			return false;
		}

		int step = -1;

		if (Level.adjacent(pos, target)) {

			if (Actor.findChar(target) == null) {
				if (Level.pit[target] && !flying && !Chasm.jumpConfirmed) {
					if (!Level.solid[target]) {
						Chasm.heroJump(this);
						interrupt();
					}
					return false;
				}
				if (Level.passable[target] || Level.avoid[target]) {
					step = target;
				}
			}

		} else {

			int len = Level.getLength();
			boolean[] p = Level.passable;
			boolean[] v = Dungeon.level.visited;
			boolean[] m = Dungeon.level.mapped;
			boolean[] passable = new boolean[len];
			for (int i = 0; i < len; i++) {
				passable[i] = p[i] && (v[i] || m[i]);
			}

			step = Dungeon.findPath(this, pos, target, passable,
					Level.fieldOfView);
		}

		if (step != -1) {

			int oldPos = pos;
			move(step);
			sprite.move(oldPos, pos);
			spend(1 / speed());

			return true;

		} else {

			return false;

		}

	}

	public boolean handle(int cell) {

		if (cell == -1) {
			return false;
		}

		Char ch;
		Heap heap;

		if (Dungeon.level.map[cell] == Terrain.ALCHEMY && cell != pos) {

			curAction = new HeroAction.Cook(cell);

		} else if (Level.fieldOfView[cell]
				&& (ch = Actor.findChar(cell)) instanceof Mob) {

			if (ch instanceof NPC) {
				curAction = new HeroAction.Interact((NPC) ch);
			} else if (ch instanceof PET) {
					curAction = new HeroAction.InteractPet((PET) ch);
			} else {
				curAction = new HeroAction.Attack(ch);
			}

		} else if ((heap = Dungeon.level.heaps.get(cell)) != null) {

			switch (heap.type) {
			case HEAP:
				curAction = new HeroAction.PickUp(cell);
				break;
			case FOR_SALE:
				curAction = heap.size() == 1 && heap.peek().price() > 0 ? new HeroAction.Buy(
						cell) : new HeroAction.PickUp(cell);
				break;
			default:
				curAction = new HeroAction.OpenChest(cell);
			}

		} else if (Dungeon.level.map[cell] == Terrain.LOCKED_DOOR
				|| Dungeon.level.map[cell] == Terrain.LOCKED_EXIT) {

			curAction = new HeroAction.Unlock(cell);

		} else if (cell == Dungeon.level.exit && (Dungeon.depth < 26 || Dungeon.townCheck(Dungeon.depth))) {

			curAction = new HeroAction.Descend(cell);

		} else if (cell == Dungeon.level.entrance) {

			curAction = new HeroAction.Ascend(cell);

		} else {

			curAction = new HeroAction.Move(cell);
			lastAction = null;

		}

		return act();
	}

	public void earnExp(int exp) {

		this.exp += exp;

		boolean levelUp = false;
		while (this.exp >= maxExp()) {
			this.exp -= maxExp();
			lvl++;

			HT += 5;
			HP += 5;
			attackSkill++;
			defenseSkill++;

			if (lvl < 10) {
				updateAwareness();
			}

			levelUp = true;
		}

		if (levelUp) {

			GLog.p(TXT_NEW_LEVEL, lvl);
			sprite.showStatus(CharSprite.POSITIVE, TXT_LEVEL_UP);
			Sample.INSTANCE.play(Assets.SND_LEVELUP);

			Badges.validateLevelReached();

			int value = HT - HP;
			if (value > 0) {
				HP += value;
				sprite.emitter().burst(Speck.factory(Speck.HEALING), 1);
			}
			
			if (subClass == HeroSubClass.WARLOCK) {

				int value2 = lvl;
				if (value2 > 0) {
					HP = HT+value2;
					sprite.emitter().burst(Speck.factory(Speck.HEALING), 1);
					
					GLog.w(TXT_OVERFILL, lvl);
				}

				
			}

			buff(Hunger.class).satisfy(10);
		}

		if (subClass == HeroSubClass.WARLOCK) {

			int value = Math.min(HT - HP, 1 + (Dungeon.depth - 1) / 5);
			if (value > 0) {
				HP += value;
				sprite.emitter().burst(Speck.factory(Speck.HEALING), 1);
			}

			buff(Hunger.class).satisfy(100);
		}
	}

	public int maxExp() {
		return 5 + lvl * 5;
	}

	void updateAwareness() {
		awareness = (float) (1 - Math.pow((heroClass == HeroClass.ROGUE ? 0.85
				: 0.90), (1 + Math.min(lvl, 9)) * 0.5));
	}

	public boolean isStarving() {
		return buff(Hunger.class) != null
				&& buff(Hunger.class).isStarving();
	}

	@Override
	public void add(Buff buff) {

		if (buff(TimekeepersHourglass.timeStasis.class) != null)
			return;

		super.add(buff);

		if (sprite != null) {
			if (buff instanceof Burning) {
				GLog.w("You catch fire!");
				interrupt();
			} else if (buff instanceof Paralysis) {
				GLog.w("You are paralysed!");
				interrupt();
			} else if (buff instanceof Poison) {
				GLog.w("You are poisoned!");
				interrupt();
			} else if (buff instanceof Ooze) {
				GLog.w("Caustic ooze eats your flesh. Wash it away!");
			} else if (buff instanceof Roots) {
				GLog.w("You can't move!");
			} else if (buff instanceof Weakness) {
				GLog.w("You feel weakened!");
			} else if (buff instanceof Blindness) {
				GLog.w("You are blinded!");
			} else if (buff instanceof Fury) {
				GLog.w("You become furious!");
				sprite.showStatus(CharSprite.POSITIVE, "furious");
			} else if (buff instanceof Charm) {
				GLog.w("You are charmed!");
			} else if (buff instanceof Cripple) {
				GLog.w("You are crippled!");
			} else if (buff instanceof Bleeding) {
				GLog.w("You are bleeding!");
			} else if (buff instanceof RingOfMight.Might) {
				if (((RingOfMight.Might) buff).level > 0) {
					HT += ((RingOfMight.Might) buff).level * 5;
				}
			} else if (buff instanceof Vertigo) {
				GLog.w("Everything is spinning around you!");
				interrupt();
			}

			else if (buff instanceof Light) {
				sprite.add(CharSprite.State.ILLUMINATED);
			}
		}

		BuffIndicator.refreshHero();
	}

	@Override
	public void remove(Buff buff) {
		super.remove(buff);

		if (buff instanceof Light) {
			sprite.remove(CharSprite.State.ILLUMINATED);
		} else if (buff instanceof RingOfMight.Might) {
			if (((RingOfMight.Might) buff).level > 0) {
				HT -= ((RingOfMight.Might) buff).level * 5;
				HP = Math.min(HT, HP);
			}
		}

		BuffIndicator.refreshHero();
	}

	@Override
	public int stealth() {
		int stealth = super.stealth();
		for (Buff buff : buffs(RingOfEvasion.Evasion.class)) {
			stealth += ((RingOfEvasion.Evasion) buff).effectiveLevel;
		}
		return stealth;
	}

	@Override
	public void die(Object cause) {

		curAction = null;

		Ankh ankh = null;

		// look for ankhs in player inventory, prioritize ones which are
		// blessed.
		for (Item item : belongings) {
			if (item instanceof Ankh) {
				if (ankh == null || ((Ankh) item).isBlessed()) {
					ankh = (Ankh) item;
				}
			}
		}

		if (ankh != null && ankh.isBlessed()) {
			this.HP = HT;

			new Flare(8, 32).color(0xFFFF66, true).show(sprite, 2f);
			CellEmitter.get(this.pos)
					.start(Speck.factory(Speck.LIGHT), 0.2f, 3);

			ankh.detach(belongings.backpack);

			Sample.INSTANCE.play(Assets.SND_TELEPORT);
			GLog.w(Ankh.TXT_REVIVE);
			Statistics.ankhsUsed++;

			return;
		}

		Actor.fixTime();
		super.die(cause);

		if (ankh == null) {

			reallyDie(cause);

		} else {
			
			ankh.detach(belongings.backpack);
			Dungeon.deleteGame(Dungeon.hero.heroClass, false);
			GameScene.show(new WndResurrect(ankh, cause));

		}
	}

	public static void reallyDie(Object cause) {

		int length = Level.getLength();
		int[] map = Dungeon.level.map;
		boolean[] visited = Dungeon.level.visited;
		boolean[] discoverable = Level.discoverable;

		for (int i = 0; i < length; i++) {

			int terr = map[i];

			if (discoverable[i]) {

				visited[i] = true;
				if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {
					Level.set(i, Terrain.discover(terr));
					GameScene.updateMap(i);
				}
			}
		}

		Bones.leave();

		Dungeon.observe();

		Dungeon.hero.belongings.identify();

		int pos = Dungeon.hero.pos;

		ArrayList<Integer> passable = new ArrayList<Integer>();
		for (Integer ofs : Level.NEIGHBOURS8) {
			int cell = pos + ofs;
			if ((Level.passable[cell] || Level.avoid[cell])
					&& Dungeon.level.heaps.get(cell) == null) {
				passable.add(cell);
			}
		}
		Collections.shuffle(passable);

		ArrayList<Item> items = new ArrayList<Item>(
				Dungeon.hero.belongings.backpack.items);
		for (Integer cell : passable) {
			if (items.isEmpty()) {
				break;
			}

			Item item = Random.element(items);
			Dungeon.level.drop(item, cell).sprite.drop(pos);
			items.remove(item);
		}

		GameScene.gameOver();

		if (cause instanceof Hero.Doom) {
			((Hero.Doom) cause).onDeath();
		}

		Dungeon.deleteGame(Dungeon.hero.heroClass, true);
	}

	@Override
	public void move(int step) {
		super.move(step);

		if (!flying) {

			if (Level.water[pos]) {
				Sample.INSTANCE.play(Assets.SND_WATER, 1, 1,
						Random.Float(0.8f, 1.25f));
			} else {
				Sample.INSTANCE.play(Assets.SND_STEP);
			}
			Dungeon.level.press(pos, this);
		}
		
		if (buff(LichenDrop.class) != null){Lichen.spawnAroundChance(pos);}
	}

	@Override
	public void onMotionComplete() {
		Dungeon.observe();
		search(false);

		super.onMotionComplete();
	}

	@Override
	public void onAttackComplete() {

		AttackIndicator.target(enemy);

		attack(enemy);
		curAction = null;

		Invisibility.dispel();

		super.onAttackComplete();
	}

	@Override
	public void onOperateComplete() {

		if (curAction instanceof HeroAction.Unlock) {

			if (theKey != null) {
				theKey.detach(belongings.backpack);
				theKey = null;
			}

			int doorCell = ((HeroAction.Unlock) curAction).dst;
			int door = Dungeon.level.map[doorCell];

			Level.set(doorCell, door == Terrain.LOCKED_DOOR ? Terrain.DOOR
					: Terrain.UNLOCKED_EXIT);
			GameScene.updateMap(doorCell);

		} else if (curAction instanceof HeroAction.OpenChest) {

			if (theKey != null) {
				theKey.detach(belongings.backpack);
				theKey = null;
			} else if (theKey == null && theSkeletonKey != null) {
				theSkeletonKey.detach(belongings.backpack);
				theSkeletonKey = null;
			}
			
			Heap heap = Dungeon.level.heaps
					.get(((HeroAction.OpenChest) curAction).dst);
			if (heap.type == Type.SKELETON || heap.type == Type.REMAINS) {
				Sample.INSTANCE.play(Assets.SND_BONES);
			}
			heap.open(this);
		}
		curAction = null;

		super.onOperateComplete();
	}

	public boolean search(boolean intentional) {

		boolean smthFound = false;

		int positive = 0;
		int negative = 0;

		int distance = 1 + positive + negative;

		float level = intentional ? (2 * awareness - awareness * awareness)
				: awareness;
		if (distance <= 0) {
			level /= 2 - distance;
			distance = 1;
		}

		int cx = pos % Level.getWidth();
		int cy = pos / Level.getWidth();
		int ax = cx - distance;
		if (ax < 0) {
			ax = 0;
		}
		int bx = cx + distance;
		if (bx >= Level.getWidth()) {
			bx = Level.getWidth() - 1;
		}
		int ay = cy - distance;
		if (ay < 0) {
			ay = 0;
		}
		int by = cy + distance;
		if (by >= Level.HEIGHT) {
			by = Level.HEIGHT - 1;
		}

		TalismanOfForesight.Foresight foresight = buff(TalismanOfForesight.Foresight.class);

		// cursed talisman of foresight makes unintentionally finding things
		// impossible.
		if (foresight != null && foresight.isCursed()) {
			level = -1;
		}

		for (int y = ay; y <= by; y++) {
			for (int x = ax, p = ax + y * Level.getWidth(); x <= bx; x++, p++) {

				if (Dungeon.visible[p]) {

					if (intentional) {
						sprite.parent.addToBack(new CheckedCell(p));
					}

					if (Level.secret[p]
							&& (intentional || Random.Float() < level)) {

						int oldValue = Dungeon.level.map[p];

						GameScene.discoverTile(p, oldValue);

						Level.set(p, Terrain.discover(oldValue));

						GameScene.updateMap(p);

						ScrollOfMagicMapping.discover(p);

						smthFound = true;

						if (foresight != null && !foresight.isCursed())
							foresight.charge();
					}
				}
			}
		}

		if (intentional) {
			sprite.showStatus(CharSprite.DEFAULT, TXT_SEARCH);
			sprite.operate(pos);
			if (foresight != null && foresight.isCursed()) {
				GLog.n("You can't concentrate, searching takes a while.");
				spendAndNext(TIME_TO_SEARCH * 3);
			} else {
				spendAndNext(TIME_TO_SEARCH);
			}

		}

		if (smthFound) {
			GLog.w(TXT_NOTICED_SMTH);
			Sample.INSTANCE.play(Assets.SND_SECRET);
			interrupt();
		}

		return smthFound;
	}

	public void resurrect(int resetLevel) {

		HP = HT;
		Dungeon.gold = 0;
		exp = 0;

		belongings.resurrect(resetLevel);

		live();
	}

	@Override
	public HashSet<Class<?>> resistances() {
		RingOfElements.Resistance r = buff(RingOfElements.Resistance.class);
		return r == null ? super.resistances() : r.resistances();
	}

	@Override
	public HashSet<Class<?>> immunities() {
		HashSet<Class<?>> immunities = new HashSet<Class<?>>();
		for (Buff buff : buffs()) {
			for (Class<?> immunity : buff.immunities)
				immunities.add(immunity);
		}
		return immunities;
	}

	@Override
	public void next() {
		super.next();
	}

	public static interface Doom {
		public void onDeath();
	}
}
