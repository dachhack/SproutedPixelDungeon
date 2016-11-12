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

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.items.PuddingCup;
import com.github.dachhack.sprout.items.TownReturnBeacon;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.OtilukeNPCSprite;
import com.github.dachhack.sprout.utils.Utils;
import com.github.dachhack.sprout.windows.WndQuest;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class OtilukeNPC extends NPC {

	{
		name = "Otiluke";
		spriteClass = OtilukeNPCSprite.class;
	}
	
	protected static final float SPAWN_DELAY = 2f;

	private static final String TXT_DUNGEON = "Thank you! Zot imprisoned me and left my powers in the stone golem you defeated. "
			                                   +"Let's go back to Dolyahaven!";	                                           		
	
	private static final String TXT_DUNGEON2 = "Thank you! Zot imprisoned me and left my powers in the stone golem you defeated. "
                                                +"Take this Beacon and let's go back to Dolyahaven!";
	
	private static final String TXT_DUNGEON3 = "Thank you for rescuing me! You have rid the universe of a great evil! ";
	private static final String TXT_DUNGEON4 = "Thank you for rescuing me! You have rid the universe of a great evil! "
			                                    +"Have a pudding cup, you earned it!";


	@Override
	protected boolean act() {
		throwItem();
		return super.act();
	}
	
	private boolean first=true;
	
	private static final String FIRST = "first";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(FIRST, first);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		first = bundle.getBoolean(FIRST);
	}

	@Override
	public int defenseSkill(Char enemy) {
		return 1000;
	}

	@Override
	public String defenseVerb() {
		return "absorbed";
	}
	
	@Override
	protected Char chooseEnemy() {
		return null;
	}

	@Override
	public void damage(int dmg, Object src) {
	}

	@Override
	public void add(Buff buff) {
	}

	public static OtilukeNPC spawnAt(int pos) {
		if (Level.passable[pos] && Actor.findChar(pos) == null) {
          
			OtilukeNPC w = new OtilukeNPC();
			w.pos = pos;		
			GameScene.add(w, SPAWN_DELAY);			

			return w;
  			
		} else {
			return null;
		}
	}
	
	
	@Override
	public void interact() {

		sprite.turnTo(pos, Dungeon.hero.pos);
		
		TownReturnBeacon beacon = Dungeon.hero.belongings.getItem(TownReturnBeacon.class);
		
			 if(Badges.checkOtilukeRescued()){
				 if(Random.Int(100)<5){				 
				    tell(TXT_DUNGEON4);
				    Dungeon.level.drop(new PuddingCup(), Dungeon.hero.pos).sprite.drop();
				 } else {
				  tell(TXT_DUNGEON3); 
				 }
			 }
			   else if(first && beacon==null) {
			   Badges.validateOtilukeRescued();
		       first=false;
		       tell(TXT_DUNGEON2);		
		       Dungeon.level.drop(new TownReturnBeacon(), Dungeon.hero.pos).sprite.drop();	
	        } else {
	        	Badges.validateOtilukeRescued();
				tell(TXT_DUNGEON);			
			}				
		
	}

	private void tell(String format, Object... args) {
		GameScene.show(new WndQuest(this, Utils.format(format, args)));
	}

	@Override
	public String description() {
		return "Otiluke! In the flesh! ";
	}

}
