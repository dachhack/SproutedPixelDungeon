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

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.items.artifacts.DriedRose;
import com.github.dachhack.sprout.items.artifacts.TimekeepersHourglass;
import com.github.dachhack.sprout.scenes.InterlevelScene;
import com.github.dachhack.sprout.sprites.ItemSprite.Glowing;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;

public class OtilukesJournal extends Item {

	private static final String TXT_PREVENTING = "Strong magic aura of this place prevents you from reading the journal!";

	
	public static final float TIME_TO_USE = 1;
	
	
	public static final String AC_RETURN = "RETURN";
	public static final String AC_ADD = "ADD A PAGE";
	public static final String AC_PORT_HOME = "SAFE SPOT";

	
	private int returnDepth = -1;
	private int returnPos;
	
	private Boolean[] rooms = new Boolean[10];
	

	{
		name = "Otiluke's journal";
		image = ItemSpriteSheet.OTILUKES_JOURNAL;

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
		
		
		actions.add(AC_RETURN);
		actions.add(AC_ADD);
		if (rooms[0]){
			actions.add(AC_PORT_HOME);
		}
		
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		if (action == AC_PORT_HOME) {

			if (Dungeon.bossLevel()) {
				hero.spend(TIME_TO_USE);
				GLog.w(TXT_PREVENTING);
				return;
			}
						
			
		}

		if (action == AC_PORT_HOME) {

			
			Buff buff = Dungeon.hero
						.buff(TimekeepersHourglass.timeFreeze.class);
				if (buff != null)
					buff.detach();

				for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0]))
					if (mob instanceof DriedRose.GhostHero)
						mob.destroy();
              if (Dungeon.depth<27){
            	returnDepth = Dungeon.depth;
       			returnPos = hero.pos;
       			InterlevelScene.returnDepth = returnDepth;
				InterlevelScene.returnPos = returnPos;
				InterlevelScene.mode = InterlevelScene.Mode.PORT1;
				Game.switchScene(InterlevelScene.class);
			}
		}
              
       if (action == AC_RETURN) {
				InterlevelScene.mode = InterlevelScene.Mode.RETURN;	
				InterlevelScene.returnDepth = returnDepth;
				InterlevelScene.returnPos = returnPos;
				Game.switchScene(InterlevelScene.class);
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
		return "The journal of the powerful Otiluke. It is missing pages.. ";
	}

}
