package club.claycoffee.ClayTech;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;

public class Recipes {
	static ItemStack newMotor;
	public final static ItemStack[] BLIND_CORE = { new ItemStack(Material.INK_SAC), new ItemStack(Material.END_CRYSTAL),
			new ItemStack(Material.INK_SAC), new ItemStack(Material.INK_SAC), Defines.MAGIC_CLAY,
			new ItemStack(Material.INK_SAC), new ItemStack(Material.INK_SAC), new ItemStack(Material.END_CRYSTAL),
			new ItemStack(Material.INK_SAC) };
	public final static ItemStack[] BLIND_SWORD = { Defines.BLIND_CORE, Defines.BLIND_CORE, Defines.BLIND_CORE,
			Defines.BLIND_CORE, new ItemStack(Material.DIAMOND_SWORD), Defines.BLIND_CORE, Defines.BLIND_CORE,
			Defines.BLIND_CORE, Defines.BLIND_CORE };
	public final static ItemStack[] POISON_EYE = { new ItemStack(Material.SPIDER_EYE),
			new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE),
			Defines.MAGIC_CLAY, new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE),
			new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE) };
	public final static ItemStack[] POISON_CORE = { new ItemStack(Material.COAL_BLOCK), Defines.POISON_EYE,
			new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.FERMENTED_SPIDER_EYE),
			new ItemStack(Material.FERMENTED_SPIDER_EYE), new ItemStack(Material.FERMENTED_SPIDER_EYE),
			new ItemStack(Material.COAL_BLOCK), Defines.POISON_EYE, new ItemStack(Material.COAL_BLOCK),
			Defines.POISON_EYE };
	public final static ItemStack[] CONFUSION_CORE = { new ItemStack(Material.PUFFERFISH),
			new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH),
			Defines.MAGIC_CLAY, new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH),
			new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH) };
	public final static ItemStack[] BLACK_ROCK_BLOCK = { new ItemStack(Material.OBSIDIAN),
			new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN),
			Defines.MAGIC_CLAY, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN),
			new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN) };
	public final static ItemStack[] SLOWNESS_CORE = { Defines.BLACK_ROCK_BLOCK, Defines.BLACK_ROCK_BLOCK,
			Defines.BLACK_ROCK_BLOCK, Defines.BLACK_ROCK_BLOCK, Defines.MAGIC_CLAY, Defines.BLACK_ROCK_BLOCK,
			Defines.BLACK_ROCK_BLOCK, Defines.BLACK_ROCK_BLOCK, Defines.BLACK_ROCK_BLOCK };
	public final static ItemStack[] ADVANCED_CONFUSION_CORE = { Defines.CONFUSION_CORE, Defines.CONFUSION_CORE,
			Defines.CONFUSION_CORE, Defines.CONFUSION_CORE, Defines.MAGIC_CLAY, Defines.CONFUSION_CORE,
			Defines.CONFUSION_CORE, Defines.CONFUSION_CORE, Defines.CONFUSION_CORE };
	public final static ItemStack[] ADVANCED_POISON_CORE = { Defines.POISON_CORE, Defines.POISON_CORE,
			Defines.POISON_CORE, Defines.POISON_CORE, Defines.MAGIC_CLAY, Defines.POISON_CORE, Defines.POISON_CORE,
			Defines.POISON_CORE, Defines.POISON_CORE };
	public final static ItemStack[] ADVANCED_SLOWNESS_CORE = { Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE,
			Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE, Defines.MAGIC_CLAY, Defines.SLOWNESS_CORE,
			Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE };
	public final static ItemStack[] ADVANCED_BLIND_CORE = { Defines.BLIND_CORE, Defines.BLIND_CORE, Defines.BLIND_CORE,
			Defines.BLIND_CORE, Defines.MAGIC_CLAY, Defines.BLIND_CORE, Defines.BLIND_CORE, Defines.BLIND_CORE,
			Defines.BLIND_CORE };
	public final static ItemStack[] FOUR_BOW = { Defines.ADVANCED_SLOWNESS_CORE, Defines.ADVANCED_POISON_CORE,
			Defines.ADVANCED_BLIND_CORE, Defines.ADVANCED_CONFUSION_CORE, new ItemStack(Material.BOW),
			Defines.ADVANCED_CONFUSION_CORE, Defines.ADVANCED_BLIND_CORE, Defines.ADVANCED_POISON_CORE,
			Defines.ADVANCED_SLOWNESS_CORE };
	public final static ItemStack[] POISON_SWORD = { Defines.POISON_CORE, Defines.POISON_CORE, Defines.POISON_CORE,
			Defines.POISON_CORE, new ItemStack(Material.DIAMOND_SWORD), Defines.POISON_CORE, Defines.POISON_CORE,
			Defines.POISON_CORE, Defines.POISON_CORE };
	public final static ItemStack[] ANTI_SLOWNESS_BOOTS = { Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE,
			Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE, new ItemStack(Material.IRON_BOOTS), Defines.SLOWNESS_CORE,
			Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE, Defines.SLOWNESS_CORE };
	public final static ItemStack[] TNT_EXPLOSION_CREATER = {new ItemStack(Material.TNT),Defines.MAGIC_CLAY,new ItemStack(Material.TNT),Defines.MAGIC_CLAY,new ItemStack(Material.DIAMOND_HOE),Defines.MAGIC_CLAY,new ItemStack(Material.TNT),new ItemStack(Material.FLINT_AND_STEEL),new ItemStack(Material.TNT)};
	
	// 食物
	public final static ItemStack[] CHICKEN_FOOT = { null, new ItemStack(Material.COAL), null, null,
			Defines.RAW_CHICKEN_FOOT, null, null, new ItemStack(Material.COAL), null };
	public final static ItemStack[] SPICY_CHICKEN_BURGER = { Defines.RAW_BREAD, Defines.RAW_VEGETABLE,
			Defines.RAW_BREAD, Defines.RAW_VEGETABLE, Defines.CHICKEN_FOOT, Defines.RAW_VEGETABLE, Defines.RAW_BREAD,
			Defines.MAGIC_CLAY, Defines.RAW_BREAD };
	public final static ItemStack[] BABA_BURGER = { new ItemStack(Material.COAL), Defines.RAW_BREAD,
			new ItemStack(Material.COAL), new ItemStack(Material.COAL), new ItemStack(Material.COAL),
			new ItemStack(Material.COAL), new ItemStack(Material.COAL), Defines.RAW_BREAD,
			new ItemStack(Material.COAL) };
	public final static ItemStack[] CHOCOLATE = { Defines.COCOA_BEAN, Defines.COCOA_BEAN, Defines.COCOA_BEAN,
			Defines.COCOA_BEAN, Defines.STARCH, Defines.COCOA_BEAN, Defines.COCOA_BEAN, Defines.COCOA_BEAN,
			Defines.COCOA_BEAN };
	public final static ItemStack[] SNAIL_FOOD = { Defines.FLOUR, Defines.FLOUR, Defines.FLOUR, Defines.FLOUR,
			Defines.SNAIL_HEALTHY, Defines.FLOUR, Defines.FLOUR, Defines.FLOUR, Defines.FLOUR };
	public final static ItemStack[] CLAY_COFFEE = { null, Defines.COCOA_BEAN, null, null, Defines.COCOA_BEAN, null,
			null, Defines.DRINK_BOTTLE, null };
	public final static ItemStack[] LEMON_POWDER_DRINK = { null, Defines.LEMON_POWDER, null, null, Defines.LEMON_POWDER,
			null, null, Defines.DRINK_BOTTLE, null };
	public final static ItemStack[] TEA_DRINK = { null, Defines.TEA_POWDER, null, null, Defines.TEA_POWDER, null, null,
			Defines.DRINK_BOTTLE, null };
	public final static ItemStack[] LEMON_TEA_DRINK = { null, Defines.TEA_POWDER, null, Defines.LEMON_POWDER,
			Defines.TEA_POWDER, Defines.LEMON_POWDER, null, Defines.DRINK_BOTTLE, null };
	public final static ItemStack[] TEA_POWDER = { null, null, null, null, Defines.RAW_TEA, null, null, null, null };
	public final static ItemStack[] LEMON_POWDER = { null, null, null, null, Defines.LEMON, null, null, null, null };
	public static ItemStack[] HONEY_SWEET;
	static {
		if (ClayTech.is115) {
			HONEY_SWEET = new ItemStack[] { new ItemStack(Material.SWEET_BERRIES),
					new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SWEET_BERRIES),
					new ItemStack(Material.SUGAR), new ItemStack(Material.HONEY_BOTTLE), new ItemStack(Material.SUGAR),
					new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR), new ItemStack(Material.SUGAR) };
		}
	}
	public final static ItemStack[] ELEMENT_CARBON = { null, null, null, null, new ItemStack(Material.COAL, 8), null,
			null, null, null };
	public final static ItemStack[] ELEMENT_OXYGEN = { null, null, null, null, new ItemStack(Material.GRASS_BLOCK, 3),
			null, null, null, null };
	public final static ItemStack[] ELEMENT_SILICON = { null, null, null, null, new ItemStack(Material.SAND, 10), null,
			null, null, null };
	public final static ItemStack[] BLISTERING_CORE = { SlimefunItems.BLISTERING_INGOT_3,
			SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3,
			SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3,
			SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BLISTERING_INGOT_3 };
	public final static ItemStack[] ELEMENT_UNIT = { SlimefunItems.DAMASCUS_STEEL_INGOT,
			SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
			null, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT,
			SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT };
	static {
		newMotor = Defines.ELECTRIC_MOTOR_8.clone();
		newMotor.setAmount(1);
	}
	public final static ItemStack[] ELECTRIC_MOTOR_8 = { null, null, null, null, SlimefunItems.ELECTRIC_MOTOR, null,
			null, null, null };
	public final static ItemStack[] HIGHSPEED_RAILWAY = { null, null, null, null, new ItemStack(Material.POWERED_RAIL),
			null, null, newMotor, null };
}
