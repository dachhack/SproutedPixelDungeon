/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
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
package com.github.dachhack.sprout.items;

import java.util.ArrayList;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Bee;
import com.github.dachhack.sprout.actors.mobs.SteelBee;
import com.github.dachhack.sprout.effects.Pushing;
import com.github.dachhack.sprout.effects.Splash;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class SteelHoneypot extends Item {

	public static final String AC_SHATTER = "SHATTER";

	{
		name = "steel honeypot";
		image = ItemSpriteSheet.STL_HONEYPOT;
		defaultAction = AC_THROW;
		stackable = true;
	}

	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_SHATTER);
		return actions;
	}

	@Override
	public void execute(final Hero hero, String action) {
		if (action.equals(AC_SHATTER)) {

			hero.sprite.zap(hero.pos);

			detach(hero.belongings.backpack);

			shatter(hero, hero.pos).collect();

			hero.next();

		} else {
			super.execute(hero, action);
		}
	}

	@Override
	protected void onThrow(int cell) {
		if (Level.pit[cell]) {
			super.onThrow(cell);
		} else {
			Dungeon.level.drop(shatter(null, cell), cell);
		}
	}

	public Item shatter(Char owner, int pos) {

		if (Dungeon.visible[pos]) {
			Sample.INSTANCE.play(Assets.SND_SHATTER);
			Splash.at(pos, 0xffd500, 5);
		}

		int newPos = pos;
		if (Actor.findChar(pos) != null) {
			ArrayList<Integer> candidates = new ArrayList<Integer>();
			boolean[] passable = Level.passable;

			for (int n : Level.NEIGHBOURS4) {
				int c = pos + n;
				if (passable[c] && Actor.findChar(c) == null) {
					candidates.add(c);
				}
			}

			newPos = candidates.size() > 0 ? Random.element(candidates) : -1;
		}

		if (newPos != -1) {
			SteelBee bee = new SteelBee();
			bee.spawn(Dungeon.depth);
			bee.setPotInfo(pos, owner);
			bee.HP = bee.HT;
			bee.pos = newPos;
			bee.state = bee.HUNTING;

			GameScene.add(bee);
			Actor.addDelayed(new Pushing(bee, pos, newPos), -1f);

			bee.sprite.alpha(0);
			bee.sprite.parent.add(new AlphaTweener(bee.sprite, 1, 0.15f));

			Sample.INSTANCE.play(Assets.SND_BEE);
			return new SteelShatteredPot().setBee(bee);
		} else {
			return this;
		}
	}

	@Override
	public boolean isUpgradable() {
		return false;
	}

	@Override
	public boolean isIdentified() {
		return true;
	}

	@Override
	public int price() {
		return 30 * quantity;
	}

	@Override
	public String info() {
		return "This large honeypot is only really lined with honey, instead it houses a giant steel bee! "
				+ "These sorts of massive bees usually stay in their hives, perhaps the pot is some sort of specialized trapper's cage? "
				+ "The bee seems pretty content inside the pot with its honey, and buzzes at you warily when you look at it.";
	}

	// The bee's broken 'home', all this item does is let its bee know where it
	// is, and who owns it (if anyone).
	public static class SteelShatteredPot extends Item {

		{
			name = "shattered steel honeypot";
			image = ItemSpriteSheet.STL_SHATTPOT;
			stackable = false;
		}

		private int myBee;
		private int beeDepth;

		public Item setBee(Char bee) {
			myBee = bee.id();
			beeDepth = Dungeon.depth;
			return this;
		}

		@Override
		public boolean doPickUp(Hero hero) {
			if (super.doPickUp(hero)) {
				setHolder(hero);
				return true;
			} else
				return false;
		}

		@Override
		public void doDrop(Hero hero) {
			super.doDrop(hero);
			updateBee(hero.pos, null);
		}

		@Override
		protected void onThrow(int cell) {
			super.onThrow(cell);
			updateBee(cell, null);
		}

		public void setHolder(Char holder) {
			updateBee(holder.pos, holder);
		}

		public void goAway() {
			updateBee(-1, null);
		}

		private void updateBee(int cell, Char holder) {
			// important, as ids are not unique between depths.
			if (Dungeon.depth != beeDepth)
				return;

			SteelBee bee = (SteelBee) Actor.findById(myBee);
			if (bee != null)
				bee.setPotInfo(cell, holder);
		}

		@Override
		public boolean isUpgradable() {
			return false;
		}

		@Override
		public boolean isIdentified() {
			return true;
		}

		@Override
		public String info() {
			String info = "The pot has been shattered, only the sticky honey that lines its walls is holding it together, and it is slowly coming apart.";

			if (Actor.findById(myBee) != null)
				info += "\n\nDespite its broken state, the bee still seems quite fond of the pot, and is understandably quite mad.";
			else
				info += "\n\nNow that its bee is gone, you can't think of a use for this wad of broken clay and drying honey.";

			return info;
		}

		private static final String MYBEE = "mybee";
		private static final String BEEDEPTH = "beedepth";

		@Override
		public void storeInBundle(Bundle bundle) {
			super.storeInBundle(bundle);
			bundle.put(MYBEE, myBee);
			bundle.put(BEEDEPTH, beeDepth);
		}

		@Override
		public void restoreFromBundle(Bundle bundle) {
			super.restoreFromBundle(bundle);
			myBee = bundle.getInt(MYBEE);
			beeDepth = bundle.getInt(BEEDEPTH);
		}
	}
}
