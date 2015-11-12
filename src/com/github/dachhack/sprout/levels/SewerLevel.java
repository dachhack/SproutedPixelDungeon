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
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.DungeonTilemap;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.hero.HeroClass;
import com.github.dachhack.sprout.actors.mobs.Sentinel;
import com.github.dachhack.sprout.actors.mobs.npcs.Ghost;
import com.github.dachhack.sprout.actors.mobs.npcs.Imp;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokoban;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokobanCorner;
import com.github.dachhack.sprout.actors.mobs.npcs.SheepSokobanSwitch;
import com.github.dachhack.sprout.actors.mobs.npcs.Tinkerer1;
import com.github.dachhack.sprout.actors.mobs.npcs.Tinkerer2;
import com.github.dachhack.sprout.actors.mobs.npcs.Ghost.GnollArcher;
import com.github.dachhack.sprout.actors.mobs.npcs.Tinkerer3;
import com.github.dachhack.sprout.items.Bomb;
import com.github.dachhack.sprout.items.DewVial;
import com.github.dachhack.sprout.items.Egg;
import com.github.dachhack.sprout.items.Mushroom;
import com.github.dachhack.sprout.items.bags.SeedPouch;
import com.github.dachhack.sprout.items.food.Blackberry;
import com.github.dachhack.sprout.items.food.Blueberry;
import com.github.dachhack.sprout.items.food.Cloudberry;
import com.github.dachhack.sprout.items.food.Moonberry;
import com.github.dachhack.sprout.scenes.GameScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.Scene;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.ColorMath;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class SewerLevel extends RegularLevel {

	{
		color1 = 0x48763c;
		color2 = 0x59994a;
	}

	@Override
	public String tilesTex() {
		return Assets.TILES_SEWERS;
	}
	
	
	@Override
	public String waterTex() {
		return Assets.WATER_SEWERS;
	}

	@Override
	protected boolean[] water() {
		return Patch.generate(feeling == Feeling.WATER ? 0.60f : 0.45f, 5);
	}

	@Override
	protected boolean[] grass() {
		return Patch.generate(feeling == Feeling.GRASS ? 0.60f : 0.40f, 4);
	}
	
	@Override
	protected void setPar(){
		Dungeon.pars[Dungeon.depth] = 500+(Dungeon.depth*50)+(secretDoors*50);
	}

	@Override
	protected void decorate() {

		for (int i = 0; i < getWidth(); i++) {
			if (map[i] == Terrain.WALL && map[i + getWidth()] == Terrain.WATER
					&& Random.Int(4) == 0) {

				map[i] = Terrain.WALL_DECO;
			}
		}

		for (int i = getWidth(); i < getLength() - getWidth(); i++) {
			if (map[i] == Terrain.WALL && map[i - getWidth()] == Terrain.WALL
					&& map[i + getWidth()] == Terrain.WATER && Random.Int(2) == 0) {

				map[i] = Terrain.WALL_DECO;
			}
		}

		for (int i = getWidth() + 1; i < getLength() - getWidth() - 1; i++) {
			if (map[i] == Terrain.EMPTY) {

				int count = (map[i + 1] == Terrain.WALL ? 1 : 0)
						+ (map[i - 1] == Terrain.WALL ? 1 : 0)
						+ (map[i + getWidth()] == Terrain.WALL ? 1 : 0)
						+ (map[i - getWidth()] == Terrain.WALL ? 1 : 0);

				if (Random.Int(16) < count * count) {
					map[i] = Terrain.EMPTY_DECO;
				}
			}
		}

		while (true) {
			int pos = roomEntrance.random();
			if (pos != entrance) {
				map[pos] = Terrain.SIGN;
				break;
			}
		}	
		
		setPar();
		
		
	}

	@Override
	protected void createItems() {
		if (!Dungeon.limitedDrops.dewVial.dropped()	&& Dungeon.depth == 1) {
			addItemToSpawn(new DewVial());
			Dungeon.limitedDrops.dewVial.drop();
			addItemToSpawn(new SeedPouch());
			Dungeon.limitedDrops.seedBag.drop();
			
			addItemToSpawn(new Moonberry());
			addItemToSpawn(new Blueberry());
			addItemToSpawn(new Cloudberry());
			addItemToSpawn(new Blackberry());
			
			addItemToSpawn(new Egg());
		}
		
		if (Dungeon.depth == 2){
			addItemToSpawn(new Mushroom());
			Tinkerer1 npc = new Tinkerer1();
			do {
				npc.pos = randomRespawnCell();
			} while (npc.pos == -1 || heaps.get(npc.pos) != null);
			mobs.add(npc);
			Actor.occupyCell(npc);
		}
				
		Ghost.Quest.spawn(this);
		spawnGnoll(this);

		if (Dungeon.hero.heroClass==HeroClass.ROGUE && Random.Int(3) == 0){addItemToSpawn(new Bomb());}
		super.createItems();
	}
	
	public static void spawnGnoll(SewerLevel level) {
		if (Dungeon.depth == 4 && !Dungeon.gnollspawned){

			GnollArcher gnoll = new Ghost.GnollArcher();
			do {
				gnoll.pos = level.randomRespawnCell();
			} while (gnoll.pos == -1);
			level.mobs.add(gnoll);
			Actor.occupyCell(gnoll);
           
			Dungeon.gnollspawned = true;
		}
	}

	
	@Override
	public void addVisuals(Scene scene) {
		super.addVisuals(scene);
		addVisuals(this, scene);
	}

	public static void addVisuals(Level level, Scene scene) {
		for (int i = 0; i < getLength(); i++) {
			if (level.map[i] == Terrain.WALL_DECO) {
				scene.add(new Sink(i));
			}
		}
	}

	@Override
	public String tileName(int tile) {
		switch (tile) {
		case Terrain.WATER:
			return "Murky water";
		default:
			return super.tileName(tile);
		}
	}

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
		case Terrain.EMPTY_DECO:
			return "Wet yellowish moss covers the floor.";
		case Terrain.BOOKSHELF:
			return "The bookshelf is packed with cheap useless books. Might it burn?";
		default:
			return super.tileDesc(tile);
		}
	}

	private static class Sink extends Emitter {

		private int pos;
		private float rippleDelay = 0;

		private static final Emitter.Factory factory = new Factory() {

			@Override
			public void emit(Emitter emitter, int index, float x, float y) {
				WaterParticle p = (WaterParticle) emitter
						.recycle(WaterParticle.class);
				p.reset(x, y);
			}
		};

		public Sink(int pos) {
			super();

			this.pos = pos;

			PointF p = DungeonTilemap.tileCenterToWorld(pos);
			pos(p.x - 2, p.y + 1, 4, 0);

			pour(factory, 0.05f);
		}

		@Override
		public void update() {
			if (visible = Dungeon.visible[pos]) {

				super.update();

				if ((rippleDelay -= Game.elapsed) <= 0) {
					GameScene.ripple(pos + getWidth()).y -= DungeonTilemap.SIZE / 2;
					rippleDelay = Random.Float(0.2f, 0.3f);
				}
			}
		}
	}

	public static final class WaterParticle extends PixelParticle {

		public WaterParticle() {
			super();

			acc.y = 50;
			am = 0.5f;

			color(ColorMath.random(0xb6ccc2, 0x3b6653));
			size(2);
		}

		public void reset(float x, float y) {
			revive();

			this.x = x;
			this.y = y;

			speed.set(Random.Float(-2, +2), 0);

			left = lifespan = 0.5f;
		}
	}
}
