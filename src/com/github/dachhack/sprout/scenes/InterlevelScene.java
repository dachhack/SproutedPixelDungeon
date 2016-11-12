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
package com.github.dachhack.sprout.scenes;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.actors.mobs.pets.PET;
import com.github.dachhack.sprout.items.Generator;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.windows.WndError;
import com.github.dachhack.sprout.windows.WndStory;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class InterlevelScene extends PixelScene {

	private static final float TIME_TO_FADE = 0.3f;

	private static final String TXT_DESCENDING = "Descending...";
	private static final String TXT_ASCENDING = "Ascending...";
	private static final String TXT_LOADING = "Loading...";
	private static final String TXT_RESURRECTING = "Resurrecting...";
	private static final String TXT_RETURNING = "Returning...";
	private static final String TXT_FALLING = "Falling...";
	//private static final String TXT_PORT = "Descending...";
	private static final String TXT_PORTSYOG = "Entering Shadow Den...";
	private static final String TXT_PORTCATA = "The dead are restless...";
	private static final String TXT_PORTONI = "Feed us!";
	private static final String TXT_PORTCHASM = "You dissolve into light...";
	private static final String TXT_PORTSEWERS = "You hear leaves blowing...";
	private static final String TXT_PORTPRISON = "Screams and chaos...";
	private static final String TXT_PORTCAVES = "Let's go fishing...";
	private static final String TXT_PORTCITY = "Clinking of coins...";
	private static final String TXT_PORTHALLS = "Prepare to meet doom...";
	private static final String TXT_PORTCRAB = "A rushing of water...";
	private static final String TXT_PORTTENGU = "Entering Tengu hideout...";
	private static final String TXT_PORTCOIN = "Coins spilling...";
	private static final String TXT_PORTBONE = "War drums and fire...";
	private static final String TXT_JOURNAL = "Flipping pages...";
	private static final String TXT_SOKOBANFAIL = "You are ejected...";
	private static final String TXT_PALANTIR = "You break the palatir...";

	private static final String ERR_FILE_NOT_FOUND = "Save file not found. If this error persists after restarting, "
			+ "it may mean this save game is corrupted. Sorry about that.";
	private static final String ERR_IO = "Cannot read save file. If this error persists after restarting, "
			+ "it may mean this save game is corrupted. Sorry about that.";

	public static enum Mode {
		DESCEND, ASCEND, CONTINUE, RESURRECT, RETURN, FALL, PORT1, PORT2, PORT3, PORT4,
		PORTSEWERS, PORTPRISON, PORTCAVES, PORTCITY, PORTHALLS, PORTCRAB, PORTTENGU, PORTCOIN, PORTBONE, RETURNSAVE,
		JOURNAL, SOKOBANFAIL, PALANTIR
	};

	public static Mode mode;

	public static int returnDepth;
	public static int returnPos;
	
	public static int journalpage;
	public static boolean first;

	public static boolean noStory = false;

	public static boolean fallIntoPit;

	private enum Phase {
		FADE_IN, STATIC, FADE_OUT
	};

	private Phase phase;
	private float timeLeft;

	private BitmapText message;

	private Thread thread;
	private Exception error = null;

	@Override
	public void create() {
		super.create();

		String text = "";
		switch (mode) {
		case DESCEND:
			text = TXT_DESCENDING;
			break;
		case ASCEND:
			text = TXT_ASCENDING;
			break;
		case CONTINUE:
			text = TXT_LOADING;
			break;
		case RESURRECT:
			text = TXT_RESURRECTING;
			break;
		case RETURN:
		case RETURNSAVE:
			text = TXT_RETURNING;
			break;
		case FALL:
			text = TXT_FALLING;
			break;
		case PORT1: 
			text = TXT_PORTCATA;
		    break;
		case PORT2: 
			text = TXT_PORTONI;
		    break;
		case  PORT3:
			text = TXT_PORTCHASM;
		    break;
		case  PORT4:
			text = TXT_PORTSYOG;
		    break;
		case  PORTSEWERS:
			text = TXT_PORTSEWERS;
		    break;
		case  PORTPRISON:
			text = TXT_PORTPRISON;
		    break;
		case  PORTCAVES:
			text = TXT_PORTCAVES;
		    break;
		case  PORTCITY:
			text = TXT_PORTCITY;
		    break;
		case  PORTHALLS:
			text = TXT_PORTHALLS;
		    break;
		case  PORTCRAB:
			text = TXT_PORTCRAB;
		    break;
		case  PORTTENGU:
			text = TXT_PORTTENGU;
		    break;
		case  PORTCOIN:
			text = TXT_PORTCOIN;
		    break;
		case  PORTBONE:
			text = TXT_PORTBONE;
		    break;
		case  JOURNAL:
			text = TXT_JOURNAL;
		    break;
		case  SOKOBANFAIL:
			text = TXT_SOKOBANFAIL;
		    break;
		case  PALANTIR:
			text = TXT_PALANTIR;
		    break;
		}

		message = PixelScene.createText(text, 9);
		message.measure();
		message.x = (Camera.main.width - message.width()) / 2;
		message.y = (Camera.main.height - message.height()) / 2;
		add(message);

		phase = Phase.FADE_IN;
		timeLeft = TIME_TO_FADE;

		thread = new Thread() {
			@Override
			public void run() {

				try {

					Generator.reset();

					switch (mode) {
					case DESCEND:
						descend();
						break;
					case ASCEND:
						ascend();
						break;
					case CONTINUE:
						restore();
						break;
					case RESURRECT:
						resurrect();
						break;
					case RETURN:
						returnTo();
						break;
					case RETURNSAVE:
						returnToSave();
						break;
					case FALL:
						fall();
						break;
					case PORT1:
						portal(1);
						break;
					case PORT2:
						portal(2);
						break;
					case PORT3:
						portal(3);
						break;
					case PORT4:
						portal(4);
						break;
					case PORTSEWERS:
						portal(5);
						break;
					case PORTPRISON:
						portal(6);
						break;
					case PORTCAVES:
						portal(7);
						break;
					case PORTCITY:
						portal(8);
						break;
					case PORTHALLS:
						portal(9);
						break;
					case PORTCRAB:
						portal(10);
						break;
					case PORTTENGU:
						portal(11);
						break;
					case PORTCOIN:
						portal(12);
						break;
					case PORTBONE:
						portal(13);
						break;
					case JOURNAL:
						journalPortal(journalpage);
						break;	
					case SOKOBANFAIL:
						ascend();
						break;	
					case PALANTIR:
						portal(14);
						break;	
					}

					if ((Dungeon.depth % 5) == 0) {
						Sample.INSTANCE.load(Assets.SND_BOSS);
					}

				} catch (Exception e) {

					error = e;

				}

				if (phase == Phase.STATIC && error == null) {
					phase = Phase.FADE_OUT;
					timeLeft = TIME_TO_FADE;
				}
			}
		};
		thread.start();
	}

	@Override
	public void update() {
		super.update();

		float p = timeLeft / TIME_TO_FADE;

		switch (phase) {

		case FADE_IN:
			message.alpha(1 - p);
			if ((timeLeft -= Game.elapsed) <= 0) {
				if (!thread.isAlive() && error == null) {
					phase = Phase.FADE_OUT;
					timeLeft = TIME_TO_FADE;
				} else {
					phase = Phase.STATIC;
				}
			}
			break;

		case FADE_OUT:
			message.alpha(p);

			if (mode == Mode.CONTINUE
					|| (mode == Mode.DESCEND && Dungeon.depth == 1)) {
				Music.INSTANCE.volume(p);
			}
			if ((timeLeft -= Game.elapsed) <= 0) {
				Game.switchScene(GameScene.class);
			}
			break;

		case STATIC:
			if (error != null) {
				String errorMsg;
				if (error instanceof FileNotFoundException)
					errorMsg = ERR_FILE_NOT_FOUND;
				else if (error instanceof IOException)
					errorMsg = ERR_IO;

				else
					throw new RuntimeException(
							"fatal error occured while moving between floors",
							error);

				add(new WndError(errorMsg) {
					@Override
					public void onBackPressed() {
						super.onBackPressed();
						Game.switchScene(StartScene.class);
					};
				});
				error = null;
			}
			break;
		}
	}

	private void descend() throws IOException {

		Actor.fixTime();
		if (Dungeon.hero == null) {
			Dungeon.init();
			if (noStory) {
				Dungeon.chapters.add(WndStory.ID_SEWERS);
				noStory = false;
			}
		} else {
			Dungeon.saveLevel();
		}

		Level level;
		if ((Dungeon.depth>60) && (Dungeon.depth >= Statistics.realdeepestFloor) && ((Random.Int(100)<50) || Dungeon.depth==65) ){
			level = Dungeon.newMineBossLevel();	
		}else if (Dungeon.townCheck(Dungeon.depth) && (Dungeon.depth >= Statistics.realdeepestFloor || Random.Int(10)<2)){
				level = Dungeon.newLevel();	
	    }else if (Dungeon.depth >= Statistics.deepestFloor && !Dungeon.townCheck(Dungeon.depth) ){				
			level = Dungeon.newLevel();
		} else {
			Dungeon.depth++;
			level = Dungeon.loadLevel(Dungeon.hero.heroClass);
		}
		Dungeon.switchLevel(level, level.entrance);
	}

	private void fall() throws IOException {

		Actor.fixTime();
		Dungeon.saveLevel();

		Level level;
		if (Dungeon.depth >= Statistics.deepestFloor) {
			level = Dungeon.newLevel();
		} else {
			Dungeon.depth++;
			level = Dungeon.loadLevel(Dungeon.hero.heroClass);
		}
		Dungeon.switchLevel(level,
				fallIntoPit ? level.pitCell() : level.randomRespawnCell());
	}

	private void ascend() throws IOException {
		Actor.fixTime();

		Dungeon.saveLevel();
		if (Dungeon.depth == 41) {
			  Dungeon.depth=40;
			  Level level = Dungeon.loadLevel(Dungeon.hero.heroClass);
			  Dungeon.switchLevel(level, level.entrance);
		} else if (Dungeon.depth > 26 && !Dungeon.townCheck(Dungeon.depth)) {
		  Dungeon.depth=1;
		  Level level = Dungeon.loadLevel(Dungeon.hero.heroClass);
		  Dungeon.switchLevel(level, level.entrance);
		} else {
		  Dungeon.depth--;
		  Level level = Dungeon.loadLevel(Dungeon.hero.heroClass);
		  Dungeon.switchLevel(level, level.exit);	
		}
	}

	private void returnTo() throws IOException {
		checkPetPort();
		Actor.fixTime();
       // Dungeon.hero.invisible=0;
        Dungeon.saveAll();
		Dungeon.depth = returnDepth;
		Level level = Dungeon.loadLevel(Dungeon.hero.heroClass);
		Dungeon.switchLevel(level,
				Level.resizingNeeded ? level.adjustPos(returnPos) : returnPos);
	}
	
	private void returnToSave() throws IOException {

		checkPetPort();
		Actor.fixTime();
       // Dungeon.hero.invisible=0;
        Dungeon.saveAll();
		if (Dungeon.bossLevel(Statistics.deepestFloor)){
			Dungeon.depth = Statistics.deepestFloor-1;
		} else {
			Dungeon.depth = Statistics.deepestFloor;
		}
		Level level = Dungeon.loadLevel(Dungeon.hero.heroClass);
		Dungeon.switchLevel(level, level.entrance);
	}

	private void restore() throws IOException {

		Actor.fixTime();

		Dungeon.loadGame(StartScene.curClass);
		if (Dungeon.depth == -1) {
			Dungeon.depth = Statistics.deepestFloor;
			Dungeon.switchLevel(Dungeon.loadLevel(StartScene.curClass), -1);
		} else {
			Level level = Dungeon.loadLevel(StartScene.curClass);
			Dungeon.switchLevel(level,
					Level.resizingNeeded ? level.adjustPos(Dungeon.hero.pos)
							: Dungeon.hero.pos);
		}
	}

	private void resurrect() throws IOException {

		Actor.fixTime();

		if (Dungeon.level.locked) {
			Dungeon.hero.resurrect(Dungeon.depth);
			Dungeon.depth--;
			Level level = Dungeon.newLevel();
			Dungeon.switchLevel(level, level.entrance);
		} else {
			Dungeon.hero.resurrect(-1);
			Dungeon.resetLevel();
		}
	}
	
	private void portal(int branch) throws IOException {

	    checkPetPort();
		Actor.fixTime();
		Dungeon.saveAll();
				
		Level level;
		switch(branch){
		case 1:
			level=Dungeon.newCatacombLevel();
			break;
		case 2:
			level = Dungeon.newFortressLevel();
			break;
		case 3:
			level = Dungeon.newChasmLevel();
			break;
		case 4:
			level = Dungeon.newInfestLevel();
			break;
		case 5:
			level = Dungeon.newFieldLevel();
			break;
		case 6:
			level = Dungeon.newBattleLevel();
			break;
		case 7:
			level = Dungeon.newFishLevel();
			break;
		case 8:
			level = Dungeon.newVaultLevel();
			break;
		case 9:
			level = Dungeon.newHallsBossLevel();
			break;
		case 10:
			level = Dungeon.newCrabBossLevel();
			break;
		case 11:
			level = Dungeon.newTenguHideoutLevel();
			break;
		case 12:
			level = Dungeon.newThiefBossLevel();
			break;
		case 13:
			level = Dungeon.newSkeletonBossLevel();
			break;
		case 14:
			level = Dungeon.newZotBossLevel();
			break;
		default:
			level = Dungeon.newLevel();
		}
		Dungeon.switchLevel(level, level.entrance);
	}
	
	private void journalPortal(int branch) throws IOException {
	    //checkPetPort();
		Actor.fixTime();
		Dungeon.saveAll();
				
		Level level;
		
		if (branch==5 && !first){
		   Dungeon.depth=55;
		   level = Dungeon.loadLevel(Dungeon.hero.heroClass);	
		   
		} else if (branch==0 && !first){
			   Dungeon.depth=50;
			   level = Dungeon.loadLevel(Dungeon.hero.heroClass);	
			   
		} else {
		   level=Dungeon.newJournalLevel(branch, first);			
		}
		
		Dungeon.switchLevel(level, level.entrance);
	}
	
	private PET checkpet(){
		for (Mob mob : Dungeon.level.mobs) {
			if(mob instanceof PET) {
				return (PET) mob;
			}
		}	
		return null;
	}
	
	private boolean checkpetNear(){
		for (int n : Level.NEIGHBOURS8) {
			int c =  Dungeon.hero.pos + n;
			if (Actor.findChar(c) instanceof PET) {
				return true;
			}
		}
		return false;
	}
	
	private void checkPetPort(){
		PET pet = checkpet();
		if(pet!=null && checkpetNear()){
		  //GLog.i("I see pet");
		  Dungeon.hero.petType=pet.type;
		  Dungeon.hero.petLevel=pet.level;
		  Dungeon.hero.petKills=pet.kills;	
		  Dungeon.hero.petHP=pet.HP;
		  Dungeon.hero.petExperience=pet.experience;
		  Dungeon.hero.petCooldown=pet.cooldown;
		  pet.destroy();
		  Dungeon.hero.petfollow=true;
		} else if (Dungeon.hero.haspet && Dungeon.hero.petfollow) {
			Dungeon.hero.petfollow=true;
		} else {
			Dungeon.hero.petfollow=false;
		}
		
	}
	
		
	@Override
	protected void onBackPressed() {
		// Do nothing
	}
}
