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

public class Sokoban3 extends JournalPage {

	private static final String TXT_VALUE = "Sokoban Portals";

	{
		name = "journal page sokoban portals";
		image = ItemSpriteSheet.JOURNAL_PAGE;
		room=3;

		stackable = false;
	}

	@Override
	public boolean doPickUp(Hero hero) {
         
		GLog.p("You found a page to Otiluke's Journal!", TXT_VALUE);
		return super.doPickUp(hero);
	
	}

	@Override
	public String info() {
		return "A loose journal page labled Sokoban Portals.\n\n "
				+"My powers have grown stronger since I created my first space. \n\n"
				+"I find myself able to alter time and position in ways I have never thought possible. \n\n"
				+"My activities have created ripples in the magical ethosphere. "
				+"On clear days I am able to capture echoes of some disturbance focused on my presence. \n\n"
				+"-Otiluke, 1355 LP. ";
		   
	}
}
