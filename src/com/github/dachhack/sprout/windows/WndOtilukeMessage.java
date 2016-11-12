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
package com.github.dachhack.sprout.windows;

import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.items.DewVial;
import com.github.dachhack.sprout.items.Palantir;
import com.github.dachhack.sprout.scenes.PixelScene;
import com.github.dachhack.sprout.sprites.ItemSprite;
import com.github.dachhack.sprout.ui.RedButton;
import com.github.dachhack.sprout.ui.Window;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.noosa.BitmapTextMultiline;

public class WndOtilukeMessage extends Window {

	private static final String TXT_MESSAGE = "The power of the Palantir has caused Zot to lose his mind. "
			                                  +"I have managed to trap him inside and set guardians. "
			                                  +"I will now sacrifice myself to create a powerful stone golem that will protect the Palantir, Zot's prison.";
	private static final String TXT_REWARD = "Okay";
	
	private static final int WIDTH = 120;
	private static final int BTN_HEIGHT = 20;
	private static final int GAP = 2;

	public WndOtilukeMessage() {

		super();
		
		Palantir palantir = new Palantir();

		IconTitle titlebar = new IconTitle();
		titlebar.icon(new ItemSprite(palantir.image(), null));
		titlebar.label(Utils.capitalize(palantir.name()));
		titlebar.setRect(0, 0, WIDTH, 0);
		add(titlebar);

		BitmapTextMultiline message = PixelScene
				.createMultiline(TXT_MESSAGE, 6);
		message.maxWidth = WIDTH;
		message.measure();
		message.y = titlebar.bottom() + GAP;
		add(message);

		RedButton btnReward = new RedButton(TXT_REWARD) {
			@Override
			protected void onClick() {				
				hide();
			}
		};
		btnReward.setRect(0, message.y + message.height() + GAP, WIDTH,
				BTN_HEIGHT);
		add(btnReward);

		resize(WIDTH, (int) btnReward.bottom());
	}

	
}
