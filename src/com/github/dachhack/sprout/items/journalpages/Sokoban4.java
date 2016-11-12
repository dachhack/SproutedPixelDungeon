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
package com.github.dachhack.sprout.items.journalpages;

import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;

public class Sokoban4 extends JournalPage {

	private static final String TXT_VALUE = "Sokoban Puzzles";

	{
		name = "journal page sokoban puzzles";
		image = ItemSpriteSheet.JOURNAL_PAGE;
		room=4;

		stackable = false;
	}

	@Override
	public boolean doPickUp(Hero hero) {
         
		GLog.p("You found a page to Otiluke's Journal!", TXT_VALUE);
		return super.doPickUp(hero);
	
	}

	@Override
	public String info() {
		return "A loose journal page labled Sokoban Puzzles. \n\n"
				+"My efforts to stop my pursuers have only slowed their progress.\n\n "
				+"I am now remembering the strange spaces where I hid some of my valuables centuries ago. \n\n"
				+"The items themselves are no longer of importance to me, "
				+"but the source of the odd power generating the sheep eluded me for years. \n\n"
				+"Perhaps this power, once illuminated, will be a source of succor. "
				+"I fear I have little options left. \n\n"
				+"-Otiluke, 1617 LP. ";
	}
}
