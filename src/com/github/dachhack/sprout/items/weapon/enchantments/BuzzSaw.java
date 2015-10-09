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
package com.github.dachhack.sprout.items.weapon.enchantments;

import java.util.ArrayList;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.items.DewVial;
import com.github.dachhack.sprout.items.weapon.Weapon;
import com.github.dachhack.sprout.items.weapon.melee.Chainsaw;
import com.github.dachhack.sprout.sprites.ItemSprite;
import com.github.dachhack.sprout.sprites.ItemSprite.Glowing;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Random;

public class BuzzSaw extends Weapon.Enchantment {

	private static final String TXT_BUZZ = "Bloodlust %s";
	

	private static ItemSprite.Glowing RED = new ItemSprite.Glowing(0x660022);
	

	@Override
	public boolean proc(Weapon weapon, Char attacker, Char defender, int damage) { 
		
		//int level = Math.max(0, weapon.level);
	
				
		DewVial vial = Dungeon.hero.belongings.getItem(DewVial.class);
		Chainsaw saw = Dungeon.hero.belongings.getItem(Chainsaw.class);
		
		if (vial != null) {	
						
		  int hits = Random.Int(Math.round(vial.checkVol()/10));
		  int dmg;
		
		  for (int i = 1; i <= hits + 1; i++) {
			  if (vial.checkVol()>0 && saw.turnedOn){
				  vial.sip();
			      dmg = Math.max(1, (attacker.damageRoll()- i)*2);
			      defender.damage(dmg, this);
			      GLog.i("Vrrrrrr!");    
			  }  else if (vial.checkVol()==0 && saw.turnedOn){
				  //defender.damage(Random.Int(level), this);
				  GLog.n("Your chainsaw is out of fuel!");
				  break;
			  } else if (vial.checkVol()>0 && !saw.turnedOn){
				  //defender.damage(Random.Int(level), this);
				  GLog.n("Your chainsaw is not turned on!");
				  break;
			  } else {
				  //defender.damage(Random.Int(level), this);
				  //GLog.n("Chainsaw is out of fuel!");
				  break;
			  }
			  if(!defender.isAlive()){break;}			  
		  } 

		} 
			
		return true;
		
	}
	
	

	@Override
	public String name(String weaponName) {
		return String.format(TXT_BUZZ, weaponName);
	}

	@Override
	public Glowing glowing() {
		return RED;
	}
}
