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
package com.github.dachhack.sprout.plants;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.blobs.Blob;
import com.github.dachhack.sprout.actors.blobs.Fire;
import com.github.dachhack.sprout.actors.blobs.WaterOfTransmutation;
import com.github.dachhack.sprout.actors.blobs.WaterOfUpgradeEating;
import com.github.dachhack.sprout.actors.blobs.WellWater;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Slow;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.particles.FlameParticle;
import com.github.dachhack.sprout.items.potions.PotionOfLiquidFlame;
import com.github.dachhack.sprout.items.potions.PotionOfMight;
import com.github.dachhack.sprout.items.potions.PotionOfOverHealing;
import com.github.dachhack.sprout.items.potions.PotionOfStrength;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.plants.Plant.Seed;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;

public class Flytrap extends Plant {

	private static final String TXT_DESC = "This plant has a giant mouth and drips caustic ooze. "
			                               +"Tossing an item into its mouth has a chance to harvest upgrade goo.";

	{
		image = 15;
		plantName = "Upgrade eater";
	}

	@Override
	public void activate(Char ch) {
		if (ch==null){
		 if (WellWater.affectCellPlant(pos)){
			super.activate(null);	
		    }
		}
	}

	@Override
	public String desc() {
		return TXT_DESC;
	}

	public static class Seed extends Plant.Seed {
		{
			plantName = "Upgrade eater";

			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_FLYTRAP;

			plantClass = Flytrap.class;
			alchemyClass = PotionOfOverHealing.class;				
		}

		@Override
		public String desc() {
			return TXT_DESC;
		}
		
		@Override
		public Plant couch(int pos) {
			GameScene.add(Blob.seed(pos, 1, WaterOfUpgradeEating.class));	
		    return super.couch(pos);		    
		}
	}
	
		
	public static boolean checkWater(){
		
	WellWater water = (WellWater) Dungeon.level.blobs.get(WaterOfUpgradeEating.class);
	  if (water == null) {
		return false;
		} else if (water != null && water.volume==0) {
	    return false;
		} else {
		return true;
		}
	 } 
}
