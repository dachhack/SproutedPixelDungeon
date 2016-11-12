package com.github.dachhack.sprout.items.artifacts;

import java.util.ArrayList;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.DungeonTilemap;
import com.github.dachhack.sprout.actors.Actor;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Frost;
import com.github.dachhack.sprout.actors.buffs.Strength;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.DeathRay;
import com.github.dachhack.sprout.effects.particles.ElmoParticle;
import com.github.dachhack.sprout.effects.particles.PurpleParticle;
import com.github.dachhack.sprout.effects.particles.SnowParticle;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.scrolls.Scroll;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.mechanics.Ballistica;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.github.dachhack.sprout.windows.WndBag;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

/**
 * Created by dachhack on 10/15/2015.
 */
public class RingOfFrost extends Artifact {

	{
		name = "Ring of Frost";
		image = ItemSpriteSheet.RING_FROST;

		level = 0;
		exp = 0;
		levelCap = 10;

		charge = 0;
		partialCharge = 0;
		chargeCap = 100;
		

		defaultAction = AC_BLAST;
	}

	protected String inventoryTitle = "Select a scroll";
	protected WndBag.Mode mode = WndBag.Mode.SCROLL;
	
	public static int consumedpts = 0;
	
	public static final String AC_BLAST = "BLAST";
	public static final String AC_ADD = "ADD";


	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		if (isEquipped(hero) && charge == 100 && !cursed)
			actions.add(AC_BLAST);
		if (isEquipped(hero) && level < levelCap && !cursed)
			actions.add(AC_ADD);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);
		if (action.equals(AC_BLAST)) {
   
			if (!isEquipped(hero))
				GLog.i("You need to equip your ring to do that.");
			else if (charge != chargeCap)
				GLog.i("Your ring isn't fully charged yet.");
			else {
				
				blast(hero.pos);
				charge = 0;
				updateQuickslot();
				GLog.p("Blast!");
				CellEmitter.get(hero.pos).start(SnowParticle.FACTORY, 0.2f, 6);
				
			}
			
		} else if (action.equals(AC_ADD)) {
			GameScene.selectItem(itemSelector, mode, inventoryTitle);
		}
	}
	
	private int distance() {
		return (level() * 2)+1;
	}
	
	public int level(){
		return level;
	}
	
	public void blast(int cell) {
		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			int dist = Level.distance(cell, mob.pos);
			 if (dist<=distance()){
			    mob.damage(Random.Int(level(),level()*level()+1), this);
			    Buff.prolong(mob, Frost.class, Frost.duration(mob)* Random.Float(1f*level(), 1.5f*level()));
				CellEmitter.get(mob.pos).start(SnowParticle.FACTORY, 0.2f, 6);
			 } 
		}	
		ringUsed();
	}
	
	
	
	protected void ringUsed() {
		
		updateQuickslot();

	}
	
	
	
	
	@Override
	protected ArtifactBuff passiveBuff() {
		return new ringRecharge();
	}

	@Override
	public String desc() {
		String desc = "The ring of frost chills the air with icy power. ";
		if (isEquipped(Dungeon.hero)) {
			desc += "\n\n";
			if (charge < 100)
				desc += "Its power is restrained for now. ";
			else
				desc += "It's power is at the brink of being unleashed! ";
		}

		return desc;
	}

	public class ringRecharge extends ArtifactBuff {
		@Override
		public boolean act() {
			if (charge < chargeCap) {
				
					partialCharge += 1+(level*level);

				if (partialCharge >= 10) {
					charge++;
					partialCharge = 0;
					if (charge == chargeCap) {
						partialCharge = 0;
					}

				}
			} else
				partialCharge = 0;

			
			updateQuickslot();

			spend(TICK);

			return true;
		}

	}
	
	private static final String PARTIALCHARGE = "partialCharge";
	private static final String CHARGE = "charge";
	private static final String CONSUMED = "consumedpts";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(PARTIALCHARGE, partialCharge);
		bundle.put(CHARGE, charge);
		bundle.put(CONSUMED, consumedpts);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		partialCharge = bundle.getInt(PARTIALCHARGE);
		charge = bundle.getInt(CHARGE);
		consumedpts = bundle.getInt(CONSUMED);
	}
	
	protected WndBag.Listener itemSelector = new WndBag.Listener() {
		@Override
		public void onSelect(Item item) {
			if (item != null && item instanceof Scroll && item.isIdentified()) {
				Hero hero = Dungeon.hero;
				int scrollWorth = item.consumedValue;
				consumedpts += scrollWorth;
			
				hero.sprite.operate(hero.pos);
				hero.busy();
				hero.spend(2f);
				Sample.INSTANCE.play(Assets.SND_BURNING);
				hero.sprite.emitter().burst(ElmoParticle.FACTORY, 12);

				item.detach(hero.belongings.backpack);
				GLog.h("Your ring consumes the power from the scroll! It is at %s points!", consumedpts);
				
				int levelChk = ((level()*level()/2)+1)*10;
								
				if (consumedpts > levelChk && level()<10) {
					upgrade();
					GLog.p("Your ring certainly looks better!");
					}
				
			
			} else if (item instanceof Scroll && !item.isIdentified()){
				GLog.w("You're not sure what type of scroll this is yet.");
		   } else if (item != null){
			GLog.w("You are unable to add this scroll to the book.");
		}
	 }
	};
	
}
