package cn.claycoffee.ClayTech;

import cn.claycoffee.ClayTech.utils.Lang;
import cn.claycoffee.ClayTech.utils.Utils;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.core.categories.LockedCategory;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ClayTechItems {
    public static final ItemStack[] NORECIPE = {null, null, null, null, null, null, null, null, null};
    // 注册
    public static final ItemStack USEFUL_DIRT = Utils.setLore(
            Utils.setDisplayName(new ItemStack(Material.DIRT), Lang.readItemText("USEFUL_DIRT")),
            Lang.readItemLore("USEFUL_DIRT"));
    public static final ItemStack ORE_DIAMOND = Utils.setDisplayName(new ItemStack(Material.DIAMOND),
            Lang.readItemText("ORE_DIAMOND"));
    public static final ItemStack MAGIC_CLAY = Utils.setLore(
            Utils.newItemD(Material.CLAY_BALL, Lang.readItemText("MAGIC_CLAY")), Lang.readItemLore("MAGIC_CLAY"));
    public static final ItemStack CLAY_STICK = Utils
            .setLore(Utils.newItemD(Material.STICK, Lang.readItemText("CLAY_STICK")), Lang.readItemLore("CLAY_STICK"));
    public static final ItemStack ARTIFICIAL_GOLD_NUGGET = Utils.newItemD(Material.GOLD_NUGGET,
            Lang.readItemText("ARTIFICIAL_GOLD_NUGGET"));
    public static final ItemStack ARTIFICIAL_GOLD_INGOT_O = Utils.newItemD(Material.COAL,
            Lang.readItemText("ARTIFICIAL_GOLD_INGOT_O"));
    public static final ItemStack ARTIFICIAL_GOLD_INGOT = Utils.newItemD(Material.GOLD_INGOT,
            Lang.readItemText("ARTIFICIAL_GOLD_INGOT"));
    public static final ItemStack ARTIFICIAL_GOLD_BLOCK = Utils.newItemD(Material.GOLD_BLOCK,
            Lang.readItemText("ARTIFICIAL_GOLD_BLOCK"));
    public static final ItemStack ARTIFICIAL_ENCHANTED_GOLDEN_APPLE = Utils.newItemD(Material.ENCHANTED_GOLDEN_APPLE,
            Lang.readItemText("ARTIFICIAL_ENCHANTED_GOLDEN_APPLE"));
    public static final ItemStack BLIND_CORE = Utils.newItemD(Material.COAL, Lang.readItemText("BLIND_CORE"));
    public static final ItemStack BLIND_SWORD = SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_16) ? Utils.setLore(Utils.newItemD(Material.NETHERITE_SWORD, Lang.readItemText("BLIND_SWORD")), Lang.readItemLore("BLIND_SWORD")) : Utils.setLore(Utils.newItemD(Material.DIAMOND_SWORD, Lang.readItemText("BLIND_SWORD")), Lang.readItemLore("BLIND_SWORD"));
    public static final ItemStack CONFUSION_CORE = Utils.newItemD(Material.INK_SAC,
            Lang.readItemText("CONFUSION_CORE"));
    public static final ItemStack BLACK_ROCK_BLOCK = Utils.newItemD(Material.OBSIDIAN,
            Lang.readItemText("BLACK_ROCK_BLOCK"));
    public static final ItemStack SLOWNESS_CORE = Utils.newItemD(Material.OBSIDIAN, Lang.readItemText("SLOWNESS_CORE"));
    public static final ItemStack POISON_EYE = Utils.newItemD(Material.SPIDER_EYE, Lang.readItemText("POISON_EYE"));
    public static final ItemStack POISON_CORE = Utils.newItemD(Material.SPIDER_EYE, Lang.readItemText("POISON_CORE"));
    public static final ItemStack ADVANCED_BLIND_CORE = Utils.newItemD(Material.COAL,
            Lang.readItemText("ADVANCED_BLIND_CORE"));
    public static final ItemStack ADVANCED_CONFUSION_CORE = Utils.newItemD(Material.INK_SAC,
            Lang.readItemText("ADVANCED_CONFUSION_CORE"));
    public static final ItemStack ADVANCED_SLOWNESS_CORE = Utils.newItemD(Material.OBSIDIAN,
            Lang.readItemText("ADVANCED_SLOWNESS_CORE"));
    public static final ItemStack ADVANCED_POISON_CORE = Utils.newItemD(Material.SPIDER_EYE,
            Lang.readItemText("ADVANCED_POISON_CORE"));
    public static final ItemStack FOUR_BOW = Utils.setLore(Utils.newItemD(Material.BOW, Lang.readItemText("FOUR_BOW")),
            Lang.readItemLore("FOUR_BOW"));
    public static final ItemStack POISON_SWORD = SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_16) ? Utils.setLore(
            Utils.newItemD(Material.NETHERITE_SWORD, Lang.readItemText("POISON_SWORD")),
            Lang.readItemLore("POISON_SWORD")) : Utils.setLore(
            Utils.newItemD(Material.DIAMOND_SWORD, Lang.readItemText("POISON_SWORD")),
            Lang.readItemLore("POISON_SWORD"));
    public static final ItemStack ANTI_SLOWNESS_BOOTS = Utils.setLore(
            Utils.newItemD(Material.IRON_BOOTS, Lang.readItemText("ANTI_SLOWNESS_BOOTS")),
            Lang.readItemLore("ANTI_SLOWNESS_BOOTS"));
    public static final ItemStack COCOA_BEAN = Utils.setLore(
            Utils.newItemD(Material.COCOA_BEANS, Lang.readItemText("COCOA_BEAN")), Lang.readItemLore("COCOA_BEAN"));
    public static final ItemStack PLASTIC = Utils.setLore(Utils.newItemD(
            SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_14) ? Material.WHITE_DYE
                    : Material.BONE_MEAL,
            Lang.readItemText("PLASTIC")), Lang.readItemLore("PLASTIC"));
    public static final ItemStack RAW_CHICKEN_FOOT = Utils.setLore(
            Utils.newItemD(Material.RABBIT_FOOT, Lang.readItemText("RAW_CHICKEN_FOOT")),
            Lang.readItemLore("RAW_CHICKEN_FOOT"));
    public static final ItemStack CHICKEN_FOOT = Utils.setLore(
            Utils.newItemD(Material.RABBIT_FOOT, Lang.readItemText("FRIED_CHICKEN_FOOT")),
            Lang.readItemLore("FRIED_CHICKEN_FOOT"));
    public static final ItemStack RAW_BREAD = Utils
            .setLore(Utils.newItemD(Material.BREAD, Lang.readItemText("RAW_BREAD")), Lang.readItemLore("RAW_BREAD"));
    public static final ItemStack DIRTY_TEA = Utils
            .setLore(Utils.newItemD(Material.KELP, Lang.readItemText("DIRTY_TEA")), Lang.readItemLore("DIRTY_TEA"));
    public static final ItemStack RAW_TEA = Utils.setLore(Utils.newItemD(Material.KELP, Lang.readItemText("TEA")),
            Lang.readItemLore("TEA"));
    public static final ItemStack TEA_POWDER = Utils.setLore(Utils.newItemD(
            SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_14) ? Material.BROWN_DYE
                    : Material.BONE_MEAL,
            Lang.readItemText("TEA_POWDER")), Lang.readItemLore("TEA_POWDER"));
    public static final ItemStack LEMON_POWDER = Utils
            .setLore(
                    Utils.newItemD(SlimefunPlugin.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_14)
                            ? Material.YELLOW_DYE
                            : Material.BONE_MEAL, Lang.readItemText("LEMON_POWDER")),
                    Lang.readItemLore("LEMON_POWDER"));
    public static final ItemStack FLOUR = Utils.setLore(Utils.newItemD(Material.SUGAR, Lang.readItemText("FLOUR")),
            Lang.readItemLore("FLOUR"));
    public static final ItemStack STARCH = Utils.setLore(Utils.newItemD(Material.SUGAR, Lang.readItemText("STARCH")),
            Lang.readItemLore("STARCH"));
    public static final ItemStack SNAIL_HEALTHY = Utils.setLore(
            Utils.newItemD(Material.NAUTILUS_SHELL, Lang.readItemText("SNAIL_HEALTHY")),
            Lang.readItemLore("SNAIL_HEALTHY"));
    public static final ItemStack SNAIL_BAD = Utils.setLore(
            Utils.newItemD(Material.NAUTILUS_SHELL, Lang.readItemText("SNAIL_BAD")), Lang.readItemLore("SNAIL_BAD"));
    public static final ItemStack HIGHSPEED_RAILWAY = Utils.setLore(
            Utils.newItemD(Material.POWERED_RAIL, Lang.readItemText("HIGHSPEED_RAILWAY")),
            Lang.readItemLore("HIGHSPEED_RAILWAY"));
    public static final ItemStack ELECTRIC_MOTOR_8 = Utils.newItemD(Material.FEATHER,
            Lang.readItemText("ELECTRIC_MOTOR_8"));
    public static final ItemStack ELEMENT_UNIT = Utils.setLore(
            Utils.newItemD(Material.FLOWER_POT, Lang.readItemText("ELEMENT_UNIT")), Lang.readItemLore("ELEMENT_UNIT"));
    public static final ItemStack ELEMENT_OXYGEN = Utils.setLore(
            Utils.newItemD(Material.FLOWER_POT, Lang.readItemText("ELEMENT_OXYGEN")),
            Lang.readItemLore("ELEMENT_OXYGEN"));
    public static final ItemStack ELEMENT_CARBON = Utils.setLore(
            Utils.newItemD(Material.FLOWER_POT, Lang.readItemText("ELEMENT_CARBON")),
            Lang.readItemLore("ELEMENT_CARBON"));
    public static final ItemStack ELEMENT_SILICON = Utils.setLore(
            Utils.newItemD(Material.FLOWER_POT, Lang.readItemText("ELEMENT_SILICON")),
            Lang.readItemLore("ELEMENT_SILICON"));
    public static final ItemStack BLISTERING_CORE = Utils.newItemD(Material.GOLD_INGOT,
            Lang.readItemText("BLISTERING_CORE"));
    public static final ItemStack TNT_EXPLOSION_CREATER = Utils.setLore(
            Utils.newItemD(Material.GOLDEN_HOE, Lang.readItemText("TNT_EXPLOSION_CREATER")),
            Lang.readItemLore("TNT_EXPLOSION_CREATER"));
    public static final ItemStack CLAY_SWEET_POTATO = Utils.setLore(
            Utils.newItemD(Material.BEETROOT, Lang.readItemText("SWEET_POTATO")), Lang.readItemLore("SWEET_POTATO"));
    public static final ItemStack COOKED_SWEET_POTATO = Utils.setLore(
            Utils.newItemD(Material.BEETROOT, Lang.readItemText("COOKED_SWEET_POTATO")),
            Lang.readItemLore("COOKED_SWEET_POTATO"));
    public static final ItemStack REINFORCED_ALLOY_PICKAXE = Utils.addEnchantH(
            Utils.addEnchantH(Utils.newItemD(Material.IRON_PICKAXE, Lang.readItemText("REINFORCED_ALLOY_PICKAXE")),
                    Enchantment.DIG_SPEED, 9),
            Enchantment.DURABILITY, 9);
    public static final ItemStack CLAY_FUSION_INGOT = Utils.newItemD(Material.IRON_INGOT,
            Lang.readItemText("CLAY_FUSION_INGOT"));
    public static final ItemStack CLAY_ALLOY_INGOT = Utils.newItemD(Material.IRON_INGOT,
            Lang.readItemText("CLAY_ALLOY_INGOT"));
    public static final ItemStack CLAY_ALLOY_PICKAXE = Utils.addEnchantH(Utils.addEnchantH(
            Utils.addEnchantH(Utils.newItemD(Material.IRON_PICKAXE, Lang.readItemText("CLAY_ALLOY_PICKAXE")),
                    Enchantment.DIG_SPEED, 10),
            Enchantment.DURABILITY, 10), Enchantment.MENDING, 1);
    public static final ItemStack CLAY_ALLOY_HELMET = Utils.addEnchantH(
            Utils.addEnchantH(
                    Utils.addEnchantH(Utils.newItemD(Material.IRON_HELMET, Lang.readItemText("CLAY_ALLOY_HELMET")),
                            Enchantment.PROTECTION_ENVIRONMENTAL, 10),
                    Enchantment.DURABILITY, 10),
            Enchantment.MENDING, 1);
    public static final ItemStack CLAY_ALLOY_CHESTPLATE = Utils
            .addEnchantH(
                    Utils.addEnchantH(Utils.addEnchantH(
                            Utils.newItemD(Material.IRON_CHESTPLATE, Lang.readItemText("CLAY_ALLOY_CHESTPLATE")),
                            Enchantment.PROTECTION_ENVIRONMENTAL, 10), Enchantment.DURABILITY, 10),
                    Enchantment.MENDING, 1);
    public static final ItemStack CLAY_ALLOY_LEGGINGS = Utils.addEnchantH(
            Utils.addEnchantH(
                    Utils.addEnchantH(Utils.newItemD(Material.IRON_LEGGINGS, Lang.readItemText("CLAY_ALLOY_LEGGINGS")),
                            Enchantment.PROTECTION_ENVIRONMENTAL, 10),
                    Enchantment.DURABILITY, 10),
            Enchantment.MENDING, 1);
    public static final ItemStack CLAY_ALLOY_BOOTS = Utils.addEnchantH(
            Utils.addEnchantH(
                    Utils.addEnchantH(Utils.newItemD(Material.IRON_BOOTS, Lang.readItemText("CLAY_ALLOY_BOOTS")),
                            Enchantment.PROTECTION_ENVIRONMENTAL, 10),
                    Enchantment.DURABILITY, 10),
            Enchantment.MENDING, 1);
    public static final ItemStack SILICON_INGOT = Utils.newItemD(Material.IRON_INGOT,
            Lang.readItemText("SILICON_INGOT"));
    public static final ItemStack ROCKET_LAUNCHER = Utils.newItemD(Material.IRON_BLOCK,
            Lang.readItemText("ROCKET_LAUNCHER"));
    public static final ItemStack MOTOR_CORE = Utils.newItemD(Material.NETHER_STAR, Lang.readItemText("MOTOR_CORE"));
    public static final ItemStack TEMPERATURE_RESISTANCE_OBSIDIAN = Utils.setLore(
            Utils.newItemD(Material.OBSIDIAN, Lang.readItemText("TEMPERATURE_RESISTANCE_OBSIDIAN")),
            Lang.readItemLore("TEMPERATURE_RESISTANCE_OBSIDIAN"));
    public static final ItemStack ROCKET_ENGINE_SHELL = Utils.newItemD(Material.IRON_INGOT,
            Lang.readItemText("ROCKET_ENGINE_SHELL"));
    public static final ItemStack ROCKET_ANTENNA = Utils.setLore(
            Utils.newItemD(Material.REDSTONE_TORCH, Lang.readItemText("ROCKET_ANTENNA")),
            Lang.readItemLore("ROCKET_ANTENNA"));
    public static final ItemStack ROCKET_CPU = Utils.setLore(
            Utils.newItemD(Material.NETHER_STAR, Lang.readItemText("ROCKET_CPU")), Lang.readItemLore("ROCKET_CPU"));
    public static final ItemStack ROCKET_CONTROL_CORE = Utils.setLore(
            Utils.newItemD(Material.FIREWORK_STAR, Lang.readItemText("ROCKET_CONTROL_CORE")),
            Lang.readItemLore("ROCKET_CONTROL_CORE"));
    public static final ItemStack ROCKET_GLASS = Utils.setLore(
            Utils.newItemD(Material.LIME_STAINED_GLASS_PANE, Lang.readItemText("ROCKET_GLASS")),
            Lang.readItemLore("ROCKET_GLASS"));
    public static final ItemStack ROCKET_STEEL_PLATE = Utils.setLore(
            Utils.newItemD(Material.PAPER, Lang.readItemText("ROCKET_STEEL_PLATE")),
            Lang.readItemLore("ROCKET_STEEL_PLATE"));
    public static final ItemStack SPACESUIT_HELMET = Utils.setLore(
            Utils.newItemD(Material.DIAMOND_HELMET, Lang.readItemText("SPACESUIT_HELMET")),
            Lang.readItemLore("SPACESUIT_HELMET"));
    public static final ItemStack SPACESUIT_CHESTPLATE = Utils.setLore(
            Utils.newItemD(Material.DIAMOND_CHESTPLATE, Lang.readItemText("SPACESUIT_CHESTPLATE")),
            Lang.readItemLore("SPACESUIT_CHESTPLATE"));
    public static final ItemStack SPACESUIT_LEGGINGS = Utils.setLore(
            Utils.newItemD(Material.DIAMOND_LEGGINGS, Lang.readItemText("SPACESUIT_LEGGINGS")),
            Lang.readItemLore("SPACESUIT_LEGGINGS"));
    public static final ItemStack SPACESUIT_BOOTS = Utils.setLore(
            Utils.newItemD(Material.DIAMOND_BOOTS, Lang.readItemText("SPACESUIT_BOOTS")),
            Lang.readItemLore("SPACESUIT_BOOTS"));
    public static final ItemStack KREEP_ROCK = Utils.setLore(
            Utils.newItemD(Material.GRANITE, Lang.readItemText("KREEP_ROCK")), Lang.readItemLore("KREEP_ROCK"));
    public static final ItemStack KREEP_INGOT = Utils.setLore(
            Utils.newItemD(Material.IRON_INGOT, Lang.readItemText("KREEP_INGOT")), Lang.readItemLore("KREEP_INGOT"));
    public static final ItemStack PLANET_BASE_SIGNER = Utils.setLore(
            Utils.newItemD(Material.OBSIDIAN, Lang.readItemText("PLANET_BASE_SIGNER")),
            Lang.readItemLore("PLANET_BASE_SIGNER"));
    public static final ItemStack TUBE = Utils.newItemD(Material.BONE, Lang.readItemText("TUBE"));
    public static final ItemStack OXYGEN_DISTRIBUTER = Utils.setLore(
            Utils.newItemD(Material.FIREWORK_STAR, Lang.readItemText("OXYGEN_DISTRIBUTER")),
            Lang.readItemLore("OXYGEN_DISTRIBUTER"));
    public static final ItemStack COPPER_ORE = Utils.setLore(
            Utils.newItemD(Material.GOLD_BLOCK, Lang.readItemText("COPPER_ORE")), Lang.readItemLore("COPPER_ORE"));
    public static final ItemStack CLAY_FUSION_ORE = Utils.setLore(
            Utils.newItemD(Material.IRON_BLOCK, Lang.readItemText("CLAY_FUSION_ORE")),
            Lang.readItemLore("CLAY_FUSION_ORE"));
    public static final ItemStack TUNA_FISH = Utils.setLore(
            Utils.newItemD(Material.TROPICAL_FISH, Lang.readItemText("TUNA_FISH")), Lang.readItemLore("TUNA_FISH"));
    public static final ItemStack GREEN_GRASS = Utils.setLore(
            Utils.newItemD(Material.GRASS, Lang.readItemText("GREEN_GRASS")), Lang.readItemLore("GREEN_GRASS"));
    public static final ItemStack INK_MODULE = Utils.setLore(
            Utils.newItemD(Material.INK_SAC, Lang.readItemText("INK_MODULE")), Lang.readItemLore("INK_MODULE"));
    public static final ItemStack COPYING_MODULE = Utils.setLore(
            Utils.newItemD(Material.DIAMOND, Lang.readItemText("COPYING_MODULE")), Lang.readItemLore("COPYING_MODULE"));
    public static final ItemStack CLAY_AIR_LOCK_PLATE = Utils.setLore(
            Utils.newItemD(Material.STONE_PRESSURE_PLATE, Lang.readItemText("CLAY_AIR_LOCK_PLATE")), Lang.readItemLore("CLAY_AIR_LOCK_PLATE"));
    public static final ItemStack CLAY_AIR_LOCK_BLOCK = Utils.setLore(
            Utils.newItemD(Material.getMaterial(ClayTech.getInstance().getConfig().getString("clay-air-lock-block-texture")), Lang.readItemText("CLAY_AIR_LOCK_BLOCK")), Lang.readItemLore("CLAY_AIR_LOCK_BLOCK"));

    // 头颅
    public static final ItemStack CLAY_FUEL = Utils.setDisplayName(
            SkullItem.fromHash("3ce2dad9baf7eaba7e80d4d0f9fac0aab01a76b12fb71c3d2af2a16fdd4c7383"),
            Lang.readItemText("CLAY_FUEL"));
    public static final ItemStack MIXED_ROCKET_FUEL = Utils.setLore(
            Utils.setDisplayName(SkullItem.fromHash("3ce2dad9baf7eaba7e80d4d0f9fac0aab01a76b12fb71c3d2af2a16fdd4c7383"),
                    Lang.readItemText("MIXED_ROCKET_FUEL")),
            Lang.readItemLore("MIXED_ROCKET_FUEL"));
    public static final ItemStack CLAYCOFFEE_HEAD = Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/58c55ba01ccc7e79b5495e5c4e00080ff6c92a832b2905fed724f2f68a3bb94c"),
            Lang.readItemText("AUTHOR_HEAD"));
    public static final ItemStack STALIN_HEAD = Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/96fb8390f61a2cc51c08201834369829d9ba301effc01b6a7cb10d830c6c5043"),
            Lang.readItemText("STALIN_HEAD"));
    public static final ItemStack MARX_HEAD = Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/57304952eff15a9fb81fa070fa549f85243c790f8fa61bf6e196a7516ce85a48"),
            Lang.readItemText("MARX_HEAD"));
    public static final ItemStack LANTERN_C = Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/7cc217a9b9e3ce3cd0484c7e8ce49d1cf741281bdda5a4d6cb821f378752718"),
            Lang.readItemText("LANTERN"));
    public static final ItemStack CLOCK_C = Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/ba10da526e5111cfb6e3ebd47693e162dd52d41a2182028daa7c2b19aa3143"),
            Lang.readItemText("BLACK_CLOCK"));
    public static final ItemStack CLAY_COFFEE = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/411511bdd55bcb82803c8039f1c155fd43062636e23d4d46c4d761c04d22c2"),
            Lang.readItemText("CLAY_COFFEE")), Lang.readItemLore("CLAY_COFFEE"));
    public static final ItemStack DRINK_BOTTLE = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/9179ce4849723434e84747ec85fbbfb1121456c8aeb2e9171fb8328921d45"),
            Lang.readItemText("DRINK_BOTTLE")), Lang.readItemLore("DRINK_BOTTLE"));
    public static final ItemStack DIRTY_DRINK_BOTTLE = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/9179ce4849723434e84747ec85fbbfb1121456c8aeb2e9171fb8328921d45"),
            Lang.readItemText("DIRTY_DRINK_BOTTLE")), Lang.readItemLore("DIRTY_DRINK_BOTTLE"));
    public static final ItemStack SPICY_CHICKEN_BURGER = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f"),
            Lang.readItemText("SPICY_CHICKEN_BURGER")), Lang.readItemLore("SPICY_CHICKEN_BURGER"));
    public static final ItemStack BABA_BURGER = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f"),
            Lang.readItemText("BABA_BURGER")), Lang.readItemLore("BABA_BURGER"));
    public static final ItemStack RAW_VEGETABLE = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/477dd842c975d8fb03b1add66db8377a18ba987052161f22591e6a4ede7f5"),
            Lang.readItemText("LETTUSE")), Lang.readItemLore("LETTUSE"));
    public static final ItemStack CLAY_LEMON = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/957fd56ca15978779324df519354b6639a8d9bc1192c7c3de925a329baef6c"),
            Lang.readItemText("LEMON")), Lang.readItemLore("LEMON"));
    public static final ItemStack LEMON_POWDER_DRINK = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/d8e94ddd769a5bea748376b4ec7383fd36d267894d7c3bee011e8e4f5fcd7"),
            Lang.readItemText("LEMON_POWDER_DRINK")), Lang.readItemLore("LEMON_POWDER_DRINK"));
    public static final ItemStack TEA_DRINK = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/d8e94ddd769a5bea748376b4ec7383fd36d267894d7c3bee011e8e4f5fcd7"),
            Lang.readItemText("TEA_DRINK")), Lang.readItemLore("TEA_DRINK"));
    public static final ItemStack LEMON_TEA_DRINK = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/d8e94ddd769a5bea748376b4ec7383fd36d267894d7c3bee011e8e4f5fcd7"),
            Lang.readItemText("LEMON_TEA_DRINK")), Lang.readItemLore("LEMON_TEA_DRINK"));
    public static final ItemStack CHOCOLATE = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/819f948d17718adace5dd6e050c586229653fef645d7113ab94d17b639cc466"),
            Lang.readItemText("CHOCOLATE")), Lang.readItemLore("CHOCOLATE"));
    public static final ItemStack SNAIL_FOOD = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/26834b5b25426de63538ec82ca8fbecfcbb3e682d8063643d2e67a7621bd"),
            Lang.readItemText("LUOSI_RICE_NOODLE")), Lang.readItemLore("LUOSI_RICE_NOODLE"));
    public static final ItemStack ROCKET = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/377bf2855806da82d902314ce3e706cd3d1c1f83ed986df19e179b17a0595"),
            Lang.readItemText("ROCKET")), Lang.readItemLore("ROCKET"));
    public static final ItemStack FUEL_TANK = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/5175bdf47aea1a4bf1d349be6b7fa4ab37f479672f4c43aca57511b427ab4"),
            Lang.readItemText("FUEL_TANK")), Lang.readItemLore("FUEL_TANK"));
    public static final ItemStack ROCKET_ENGINE = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/5175bdf47aea1a4bf1d349be6b7fa4ab37f479672f4c43aca57511b427ab4"),
            Lang.readItemText("ROCKET_ENGINE")), Lang.readItemLore("ROCKET_ENGINE"));
    ;
    public static final ItemStack ROCKET_FUEL_TANK = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/5175bdf47aea1a4bf1d349be6b7fa4ab37f479672f4c43aca57511b427ab4"),
            Lang.readItemText("ROCKET_FUEL_TANK")), Lang.readItemLore("ROCKET_FUEL_TANK"));
    public static final ItemStack OXYGEN_TANK = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/52baeb4a35da8a85d14bdccf7184f5545088f954da55144f235c2983fdb8e05b"),
            Lang.readItemText("OXYGEN_TANK")), Lang.readItemLore("OXYGEN_TANK"));
    public static final ItemStack SPACESUIT_OXYGEN_TANK = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/52baeb4a35da8a85d14bdccf7184f5545088f954da55144f235c2983fdb8e05b"),
            Lang.readItemText("SPACESUIT_OXYGEN_TANK")), Lang.readItemLore("SPACESUIT_OXYGEN_TANK"));
    // 机器
    public static final ItemStack CLAY_CRAFTING_TABLE = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/6d6c65b44c34b1acc2ccb346752397125f0d9ffa0ab3c50a99d1db3b74c63"),
            Lang.readItemText("CLAY_FUSION_MACHINE")),
            Utils.replaceList(
                    Utils.replaceList(Lang.readItemLore("CLAY_FUSION_MACHINE"), "%TIER%",
                            LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE).replaceAll("&", "§")),
                    "%POWER%", LoreBuilder.powerBuffer(128).replaceAll("&", "§")));
    public static final ItemStack CLAY_ELECTRIC_STONE_CRUSHER = Utils
            .setLore(
                    Utils.newItemD(Material.RED_STAINED_GLASS, Lang.readItemText("CLAY_ELECTRIC_STONE_CRUSHER")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_ELECTRIC_STONE_CRUSHER"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE).replaceAll("&",
                                                    "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(128).replaceAll("&", "§")));
    public static final ItemStack CLAY_FOOD_CAULDRON = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/dfd9b2f42d5f1c2a77b511fe41a4c6b5c192fb10b2ceadde05bd1af52a151"),
            Lang.readItemText("CLAY_ELETRIC_CAULDRON")),
            Utils.replaceList(
                    Utils.replaceList(Lang.readItemLore("CLAY_ELETRIC_CAULDRON"), "%TIER%",
                            LoreBuilder.machine(MachineTier.AVERAGE, MachineType.MACHINE).replaceAll("&", "§")),
                    "%POWER%", LoreBuilder.powerBuffer(512).replaceAll("&", "§")));
    public static final ItemStack CLAY_FOOD_CHALKING_MACHINE = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/98636123b1a3755abd8aef6d85b2a85bf10f486edefdd1a3cef7679d825"),
            Lang.readItemText("CLAY_FOOD_CHALKING_MACHINE")),
            Utils.replaceList(
                    Utils.replaceList(Lang.readItemLore("CLAY_FOOD_CHALKING_MACHINE"), "%TIER%",
                            LoreBuilder.machine(MachineTier.AVERAGE, MachineType.MACHINE).replaceAll("&", "§")),
                    "%POWER%", LoreBuilder.powerBuffer(512).replaceAll("&", "§")));
    public static final ItemStack CLAY_ELEMENT_EXTRACTER = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/c74170c66bf3140f234b322add724c5df6949a9209f807ebf86d4f9c8c1e178"),
            Lang.readItemText("CLAY_ELEMENT_EXTRACTER")),
            Utils.replaceList(
                    Utils.replaceList(Lang.readItemLore("CLAY_ELEMENT_EXTRACTER"), "%TIER%",
                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE).replaceAll("&", "§")),
                    "%POWER%", LoreBuilder.powerBuffer(1024).replaceAll("&", "§")));
    public static final ItemStack CLAY_EXPERIMENT_TABLE_NORMAL = Utils.setLore(Utils.setDisplayName(SkullItem.fromURL(
            "http://textures.minecraft.net/texture/52baeb4a35da8a85d14bdccf7184f5545088f954da55144f235c2983fdb8e05b"),
            Lang.readItemText("CLAY_EXPERIMENT_TABLE_NORMAL")),
            Utils.replaceList(
                    Utils.replaceList(Lang.readItemLore("CLAY_EXPERIMENT_TABLE_NORMAL"), "%TIER%",
                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE).replaceAll("&", "§")),
                    "%POWER%", LoreBuilder.powerBuffer(1024).replaceAll("&", "§")));
    public static final ItemStack CLAY_ROCKET_ASSEMBLING_MACHINE = Utils
            .setLore(
                    Utils.newItemD(Material.GOLD_BLOCK, Lang.readItemText("CLAY_ROCKET_ASSEMBLING_MACHINE")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_ROCKET_ASSEMBLING_MACHINE"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
                                                    .replaceAll("&", "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(512).replaceAll("&", "§")));
    public static final ItemStack CLAY_ROCKET_FUEL_GENERATOR = Utils
            .setLore(
                    Utils.newItemD(Material.EMERALD_BLOCK, Lang.readItemText("CLAY_ROCKET_FUEL_GENERATOR")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_ROCKET_FUEL_GENERATOR"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
                                                    .replaceAll("&", "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(256).replaceAll("&", "§")));
    public static final ItemStack CLAY_ROCKET_FUEL_INJECTOR = Utils
            .setLore(
                    Utils.newItemD(Material.DIAMOND_BLOCK, Lang.readItemText("CLAY_ROCKET_FUEL_INJECTOR")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_ROCKET_FUEL_INJECTOR"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
                                                    .replaceAll("&", "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(256).replaceAll("&", "§")));
    public static final ItemStack CLAY_SPACESUIT_OXYGEN_INJECTOR = Utils
            .setLore(
                    Utils.newItemD(Material.LAPIS_BLOCK, Lang.readItemText("CLAY_SPACESUIT_OXYGEN_INJECTOR")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_SPACESUIT_OXYGEN_INJECTOR"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
                                                    .replaceAll("&", "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(256).replaceAll("&", "§")));
    public static final ItemStack CLAY_COBBLESTONE_GENERATOR = Utils
            .setLore(
                    Utils.newItemD(Material.FURNACE, Lang.readItemText("CLAY_COBBLESTONE_GENERATOR")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_COBBLESTONE_GENERATOR"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
                                                    .replaceAll("&", "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(256).replaceAll("&", "§")));
    public static final ItemStack CLAY_ELECTRIC_WATER_PUMP = Utils
            .setLore(
                    Utils.newItemD(Material.DISPENSER, Lang.readItemText("CLAY_ELECTRIC_WATER_PUMP")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_ELECTRIC_WATER_PUMP"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
                                                    .replaceAll("&", "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(256).replaceAll("&", "§")));
    public static final ItemStack CLAY_ELECTRIC_COPIER = Utils
            .setLore(
                    Utils.newItemD(Material.IRON_BLOCK, Lang.readItemText("CLAY_ELECTRIC_COPIER")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_ELECTRIC_COPIER"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.ADVANCED, MachineType.MACHINE)
                                                    .replaceAll("&", "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(256).replaceAll("&", "§")));
    public static final ItemStack CLAY_WITHER_KILLER = Utils
            .setLore(
                    Utils.newItemD(Material.OBSIDIAN, Lang.readItemText("CLAY_WITHER_KILLER")), Utils
                            .replaceList(
                                    Utils.replaceList(Lang.readItemLore("CLAY_WITHER_KILLER"), "%TIER%",
                                            LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE)
                                                    .replaceAll("&", "§")),
                                    "%POWER%", LoreBuilder.powerBuffer(5120).replaceAll("&", "§")));

    private static final NamespacedKey N_BASIC = new NamespacedKey(ClayTech.plugin, "claycategory");
    // 分类
    public static final LockedCategory C_BASICS = new LockedCategory(N_BASIC,
            Utils.newItemD(Material.CLAY, Lang.readCategoriesText("Basic")),
            new NamespacedKey(SlimefunPlugin.instance(), "basic_machines"));
    public static final LockedCategory C_WEAPONS = new LockedCategory(
            new NamespacedKey(ClayTech.plugin, "claycategory1"),
            Utils.newItemD(Material.DIAMOND_SWORD, Lang.readCategoriesText("Weapons")), N_BASIC);
    public static final LockedCategory C_FOOD = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory"),
            Utils.newItemD(Material.ENCHANTED_GOLDEN_APPLE, Lang.readCategoriesText("Food")), N_BASIC);
    public static final LockedCategory C_FOODMATERIALS = new LockedCategory(
            new NamespacedKey(ClayTech.plugin, "claycategory2"),
            Utils.newItemD(Material.COCOA_BEANS, Lang.readCategoriesText("FoodMakings")), N_BASIC);
    public static final LockedCategory C_DRINK = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory"),
            Utils.newItemD(Material.WATER_BUCKET, Lang.readCategoriesText("Drink")), N_BASIC);
    public static final LockedCategory C_MATERIALS = new LockedCategory(
            new NamespacedKey(ClayTech.plugin, "claycategory3"),
            Utils.newItemD(Material.COAL, Lang.readCategoriesText("Makings")), N_BASIC);
    public static final LockedCategory C_ARMORS = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory"),
            Utils.newItemD(Material.DIAMOND_CHESTPLATE, Lang.readCategoriesText("Armors")), N_BASIC);
    public static final LockedCategory C_DECORATES = new LockedCategory(
            new NamespacedKey(ClayTech.plugin, "claycategory4"),
            Utils.newItemD(Material.PLAYER_HEAD, Lang.readCategoriesText("Decorates")), N_BASIC);
    public static final LockedCategory C_ELEMENTS = new LockedCategory(
            new NamespacedKey(ClayTech.plugin, "claycategory5"),
            Utils.newItemD(Material.FLOWER_POT, Lang.readCategoriesText("Elements")), N_BASIC);
    public static final LockedCategory C_MACHINES = new LockedCategory(
            new NamespacedKey(ClayTech.plugin, "claycategory6"),
            Utils.newItemD(Material.FURNACE, Lang.readCategoriesText("Machines")), N_BASIC);
    public static final LockedCategory C_OTHER = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory7"),
            Utils.newItemD(Material.POWERED_RAIL, Lang.readCategoriesText("Other")), N_BASIC);
    public static final LockedCategory C_TOOLS = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory8"),
            Utils.newItemD(Material.DIAMOND_PICKAXE, Lang.readCategoriesText("Tools")), N_BASIC);
    public static final LockedCategory C_ORESTHINGS = new LockedCategory(
            new NamespacedKey(ClayTech.plugin, "claycategory9"),
            Utils.newItemD(Material.BRICK, Lang.readCategoriesText("OreThings")), N_BASIC);
    public static ItemStack HONEY_SWEET = SlimefunPlugin.getMinecraftVersion()
            .isAtLeast(MinecraftVersion.MINECRAFT_1_15)
            ? Utils.setLore(Utils.newItemD(Material.HONEYCOMB, Lang.readItemText("HONEY_SWEET")),
            Lang.readItemLore("HONEY_SWEET"))
            : Utils.setLore(Utils.newItemD(Material.SUGAR, Lang.readItemText("HONEY_SWEET")),
            Lang.readItemLore("HONEY_SWEET"));
}
