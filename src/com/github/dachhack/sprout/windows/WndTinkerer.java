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
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Dewcharge;
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

public class WndTinkerer extends Window {

	private static final String TXT_MESSAGE = "Thanks for the Toadstool Mushroom! "
			                                  +"I can upgrade your dew vial for you. "
			                                  +"I can make it either draw out dew from certain vanquished enemies, "
			                                  +"or I can make it able to regrow the surrounding dungeon by watering with dew. ";
	
	private static final String TXT_MESSAGE_WATER = "Water with dew allows you to grow high grass around you once you have 50 drops in your vial. "
			                                        +"Watering costs 2 drops but you will be able to find more drop, nuts, berries, and seeds by trampling the grass. ";
	
	
	private static final String TXT_MESSAGE_DRAW = "Drawing out dew makes it so that mobs on special levels drop dew to fill your vial. "
			                                        +"Additionally, your character is buffed with dew charge at the start of each normal level. "
			                                        +"As long as you are dew charged, enemies drop dew to fill your vial. "
			                                        +"Each level dew charges you for a set amount of moves. "
			                                        +"Each level also has a move goal for killing all regular generated enemies. (Not special enemies like statues and piranha) "
			                                        +"Killing all regular enemies that were generated with the level clears that level. "
			                                        +"If you clear a level in less moves than the goal, the additional moves are added to your dew charge for the next level. "
			                                        +"You will need to clear the levels as fast as you can to get dew upgrades. "
			                                        +"The dew vial will also allow you to choose which item you apply upgrades to when blessing. ";
	
	private static final String TXT_WATER = "Water with Dew";
	private static final String TXT_DRAW = "Draw Out Dew";
	private static final String TXT_DRAW_INFO = "Tell me more about Draw Out Dew";

	private static final String TXT_FARAWELL = "Good luck in your quest, %s!";
	private static final String TXT_FARAWELL_DRAW = "Good luck in your quest, %s! I'll give you a head start drawing out dew!";


	private static final int WIDTH = 120;
	private static final int BTN_HEIGHT = 20;
	private static final float GAP = 2;

	public WndTinkerer(final Tinkerer1 tinkerer, final Item item) {

		super();

		IconTitle titlebar = new IconTitle();
		titlebar.icon(new ItemSprite(item.image(), null));
		titlebar.label(Utils.capitalize(item.name()));
		titlebar.setRect(0, 0, WIDTH, 0);
		add(titlebar);

		BitmapTextMultiline message = PixelScene
				.createMultiline(TXT_MESSAGE, 6);
		message.maxWidth = WIDTH;
		message.measure();
		message.y = titlebar.bottom() + GAP;
		add(message);

		RedButton btnBattle = new RedButton(TXT_WATER) {
			@Override
			protected void onClick() {
				selectUpgrade(tinkerer, 1);
			}
		};
		btnBattle.setRect(0, message.y + message.height() + GAP, WIDTH,
				BTN_HEIGHT);
		add(btnBattle);

		/*
		BitmapTextMultiline message_draw = PixelScene
				.createMultiline(TXT_MESSAGE_DRAW, 6);
		message_draw.maxWidth = WIDTH;
		message_draw.measure();
		message_draw.y = btnBattle.bottom() + GAP;
		add(message_draw);
		*/
		
		RedButton btnNonBattle = new RedButton(TXT_DRAW) {
			@Override
			protected void onClick() {
				selectUpgrade(tinkerer, 2);
			}
		};
		
		btnNonBattle.setRect(0, btnBattle.bottom() + GAP, WIDTH, BTN_HEIGHT);
		add(btnNonBattle);
		
		RedButton btnNonBattle2 = new RedButton(TXT_DRAW_INFO) {
			@Override
			protected void onClick() {
				GameScene.show(new WndDewDrawInfo(item));				
			}
		};
		btnNonBattle2.setRect(0, btnNonBattle.bottom() + GAP, WIDTH, BTN_HEIGHT);
		add(btnNonBattle2);

		resize(WIDTH, (int) btnNonBattle2.bottom());
	}

	private void selectUpgrade(Tinkerer1 tinkerer, int type) {

		hide();
		
		Mushroom mushroom = Dungeon.hero.belongings.getItem(Mushroom.class);
		mushroom.detach(Dungeon.hero.belongings.backpack);
		
		if (type==1){
			
			Dungeon.dewWater=true;
			
		} else if (type==2){
			
			Dungeon.dewDraw=true;
		}
		
		if (type==1){
		    tinkerer.yell(Utils.format(TXT_FARAWELL, Dungeon.hero.givenName()));
		} else if (type==2){
			tinkerer.yell(Utils.format(TXT_FARAWELL_DRAW, Dungeon.hero.givenName()));
			Statistics.prevfloormoves=500;
			Buff.prolong(Dungeon.hero, Dewcharge.class, Dewcharge.DURATION+50);
	        GLog.p("You feel the dungeon charge with dew!");
		}
		
		tinkerer.destroy();

		tinkerer.sprite.die();

		//Wandmaker.Quest.complete();
	}
}
