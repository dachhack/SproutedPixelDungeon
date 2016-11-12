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

public class Town extends JournalPage {

	private static final String TXT_VALUE = "Dolyahaven";

	{
		name = "journal page Dolyahaven";
		image = ItemSpriteSheet.JOURNAL_PAGE;
		room=5;

		stackable = false;
	}

	@Override
	public boolean doPickUp(Hero hero) {
         
		GLog.p("You found a page to Otiluke's Journal! It reads, %s.", TXT_VALUE);
		return super.doPickUp(hero);
	
	}

	@Override
	public String info() {
		return "A loose journal page labled Dolyahaven. \n\n"
				+"With what little power I have left I have "
				+"momentarily bested Zot and obtained his orb of power. \n\n"
				+"Although its power is warded against me, I can transpose "
				+"objects from this world into the heart of the orb. \n\n"
				+"I hear a rushing of power in the distance... Zot approaches! \n\n"
				+"My last act will be to return to Dolyahaven, my birthplace "
				+"and seal this script into the orb.\n\n Zot will need to break "
				+"the orb and source of his power to follow me. \n\n"
				+"-Otiluke, 1999 LP. ";
	}
}
