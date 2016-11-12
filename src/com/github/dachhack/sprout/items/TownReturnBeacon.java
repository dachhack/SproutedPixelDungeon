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
import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.items.artifacts.DriedRose;
import com.github.dachhack.sprout.items.artifacts.TimekeepersHourglass;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.scenes.InterlevelScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

public class TownReturnBeacon extends Item {

	
	private static final String TXT_INFO = "Return beacon is an intricate magic device, that allows you to return to a place you have already been.";
	private static final String TXT_CREATURES = "Psychic aura of neighbouring creatures doesn't allow you to use the lloyd's beacon at this moment.";

	public static final float TIME_TO_USE = 1;

	//public static final String AC_SET = "SET";
	public static final String AC_RETURN = "RETURN TO MINE";
	public static final String AC_RETURNTOWN = "DOLYAHAVEN";
	public static final String FAIL = "Strong magic aura of this place prevents you from using the beacon!";

	private int returnDepth = -1;
	private int returnPos;

	{
		name = "dolyahaven return beacon";
		image = ItemSpriteSheet.BEACON;

		unique = true;
	}
	
	private static final String DEPTH = "depth";
	private static final String POS = "pos";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(DEPTH, returnDepth);
		if (returnDepth != -1) {
			bundle.put(POS, returnPos);
		}
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		returnDepth = bundle.getInt(DEPTH);
		returnPos = bundle.getInt(POS);
	}
	

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		if (Dungeon.depth==55 && returnDepth>55 && !Badges.checkOtilukeRescued()){
		actions.add(AC_RETURN);
		}
		if(Dungeon.depth>55){
		   actions.add(AC_RETURNTOWN);	
		}
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		
		if (action == AC_RETURNTOWN) {
		
		   if (Dungeon.bossLevel() || Dungeon.level.locked || hero.petfollow) {
		     	hero.spend(TIME_TO_USE);
			    GLog.w(FAIL);
			    return;
		    }

		  for (int i = 0; i < Level.NEIGHBOURS8.length; i++) {
			   if (Actor.findChar(hero.pos + Level.NEIGHBOURS8[i]) != null) {
				GLog.w(TXT_CREATURES);
				return;
			   }
		   }
		
		}
		
	     if (action == AC_RETURNTOWN) {
	    	 
	    	 hero.spend(TIME_TO_USE);
	    	 
	    	    returnDepth = Dungeon.depth;
				returnPos = hero.pos;

				Buff buff = Dungeon.hero
						.buff(TimekeepersHourglass.timeFreeze.class);
				if (buff != null)
					buff.detach();

				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0]))
					if (mob instanceof DriedRose.GhostHero)
						mob.destroy();

				InterlevelScene.mode = InterlevelScene.Mode.RETURN;
				InterlevelScene.returnDepth = 55;
				InterlevelScene.returnPos = 1925;
				Game.switchScene(InterlevelScene.class);
				
	     } else if (action == AC_RETURN) {
	    	 
	    	 hero.spend(TIME_TO_USE);
	    	  
	    	 Buff buff = Dungeon.hero
						.buff(TimekeepersHourglass.timeFreeze.class);
				if (buff != null)
					buff.detach();

				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0]))
					if (mob instanceof DriedRose.GhostHero)
						mob.destroy();

				InterlevelScene.mode = InterlevelScene.Mode.RETURN;
				InterlevelScene.returnDepth = returnDepth;
				InterlevelScene.returnPos = returnPos;
				Game.switchScene(InterlevelScene.class);
				
		} else {

			super.execute(hero, action);

		}
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
		return TXT_INFO;
	}
}
