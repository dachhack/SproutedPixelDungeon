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
package com.github.dachhack.sprout.windows;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Chrome;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.npcs.Blacksmith;
import com.github.dachhack.sprout.actors.mobs.npcs.Tinkerer1;
import com.github.dachhack.sprout.actors.mobs.npcs.Wandmaker;
import com.github.dachhack.sprout.items.DewVial;
import com.github.dachhack.sprout.items.DewVial2;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.Mushroom;
import com.github.dachhack.sprout.items.SanChikarahDeath;
import com.github.dachhack.sprout.items.quest.Pickaxe;
import com.github.dachhack.sprout.items.wands.Wand;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.scenes.PixelScene;
import com.github.dachhack.sprout.sprites.ItemSprite;
import com.github.dachhack.sprout.ui.ItemSlot;
import com.github.dachhack.sprout.ui.RedButton;
import com.github.dachhack.sprout.ui.Window;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.noosa.BitmapTextMultiline;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Component;

public class WndDewDrawInfo extends Window {
	
	//if people don't get it after this, I quit. I just quit.

	private static final String TXT_MESSAGE = "Drawing out dew makes it so that mobs on special levels drop dew to fill your vial. "
            +"Additionally, your character is buffed with dew charge at the start of each normal level. "
            +"As long as you are dew charged, enemies drop dew to fill your vial. ";
	
	private static final String TXT_MESSAGE2 = "Each level dew charges you for a set amount of moves. "
            +"Each level also has a move goal for killing all regular generated enemies. (Not special enemies like statues and piranha) "
            +"Killing all regular enemies that were generated with the level clears that level. ";
	
	private static final String TXT_MESSAGE3 = "If you clear a level in less moves than the goal, the additional moves are added to your dew charge for the next level. "
            +"You will need to clear the levels as fast as you can to get dew upgrades. ";

	private static final String TXT_MESSAGE4 = "The dew vial will also allow you to choose which item you apply upgrades to when blessing. ";
	
	private static final String TXT_WATER = "Okay! Thanks for that!";


	private static final int WIDTH = 120;
	private static final int BTN_HEIGHT = 20;
	private static final float GAP = 2;

	public WndDewDrawInfo(final Item item) {

		super();
		
		Item dewvial = new DewVial();

		IconTitle titlebar = new IconTitle();
		titlebar.icon(new ItemSprite(dewvial.image(), null));
		titlebar.label(Utils.capitalize(dewvial.name()));
		titlebar.setRect(0, 0, WIDTH, 0);
		add(titlebar);

		BitmapTextMultiline message = PixelScene.createMultiline(TXT_MESSAGE, 6);
		message.maxWidth = WIDTH;
		message.measure();
		message.y = titlebar.bottom() + GAP;
		add(message);

		BitmapTextMultiline message2 = PixelScene.createMultiline(TXT_MESSAGE2, 6);
		message2.maxWidth = WIDTH;
		message2.measure();
		message2.y = message.y + message.height() + GAP;
		add(message2);

		BitmapTextMultiline message3 = PixelScene.createMultiline(TXT_MESSAGE3, 6);
		message3.maxWidth = WIDTH;
		message3.measure();
		message3.y = message2.y + message2.height() + GAP;
		add(message3);
		
		BitmapTextMultiline message4 = PixelScene.createMultiline(TXT_MESSAGE4, 6);
		message4.maxWidth = WIDTH;
		message4.measure();
		message4.y = message3.y + message3.height() + GAP;
		add(message4);
		
		
		RedButton btnBattle = new RedButton(TXT_WATER) {
			@Override
			protected void onClick() {
				hide();
			}
		};
		btnBattle.setRect(0, message4.y + message4.height() + GAP, WIDTH,
				BTN_HEIGHT);
		add(btnBattle);

		

		resize(WIDTH, (int) btnBattle.bottom());
	}

	
}
