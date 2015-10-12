package com.github.dachhack.sprout.scenes;

import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Chrome;
import com.github.dachhack.sprout.Rankings;
import com.github.dachhack.sprout.ShatteredPixelDungeon;
import com.github.dachhack.sprout.ui.Archs;
import com.github.dachhack.sprout.ui.RedButton;
import com.github.dachhack.sprout.ui.ScrollPane;
import com.github.dachhack.sprout.ui.Window;
import com.watabou.noosa.BitmapTextMultiline;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.ui.Component;

//TODO: update this class with relevant info as new versions come out.
public class WelcomeScene extends PixelScene {

	private static final String TTL_Welcome = "Welcome!";

	private static final String TTL_Update = "v0.3.0: SPD 0.2.4c: PD 1.7.5 and Some Extras!";

	private static final String TTL_Future = "Wait What?";

	private static final String TXT_Welcome = "Sprouted Pixel Dungeon\n\n"
			+"This is a rework/expansion of Watabou's Pixel Dungeon.\n\n"
			+ "Included are all additions from Shattered Pixel Dungeon (0.2.4c) by 00-Evan\n\n"
			+ "This version was adapted for those who love to grind, level up, and collect loot\n\n"
			+ "Happy Dungeoneering!\n\n"
			+"\n\n"

			+"Sprouted Pixel Dungeon differences:\n\n"
			+"Much larger levels creating a different game play and strategy experience.\n\n"
			+"Mobs drop monster meat to facilitate longer and more in-depth exploration of the larger levels.\n\n"
			+"Dew system has been revised to create a currency for upgrade opportunities.\n\n"
			+"Dew vial has been reworked as a new pivotal resource for exploring the dungeon.\n\n"
			+"Wraiths and grave hunting is now a major part of the game progression.\n\n"
			+"Boss fights have been completely reworked to be more intense and challenging requiring completely new tactics.\n\n"
			+"Mobs now adjust strength as you go deeper in the dungeon to stay balanced with upgrades.\n\n"
			+"New levels are accessible at each stage with Four additional levels available at the end of the game.\n\n"
			+"New levels include unique enemies, items and rewards.\n\n"
			+"\n\n"
			
			+"New original items include:\n\n"
			+"Nuts\n\n"
			+"Four kinds of berries\n\n"
			+"Mushrooms\n\n"
			+"New potion type\n\n"
			+"New scroll type\n\n"			
			+"\n\n"
			
			+"Hidden rarities include:\n\n"
			+"Golden nut\n\n"
			+"The Spork\n\n"
			+"Full Moon Berry\n\n"
			+"Honey\n\n"
			+"\n\n"
			
			+"New Mobs include:\n\n"
			+"Rat Boss\n\n"
			+"Shinobi\n\n"
			+"Robots\n\n"
			+"Liches\n\n"
			+"Demon Goo\n\n"
			+"\n\n"

			+"Many other tweaks and additions have been included!\n\n";


	private static final String TXT_Update = 
			"Version 0.3.0 adds in new mini-boss fights where you can earn adamant ore.\n\n"
			+"Items max out at 15 upgrades.\n\n"
			+"You can upgrade items past 15 if they have been reinforced with adamantite ore.\n\n"	
			+"There are five pieces of adamantite available in the game obtainable by completing new mini-boss fights.\n\n"
			+"To reinforce your items, you need to take them to the blacksmiths.\n\n"
			+"He takes all your black gold when reinforcing so any black gold over 50 is wasted.\n\n"
			+"Keys to the mini-boss battles drop on ancient key levels sometime after killing 50 mobs.\n\n"
			+"\n\n"
			+"Enhancements:\n\n"
			+"Autotarget\n\n"
			+"Surprise attack indicator\n\n"
			+"Dropped items visible on level in fog of war\n\n"
			+"Keyring!\n\n"
			+"\n\n"
			+"Rebalance:\n\n"
			+"New mobs in sewers and prison rebalanced to make these stages more survivable.\n\n"
			+"Gray Rat\n\n"
			+"Brown Bat\n\n"
			+"Fossil Skeleton\n\n"
			+"\n\n"
			+"Special Halloween Item!\n\n"
			+"The Goo will drop a Bloodlust Enchanted Chainsaw if he doesn't drop a Mr Destucto\n\n"
			+"Also added a giant Venus Flytrap plant that converts your spare upgraded items to upgrade goo.\n\n"
			+"Happy Halloween!\n\n"
			+"\n\n"
			+"This new update features many new sprites from Pavel Provotorov. He's done a great job making Sprouted come alive. Many thanks!\n\n"
			         
            +"\n\n"
            +"\n\n"
			+"Version 0.2.5 adds back optional watering to the dew vial and re-works dew levels.\n\n"
			+"Added Tinkerer mini-quest to level 2 where you will choose how to grind dew drops.\n\n"
			+"Added Tinkerer mini-quest to level 12.\n\n"
			+"Added Tinkerer mini-quest to Oni level.\n\n"
			+"Reworked Shadow Yog battle to be extremely challenging.\n\n"
			+"Added protectors to the dew levels.\n\n"
			+"Mushrooms on dew levels\n\n"
			+"Orb of Zot is active now.\n\n"
			+"Added Starflower seed and plant (Thanks Shattered!).\n\n"
			+"Added Phase Pitcher seed and plant.\n\n"
			+"Added a sign to the pits to point out the scroll of teleport.\n\n"
			+"Buffed mobs throughout to balance upgrades.\n\n"
			+"Ranking reflect Shadow Yog battle.\n\n"
			+"Featuring new sprites for Books, Mushrooms, Berries by Pavel Provotorov!"
			+"\n\n"
			
			+"Version 0.2.0a is a bug fix from 0.2.0.\n\n" 
			+"Fixed Overfill mechanic\n\n"
			+"Buffed Key level mobs and removed meat drop form archers\n\n"
			+"Fixed Gold Thief gold drop\n\n"
			+"A couple other fixes to hidden items\n\n"
			+"\n\n"
			
			+"Please note that saves from the previous release (0.1.5) will not open properly on this version.\n\n"			
			+"\n\n"
			+"Major Revisions:\n\n"
			+"Water command has been removed from the dew vial.\n\n"
			+"Instead of watering you can grind dew on one of four new levels.\n\n"	
			+"Each stage has a level accessable by finding an ancient key.\n\n"	
			+"These new levels are populated by new mobs and are rich in dew.\n\n"	
			+"Each level has an opportunity to earn special rewards by dispatching 100+ mobs before you leave.\n\n"	
			+"\n\n"
			+"Each level of the demon halls is sealed and you will need to find a way to unseal to go down.\n\n"
			+"\n\n"
			+"Three new levels are available in the imp shop for post-yog play.\n\n"
			+"Completing these three levels allows you to unlock a final Yog boss.\n\n"
			+"A special trophy item is dropped if you vanquish this final Yog.\n\n"
			+"\n\n"
			+"Smaller Tweaks:\n\n"
			+"Lloyd's Beacon replaced by Mr Desructo.\n\n"
			+"Buffed wand of magic missle including buffing the disenchant function to carry over more upgrades.\n\n"
			+"Nerfed Berry Regen.\n\n"
			+"Buffed wand of magic missle including buffing the disenchant function to carry over more upgrades.\n\n"
			+"Tweaked Yog fight to make it less annoying.\n\n"
			+"Seeds no longer drop as random loot in the dungeon. (Still get from high grass.)\n\n"
			+"\n\n"
			+"\n\n"
			
			+"Sprouted Pixel Dungeon differences:\n\n"
			+"Much larger levels creating a different game play and strategy experience.\n\n"
			+"Mobs drop monster meat to facilitate longer and more in-depth exploration of the larger levels.\n\n"
			+"Dew system has been revised to create a currency for upgrade opportunities.\n\n"
			+"Dew vial has been reworked as a new pivotal resource for exploring the dungeon.\n\n"
			+"Wraiths and grave hunting is now a major part of the game progression.\n\n"
			+"Boss fights have been completely reworked to be more intense and challenging requiring completely new tactics.\n\n"
			+"Mobs now adjust strength as you go deeper in the dungeon to stay balanced with upgrades.\n\n"
			+"New levels are accessible at each stage with Four additional levels available at the end of the game.\n\n"
			+"New levels include unique enemies, items and rewards.\n\n"
			+"\n\n"
			
			+"New original items include:\n\n"
			+"Nuts\n\n"
			+"Four kinds of berries\n\n"
	        +"New potion type\n\n"
	        +"New scroll type\n\n"
	        +"\n\n"
	        
			+"Hidden rarities include:\n\n"
			+"Golden nut\n\n"
			+"The Spork\n\n"
			+"Full Moon Berry\n\n"
			+"Honey\n\n"
			+"\n\n"
	        
            +"New Mobs include:\n\n"
            +"Rat Boss\n\n"
            +"Shinobi\n\n"
            +"Robots\n\n"
            +"Liches\n\n"
            +"Demon Goo\n\n"
            +"\n\n"
            
			+"Many other tweaks and additions have been included!\n\n"
	
			;

	private static final String TXT_Future = "It seems that your current saves are from a future version of Sprouted Pixel Dungeon!\n\n"
			+ "Either you're messing around with older versions of the app, or something has gone buggy.\n\n"
			+ "Regardless, tread with caution! Your saves may contain things which don't exist in this version, "
			+ "this could cause some very weird errors to occur.";

	private static final String LNK = "https://play.google.com/store/apps/details?id=com.github.dachhack.sprout";

	@Override
	public void create() {
		super.create();

		final int gameversion = ShatteredPixelDungeon.version();

		BitmapTextMultiline title;
		BitmapTextMultiline text;

		if (gameversion == 0) {

			text = createMultiline(TXT_Welcome, 8);
			title = createMultiline(TTL_Welcome, 16);

		} else if (gameversion <= Game.versionCode) {

			text = createMultiline(TXT_Update, 6);
			title = createMultiline(TTL_Update, 9);

		} else {

			text = createMultiline(TXT_Future, 8);
			title = createMultiline(TTL_Future, 16);

		}

		int w = Camera.main.width;
		int h = Camera.main.height;

		int pw = w - 10;
		int ph = h - 50;

		title.maxWidth = pw;
		title.measure();
		title.hardlight(Window.SHPX_COLOR);

		title.x = align((w - title.width()) / 2);
		title.y = align(8);
		add(title);

		NinePatch panel = Chrome.get(Chrome.Type.WINDOW);
		panel.size(pw, ph);
		panel.x = (w - pw) / 2;
		panel.y = (h - ph) / 2;
		add(panel);

		ScrollPane list = new ScrollPane(new Component());
		add(list);
		list.setRect(panel.x + panel.marginLeft(), panel.y + panel.marginTop(),
				panel.innerWidth(), panel.innerHeight());
		list.scrollTo(0, 0);

		Component content = list.content();
		content.clear();

		text.maxWidth = (int) panel.innerWidth();
		text.measure();

		content.add(text);

		content.setSize(panel.innerWidth(), text.height());

		RedButton okay = new RedButton("Okay!") {
			@Override
			protected void onClick() {

				if (gameversion <= 32) {
					// removes all bags bought badge from pre-0.2.4 saves.
					Badges.disown(Badges.Badge.ALL_BAGS_BOUGHT);
					Badges.saveGlobal();

					// imports new ranking data for pre-0.2.3 saves.
					if (gameversion <= 29) {
						Rankings.INSTANCE.load();
						Rankings.INSTANCE.save();
					}
				}

				ShatteredPixelDungeon.version(Game.versionCode);
				Game.switchScene(TitleScene.class);
			}
		};

		/*
		 * okay.setRect(text.x, text.y + text.height() + 5, 55, 18); add(okay);
		 * 
		 * RedButton changes = new RedButton("Changes") {
		 * 
		 * @Override protected void onClick() { parent.add(new WndChanges()); }
		 * };
		 * 
		 * changes.setRect(text.x + 65, text.y + text.height() + 5, 55, 18);
		 * add(changes);
		 */

		okay.setRect((w - pw) / 2, h - 22, pw, 18);
		add(okay);

		Archs archs = new Archs();
		archs.setSize(Camera.main.width, Camera.main.height);
		addToBack(archs);

		fadeIn();
	}
}
