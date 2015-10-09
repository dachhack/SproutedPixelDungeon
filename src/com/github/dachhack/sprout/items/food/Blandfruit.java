package com.github.dachhack.sprout.items.food;


import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Badges;
import com.github.dachhack.sprout.Statistics;
import com.github.dachhack.sprout.actors.buffs.Barkskin;
import com.github.dachhack.sprout.actors.buffs.Bleeding;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Cripple;
import com.github.dachhack.sprout.actors.buffs.EarthImbue;
import com.github.dachhack.sprout.actors.buffs.FireImbue;
import com.github.dachhack.sprout.actors.buffs.Hunger;
import com.github.dachhack.sprout.actors.buffs.Invisibility;
import com.github.dachhack.sprout.actors.buffs.Poison;
import com.github.dachhack.sprout.actors.buffs.ToxicImbue;
import com.github.dachhack.sprout.actors.buffs.Weakness;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.npcs.Wandmaker;
import com.github.dachhack.sprout.effects.Speck;
import com.github.dachhack.sprout.effects.SpellSprite;
import com.github.dachhack.sprout.items.Item;
import com.github.dachhack.sprout.items.potions.Potion;
import com.github.dachhack.sprout.items.potions.PotionOfExperience;
import com.github.dachhack.sprout.items.potions.PotionOfFrost;
import com.github.dachhack.sprout.items.potions.PotionOfHealing;
import com.github.dachhack.sprout.items.potions.PotionOfInvisibility;
import com.github.dachhack.sprout.items.potions.PotionOfLevitation;
import com.github.dachhack.sprout.items.potions.PotionOfLiquidFlame;
import com.github.dachhack.sprout.items.potions.PotionOfMight;
import com.github.dachhack.sprout.items.potions.PotionOfMindVision;
import com.github.dachhack.sprout.items.potions.PotionOfParalyticGas;
import com.github.dachhack.sprout.items.potions.PotionOfPurity;
import com.github.dachhack.sprout.items.potions.PotionOfStrength;
import com.github.dachhack.sprout.items.potions.PotionOfToxicGas;
import com.github.dachhack.sprout.items.scrolls.ScrollOfRecharging;
import com.github.dachhack.sprout.plants.Blindweed;
import com.github.dachhack.sprout.plants.Dreamfoil;
import com.github.dachhack.sprout.plants.Earthroot;
import com.github.dachhack.sprout.plants.Fadeleaf;
import com.github.dachhack.sprout.plants.Firebloom;
import com.github.dachhack.sprout.plants.Icecap;
import com.github.dachhack.sprout.plants.Phaseshift;
import com.github.dachhack.sprout.plants.Plant.Seed;
import com.github.dachhack.sprout.plants.Sorrowmoss;
import com.github.dachhack.sprout.plants.Stormvine;
import com.github.dachhack.sprout.plants.Sungrass;
import com.github.dachhack.sprout.sprites.ItemSprite;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.github.dachhack.sprout.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

/**
 * Created by debenhame on 12/08/2014.
 */
public class Blandfruit extends Food {

	public String message = "You eat the Blandfruit, bleugh!";
	public String info = "So dry and insubstantial, perhaps stewing it with another ingredient would improve it.";

	public Potion potionAttrib = null;
	public ItemSprite.Glowing potionGlow = null;

	{
		name = "Blandfruit";
		stackable = true;
		image = ItemSpriteSheet.BLANDFRUIT;
		energy = (Hunger.STARVING - Hunger.HUNGRY) / 2;
		hornValue = 6; // only applies when blandfruit is cooked

		bones = true;
	}

	@Override
	public boolean isSimilar(Item item) {
		if (item instanceof Blandfruit) {
			if (potionAttrib == null) {
				if (((Blandfruit) item).potionAttrib == null)
					return true;
			} else if (((Blandfruit) item).potionAttrib != null) {
				if (((Blandfruit) item).potionAttrib.getClass() == potionAttrib
						.getClass())
					return true;
			}
		}
		return false;
	}

	@Override
	public void execute(Hero hero, String action) {
		if (action.equals(AC_EAT)) {

			if (potionAttrib == null) {

				detach(hero.belongings.backpack);

				hero.buff(Hunger.class).satisfy(energy);
				GLog.i(message);

				hero.sprite.operate(hero.pos);
				hero.busy();
				SpellSprite.show(hero, SpellSprite.FOOD);
				Sample.INSTANCE.play(Assets.SND_EAT);

				hero.spend(1f);

				Statistics.foodEaten++;
				Badges.validateFoodEaten();
			} else {

				hero.buff(Hunger.class).satisfy(Hunger.HUNGRY);

				detach(hero.belongings.backpack);

				hero.spend(1f);
				hero.busy();

				if (potionAttrib instanceof PotionOfFrost) {
					GLog.i("the Icefruit tastes a bit like Frozen Carpaccio.");
					switch (Random.Int(5)) {
					case 0:
						GLog.i("You see your hands turn invisible!");
						Buff.affect(hero, Invisibility.class,
								Invisibility.DURATION);
						break;
					case 1:
						GLog.i("You feel your skin harden!");
						Buff.affect(hero, Barkskin.class).level(hero.HT / 4);
						break;
					case 2:
						GLog.i("Refreshing!");
						Buff.detach(hero, Poison.class);
						Buff.detach(hero, Cripple.class);
						Buff.detach(hero, Weakness.class);
						Buff.detach(hero, Bleeding.class);
						break;
					case 3:
						GLog.i("You feel better!");
						if (hero.HP < hero.HT) {
							hero.HP = Math.min(hero.HP + hero.HT / 4, hero.HT);
							hero.sprite.emitter().burst(
									Speck.factory(Speck.HEALING), 1);
						}
						break;
					}
				} else if (potionAttrib instanceof PotionOfLiquidFlame) {
					GLog.i("You feel a great fire burning within you!");
					Buff.affect(hero, FireImbue.class).set(FireImbue.DURATION);
				} else if (potionAttrib instanceof PotionOfToxicGas) {
					GLog.i("You are imbued with vile toxic power!");
					Buff.affect(hero, ToxicImbue.class)
							.set(ToxicImbue.DURATION);
				} else if (potionAttrib instanceof PotionOfParalyticGas) {
					GLog.i("You feel the power of the earth coursing through you!");
					Buff.affect(hero, EarthImbue.class, EarthImbue.DURATION);
				} else
					potionAttrib.apply(hero);

				Sample.INSTANCE.play(Assets.SND_EAT);
				SpellSprite.show(hero, SpellSprite.FOOD);
				hero.sprite.operate(hero.pos);

				switch (hero.heroClass) {
				case WARRIOR:
					if (hero.HP < hero.HT) {
						hero.HP = Math.min(hero.HP + 5, hero.HT);
						hero.sprite.emitter().burst(
								Speck.factory(Speck.HEALING), 1);
					}
					break;
				case MAGE:
					hero.belongings.charge(false);
					ScrollOfRecharging.charge(hero);
					break;
				case ROGUE:
				case HUNTRESS:
					break;
				}
			}
		} else {
			super.execute(hero, action);
		}
	}

	@Override
	public String info() {
		return info;
	}

	@Override
	public int price() {
		return 20 * quantity;
	}

	public Item cook(Seed seed) {

		try {
			return imbuePotion((Potion) seed.alchemyClass.newInstance());
		} catch (Exception e) {
			return null;
		}

	}

	public Item imbuePotion(Potion potion) {

		potionAttrib = potion;
		potionAttrib.ownedByFruit = true;

		potionAttrib.image = ItemSpriteSheet.BLANDFRUIT;

		info = "The fruit has plumped up from its time soaking in the pot and has even absorbed the properties "
				+ "of the seed it was cooked with.\n\n";

		if (potionAttrib instanceof PotionOfHealing) {

			name = "Sunfruit";
			potionGlow = new ItemSprite.Glowing(0x2EE62E);
			info += "It looks delicious and hearty, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfStrength) {

			name = "Rotfruit";
			potionGlow = new ItemSprite.Glowing(0xCC0022);
			info += "It looks delicious and powerful, ready to be eaten!";
			
		} else if (potionAttrib instanceof PotionOfMight) {

			name = "Mightyfruit";
			potionGlow = new ItemSprite.Glowing(0xFF3300);
			info += "It looks delicious and super powerful, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfParalyticGas) {

			name = "Earthfruit";
			potionGlow = new ItemSprite.Glowing(0x67583D);
			info += "It looks delicious and firm, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfInvisibility) {

			name = "Blindfruit";
			potionGlow = new ItemSprite.Glowing(0xE5D273);
			info += "It looks delicious and shiny, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfLiquidFlame) {

			name = "Firefruit";
			potionGlow = new ItemSprite.Glowing(0xFF7F00);
			info += "It looks delicious and spicy, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfFrost) {

			name = "Icefruit";
			potionGlow = new ItemSprite.Glowing(0x66B3FF);
			info += "It looks delicious and refreshing, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfMindVision) {

			name = "Fadefruit";
			potionGlow = new ItemSprite.Glowing(0xB8E6CF);
			info += "It looks delicious and shadowy, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfToxicGas) {

			name = "Sorrowfruit";
			potionGlow = new ItemSprite.Glowing(0xA15CE5);
			info += "It looks delicious and crisp, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfLevitation) {

			name = "Stormfruit";
			potionGlow = new ItemSprite.Glowing(0x1C3A57);
			info += "It looks delicious and lightweight, ready to be eaten!";

		} else if (potionAttrib instanceof PotionOfPurity) {

			name = "Dreamfruit";
			potionGlow = new ItemSprite.Glowing(0x8E2975);
			info += "It looks delicious and clean, ready to be eaten!";
			
		} else if (potionAttrib instanceof PotionOfExperience) {

			name = "Starfruit";
			potionGlow = new ItemSprite.Glowing( 0xA79400 );
			info += "It looks delicious and glorious, ready to be eaten!";

		}

		return this;
	}

	public static final String POTIONATTRIB = "potionattrib";

	@Override
	public void cast(final Hero user, int dst) {
		if (potionAttrib instanceof PotionOfLiquidFlame
				|| potionAttrib instanceof PotionOfToxicGas
				|| potionAttrib instanceof PotionOfParalyticGas
				|| potionAttrib instanceof PotionOfFrost
				|| potionAttrib instanceof PotionOfLevitation
				|| potionAttrib instanceof PotionOfPurity) {
			potionAttrib.cast(user, dst);
			detach(user.belongings.backpack);
		} else {
			super.cast(user, dst);
		}

	}

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(POTIONATTRIB, potionAttrib);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		if (bundle.contains(POTIONATTRIB)) {
			imbuePotion((Potion) bundle.get(POTIONATTRIB));

			// TODO: legacy code for pre-v0.2.3, remove when saves from that
			// version are no longer supported.
		} else if (bundle.contains("name")) {
			name = bundle.getString("name");

			if (name.equals("Healthfruit"))
				cook(new Sungrass.Seed());
			else if (name.equals("Powerfruit"))
				cook(new Wandmaker.Rotberry.Seed());
			else if (name.equals("Paralyzefruit"))
				cook(new Earthroot.Seed());
			else if (name.equals("Invisifruit"))
				cook(new Blindweed.Seed());
			else if (name.equals("Flamefruit"))
				cook(new Firebloom.Seed());
			else if (name.equals("Frostfruit"))
				cook(new Icecap.Seed());
			else if (name.equals("Visionfruit"))
				cook(new Fadeleaf.Seed());
			else if (name.equals("Toxicfruit"))
				cook(new Sorrowmoss.Seed());
			else if (name.equals("Floatfruit"))
				cook(new Stormvine.Seed());
			else if (name.equals("Purefruit"))
				cook(new Dreamfoil.Seed());
			else if (name.equals("Mightyfruit"))
				cook(new Phaseshift.Seed());
		}

	}

	@Override
	public ItemSprite.Glowing glowing() {
		return potionGlow;
	}

}
