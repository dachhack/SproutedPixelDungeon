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

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.items.DewVial;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.Mushroom;
import com.github.dachhack.sprout.items.TownReturnBeacon;
import com.github.dachhack.sprout.items.keys.SkeletonKey;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.TinkererSprite;
import com.github.dachhack.sprout.sprites.VillagerSprite;
import com.github.dachhack.sprout.utils.Utils;
import com.github.dachhack.sprout.windows.WndQuest;
import com.github.dachhack.sprout.windows.WndTinkerer3;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Tinkerer5 extends NPC {

	{
		name = "villager";
		spriteClass = VillagerSprite.class;
	}

	private static final String TXT_DUNGEON = "We used to mine stone ore out of the mines. "
			                                  +"Once they became infested with demons, the passageways started to shift. "
			                                  +"Be careful if you go there, once you leave a floor you may return to find it completely different! ";
	
	
	private static final String TXT_DUNGEON2 = "The building directly to the East from here houses the alter of the Gods. "
			                                   +"Bringing three Norn stones to the alter will be rewarded with a special treasure. ";
	
	private static final String TXT_DUNGEON3 = "You will need this Beacon left by Otiluke. Use it when you need to return to the town. Keep it with you always.";

	@Override
	protected boolean act() {
		throwItem();
		return super.act();
	}
	
	@Override
	protected Char chooseEnemy() {
		return null;
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
	public void damage(int dmg, Object src) {
	}

	@Override
	public void add(Buff buff) {
	}


	@Override
	public void interact() {

		sprite.turnTo(pos, Dungeon.hero.pos);
		    	
	      if(first) {
		       first=false;
		       tell(TXT_DUNGEON3);		
		       Dungeon.level.drop(new TownReturnBeacon(), Dungeon.hero.pos).sprite.drop();	
	        } else if (Random.Int(2)==0) {
				tell(TXT_DUNGEON);
			} else {
				tell(TXT_DUNGEON2);
			}				
		
	}

	private void tell(String format, Object... args) {
		GameScene.show(new WndQuest(this, Utils.format(format, args)));
	}

	@Override
	public String description() {
		return "A villager. ";
	}

}
