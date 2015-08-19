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

import java.io.IOException;
import java.util.ArrayList;

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.MrDestructo;
import com.github.dachhack.sprout.actors.mobs.OrbOfZotMob;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.scenes.AmuletScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class OrbOfZot extends Item {

	//private static final String AC_END = "END THE GAME";

	{
		name = "Orb Of Zot";
		image = ItemSpriteSheet.ORBOFZOT;
		defaultAction = AC_ACTIVATETHROW;
		unique = true;
	}

	
	
	private static boolean activate = false;

	private static final String AC_ACTIVATETHROW = "Activate & Throw";
	
	
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_ACTIVATETHROW);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		if (action.equals(AC_ACTIVATETHROW)) {
			activate = true;
			action = AC_THROW;
		} else
			activate = false;

		super.execute(hero, action);
	}

	@Override
	protected void onThrow(int cell) {
		
		if (Actor.findChar(cell) != null) {
			ArrayList<Integer> candidates = new ArrayList<>();
			for (int i : Level.NEIGHBOURS8)
				if (Level.passable[cell + i])
					candidates.add(cell + i);
			int newCell = candidates.isEmpty() ? cell : Random
					.element(candidates);
			
			   if (!Level.pit[newCell] && activate) {
				   OrbOfZotMob.spawnAt(newCell);
			   } else {
			   Dungeon.level.drop(this, newCell).sprite.drop(cell);
			   }
			   
		} else if (!Level.pit[cell] && activate) {
			  OrbOfZotMob.spawnAt(cell);
		} else {
			
			super.onThrow(cell);
		}

	}
	
	@Override
	public boolean doPickUp(Hero hero) {
		if (super.doPickUp(hero)) {

			if (!Statistics.orbObtained) {
				Statistics.orbObtained = true;
				Badges.validateOrbObtained();

				//showAmuletScene(true);
			}

			return true;
		} else {
			return false;
		}
	}
/*
	private void showAmuletScene(boolean showText) {
		try {
			Dungeon.saveAll();
			AmuletScene.noText = !showText;
			Game.switchScene(AmuletScene.class);
		} catch (IOException e) {
		}
	}
*/
	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public String info() {
		return "The Orb of Zot is source of unlimited power created by the wizard Zot. "
				+ "Apparently, Yog was harnessing its power to infest the dungeon. ";
	}
}
