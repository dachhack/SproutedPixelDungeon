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

import com.github.dachhack.sprout.Chrome;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.ShatteredPixelDungeon;
import com.github.dachhack.sprout.scenes.PixelScene;
import com.github.dachhack.sprout.ui.Window;
import com.watabou.input.Touchscreen.Touch;
import com.watabou.noosa.BitmapTextMultiline;
import com.watabou.noosa.Game;
import com.watabou.noosa.TouchArea;
import com.watabou.utils.SparseArray;

public class WndStory extends Window {

	private static final int WIDTH_P = 120;
	private static final int WIDTH_L = 144;
	private static final int MARGIN = 6;

	private static final float bgR = 0.77f;
	private static final float bgG = 0.73f;
	private static final float bgB = 0.62f;

	public static final int ID_SEWERS = 0;
	public static final int ID_PRISON = 1;
	public static final int ID_CAVES = 2;
	public static final int ID_METROPOLIS = 3;
	public static final int ID_HALLS = 4;
	public static final int ID_SOKOBAN1 = 5;
	public static final int ID_SOKOBAN2 = 6;
	public static final int ID_SOKOBAN3 = 7;
	public static final int ID_SOKOBAN4 = 8;
	public static final int ID_SAFELEVEL = 9;
	public static final int ID_TOWN = 10;
	public static final int ID_ZOT = 11;

	private static final SparseArray<String> CHAPTERS = new SparseArray<String>();

	static {
		CHAPTERS.put(
				ID_SEWERS,
				"The Dungeon lies right beneath the City, its upper levels actually constitute the City's sewer system.\n\n "
						+ "As dark energy has crept up from below the usually harmless sewer creatures have become more and more "
						+ "dangerous. The city sends guard patrols down here to try and maintain safety for those above, but "
						+ "they are slowly failing.\n\n This place is dangerous, but at least the evil magic at work here is weak.");

		CHAPTERS.put(
				ID_PRISON,
				"Many years ago an underground prison was built here for the most dangerous criminals. At the time it seemed "
						+ "like a very clever idea, because this place indeed was very hard to escape. But soon dark miasma started to permeate "
						+ "from below, driving prisoners and guards insane. In the end the prison was abandoned, though some convicts "
						+ "were left locked up here.");

		CHAPTERS.put(
				ID_CAVES,
				"The caves, which stretch down under the abandoned prison, are sparcely populated. They lie too deep to be exploited "
						+ "by the City and they are too poor in minerals to interest the dwarves. In the past there was a trade outpost "
						+ "somewhere here on the route between these two states, but it has perished since the decline of Dwarven Metropolis. "
						+ "Only omnipresent gnolls and subterranean animals dwell here now.");

		CHAPTERS.put(
				ID_METROPOLIS,
				"Dwarven Metropolis was once the greatest of dwarven city-states. In its heyday the mechanized army of dwarves "
						+ "has successfully repelled the invasion of the old god and his demon army. But it is said, that the returning warriors "
						+ "have brought seeds of corruption with them, and that victory was the beginning of the end for the underground kingdom.");

		CHAPTERS.put(
				ID_HALLS,
				"In the past these levels were the outskirts of Metropolis. After the costly victory in the war with the old god "
						+ "dwarves were too weakened to clear them of remaining demons. Gradually demons have tightened their grip on this place "
						+ "and now it's called Demon Halls.\n\n"
						+ "Very few adventurers have ever descended this far...");
		CHAPTERS.put(
				ID_SOKOBAN1,
				"I have devised a method for creating extra-planar spaces in which to hide my belongings.\n\n "
						+"Oddly, strange breeds of sheep have materialized in these spaces. "
						+"Maybe I can use these ovine denizens to my advantage. \n\n"
						+"-Otiluke, 1345 LP. ");
		CHAPTERS.put(
				ID_SOKOBAN2,
				"Having mastered the elements required for creating my spaces, "
						+"I am now set out to build a grand fortress in which to hide my valuables. \n\n"
						+"-Otiluke, 1348 LP. ");
		CHAPTERS.put(
				ID_SOKOBAN3,
				"My powers have grown stronger since I created my first space.\n\n "
						+"I find myself able to alter time and position in ways I have never thought possible.\n\n "
						+"My activities have created ripples in the magical ethosphere. "
						+"On clear days I am able to capture echoes of some disturbance focused on my presence. \n\n"
						+"-Otiluke, 1355 LP. ");
		CHAPTERS.put(
				ID_SOKOBAN4,
				"My efforts to stop my pursuers have only slowed their progress.\n\n "
						+"I am now remembering the strange spaces where I hid some of my valuables centuries ago. "
						+"The items themselves are no longer of importance to me, "
						+"but the source of the odd power generating the sheep eluded me for years.\n\n "
						+"Perhaps this power, once illuminated, will be a source of succor. "
						+"I fear I have little options left. \n\n"
						+"-Otiluke, 1617 LP. ");
		CHAPTERS.put(
				ID_SAFELEVEL,
				"A small space carved out of the fabric of time serves well to keep me replenished. "
				+"I have been able to rest here and store some of my items to relieve my burden.");
		CHAPTERS.put(
				ID_TOWN,
				"With what little power I have left I have "
						+"momentarily bested Zot and obtained his orb of power.\n\n "
						+"Although its power is warded against me, I can transpose "
						+"objects from this world into the heart of the orb.\n\n "
						+"I hear a rushing of power in the distance... Zot approaches!\n\n "
						+"My last act will be to return to Dolyahaven, my birthplace "
						+"and seal this script into the orb.\n\n Zot will need to break "
						+"the orb and source of his power to follow me.\n\n "
						+"-Otiluke, 1999 LP. ");
		CHAPTERS.put(
				ID_ZOT,
				"You gaze into the Palantir you are sucked into the power of the sphere.\n\n"
				+"You sense a great evil lurking nearby.");
	};

	private BitmapTextMultiline tf;

	private float delay;

	public WndStory(String text) {
		super(0, 0, Chrome.get(Chrome.Type.SCROLL));

		tf = PixelScene.createMultiline(text, 7);
		tf.maxWidth = ShatteredPixelDungeon.landscape() ? WIDTH_L - MARGIN * 2
				: WIDTH_P - MARGIN * 2;
		tf.measure();
		tf.ra = bgR;
		tf.ga = bgG;
		tf.ba = bgB;
		tf.rm = -bgR;
		tf.gm = -bgG;
		tf.bm = -bgB;
		tf.x = MARGIN;
		add(tf);

		add(new TouchArea(chrome) {
			@Override
			protected void onClick(Touch touch) {
				hide();
			}
		});

		resize((int) (tf.width() + MARGIN * 2),
				(int) Math.min(tf.height(), 180));
	}

	@Override
	public void update() {
		super.update();

		if (delay > 0 && (delay -= Game.elapsed) <= 0) {
			shadow.visible = chrome.visible = tf.visible = true;
		}
	}

	public static void showChapter(int id) {

		if (Dungeon.chapters.contains(id)) {
			return;
		}

		String text = CHAPTERS.get(id);
		if (text != null) {
			WndStory wnd = new WndStory(text);
			if ((wnd.delay = 0.6f) > 0) {
				wnd.shadow.visible = wnd.chrome.visible = wnd.tf.visible = false;
			}

			Game.scene().add(wnd);

			Dungeon.chapters.add(id);
		}
	}
}
