package club.claycoffee.ClayTech;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;

public class Recipes {
	static ItemStack newMotor;
	public final static ItemStack[] BLIND_CORE = { new ItemStack(Material.INK_SAC), new ItemStack(Material.END_CRYSTAL),
			new ItemStack(Material.INK_SAC), new ItemStack(Material.INK_SAC), ClayTechItems.MAGIC_CLAY,
			new ItemStack(Material.INK_SAC), new ItemStack(Material.INK_SAC), new ItemStack(Material.END_CRYSTAL),
			new ItemStack(Material.INK_SAC) };
	public final static ItemStack[] BLIND_SWORD = { ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE,
			ClayTechItems.BLIND_CORE, new ItemStack(Material.DIAMOND_SWORD), ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE,
			ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE };
	public final static ItemStack[] POISON_EYE = { new ItemStack(Material.SPIDER_EYE),
			new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE),
			ClayTechItems.MAGIC_CLAY, new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE),
			new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.SPIDER_EYE) };
	public final static ItemStack[] POISON_CORE = { new ItemStack(Material.COAL_BLOCK), ClayTechItems.POISON_EYE,
			new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.FERMENTED_SPIDER_EYE),
			new ItemStack(Material.FERMENTED_SPIDER_EYE), new ItemStack(Material.FERMENTED_SPIDER_EYE),
			new ItemStack(Material.COAL_BLOCK), ClayTechItems.POISON_EYE, new ItemStack(Material.COAL_BLOCK),
			ClayTechItems.POISON_EYE };
	public final static ItemStack[] CONFUSION_CORE = { new ItemStack(Material.PUFFERFISH),
			new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH),
			ClayTechItems.MAGIC_CLAY, new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH),
			new ItemStack(Material.PUFFERFISH), new ItemStack(Material.PUFFERFISH) };
	public final static ItemStack[] BLACK_ROCK_BLOCK = { new ItemStack(Material.OBSIDIAN),
			new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN),
			ClayTechItems.MAGIC_CLAY, new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN),
			new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSIDIAN) };
	public final static ItemStack[] SLOWNESS_CORE = { ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.BLACK_ROCK_BLOCK,
			ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.MAGIC_CLAY, ClayTechItems.BLACK_ROCK_BLOCK,
			ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.BLACK_ROCK_BLOCK, ClayTechItems.BLACK_ROCK_BLOCK };
	public final static ItemStack[] ADVANCED_CONFUSION_CORE = { ClayTechItems.CONFUSION_CORE, ClayTechItems.CONFUSION_CORE,
			ClayTechItems.CONFUSION_CORE, ClayTechItems.CONFUSION_CORE, ClayTechItems.MAGIC_CLAY, ClayTechItems.CONFUSION_CORE,
			ClayTechItems.CONFUSION_CORE, ClayTechItems.CONFUSION_CORE, ClayTechItems.CONFUSION_CORE };
	public final static ItemStack[] ADVANCED_POISON_CORE = { ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE,
			ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE, ClayTechItems.MAGIC_CLAY, ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE,
			ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE };
	public final static ItemStack[] ADVANCED_SLOWNESS_CORE = { ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE,
			ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE, ClayTechItems.MAGIC_CLAY, ClayTechItems.SLOWNESS_CORE,
			ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE };
	public final static ItemStack[] ADVANCED_BLIND_CORE = { ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE,
			ClayTechItems.BLIND_CORE, ClayTechItems.MAGIC_CLAY, ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE, ClayTechItems.BLIND_CORE,
			ClayTechItems.BLIND_CORE };
	public final static ItemStack[] FOUR_BOW = { ClayTechItems.ADVANCED_SLOWNESS_CORE, ClayTechItems.ADVANCED_POISON_CORE,
			ClayTechItems.ADVANCED_BLIND_CORE, ClayTechItems.ADVANCED_CONFUSION_CORE, new ItemStack(Material.BOW),
			ClayTechItems.ADVANCED_CONFUSION_CORE, ClayTechItems.ADVANCED_BLIND_CORE, ClayTechItems.ADVANCED_POISON_CORE,
			ClayTechItems.ADVANCED_SLOWNESS_CORE };
	public final static ItemStack[] POISON_SWORD = { ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE,
			ClayTechItems.POISON_CORE, new ItemStack(Material.DIAMOND_SWORD), ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE,
			ClayTechItems.POISON_CORE, ClayTechItems.POISON_CORE };
	public final static ItemStack[] ANTI_SLOWNESS_BOOTS = { ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE,
			ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE, new ItemStack(Material.IRON_BOOTS), ClayTechItems.SLOWNESS_CORE,
			ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE, ClayTechItems.SLOWNESS_CORE };
	public final static ItemStack[] TNT_EXPLOSION_CREATER = {new ItemStack(Material.TNT),ClayTechItems.MAGIC_CLAY,new ItemStack(Material.TNT),ClayTechItems.MAGIC_CLAY,new ItemStack(Material.DIAMOND_HOE),ClayTechItems.MAGIC_CLAY,new ItemStack(Material.TNT),new ItemStack(Material.FLINT_AND_STEEL),new ItemStack(Material.TNT)};
	
	// 食物
	public final static ItemStack[] CHICKEN_FOOT = { null, new ItemStack(Material.COAL), null, null,
			ClayTechItems.RAW_CHICKEN_FOOT, null, null, new ItemStack(Material.COAL), null };
	public final static ItemStack[] SPICY_CHICKEN_BURGER = { ClayTechItems.RAW_BREAD, ClayTechItems.RAW_VEGETABLE,
			ClayTechItems.RAW_BREAD, ClayTechItems.RAW_VEGETABLE, ClayTechItems.CHICKEN_FOOT, ClayTechItems.RAW_VEGETABLE, ClayTechItems.RAW_BREAD,
			ClayTechItems.MAGIC_CLAY, ClayTechItems.RAW_BREAD };
	public final static ItemStack[] BABA_BURGER = { new ItemStack(Material.COAL), ClayTechItems.RAW_BREAD,
			new ItemStack(Material.COAL), new ItemStack(Material.COAL), new ItemStack(Material.COAL),
			new ItemStack(Material.COAL), new ItemStack(Material.COAL), ClayTechItems.RAW_BREAD,
			new ItemStack(Material.COAL) };
	public final static ItemStack[] CHOCOLATE = { ClayTechItems.COCOA_BEAN, ClayTechItems.COCOA_BEAN, ClayTechItems.COCOA_BEAN,
			ClayTechItems.COCOA_BEAN, ClayTechItems.STARCH, ClayTechItems.COCOA_BEAN, ClayTechItems.COCOA_BEAN, ClayTechItems.COCOA_BEAN,
			ClayTechItems.COCOA_BEAN };
	public final static ItemStack[] SNAIL_FOOD = { ClayTechItems.FLOUR, ClayTechItems.FLOUR, ClayTechItems.FLOUR, ClayTechItems.FLOUR,
			ClayTechItems.SNAIL_HEALTHY, ClayTechItems.FLOUR, ClayTechItems.FLOUR, ClayTechItems.FLOUR, ClayTechItems.FLOUR };
	public final static ItemStack[] CLAY_COFFEE = { null, ClayTechItems.COCOA_BEAN, null, null, ClayTechItems.COCOA_BEAN, null,
			null, ClayTechItems.DRINK_BOTTLE, null };
	public final static ItemStack[] LEMON_POWDER_DRINK = { null, ClayTechItems.LEMON_POWDER, null, null, ClayTechItems.LEMON_POWDER,
			null, null, ClayTechItems.DRINK_BOTTLE, null };
	public final static ItemStack[] TEA_DRINK = { null, ClayTechItems.TEA_POWDER, null, null, ClayTechItems.TEA_POWDER, null, null,
			ClayTechItems.DRINK_BOTTLE, null };
	public final static ItemStack[] LEMON_TEA_DRINK = { null, ClayTechItems.TEA_POWDER, null, ClayTechItems.LEMON_POWDER,
			ClayTechItems.TEA_POWDER, ClayTechItems.LEMON_POWDER, null, ClayTechItems.DRINK_BOTTLE, null };
	public final static ItemStack[] TEA_POWDER = { null, null, null, null, ClayTechItems.RAW_TEA, null, null, null, null };
	public final static ItemStack[] LEMON_POWDER = { null, null, null, null, ClayTechItems.LEMON, null, null, null, null };
	public final static ItemStack[] COOKED_SWEET_POTATO = {null,new ItemStack(Material.COAL),null,null,ClayTechItems.SWEET_POTATO,null,null,new ItemStack(Material.COAL),null};
	
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
		newMotor = ClayTechItems.ELECTRIC_MOTOR_8.clone();
		newMotor.setAmount(1);
	}
	public final static ItemStack[] ELECTRIC_MOTOR_8 = { null, null, null, null, SlimefunItems.ELECTRIC_MOTOR, null,
			null, null, null };
	public final static ItemStack[] HIGHSPEED_RAILWAY = { null, null, null, null, new ItemStack(Material.POWERED_RAIL),
			null, null, newMotor, null };
}
