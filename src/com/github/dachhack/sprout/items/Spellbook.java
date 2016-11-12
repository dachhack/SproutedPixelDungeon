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

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Haste;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.effects.particles.ElmoParticle;
import com.github.dachhack.sprout.items.spells.Spell;
import com.github.dachhack.sprout.items.keys.IronKey;
import com.github.dachhack.sprout.items.misc.Spectacles.MagicSight;
import com.github.dachhack.sprout.items.rings.RingOfForce;
import com.github.dachhack.sprout.items.scrolls.Scroll;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.scenes.InterlevelScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.windows.WndBag;
import com.github.dachhack.sprout.windows.WndOtiluke;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class Spellbook extends Item {

	private static final String TXT_PREVENTING = "Strong magic aura of this place prevents you from reading the journal!";

	/* Spells can be learned up to 5 per spell
	 * Each instance of each spell takes charges
	 * gain X charges when moving
	 * gain X charges when sleeping
	 * item to completely recharge or recharge X points
	 * learn spells window
	 * view spells learned
	 * quickslot spells
	 * use spells window
	 * 
	 * Firebolt 1,2,3
	 * Icebolt 1,2,3
	 * Litbolt 1,2,3
	 * Disintegration 1,2,3
	 * Disintegrating cloud 1,2
	 * Slow
	 * Haste 
	 * Psionic Blast 1,2,3
	 * Dispel
	 * Amok
	 * Fury
	 * Earthquake 1,2,3
	 * Poison 1,2,3
	 * Teleport 
	 * Fright
	 * Recharge
	 * Infuse
	 * Regrowth
	 * Float
	 * Magic Missile
	 * Transfusion
	 */
	
	
	public final float TIME_TO_USE = 1;
	public final int fullCharge = 1000;
	
	
	public static final String AC_SELECT = "SELECT SPELLS";
	public static final String AC_VIEW = "VIEW SPELLS";
		
	protected String inventoryTitle = "Select a journal page";
	protected WndBag.Mode mode = WndBag.Mode.JOURNALPAGES;

	
	public int returnDepth = -1;
	public int returnPos;
	
	public int charge = 0;
	public int level = 1;
	
	public int checkReading(){
		int lvl=1;			
		if (Dungeon.hero.buff(MagicSight.class) != null){
			lvl+=1;
		}
		return lvl;
	}
	
	public int reqCharges(){
			
		int calcCharges = Math.round(fullCharge/(level*checkReading()));
		return calcCharges;
		
	}
	

	public boolean[] spells = new boolean[20];	
	public int[] charges = new int[20];	
	public boolean[] learn = new boolean[20];
	public int[] learnCount = new int[20];	
		
	{
		name = "Spellbook";
		image = ItemSpriteSheet.OTILUKES_JOURNAL;

		unique = true;
		
		//rooms[0] = true;
		//firsts[0] = true;
	}
		
	private static final String DEPTH = "depth";
	private static final String POS = "pos";
	private static final String SPELLS = "rooms";
	private static final String CHARGES = "charges";
	private static final String CHARGE = "charge";
	private static final String LEARN = "learn";
	private static final String LEARNCOUNT = "learnCount";
	private static final String LEVEL = "level";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(DEPTH, returnDepth);
		bundle.put(SPELLS, spells);
		bundle.put(CHARGE, charge);
		bundle.put(LEARN, learn);
		bundle.put(LEARNCOUNT, learnCount);
		bundle.put(CHARGES, charges);
		bundle.put(LEVEL, level);
		if (returnDepth != -1) {
			bundle.put(POS, returnPos);
		}
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		returnDepth = bundle.getInt(DEPTH);
		returnPos = bundle.getInt(POS);
		charge = bundle.getInt(CHARGE);
		level = bundle.getInt(LEVEL);
		spells = bundle.getBooleanArray(SPELLS);
		charges = bundle.getIntArray(CHARGES);
		learn = bundle.getBooleanArray(LEARN);
		learnCount = bundle.getIntArray(LEARNCOUNT);
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		
		actions.add(AC_SELECT);
		actions.add(AC_VIEW);
						
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		
		if (action == AC_SELECT) {
				
		//	GameScene.show(new WndOtiluke(rooms, this));
			
		}
              
       if (action == AC_VIEW) {
    	   
    	  
		}
                 					
		 else {

			super.execute(hero, action);

		}
	}

	@Override
	public int price() {
		return 300*quantity;
	}
	
	public void reset() {
		returnDepth = -1;
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
		
		String strdesc = "Your trusty spellbook.";		
		return strdesc;
	}
	
	protected WndBag.Listener itemSelector = new WndBag.Listener() {
		@Override
		public void onSelect(Item item) {
			if (item != null && item instanceof Spell) {
				Hero hero = Dungeon.hero;
				int spellnum = ((Spell) item).spellnum;
			
				hero.sprite.operate(hero.pos);
				hero.busy();
				hero.spend(2f);
				Sample.INSTANCE.play(Assets.SND_BURNING);
				hero.sprite.emitter().burst(ElmoParticle.FACTORY, 12);

				item.detach(hero.belongings.backpack);
				GLog.h("You add the spell to your book!");							
				
				
				spells[spellnum] = true;				
				
		}
	 }
	};
	

}
