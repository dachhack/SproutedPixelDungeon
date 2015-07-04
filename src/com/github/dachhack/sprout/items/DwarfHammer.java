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
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;

public class DwarfHammer extends Item {

	private static final String TXT_PREVENTING = "This is not the place to use the hammer.";
	private static final String TXT_UNSEAL = "You unseal the entrance to the next level.";
	
	public static final float TIME_TO_USE = 1;

	public static final String AC_BREAK = "BREAK SEAL";

		{
		name = "dwarf demon hammer";
		image = ItemSpriteSheet.DWARFHAMMER;

		}

	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_BREAK);
		
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {

		if (action == AC_BREAK) {

			if (Dungeon.bossLevel()) {
				hero.spend(DwarfHammer.TIME_TO_USE);
				GLog.w(TXT_PREVENTING);
				return;
			}
			
			if (Dungeon.depth > 24 || Dungeon.depth < 22) {
				hero.spend(DwarfHammer.TIME_TO_USE);
				GLog.w(TXT_PREVENTING);
				return;
			}
			
			if (!Dungeon.visible[Dungeon.level.exit]) {
				hero.spend(DwarfHammer.TIME_TO_USE);
				GLog.w(TXT_PREVENTING);
				return;
			}


		}

		if (action == AC_BREAK) {
           
		  if (hero.pos != Dungeon.level.exit)	{
			  detach(Dungeon.hero.belongings.backpack);			  
			  		 
			  Dungeon.sealedlevel=false;
			  
			  Dungeon.level.map[Dungeon.level.exit]=Terrain.EMPTY;
			  GameScene.updateMap(Dungeon.level.exit);
			  Dungeon.observe();

			  Dungeon.level.map[Dungeon.level.exit]=Terrain.EXIT;
			  GameScene.updateMap(Dungeon.level.exit);
			  Dungeon.observe();
			  
			  GLog.w(TXT_UNSEAL);  	
				  
		  }
			
		} else {
			GLog.w(TXT_PREVENTING);
			super.execute(hero, action);
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
		return "Too fragile to use as a weapon, this magic hammer was used to seal the demons in this level. "
			  +"Perhaps there is just enough magic left to unseal the next level... ";
	}

}
