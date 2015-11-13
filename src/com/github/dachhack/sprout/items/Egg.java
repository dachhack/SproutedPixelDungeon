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
package com.github.dachhack.sprout.items;

import java.util.ArrayList;

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.pets.BlueDragon;
import com.github.dachhack.sprout.actors.mobs.pets.Fairy;
import com.github.dachhack.sprout.actors.mobs.pets.GreenDragon;
import com.github.dachhack.sprout.actors.mobs.pets.PET;
import com.github.dachhack.sprout.actors.mobs.pets.RedDragon;
import com.github.dachhack.sprout.actors.mobs.pets.Scorpion;
import com.github.dachhack.sprout.actors.mobs.pets.Spider;
import com.github.dachhack.sprout.actors.mobs.pets.SugarplumFairy;
import com.github.dachhack.sprout.actors.mobs.pets.Velocirooster;
import com.github.dachhack.sprout.actors.mobs.pets.VioletDragon;
import com.github.dachhack.sprout.actors.mobs.pets.bee;
import com.github.dachhack.sprout.effects.Pushing;
import com.github.dachhack.sprout.effects.particles.SparkParticle;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.levels.traps.LightningTrap;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Egg extends Item {

	private static final String TXT_PREVENTING = "This is not the best place to try that.";
	private static final String TXT_NOTREADY = "Something tells you it's not ready yet.";
	private static final String TXT_YOLK = "Ewww. Gross, uncooked egg of a random creature.";
	private static final String TXT_HATCH = "Something hatches!";
	private static final String TXT_SCRATCH = "Something scratches back!";
	private static final String TXT_SLITHERS = "Something squirms inside!";
	private static final String TXT_KICKS = "Something powerful kicks back!";
	private static final String TXT_SLOSH = "Just some sloshing around.";
	private static final String TXT_ZAP = "Ouch! Something zaps you back!.";
	
	public static final float TIME_TO_USE = 1;

	public static final String AC_BREAK = "BREAK OPEN";
	public static final String AC_SHAKE = "SHAKE";
	
	public static final int RED_DRAGON = 30;
	public static final int GREEN_DRAGON = 5;
	public static final int BLUE_DRAGON = 5;
	public static final int VIOLET_DRAGON = 5;
	public static final int SPIDER = 2000;
	public static final int SCORPION = 4000;
	public static final int VELOCIROOSTER = 1;
	public static final int FAIRY = 10;
	

		{
		name = "egg";
		image = ItemSpriteSheet.EGG;
		unique = true;
		stackable = false;
		}
		
		public int startMoves = 0;
		public int moves = 0;
		public int burns = 0;
		public int freezes = 0;
		public int poisons = 0;
		public int lits = 0;
		public int summons = 0;
		
		private static final String STARTMOVES = "startMoves";
		private static final String MOVES = "moves";
		private static final String BURNS = "burns";
		private static final String FREEZES = "freezes";
		private static final String POISONS = "poisons";
		private static final String LITS = "lits";
		private static final String SUMMONS = "summons";
		
		
		
		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(STARTMOVES, startMoves);
			bundle.put(MOVES, moves);
			bundle.put(BURNS, burns);
			bundle.put(FREEZES, freezes);
			bundle.put(POISONS, poisons);
			bundle.put(LITS, lits);
			bundle.put(SUMMONS, summons);
			
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			startMoves = bundle.getInt(STARTMOVES);
			moves = bundle.getInt(MOVES);
			burns = bundle.getInt(BURNS);
			freezes = bundle.getInt(FREEZES);
			poisons = bundle.getInt(POISONS);
			lits = bundle.getInt(LITS);
			summons = bundle.getInt(SUMMONS);
			
		}
						
		public int checkMoves () {
			return moves;
		}
		public int checkBurns () {
			return burns;
		}
		public int checkFreezes () {
			return freezes;
		}
		public int checkPoisons () {
			return poisons;
		}
		public int checkLits () {
			return lits;
		}
		public int checkSummons () {
			return summons;
		}
		
		@Override
		public boolean doPickUp(Hero hero) {
				
				GLog.w("The egg likes to be warm in your pack.");
				
				Egg egg = hero.belongings.getItem(Egg.class);
				if (egg!=null){
					GLog.w("You can probably only keep one egg warm at a time.");
				}
						 
			 return super.doPickUp(hero);				
		}	
		
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_BREAK);
		actions.add(AC_SHAKE);
		
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		if (action == AC_BREAK) {

			if (Dungeon.depth>26) {
				hero.spend(Egg.TIME_TO_USE);
				GLog.w(TXT_PREVENTING);
				return;
			}			
		}

		if (action == AC_BREAK) {	
			
			boolean hatch = false;
			
			 if (checkSummons()>=FAIRY) {
				 if (Dungeon.getMonth()==11){
				   SugarplumFairy pet = new SugarplumFairy();
				   eggHatch(pet);
					  hatch=true;
				 } else {
				  Fairy pet = new Fairy();
				  eggHatch(pet);
				  hatch=true;
				 }				 
				//spawn fairy	
			  } else if (checkFreezes()>=BLUE_DRAGON) {
				  BlueDragon pet = new BlueDragon();
				  eggHatch(pet);
				  hatch=true;
				  //spawn ice dragon			
			  } else if (checkPoisons()>=VIOLET_DRAGON) {
				  VioletDragon pet = new VioletDragon();
				  eggHatch(pet);
				  hatch=true;
				  //spawn green dragon
			  } else if (checkLits()>=GREEN_DRAGON) {
				  GreenDragon pet = new GreenDragon();
				  eggHatch(pet);
				  hatch=true;
				  //spawn lit dragon
			  } else if (checkBurns()>=RED_DRAGON) {			
				  RedDragon pet = new RedDragon();
				  eggHatch(pet);
				  hatch=true;
				 //spawn red dragon	
			  } else if (checkBurns()>=VELOCIROOSTER) {
				  Velocirooster pet = new Velocirooster();
				  eggHatch(pet);
				  hatch=true;
				  //spawn velocirooster
			  } else if (checkMoves()>=SCORPION) {
				  Scorpion pet = new Scorpion();
				  eggHatch(pet);
				  hatch=true;
				  //spawn spider
			  } else if (checkMoves()>=SPIDER) {
				  Spider pet = new Spider();
				  eggHatch(pet);
				  hatch=true;
				  //spawn bat
			  }	
		           
		  if (!hatch)	{
			  detach(Dungeon.hero.belongings.backpack);		 			 
			  GLog.w(TXT_YOLK);  			  
		  }
		  
		  hero.next();
		
		}
		
		else if (action == AC_SHAKE) {
						            
			 boolean alive = false;
			  
			  if (checkSummons()>=FAIRY) {
				   GLog.w(TXT_ZAP);
				   Dungeon.hero.sprite.centerEmitter().burst(SparkParticle.FACTORY, 3);
				   Dungeon.hero.sprite.flash();
				   Dungeon.hero.damage(1, LightningTrap.LIGHTNING);	
				   alive = true;
				  //spawn fairy
			  } else if (checkFreezes()>=BLUE_DRAGON) {
				 GLog.w(TXT_KICKS);
				 alive = true;
				  //spawn ice dragon
			  } else if (checkPoisons()>=VIOLET_DRAGON) {
				   GLog.w(TXT_KICKS);
				   alive = true;
				  //spawn green dragon
			  } else if (checkLits()>=GREEN_DRAGON) {
				  GLog.w(TXT_KICKS);
				  alive = true;
				  //spawn lit dragon
			  } else if (checkBurns()>=RED_DRAGON) {
				  GLog.w(TXT_KICKS);
				  alive = true;
				  //spawn dragon
			  } else if (checkBurns()>=VELOCIROOSTER) {
				  GLog.w(TXT_SCRATCH);
				  alive = true;
				  //spawn velocirooster
			  } else if (checkMoves()>=SCORPION) {
				  GLog.w(TXT_SLITHERS);
				  alive = true;
				  //spawn spider
			  } else if (checkMoves()>=SPIDER) {
				  GLog.w(TXT_SLITHERS);
				  alive = true;
				  //spawn scorpion
			  }
			  
			  if (!alive)	{				  
				  GLog.w(TXT_SLOSH);  			  
			  }
			  				
		} else {

			super.execute(hero, action);

		}
		
		
		
	}	
	
	public int getSpawnPos(){
		int newPos = -1;
		int pos = Dungeon.hero.pos;
			ArrayList<Integer> candidates = new ArrayList<Integer>();
			boolean[] passable = Level.passable;

			for (int n : Level.NEIGHBOURS8) {
				int c = pos + n;
				if (passable[c] && Actor.findChar(c) == null) {
					candidates.add(c);
				}
			}

			newPos = candidates.size() > 0 ? Random.element(candidates) : -1;
			
		return newPos;
	}
	
	
	public void eggHatch (PET pet) {		
		
		  int spawnPos = getSpawnPos();
		  if (spawnPos != -1 && !Dungeon.hero.haspet) {
				
				pet.spawn(1);
				pet.HP = pet.HT;
				pet.pos = spawnPos;
				pet.state = pet.HUNTING;

				GameScene.add(pet);
				Actor.addDelayed(new Pushing(pet, Dungeon.hero.pos, spawnPos), -1f);

				pet.sprite.alpha(0);
				pet.sprite.parent.add(new AlphaTweener(pet.sprite, 1, 0.15f));
				
				detach(Dungeon.hero.belongings.backpack);		 			 
				GLog.w(TXT_HATCH);
				Dungeon.hero.haspet=true;
				
		  } else {
			  
			  Dungeon.hero.spend(Egg.TIME_TO_USE);
			  GLog.w(TXT_NOTREADY);

		  }
	}
		
	@Override
	public int price() {
		return 500 * quantity;
	}
	
	
	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

		
	@Override
	public String info() {
		return "An egg of some creature. It's rather large and hard. Who knows what it contains?";
	}

}
