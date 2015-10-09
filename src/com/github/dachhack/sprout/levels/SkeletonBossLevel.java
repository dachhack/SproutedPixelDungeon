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

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Bones;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.mobs.Bestiary;
import com.github.dachhack.sprout.actors.mobs.CrabKing;
import com.github.dachhack.sprout.actors.mobs.DwarfKingTomb;
import com.github.dachhack.sprout.actors.mobs.HermitCrab;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.actors.mobs.Shaman;
import com.github.dachhack.sprout.actors.mobs.Shell;
import com.github.dachhack.sprout.actors.mobs.SkeletonHand1;
import com.github.dachhack.sprout.actors.mobs.SkeletonHand2;
import com.github.dachhack.sprout.actors.mobs.SkeletonKing;
import com.github.dachhack.sprout.items.Heap;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.keys.SkeletonKey;
import com.github.dachhack.sprout.items.potions.PotionOfLevitation;
import com.github.dachhack.sprout.items.potions.PotionOfLiquidFlame;
import com.github.dachhack.sprout.levels.painters.Painter;
import com.github.dachhack.sprout.scenes.GameScene;
import com.watabou.noosa.Scene;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class SkeletonBossLevel extends Level {

	{
		color1 = 0x6a723d;
		color2 = 0x88924c;

		viewDistance = 8;
	}
	
	private static final int TOP = 2;
	private static final int HALL_WIDTH = 13;
	private static final int HALL_HEIGHT = 15;
	private static final int CHAMBER_HEIGHT = 3;

	private static final int LEFT = (WIDTH - HALL_WIDTH) / 2;
	private static final int CENTER = LEFT + HALL_WIDTH / 2;

	private int arenaDoor;
	private boolean enteredArena = false;
	private boolean keyDropped = false;

	@Override
	public String tilesTex() {
		return Assets.TILES_SKELETON;
	}

	@Override
	public String waterTex() {
		return Assets.WATER_PRISON;
	}

	private static final String DOOR = "door";
	private static final String ENTERED = "entered";
	private static final String DROPPED = "droppped";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(DOOR, arenaDoor);
		bundle.put(ENTERED, enteredArena);
		bundle.put(DROPPED, keyDropped);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		arenaDoor = bundle.getInt(DOOR);
		enteredArena = bundle.getBoolean(ENTERED);
		keyDropped = bundle.getBoolean(DROPPED);
	}

	@Override
	protected boolean build() {

		Painter.fill(this, LEFT, TOP, HALL_WIDTH, HALL_HEIGHT, Terrain.EMPTY);
		Painter.fill(this, CENTER, TOP, 1, HALL_HEIGHT, Terrain.EMPTY);

		int y = TOP + 1;
		while (y < TOP + HALL_HEIGHT) {
			map[y * WIDTH + CENTER - 2] = Terrain.STATUE;
			map[y * WIDTH + CENTER + 2] = Terrain.STATUE;
			y += 2;
		}
		
		exit = (TOP - 1) * WIDTH + CENTER;
		map[exit] = Terrain.LOCKED_EXIT;

		arenaDoor = (TOP + HALL_HEIGHT) * WIDTH + CENTER;
		map[arenaDoor] = Terrain.DOOR;

		Painter.fill(this, LEFT, TOP + HALL_HEIGHT + 1, HALL_WIDTH,
				CHAMBER_HEIGHT, Terrain.EMPTY);
		Painter.fill(this, LEFT, TOP + HALL_HEIGHT + 1, 1, CHAMBER_HEIGHT,
				Terrain.WATER);
		Painter.fill(this, LEFT + HALL_WIDTH - 1, TOP + HALL_HEIGHT + 1, 1,
				CHAMBER_HEIGHT, Terrain.WATER);

		entrance = (TOP + HALL_HEIGHT + 2 + Random.Int(CHAMBER_HEIGHT - 1))
				* WIDTH + LEFT + (/* 1 + */Random.Int(HALL_WIDTH - 2));
		map[entrance] = Terrain.PEDESTAL;

		map[exit] = Terrain.WALL;

		return true;
	}

	@Override
	protected void decorate() {

		for (int i = 0; i < LENGTH; i++) {
			if (map[i] == Terrain.EMPTY && Random.Int(10) == 0) {
				map[i] = Terrain.EMPTY_DECO;
			} else if (map[i] == Terrain.WALL && Random.Int(8) == 0) {
				map[i] = Terrain.WALL_DECO;
			}
		}
		
		int shrub1 = arenaDoor + WIDTH;
		int shrub2 = arenaDoor + WIDTH + 1;
		int shrub3 = arenaDoor + WIDTH - 1;
		int potionpos = arenaDoor + 2*WIDTH;
		map[shrub1] = Terrain.SHRUB;
		map[shrub2] = Terrain.SHRUB;
		map[shrub3] = Terrain.SHRUB;
		drop(new PotionOfLiquidFlame(), potionpos);
		
		for (int i = 0; i < LENGTH; i++) {
			if (map[i] == Terrain.WALL && Random.Int(8) == 0) {
				map[i] = Terrain.WALL_DECO;
			}
			if (map[i]==Terrain.ENTRANCE){map[i] = Terrain.EMPTY;}			
			if (map[i]==Terrain.EMPTY && heaps.get(i) == null && Random.Float()<.20){map[i] = Terrain.HIGH_GRASS;}
			if (map[i]==Terrain.EMPTY && heaps.get(i) == null && Random.Float()<.25){map[i] = Terrain.GRASS;}
			if (map[i]==Terrain.EMPTY && heaps.get(i) == null && Random.Float()<.30){map[i] = Terrain.SHRUB;}
		}

		//int sign = arenaDoor + WIDTH + 1;
		//map[sign] = Terrain.SIGN;
	}

	public static int pedestal(boolean left) {
		if (left) {
			return (TOP + HALL_HEIGHT / 2) * WIDTH + CENTER - 2;
		} else {
			return (TOP + HALL_HEIGHT / 2) * WIDTH + CENTER + 2;
		}
	}

	@Override
	protected void createMobs() {
	}

	@Override
	public Actor respawner() {
		return null;
	}

	@Override
	protected void createItems() {
		Item item = Bones.get();
		if (item != null) {
			int pos;
			do {
				pos = Random.IntRange(LEFT + 1, LEFT + HALL_WIDTH - 2)
						+ Random.IntRange(TOP + HALL_HEIGHT + 1, TOP
								+ HALL_HEIGHT + CHAMBER_HEIGHT) * WIDTH;
			} while (pos == entrance || map[pos] == Terrain.SIGN);
			drop(item, pos).type = Heap.Type.REMAINS;
		}
	}

	@Override
	public int randomRespawnCell() {
		return -1;
	}

	@Override
	public void press(int cell, Char hero) {

		super.press(cell, hero);

		if (!enteredArena && outsideEntraceRoom(cell) && hero == Dungeon.hero) {

			enteredArena = true;
			//locked = true;

			Mob boss = new SkeletonKing();
			Mob hand1 = new SkeletonHand1();
			Mob hand2 = new SkeletonHand2();
			boss.state = boss.HUNTING;
			hand1.state = hand1.HUNTING;
			hand2.state = hand2.HUNTING;
			int count = 0;
			do {
				boss.pos = Random.Int(LENGTH);
				hand1.pos = (TOP + 1) * WIDTH + CENTER;
				hand2.pos = (TOP + 1) * WIDTH + CENTER+1;
				
			} while (!passable[boss.pos] 
					|| !outsideEntraceRoom(boss.pos)
					|| (Dungeon.visible[boss.pos] && count++ < 20));
			
			GameScene.add(boss);
			
			GameScene.add(hand1);
			GameScene.add(hand2);
			

			if (Dungeon.visible[boss.pos]) {
				boss.notice();
				boss.sprite.alpha(0);
				boss.sprite.parent.add(new AlphaTweener(boss.sprite, 1, 0.1f));
			}

			//set(arenaDoor, Terrain.WALL);
			//GameScene.updateMap(arenaDoor);
			Dungeon.observe();
		}
	}

	@Override
	public Heap drop(Item item, int cell) {

		if (!keyDropped && item instanceof SkeletonKey) {

			keyDropped = true;
			locked = false;

			set(arenaDoor, Terrain.DOOR);
			GameScene.updateMap(arenaDoor);
			Dungeon.observe();
		}

		return super.drop(item, cell);
	}

	private boolean outsideEntraceRoom(int cell) {
		return cell / WIDTH < arenaDoor / WIDTH;
	}

	public String tileName(int tile) {
		switch (tile) {
		case Terrain.WATER:
			return "Dark cold water.";
		case Terrain.HIGH_GRASS:
			return "Ancient pottery.";
		default:
			return super.tileName(tile);
		}
	}

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
		case Terrain.EMPTY_DECO:
			return "Oddly shaped bones are piled up here. Good thing they are not animated. ";
		case Terrain.HIGH_GRASS:
			return "Ancient pottery litters the floor.";
		case Terrain.BOOKSHELF:
			return "The bookshelf is packed with cheap useless books. Might it burn?";
		default:
			return super.tileDesc(tile);
		}
	}

	@Override
	public void addVisuals(Scene scene) {
		CityLevel.addVisuals(this, scene);
	}
}
