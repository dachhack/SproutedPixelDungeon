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
package com.github.dachhack.sprout.items.scrolls;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Invisibility;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokoban;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokobanCorner;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokobanSwitch;
import com.github.dachhack.sprout.effects.particles.ShadowParticle;
import com.github.dachhack.sprout.items.wands.WandOfBlink;
import com.github.dachhack.sprout.items.wands.WandOfFlock.Sheep;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;

public class ScrollOfTeleportation extends Scroll {

	public static final String TXT_TELEPORTED = "In a blink of an eye you were teleported to another location of the level.";

	public static final String TXT_NO_TELEPORT = "Strong magic aura of this place prevents you from teleporting!";
	
	public static final String TXT_DEACTIVATE = "This portal appears to be deactivated right now...";

	{
		name = "Scroll of Teleportation";
		consumedValue = 10;
	}

	@Override
	protected void doRead() {

		Sample.INSTANCE.play(Assets.SND_READ);
		Invisibility.dispel();

		teleportHero(curUser);
		setKnown();

		curUser.spendAndNext(TIME_TO_READ);
	}

	public static void teleportHero(Hero hero) {

		int count = 10;
		int pos;
		do {
			pos = Dungeon.level.randomRespawnCell();
			if (count-- <= 0) {
				break;
			}
		} while (pos == -1);

		if (pos == -1) {

			GLog.w(TXT_NO_TELEPORT);

		} else {

			WandOfBlink.appear(hero, pos);
			Dungeon.level.press(pos, hero);
			Dungeon.observe();

			GLog.i(TXT_TELEPORTED);

		}
	}

	public static void teleportHeroLocation(Hero hero, int spot) {
		
		Char ch = Actor.findChar(spot);
		boolean sheepchk = false;
		
		if (ch!=null && (ch instanceof SheepSokoban || ch instanceof SheepSokobanSwitch || ch instanceof SheepSokobanCorner || ch instanceof Sheep)){
			sheepchk = true;
		}

		if (Level.passable[spot] && (Actor.findChar(spot) == null || sheepchk)) {
			
			//GLog.i("clear");
			
			if (Actor.findChar(spot) != null && sheepchk){
				Camera.main.shake(2, 0.3f);
				ch.destroy();
				ch.sprite.killAndErase();
				ch.sprite.emitter().burst(ShadowParticle.UP, 5);
				Level.set(spot, Terrain.WOOL_RUG);
				GameScene.updateMap(spot);
			}
			
			WandOfBlink.appear(hero, spot);
			Dungeon.level.press(spot, hero);
			Dungeon.observe();

			GLog.i(TXT_TELEPORTED);
		} 
		
		
		else {
		
		int count = 10;
		int pos;
		do {
			pos = Dungeon.level.randomRespawnCell();
			if (count-- <= 0) {
				break;
			}
		} while (pos == -1);

		if (pos == -1) {

			GLog.w(TXT_DEACTIVATE);

		} else {

			WandOfBlink.appear(hero, pos);
			Dungeon.level.press(pos, hero);
			Dungeon.observe();

			GLog.i(TXT_TELEPORTED);

		}
	  }
	}

	@Override
	public String desc() {
		return "The spell on this parchment instantly transports the reader "
				+ "to a random location on the dungeon level. It can be used "
				+ "to escape a dangerous situation, but the unlucky reader might "
				+ "find himself in an even more dangerous place.";
	}

	@Override
	public int price() {
		return isKnown() ? 40 * quantity : super.price();
	}
}
