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

import java.util.ArrayList;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Journal;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.blobs.Blob;
import com.github.dachhack.sprout.actors.blobs.ToxicGas;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Roots;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.items.Heap;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.potions.PotionOfStrength;
import com.github.dachhack.sprout.items.quest.CorpseDust;
import com.github.dachhack.sprout.items.wands.Wand;
import com.github.dachhack.sprout.items.wands.WandOfAmok;
import com.github.dachhack.sprout.items.wands.WandOfAvalanche;
import com.github.dachhack.sprout.items.wands.WandOfBlink;
import com.github.dachhack.sprout.items.wands.WandOfDisintegration;
import com.github.dachhack.sprout.items.wands.WandOfFirebolt;
import com.github.dachhack.sprout.items.wands.WandOfLightning;
import com.github.dachhack.sprout.items.wands.WandOfPoison;
import com.github.dachhack.sprout.items.wands.WandOfRegrowth;
import com.github.dachhack.sprout.items.wands.WandOfSlowness;
import com.github.dachhack.sprout.items.wands.WandOfTelekinesis;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.PrisonLevel;
import com.github.dachhack.sprout.levels.Room;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.plants.Plant;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.sprites.SheepSprite;
import com.github.dachhack.sprout.sprites.SokobanCornerSheepSprite;
import com.github.dachhack.sprout.sprites.WandmakerSprite;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.github.dachhack.sprout.windows.WndQuest;
import com.github.dachhack.sprout.windows.WndWandmaker;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class SheepSokobanCorner extends NPC {

	private static final String[] QUOTES = { "Baa!", "Baa?", "Baa.",
	"Baa..." };

{
name = "sheep";
spriteClass = SokobanCornerSheepSprite.class;
}


@Override
protected boolean act() {
	throwItem();
	return super.act();
}

@Override
public void damage(int dmg, Object src) {
}

@Override
public String description() {
return "This is a magic sheep. What's so magical about it? You can't kill it. "
		+ "You could probably push it out of the way though.";
}

/*  -W-1 -W  -W+1
 *  -1    P  +1
 *  W-1   W  W+1
 * 
 */

@Override
public void interact() {
	int curPos = pos;
	int movPos = pos;
	int width = Level.getWidth();
    boolean moved = false;
	int posDif = Dungeon.hero.pos-curPos;
	
	if (posDif==1) {
		movPos = curPos-1;
	} else if(posDif==-1) {
		movPos = curPos+1;
	} else if(posDif==width) {
		movPos = curPos-width;
	} else if(posDif==-width) {
		movPos = curPos+width;
	} 
	
	else if(posDif==-width+1) {
		movPos = curPos+width-1;
		
	} else if(posDif==-width-1) {
		movPos = curPos+width+1;
		
	} else if(posDif==width+1) {
		movPos = curPos-(width+1);
		
	} else if(posDif==width-1) {
		movPos = curPos-(width-1);
	}    
	
	if (movPos != pos && Level.passable[movPos] && Actor.findChar(movPos) == null){
		
		moveSprite(curPos,movPos);
		move(movPos);
		moved=true;
			
	} 
	
	if(moved){
      Dungeon.hero.sprite.move(Dungeon.hero.pos, curPos);
	  Dungeon.hero.move(curPos);
	}
	
    yell(Random.element(QUOTES));
    
}

}
