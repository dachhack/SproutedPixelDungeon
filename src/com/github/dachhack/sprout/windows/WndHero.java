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

import java.util.HashSet;
import java.util.Locale;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Dewcharge;
import com.github.dachhack.sprout.actors.buffs.Hunger;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.actors.mobs.pets.PET;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.food.Blackberry;
import com.github.dachhack.sprout.items.food.Blueberry;
import com.github.dachhack.sprout.items.food.ChargrilledMeat;
import com.github.dachhack.sprout.items.food.Cloudberry;
import com.github.dachhack.sprout.items.food.FrozenCarpaccio;
import com.github.dachhack.sprout.items.food.FullMoonberry;
import com.github.dachhack.sprout.items.food.Meat;
import com.github.dachhack.sprout.items.food.Moonberry;
import com.github.dachhack.sprout.items.food.MysteryMeat;
import com.github.dachhack.sprout.items.food.Nut;
import com.github.dachhack.sprout.items.food.ToastedNut;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.plants.Plant;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.scenes.PixelScene;
import com.github.dachhack.sprout.sprites.CharSprite;
import com.github.dachhack.sprout.sprites.HeroSprite;
import com.github.dachhack.sprout.ui.BuffIndicator;
import com.github.dachhack.sprout.ui.HealthBar;
import com.github.dachhack.sprout.ui.RedButton;
import com.github.dachhack.sprout.ui.Window;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.utils.Utils;
import com.watabou.gltextures.SmartTexture;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;

public class WndHero extends WndTabbed {

	private static final String TXT_STATS = "Stats";
	private static final String TXT_BUFFS = "Buffs";
	private static final String TXT_PET = "Pet";

	private static final String TXT_HEALS = "%+dHP";
	
	private static final String TXT_EXP = "Experience";
	private static final String TXT_STR = "Strength";
	private static final String TXT_KILLS = "Kills";
	private static final String TXT_BREATH = "Breath Weapon";
	private static final String TXT_SPIN = "Spinneretes";
	private static final String TXT_STING = "Stinger";
	private static final String TXT_FEATHERS = "Feathers";
	private static final String TXT_SPARKLE = "Wand Attack";
	private static final String TXT_ATTACK = "Attack Skill";
	private static final String TXT_HEALTH = "Health";
	private static final String TXT_GOLD = "Gold Collected";
	private static final String TXT_DEPTH = "Maximum Depth";
	private static final String TXT_MOVES = "Game Moves";
	private static final String TXT_MOVES2 = "Floor Moves";
	private static final String TXT_MOVES3 = "Floor Move Goal";
	private static final String TXT_MOVES4 = "Prev Under Goal";
	private static final String TXT_HUNGER = "Hunger";

	private static final int WIDTH = 100;
	private static final int TAB_WIDTH = 40;

	private StatsTab stats;
	private PetTab pet;
	private BuffsTab buffs;

	private SmartTexture icons;
	private TextureFilm film;
	
	
	private PET checkpet(){
		for (Mob mob : Dungeon.level.mobs) {
			if(mob instanceof PET) {
				return (PET) mob;
			}
		}	
		return null;
	}

	public WndHero() {

		super();

		icons = TextureCache.get(Assets.BUFFS_LARGE);
		film = new TextureFilm(icons, 16, 16);

		stats = new StatsTab();
		add(stats);
		
		PET heropet = checkpet();
		
		if (heropet!=null){
		  pet = new PetTab(heropet);
		  add(pet);
		}
		
		buffs = new BuffsTab();
		add(buffs);
		
		
		add(new LabeledTab(TXT_STATS) {
			@Override
			protected void select(boolean value) {
				super.select(value);
				stats.visible = stats.active = selected;
			};
		});
		

		if (heropet!=null){
		add(new LabeledTab(TXT_PET) {
			@Override
			protected void select(boolean value) {
				super.select(value);
				pet.visible = pet.active = selected;
			};
		});
		}

		add(new LabeledTab(TXT_BUFFS) {
			@Override
			protected void select(boolean value) {
				super.select(value);
				buffs.visible = buffs.active = selected;
			};
		});
		
		resize(WIDTH, (int) Math.max(stats.height(), buffs.height()));

		layoutTabs();

		select(0);
	}

	private class StatsTab extends Group {

		private static final String TXT_TITLE = "Level %d %s";
		private static final String TXT_CATALOGUS = "Catalogus";
		private static final String TXT_JOURNAL = "Journal";

		private static final int GAP = 5;

		private float pos;

		public StatsTab() {

			Hero hero = Dungeon.hero;

			IconTitle title = new IconTitle();
			title.icon(HeroSprite.avatar(hero.heroClass, hero.tier()));
			title.label(Utils.format(TXT_TITLE, hero.lvl, hero.className())
					.toUpperCase(Locale.ENGLISH), 9);
			title.color(Window.SHPX_COLOR);
			title.setRect(0, 0, WIDTH, 0);
			add(title);

			RedButton btnCatalogus = new RedButton(TXT_CATALOGUS) {
				@Override
				protected void onClick() {
					hide();
					GameScene.show(new WndCatalogus());
				};
				@Override
				protected boolean onLongClick() {
					Hero heroToBuff = Dungeon.hero;
					if (heroToBuff.pos==Dungeon.level.entrance && heroToBuff.belongings.weapon == null ){
					heroToBuff.heroClass.playtest(heroToBuff);
					}
					return true;
				};
			};
			btnCatalogus.setRect(0, title.height(),
					btnCatalogus.reqWidth() + 2, btnCatalogus.reqHeight() + 2);
			add(btnCatalogus);

			RedButton btnJournal = new RedButton(TXT_JOURNAL) {
				@Override
				protected void onClick() {
					hide();
					GameScene.show(new WndJournal());
				}
			};
			btnJournal.setRect(btnCatalogus.right() + 1, btnCatalogus.top(),
					btnJournal.reqWidth() + 2, btnJournal.reqHeight() + 2);
			add(btnJournal);

			pos = btnCatalogus.bottom() + GAP;

			statSlot(TXT_STR, hero.STR());
			statSlot(TXT_HEALTH, hero.HP + "/" + hero.HT);
			statSlot(TXT_EXP, hero.exp + "/" + hero.maxExp());

			pos += GAP;

			statSlot(TXT_GOLD, Statistics.goldCollected);
			statSlot(TXT_DEPTH, Statistics.deepestFloor);
			
			pos += GAP;
			
			statSlot(TXT_MOVES, (int) Statistics.moves);
			
			if(Dungeon.hero.buff(Hunger.class) != null){
				statSlot(TXT_HUNGER, Dungeon.hero.buff(Hunger.class).hungerLevel());
			}
			
			pos += GAP;
			
			if (Dungeon.dewDraw){
			statSlot(TXT_MOVES2, (int) Dungeon.level.currentmoves);
			statSlot(TXT_MOVES3, (int) Dungeon.pars[Dungeon.depth]);
			statSlot(TXT_MOVES4, (int) Statistics.prevfloormoves);
			}
			
			pos += GAP;
		}

		private void statSlot(String label, String value) {

			BitmapText txt = PixelScene.createText(label, 8);
			txt.y = pos;
			add(txt);

			txt = PixelScene.createText(value, 8);
			txt.measure();
			txt.x = PixelScene.align(WIDTH * 0.65f);
			txt.y = pos;
			add(txt);

			pos += GAP + txt.baseLine();
		}

		private void statSlot(String label, int value) {
			statSlot(label, Integer.toString(value));
		}

		public float height() {
			return pos;
		}
	}

	private class BuffsTab extends Group {

		private static final int GAP = 2;

		private float pos;

		public BuffsTab() {
			for (Buff buff : Dungeon.hero.buffs()) {
				buffSlot(buff);
			}
		}

		private void buffSlot(Buff buff) {

			int index = buff.icon();

			if (index != BuffIndicator.NONE) {

				Image icon = new Image(icons);
				icon.frame(film.get(index));
				icon.y = pos;
				add(icon);

				BitmapText txt = PixelScene.createText(buff.toString(), 8);
				txt.x = icon.width + GAP;
				txt.y = pos + (int) (icon.height - txt.baseLine()) / 2;
				add(txt);

				pos += GAP + icon.height;
			}
		}

		public float height() {
			return pos;
		}
	}
	
	private class PetTab extends Group {

		private static final String TXT_TITLE = "Level %d %s";
		private static final String TXT_FEED = "Feed";
		private static final String TXT_SELECT = "What do you want to feed your pet?";
		
		private CharSprite image;
		private BitmapText name;
		private HealthBar health;
		private BuffIndicator buffs;

		private static final int GAP = 5;

		private float pos;
		
				
		public PetTab(PET heropet) {		
						
			name = PixelScene.createText(Utils.capitalize(heropet.name), 9);
			name.hardlight(TITLE_COLOR);
			name.measure();
			//add(name);

			image = heropet.sprite();
			add(image);

			health = new HealthBar();
			health.level((float) heropet.HP / heropet.HT);
			add(health);

			buffs = new BuffIndicator(heropet);
			add(buffs);
		
			
			
			IconTitle title = new IconTitle();
			title.icon(image);
			title.label(Utils.format(TXT_TITLE, heropet.level, heropet.name).toUpperCase(Locale.ENGLISH), 9);
			title.color(Window.SHPX_COLOR);
			title.setRect(0, 0, WIDTH, 0);
			add(title);

			RedButton btnFeed = new RedButton(TXT_FEED) {
				@Override
				protected void onClick() {
					hide();
					GameScene.selectItem(itemSelector, WndBag.Mode.ALL, TXT_SELECT);
				}
			};
			btnFeed.setRect(0, title.height(),
					btnFeed.reqWidth() + 2, btnFeed.reqHeight() + 2);
			add(btnFeed);


			pos = btnFeed.bottom() + GAP;

			statSlot(TXT_ATTACK, heropet.attackSkill(null));
			statSlot(TXT_HEALTH, heropet.HP + "/" + heropet.HT);
			statSlot(TXT_KILLS, heropet.kills);
			statSlot(TXT_EXP, heropet.level<10 ? heropet.experience + "/" + (heropet.level*heropet.level*heropet.level) : "Max");
			if (heropet.type==4 || heropet.type==5 || heropet.type==6 || heropet.type==7){
			  statSlot(TXT_BREATH, heropet.cooldown==0 ? "Ready" : heropet.cooldown + " Turns");
			} else if (heropet.type==1){
				statSlot(TXT_SPIN, heropet.cooldown==0 ? "Armed" : heropet.cooldown + " Turns");
			} else if (heropet.type==3){
				statSlot(TXT_FEATHERS, heropet.cooldown==0 ? "Ruffled" : heropet.cooldown + " Turns");
			} else if (heropet.type==8){
				statSlot(TXT_STING, heropet.cooldown==0 ? "Ready" : heropet.cooldown + " Turns");
			} else if (heropet.type==10 || heropet.type==11){
				statSlot(TXT_SPARKLE, heropet.cooldown==0 ? "Sparkling" : heropet.cooldown + " Turns");
			}
			
			pos += GAP;

			
		}

		private void statSlot(String label, String value) {

			BitmapText txt = PixelScene.createText(label, 8);
			txt.y = pos;
			add(txt);

			txt = PixelScene.createText(value, 8);
			txt.measure();
			txt.x = PixelScene.align(WIDTH * 0.65f);
			txt.y = pos;
			add(txt);

			pos += GAP + txt.baseLine();
		}

		private void statSlot(String label, int value) {
			statSlot(label, Integer.toString(value));
		}

		public float height() {
			return pos;
		}
	}
	
	private final WndBag.Listener itemSelector = new WndBag.Listener() {
		@Override
		public void onSelect(Item item) {
			if (item != null) {
				feed(item);
			}
		}
	};
	
	private boolean checkpetNear(){
		for (int n : Level.NEIGHBOURS8) {
			int c = Dungeon.hero.pos + n;
			if (Actor.findChar(c) instanceof PET) {
				return true;
			}
		}
		return false;
	}
	
	private void feed(Item item) {
						
		PET heropet = checkpet();
		boolean nomnom = checkFood(heropet.type, item);
		boolean nearby = checkpetNear();
	
		if (nomnom && nearby){
		  int effect = heropet.HT-heropet.HP;
		  if (effect > 0){
		    heropet.HP=heropet.HT;
		    heropet.sprite.emitter().burst(Speck.factory(Speck.HEALING),2);
		    heropet.sprite.showStatus(CharSprite.POSITIVE, TXT_HEALS, effect);
		  }
	      heropet.cooldown=1;  
		  item.detach(Dungeon.hero.belongings.backpack);
		  GLog.n("Your pet eats the %s.",item.name());
		}else if (!nearby){
			GLog.n("Your pet is too far away!");
		} else {
		  GLog.n("Your pet rejects the %s.",item.name());
		  
		}		
	}

	private boolean checkFood(Integer petType, Item item){
		boolean nomnom = false;
		
		if (petType==1){ //Spider
			if (item instanceof Nut){				
				nomnom=true;
			}
		} 
		
		if (petType==2){ //steel bee
			if (item instanceof Blackberry
				|| item instanceof Blueberry 
				|| item instanceof Cloudberry
				|| item instanceof Moonberry
				|| item instanceof FullMoonberry
				){				
				nomnom=true;
			}
		} 
		if (petType==3){//Velocirooster 
			if (item instanceof Plant.Seed
				|| item instanceof Nut
				|| item instanceof ToastedNut
				){				
				nomnom=true;
			}
		}			
		if (petType==4){//red dragon - fire
			if (item instanceof Meat
				|| item instanceof ChargrilledMeat 
				|| item instanceof FrozenCarpaccio
				|| item instanceof MysteryMeat
				){				
				nomnom=true;
			}
		}
		
		if (petType==5){//green dragon - lit
			if (item instanceof Meat
				|| item instanceof ChargrilledMeat 
				|| item instanceof Plant.Seed
				|| item instanceof Nut
				|| item instanceof ToastedNut
				|| item instanceof Blackberry
				|| item instanceof Blueberry 
				|| item instanceof Cloudberry
				|| item instanceof Moonberry
				|| item instanceof FullMoonberry
				){				
				nomnom=true;
			}
		}
		
		if (petType==6){//violet dragon - poison
			if (item instanceof Meat
				|| item instanceof ChargrilledMeat 
				|| item instanceof FrozenCarpaccio
				|| item instanceof MysteryMeat
				|| item instanceof Nut
				|| item instanceof ToastedNut
				){				
				nomnom=true;
			}
		}
		if (petType==7){//blue dragon - ice
			if (item instanceof Meat
				|| item instanceof ChargrilledMeat 
				|| item instanceof FrozenCarpaccio
				|| item instanceof Plant.Seed
				){				
				nomnom=true;
			}
		}
		
		if (petType==8){ //scorpion
			if (item instanceof Meat
				|| item instanceof ChargrilledMeat 
				|| item instanceof FrozenCarpaccio
				|| item instanceof MysteryMeat
				){				
				nomnom=true;
			}
		} 
		
		if (petType==9){//Vorpal Bunny 
			if (item instanceof Meat
				|| item instanceof ChargrilledMeat 
				|| item instanceof FrozenCarpaccio
				|| item instanceof MysteryMeat
				){				
				nomnom=true;
			}
		}
		if (petType==10){//Fairy
			if (item instanceof Blackberry
				|| item instanceof Blueberry 
				|| item instanceof Cloudberry
				|| item instanceof Moonberry
				|| item instanceof FullMoonberry
				){				
				nomnom=true;
			}
		}
		if (petType==11){//Sugarplum Fairy
			if (item instanceof Blackberry
				|| item instanceof Blueberry 
				|| item instanceof Cloudberry
				|| item instanceof Moonberry
				|| item instanceof FullMoonberry
				){				
				nomnom=true;
			}
		}
	return nomnom;		
	}
	
}
