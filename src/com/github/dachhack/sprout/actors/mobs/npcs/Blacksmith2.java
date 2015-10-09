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
package com.github.dachhack.sprout.actors.mobs.npcs;

import java.util.Collection;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Journal;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.items.AdamantArmor;
import com.github.dachhack.sprout.items.AdamantRing;
import com.github.dachhack.sprout.items.AdamantWand;
import com.github.dachhack.sprout.items.AdamantWeapon;
import com.github.dachhack.sprout.items.EquipableItem;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.SanChikarah;
import com.github.dachhack.sprout.items.SanChikarahDeath;
import com.github.dachhack.sprout.items.SanChikarahLife;
import com.github.dachhack.sprout.items.SanChikarahTranscend;
import com.github.dachhack.sprout.items.armor.Armor;
import com.github.dachhack.sprout.items.quest.DarkGold;
import com.github.dachhack.sprout.items.quest.Pickaxe;
import com.github.dachhack.sprout.items.rings.Ring;
import com.github.dachhack.sprout.items.scrolls.ScrollOfUpgrade;
import com.github.dachhack.sprout.items.wands.Wand;
import com.github.dachhack.sprout.items.weapon.melee.MeleeWeapon;
import com.github.dachhack.sprout.items.weapon.missiles.Boomerang;
import com.github.dachhack.sprout.levels.Room;
import com.github.dachhack.sprout.levels.Room.Type;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.BlacksmithSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.windows.WndBlacksmith;
import com.github.dachhack.sprout.windows.WndBlacksmith2;
import com.github.dachhack.sprout.windows.WndQuest;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Blacksmith2 extends NPC {


	private static final String TXT_LOOKS_BETTER = "your %s pulsates with magical energy. ";
	private static final String TXT_GET_LOST = "I'm busy. Get lost!";
	private static final String TXT2 = "My brother and I make all the items in this dungeon. "
			                          +"He melts down two upgraded items to enhance one of them. "
			                          +"My specialty is reinforcing items with adamantite. "
			                          +"Come back to me when you have 50 dark gold and some adamantite for me to work with. " ;
	
	private static final String TXT3 = "Oh ho! Looks like you have some adamantite there. "
                                     +"I can reinforce an item with adamantite if you wish. "
                                     +"Reinforced items can handle higher levels of magical upgrade. "
                                     +"It'll cost you though!. "
                                     +"Come back to me when you have 50 dark gold. " ;
	
	

	{
		name = "troll blacksmith";
		spriteClass = BlacksmithSprite.class;
	}
	

	@Override
	protected boolean act() {
		throwItem();
		return super.act();
	}

	@Override
	public void interact() {

		sprite.turnTo(pos, Dungeon.hero.pos);
		
		
		DarkGold gold = Dungeon.hero.belongings.getItem(DarkGold.class);
		if (!checkAdamant()) {
			tell(TXT2);
		} else if (gold == null || gold.quantity() < 50) {
			tell(TXT3);
		} else if (checkAdamant() && gold != null && gold.quantity() > 49){
		GameScene.show(new WndBlacksmith2(this, Dungeon.hero));
		} else {
			tell(TXT2);
		}
		
	}

	public static String verify(Item item1, Item item2) {
	
		if (item1 == item2) {
			return "Select 2 different items, not the same item twice!";
		}

		if (!item1.isIdentified()) {
			return "I need to know what I'm working with, identify first!";
		}

		if (item1.cursed) {
			return "I don't work with cursed items!";
		}
		
		if (item1.reinforced) {
			return "This is already as strong as it gets!";
		}

		if (item1.level < 0) {
			return "This is junk, the quality is too poor!";
		}

		if (!item1.isUpgradable()) {
			return "I can't reforge these items!";
		}
		
		if(item1 instanceof Armor && item2 instanceof AdamantArmor){
			return null;			
		}
		
		if(item1 instanceof MeleeWeapon && item2 instanceof AdamantWeapon){
			return null;
		}
		
		if(item1 instanceof Boomerang && item2 instanceof AdamantWeapon){
			return null;
		}
		
		if(item1 instanceof Wand && item2 instanceof AdamantWand){
			return null;
		}
		
		if(item1 instanceof Ring && item2 instanceof AdamantRing){
			return null;
		}
		
		return "This won't work. Pick and item and a matching adamantite item. ";
		
	}
	
	public static void upgrade(Item item1, Item item2) {
		
		item1.reinforced=true;
		item2.detach(Dungeon.hero.belongings.backpack);
		DarkGold gold = Dungeon.hero.belongings.getItem(DarkGold.class);
		if (gold == null || gold.quantity() > 49) {
			gold.detachAll(Dungeon.hero.belongings.backpack);
		}
		
		GLog.p(TXT_LOOKS_BETTER, item1.name());
		Dungeon.hero.spendAndNext(2f);
		Badges.validateItemLevelAquired(item1);
		
	}
	
	
	private void tell(String text) {
		GameScene.show(new WndQuest(this, text));		
	}

	
	public static boolean checkAdamant() {
		AdamantArmor armor1 = Dungeon.hero.belongings.getItem(AdamantArmor.class);
		AdamantWeapon weapon1 = Dungeon.hero.belongings.getItem(AdamantWeapon.class);
		AdamantRing ring1 = Dungeon.hero.belongings.getItem(AdamantRing.class);
		AdamantWand wand1 = Dungeon.hero.belongings.getItem(AdamantWand.class);
		
		if(armor1!=null ||  weapon1!=null || ring1!=null || wand1!=null) {
			return true;
		}
		   return false;		
	}
	
	
	

	@Override
	public int defenseSkill(Char enemy) {
		return 1000;
	}

	@Override
	public void damage(int dmg, Object src) {
	}

	@Override
	public void add(Buff buff) {
	}

	@Override
	public boolean reset() {
		return true;
	}

	@Override
	public String description() {
		return "This troll blacksmith looks like all trolls look: he is tall and lean, and his skin resembles stone "
				+ "in both color and texture. The troll blacksmith is tinkering with unproportionally small tools.";
	}

	
}
