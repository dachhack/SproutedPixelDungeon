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
package com.github.dachhack.sprout.items.wands;

import com.watabou.noosa.audio.Sample;
import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Amok;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Vertigo;
import com.github.dachhack.sprout.effects.MagicMissile;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.utils.Callback;

public class WandOfAmok extends Wand {

	{
		name = "Wand of Amok";
	}

	@Override
	protected void onZap(int cell) {
		Char ch = Actor.findChar(cell);
		if (ch != null) {

			if (ch == Dungeon.hero) {
				Buff.affect(ch, Vertigo.class, Vertigo.duration(ch));
			} else {
				Buff.affect(ch, Amok.class, 3f + level());
			}

		} else {

			GLog.i("nothing happened");

		}
	}

	@Override
	protected void fx(int cell, Callback callback) {
		MagicMissile.purpleLight(curUser.sprite.parent, curUser.pos, cell,
				callback);
		Sample.INSTANCE.play(Assets.SND_ZAP);
	}

	@Override
	public String desc() {
		return "The purple light from this wand will make the target run amok "
				+ "attacking random creatures in its vicinity.";
	}
}
