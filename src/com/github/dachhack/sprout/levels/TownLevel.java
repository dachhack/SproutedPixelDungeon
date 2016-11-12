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
package com.github.dachhack.sprout.levels;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.blobs.Alchemy;
import com.github.dachhack.sprout.actors.blobs.Alter;
import com.github.dachhack.sprout.actors.blobs.Blob;
import com.github.dachhack.sprout.actors.blobs.Fire;
import com.github.dachhack.sprout.actors.blobs.Foliage;
import com.github.dachhack.sprout.actors.blobs.Portal;
import com.github.dachhack.sprout.actors.blobs.WaterOfHealth;
import com.github.dachhack.sprout.actors.blobs.WaterOfTransmutation;
import com.github.dachhack.sprout.actors.blobs.WellWater;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Bestiary;
import com.github.dachhack.sprout.actors.mobs.BlueCat;
import com.github.dachhack.sprout.actors.mobs.Eye;
import com.github.dachhack.sprout.actors.mobs.LitTower;
import com.github.dachhack.sprout.actors.mobs.MineSentinel;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.actors.mobs.SokobanSentinel;
import com.github.dachhack.sprout.actors.mobs.Zot;
import com.github.dachhack.sprout.actors.mobs.Yog.BurningFist;
import com.github.dachhack.sprout.actors.mobs.Yog.InfectingFist;
import com.github.dachhack.sprout.actors.mobs.Yog.PinningFist;
import com.github.dachhack.sprout.actors.mobs.Yog.RottingFist;
import com.github.dachhack.sprout.actors.mobs.npcs.Blacksmith;
import com.github.dachhack.sprout.actors.mobs.npcs.ImpShopkeeper;
import com.github.dachhack.sprout.actors.mobs.npcs.OtilukeNPC;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokoban;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokobanBlack;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokobanCorner;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokobanSwitch;
import com.github.dachhack.sprout.actors.mobs.npcs.Shopkeeper;
import com.github.dachhack.sprout.actors.mobs.npcs.Tinkerer4;
import com.github.dachhack.sprout.actors.mobs.npcs.Tinkerer5;
import com.github.dachhack.sprout.actors.mobs.pets.Bunny;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.items.ActiveMrDestructo;
import com.github.dachhack.sprout.items.Egg;
import com.github.dachhack.sprout.items.Generator;
import com.github.dachhack.sprout.items.Gold;
import com.github.dachhack.sprout.items.Heap;
import com.github.dachhack.sprout.items.InactiveMrDestructo;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.SeekingClusterBombItem;
import com.github.dachhack.sprout.items.Generator.Category;
import com.github.dachhack.sprout.items.artifacts.TimekeepersHourglass;
import com.github.dachhack.sprout.items.keys.IronKey;
import com.github.dachhack.sprout.items.potions.PotionOfHealing;
import com.github.dachhack.sprout.items.potions.PotionOfLiquidFlame;
import com.github.dachhack.sprout.items.potions.PotionOfOverHealing;
import com.github.dachhack.sprout.items.scrolls.ScrollOfMagicalInfusion;
import com.github.dachhack.sprout.items.scrolls.ScrollOfRegrowth;
import com.github.dachhack.sprout.items.scrolls.ScrollOfUpgrade;
import com.github.dachhack.sprout.items.wands.WandOfFlock.Sheep;
import com.github.dachhack.sprout.levels.features.Chasm;
import com.github.dachhack.sprout.levels.features.Door;
import com.github.dachhack.sprout.levels.features.HighGrass;
import com.github.dachhack.sprout.levels.traps.ActivatePortalTrap;
import com.github.dachhack.sprout.levels.traps.AlarmTrap;
import com.github.dachhack.sprout.levels.traps.ChangeSheepTrap;
import com.github.dachhack.sprout.levels.traps.FireTrap;
import com.github.dachhack.sprout.levels.traps.FleecingTrap;
import com.github.dachhack.sprout.levels.traps.GrippingTrap;
import com.github.dachhack.sprout.levels.traps.HeapGenTrap;
import com.github.dachhack.sprout.levels.traps.LightningTrap;
import com.github.dachhack.sprout.levels.traps.ParalyticTrap;
import com.github.dachhack.sprout.levels.traps.PoisonTrap;
import com.github.dachhack.sprout.levels.traps.SokobanPortalTrap;
import com.github.dachhack.sprout.levels.traps.SummoningTrap;
import com.github.dachhack.sprout.levels.traps.ToxicTrap;
import com.github.dachhack.sprout.plants.Dewcatcher;
import com.github.dachhack.sprout.plants.Flytrap;
import com.github.dachhack.sprout.plants.Phaseshift;
import com.github.dachhack.sprout.plants.Plant;
import com.github.dachhack.sprout.plants.Starflower;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.windows.WndOtilukeMessage;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;
import com.watabou.utils.SparseArray;

public class TownLevel extends Level {


	{
		color1 = 0x534f3e;
		color2 = 0xb9d661;
		WIDTH = 48;
		HEIGHT = 48;
		LENGTH = HEIGHT*WIDTH;
		special=false;
	}
	
		
    public int mineDepth=0;
    
    public int[] scrollspots;
    public int[] storespots;
    //public int[] potionspots;
	
    
	private static final String MINEDEPTH = "mineDepth";
	private static final String SCROLLSPOTS = "scrollspots";
	private static final String STORESPOTS = "storespots";
	//private static final String POTIONSPOTS = "potionspots";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(MINEDEPTH, mineDepth);
		bundle.put(SCROLLSPOTS, scrollspots);
		bundle.put(STORESPOTS, storespots);
		//bundle.put(POTIONSPOTS, potionspots);
	}
		
	@Override
	public void restoreFromBundle(Bundle bundle) {
		      super.restoreFromBundle(bundle);
		      mineDepth = bundle.getInt(MINEDEPTH);
		      scrollspots = bundle.getIntArray(SCROLLSPOTS);
		      storespots = bundle.getIntArray(STORESPOTS);
		      //if (Dungeon.version>134){
		     //   potionspots = bundle.getIntArray(POTIONSPOTS);
		     // }
		     // storeStock();
	}
	
	private boolean checkOtiluke(){
		boolean check=false;
		for (Mob mob : mobs) {
			if (mob instanceof OtilukeNPC ) {
			check=true;	
					}
		}
		return check;
	}

	public void storeStock (){
		
		
		if(Actor.findChar(13 + WIDTH * 10) == null){
			 Mob shopkeeper =  new Shopkeeper();
		      shopkeeper.pos = 13 + WIDTH * 10;
		      mobs.add(shopkeeper);
		}
		if(Actor.findChar(8 + WIDTH * 23) == null){
			 Mob shopkeeper2 =  new Shopkeeper();
		      shopkeeper2.pos = 8 + WIDTH * 23;
		      mobs.add(shopkeeper2);
		}
		
		if  (Badges.checkOtilukeRescued()&&!checkOtiluke()){
			if(Actor.findChar(32 + WIDTH * 15) == null){
				 Mob otiluke =  new OtilukeNPC();
				 otiluke.pos = 32 + WIDTH * 15;
			      mobs.add(otiluke);
			}
			
			map[exit] = Terrain.STATUE;
		 }
		
		/* if (Dungeon.version>134){
	
		if(Actor.findChar(22 + WIDTH * 8) == null){
			 Mob shopkeeper3 =  new Shopkeeper();
		      shopkeeper3.pos = 22 + WIDTH * 8;
		      mobs.add(shopkeeper3);
		}
		
		 }
		 */
		if(storeRefresh()){
			
			
			for (int i : scrollspots) {
				Heap heap = heaps.get(i);
				if (heap == null){
					drop(new ScrollOfUpgrade(), i).type = Heap.Type.FOR_SALE;
				}					
			}
			
			
			for (int i : storespots) {
				Heap heap = heaps.get(i);
				if (heap == null){
					Item storeitem = storeItem();
					drop(storeitem, i).type = Heap.Type.FOR_SALE;
				}					
			}
			/*
			if (Dungeon.version>134){
			for (int i : potionspots) {
				Heap heap = heaps.get(i);
				if (heap == null){
					if (Random.Float()<.8){
					    drop(new PotionOfHealing(), i).type = Heap.Type.FOR_SALE;
					} else {
						drop(new PotionOfOverHealing(), i).type = Heap.Type.FOR_SALE;	
					}
				}					
			}
			}
			*/
			
		}
		
	}
	
	public Item storeItem (){
		Item prize;
		switch (Random.Int(10)) {
		case 0:
			prize = new PotionOfHealing();
			break;
		case 1:
			prize = Generator.random(Generator.Category.SEEDRICH);
			break;
		case 2:
			prize = new PotionOfHealing();
			break;
		case 3:
			prize =  new PotionOfHealing();
			break;
		case 5:
			prize =  (Random.Int(10) < 3) ? new ActiveMrDestructo() :  new InactiveMrDestructo();
			break;
		case 6:
			prize =  new SeekingClusterBombItem();
			break;
		case 7:
			prize = new PotionOfHealing();
			break;
		case 8:
			prize = new PotionOfHealing();
			break;
		case 9:
			prize = new PotionOfHealing();
			break;
		default:
			prize = new PotionOfHealing();
			break;
		}

		return prize;
 
	}
	
	public boolean storeRefresh(){
		boolean check=false;
		if (Statistics.realdeepestFloor>mineDepth){
			mineDepth=Statistics.realdeepestFloor;
			check=true;			
		}		
		return check;
	}
	
	

  @Override
  public void create() {
	  
	   super.create();	
   }	
  
   	
  @Override
	protected void createItems() {
				 
		/* 6 + WIDTH * 25	to    10 + WIDTH *25  Magic
		 * 4 + WIDTH * 27   to    4 + WIDTH * 22
		 * 
		 * 7 + WIDTH * 10	to    7 + WIDTH * 7		Normal
		 * 12 + WIDTH * 13	to    12 + WIDTH * 8	
		 */
		 //drop(new PotionOfLiquidFlame(), 9 + WIDTH * 24).type = Heap.Type.FOR_SALE;
	  
	  Mob shopkeeper =  new Shopkeeper();
      shopkeeper.pos = 13 + WIDTH * 10;
      mobs.add(shopkeeper);
      
      Mob shopkeeper2 =  new Shopkeeper();
      shopkeeper2.pos = 8 + WIDTH * 23;
      mobs.add(shopkeeper2);
      /*
      Mob shopkeeper3 =  new Shopkeeper();
      shopkeeper3.pos = 22 + WIDTH * 8;
      mobs.add(shopkeeper3);
      */
      
      Mob bluecat =  new BlueCat();
      bluecat.pos = 35 + WIDTH * 5;
      mobs.add(bluecat);
      
      
      Mob tinkerer1 =  new Tinkerer4();
      tinkerer1.pos = 31 + WIDTH * 20;
      mobs.add(tinkerer1);
      
      Mob tinkerer2 =  new Tinkerer5();
      tinkerer2.pos = 14 + WIDTH * 33;
      mobs.add(tinkerer2);
      
     
             
      scrollspots = new int[11];
      scrollspots[0] =  4 + WIDTH * 27;
      scrollspots[1] =  4 + WIDTH * 26;
      scrollspots[2] =  4 + WIDTH * 25;
      scrollspots[3] =  4 + WIDTH * 24;
      scrollspots[4] =  4 + WIDTH * 23;
      scrollspots[5] =  4 + WIDTH * 22;
      
      scrollspots[6] =  6 + WIDTH * 25;
      scrollspots[7] =  7 + WIDTH * 25;
      scrollspots[8] =  8 + WIDTH * 25;
      scrollspots[9] =  9 + WIDTH * 25;
      scrollspots[10] =  10 + WIDTH * 25;
      
      storespots = new int[9];
      storespots[0] =  7 + WIDTH * 10;
      storespots[1] =  7 + WIDTH * 9;
      storespots[2] =  7 + WIDTH * 8;
      storespots[3] =  7 + WIDTH * 7;
      
      storespots[4] =  12 + WIDTH * 13;
      storespots[5] =  12 + WIDTH * 12;
      storespots[6] =  12 + WIDTH * 11;
      storespots[7] =  12 + WIDTH * 10;
      storespots[8] =  12 + WIDTH * 9;
      
     /* 
      potionspots = new int[10];
      potionspots[0] =  20 + WIDTH * 6;
      potionspots[1] =  20 + WIDTH * 7;
      potionspots[2] =  20 + WIDTH * 8;
      potionspots[3] =  20 + WIDTH * 9;
      
      potionspots[4] =  20 + WIDTH * 10;
      potionspots[5] =  20 + WIDTH * 11;
      potionspots[6] =  21 + WIDTH * 6;
      potionspots[7] =  22 + WIDTH * 6;
      potionspots[8] =  23 + WIDTH * 6;
      potionspots[9] =  24 + WIDTH * 6;
        */
      
      storeStock();      
          
		Alter alter = new Alter();
		alter.seed(33 + WIDTH * 32, 1);
		blobs.put(Alter.class, alter);	
		
		addChest(39 + WIDTH * 4);
		addChest(40 + WIDTH * 4);
		addChest(41 + WIDTH * 4);
		addChest(42 + WIDTH * 4);
		
		addChest(41 + WIDTH * 18);
		addChest(42 + WIDTH * 18);
		
		addChest(7 + WIDTH * 5);
		addChest(8 + WIDTH * 5);
		addChest(9 + WIDTH * 5);
		
		addChest(27 + WIDTH * 11);
		addChest(27 + WIDTH * 12);
		
      //Activate Bunny Rancher Mode
      /*
      for (int i = 0; i < LENGTH; i++) {				
			if (Random.Int(10)<2 && map[i]==Terrain.EMPTY){
				Bunny pet = new Bunny(); 
				mobs.add(pet); pet.pos = i; 
				Actor.occupyCell(pet);
			}
		}
		*/
      
	}	
  
  private void addChest(int pos) {
		
		Item prize;
		switch (Random.Int(10)) {
		case 0:
			prize = new Egg();
			break;
		case 1:
			prize = new Phaseshift.Seed();
			break;
		case 2:
			prize = Generator.random(Generator.Category.BERRY);
			break;
		case 3:
			prize =  new Starflower.Seed();
			break;
		case 5:
			prize =  new ActiveMrDestructo();
			break;
		case 6:
			prize =  new SeekingClusterBombItem();
			break;
		default:
			prize = new Gold(Random.IntRange(1, 5));
			break;
		}

		drop(prize, pos).type = Heap.Type.CHEST;
	}
  
  
	@Override
	public void press(int cell, Char ch) {
		
		if(!special){
			storeStock();
			special=true;
		}
				
		if (pit[cell] && ch == Dungeon.hero) {
			Chasm.heroFall(cell);
			return;
		}

		TimekeepersHourglass.timeFreeze timeFreeze = null;

		if (ch != null)
			timeFreeze = ch.buff(TimekeepersHourglass.timeFreeze.class);

		boolean trap = false;
		boolean interrupt = false;
		
		switch (map[cell]) {			
					
        
		case Terrain.HIGH_GRASS:
			HighGrass.trample(this, cell, ch);
			break;

		case Terrain.WELL:
			WellWater.affectCell(cell);
			break;

		case Terrain.ALCHEMY:
			if (ch == null) {
				Alchemy.transmute(cell);
			}
			break;
			
		case Terrain.PEDESTAL:
			if (ch == null) {
				Alter.transmute(cell);
			}
			break;


		case Terrain.DOOR:
			Door.enter(cell);
			break;
		}

		if (trap){

			if (Dungeon.visible[cell])
				Sample.INSTANCE.play(Assets.SND_TRAP);

			if (ch == Dungeon.hero)
				Dungeon.hero.interrupt();

			set(cell, Terrain.INACTIVE_TRAP);
			GameScene.updateMap(cell);					
		} 
		
		if (interrupt){

			Dungeon.hero.interrupt();
			GameScene.updateMap(cell);					
		} 

		Plant plant = plants.get(cell);
		if (plant != null) {
			plant.activate(ch);
		}
	}

	
	
	@Override
	public void mobPress(Mob mob) {

		int cell = mob.pos;

		if (pit[cell] && !mob.flying) {
			Chasm.mobFall(mob);
			return;
		}

		boolean trap = true;
	
		switch (map[cell]) {

		case Terrain.TOXIC_TRAP:
			ToxicTrap.trigger(cell, mob);
			break;

		case Terrain.FIRE_TRAP:
			FireTrap.trigger(cell, mob);
			break;

		case Terrain.PARALYTIC_TRAP:
			ParalyticTrap.trigger(cell, mob);
			break;
			
		
		case Terrain.POISON_TRAP:
			PoisonTrap.trigger(cell, mob);
			break;

		case Terrain.ALARM_TRAP:
			AlarmTrap.trigger(cell, mob);
			break;

		case Terrain.LIGHTNING_TRAP:
			LightningTrap.trigger(cell, mob);
			break;

		case Terrain.GRIPPING_TRAP:
			GrippingTrap.trigger(cell, mob);
			break;

		case Terrain.SUMMONING_TRAP:
			SummoningTrap.trigger(cell, mob);
			break;

		case Terrain.DOOR:
			Door.enter(cell);

		default:
			trap = false;
		}

		if (trap) {
			if (Dungeon.visible[cell]) {
				Sample.INSTANCE.play(Assets.SND_TRAP);
			}
			set(cell, Terrain.INACTIVE_TRAP);
			GameScene.updateMap(cell);
		}		
		
		Plant plant = plants.get(cell);
		if (plant != null) {
			plant.activate(mob);
		}
		
		Dungeon.observe();
	}

	@Override
	public String tilesTex() {
		return Assets.TILES_TOWN;
	}

	@Override
	public String waterTex() {
		return Assets.WATER_PRISON;
	}

	@Override
	protected boolean build() {
		
		map = TownLayouts.TOWN_LAYOUT.clone();
		decorate();

		buildFlagMaps();
		cleanWalls();
			
		entrance = 25 + WIDTH * 21;
		exit = 5 + WIDTH * 40;


		return true;
	}
	@Override
	protected void decorate() {
		//do nothing, all decorations are hard-coded.
	}

	@Override
	protected void createMobs() {
		/*
		    SokobanSentinel mob = new SokobanSentinel();
			mob.pos = 38 + WIDTH * 21;
			mobs.add(mob);
			Actor.occupyCell(mob);
			
			SokobanSentinel mob2 = new SokobanSentinel();
			mob2.pos = 25 + WIDTH * 36;
			mobs.add(mob2);
			Actor.occupyCell(mob2);		
			*/	
	}
	
	

	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
		case Terrain.EMPTY_DECO:
			return "There are old blood stains on the floor.";
		default:
			return super.tileDesc(tile);
		}
	}

	@Override
	public String tileName(int tile) {
		switch (tile) {
		case Terrain.WATER:
			return "Suspiciously colored water";
		case Terrain.HIGH_GRASS:
			return "High blooming flowers";
		default:
			return super.tileName(tile);
		}
	}



	@Override
	public int randomRespawnCell() {
		return -1;
	}



	
	
	

}
