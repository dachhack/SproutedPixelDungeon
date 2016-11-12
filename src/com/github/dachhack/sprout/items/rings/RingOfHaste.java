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
package com.github.dachhack.sprout.items.rings;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.actors.mobs.pets.PET;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.windows.WndDewVial;
import com.github.dachhack.sprout.windows.WndPetHaste;

public class RingOfHaste extends Ring {

	{
		name = "Ring of Haste";
	}

	@Override
	protected RingBuff buff() {
		return new Haste();
	}
	

	private PET checkpet(){
		for (Mob mob : Dungeon.level.mobs) {
			if(mob instanceof PET) {
				return (PET) mob;
			}
		}	
		return null;
	}

	@Override
	public String desc() {
		return isKnown() ? "This ring reduces the stress of movement on the wearer, allowing them to run "
				+ "at superhuman speeds. A degraded ring will instead weigh the wearer down. "
				+ "Upgrade benefit on this ring maxes out at 10 upgrades. "
				: super.desc();
	}

	public class Haste extends RingBuff {		
	}
	

	@Override
	public boolean doEquip(Hero hero) {
		
		PET heropet = checkpet();
		
		
		if (Dungeon.hero.haspet && heropet!=null && isKnown()){
			GameScene.show(new WndPetHaste(heropet,this));
		}
		
		return super.doEquip(hero);
	}
	
	@Override
	public boolean doUnequip(Hero hero, boolean collect, boolean single) {
		
		PET heropet = checkpet();
		
		if (Dungeon.hero.haspet && heropet!=null&&Dungeon.petHasteLevel>0){
			Dungeon.petHasteLevel=0;
			GLog.w("Your "+heropet.name+" is moving slower." );	  				    		
		}
		
		return super.doUnequip(hero, collect, single);
		
	}
	
	
	@Override
	public Item upgrade() {
		
		if (level>7){GLog.w("Warning! High level Rings of Haste tend to cause ...complications!");} 
		
		return super.upgrade();		

	}
	

}
