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
import java.util.Arrays;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.items.artifacts.DriedRose;
import com.github.dachhack.sprout.items.artifacts.TimekeepersHourglass;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.scenes.InterlevelScene;
import com.github.dachhack.sprout.sprites.ItemSprite.Glowing;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.windows.WndBlacksmith;
import com.github.dachhack.sprout.windows.WndOtiluke;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;

public class OtilukesJournal extends Item {

	private static final String TXT_PREVENTING = "Strong magic aura of this place prevents you from reading the journal!";

	
	public static final float TIME_TO_USE = 1;
	
	
	public static final String AC_RETURN = "RETURN";
	public static final String AC_ADD = "ADD A PAGE";
	public static final String AC_PORT = "READ";

	
	private int returnDepth = -1;
	private int returnPos;
	
	public boolean[] rooms = new boolean[10];	
	
	{
		name = "Otiluke's journal";
		image = ItemSpriteSheet.OTILUKES_JOURNAL;

		unique = true;
	}

	
	private static final String DEPTH = "depth";
	private static final String POS = "pos";
	private static final String ROOMS = "rooms";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(DEPTH, returnDepth);
		bundle.put(ROOMS, rooms);
		if (returnDepth != -1) {
			bundle.put(POS, returnPos);
		}
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		returnDepth = bundle.getInt(DEPTH);
		returnPos = bundle.getInt(POS);
		rooms = bundle.getBooleanArray(ROOMS);
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		
		
		actions.add(AC_RETURN);
		actions.add(AC_ADD);
		actions.add(AC_PORT);
		
		
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		if (action == AC_PORT) {

			if (Dungeon.bossLevel()) {
				hero.spend(TIME_TO_USE);
				GLog.w(TXT_PREVENTING);
				return;
			}
						
			
		}

		if (action == AC_PORT) {

			GameScene.show(new WndOtiluke(rooms, this));
			
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
