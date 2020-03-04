package club.claycoffee.ClayTech;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import club.claycoffee.ClayTech.utils.Utils;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;

public class Defines {
	public static final ItemStack[] NORECIPE = { null, null, null, null, null, null, null, null, null };

	// 分类
	public static final LockedCategory C_BASICS = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory"),
			Utils.newItemD(Material.CLAY, "§b粘土科技 - 基础"), Categories.MACHINES_1);
	public static final LockedCategory C_WEAPONS = new LockedCategory(
			new NamespacedKey(ClayTech.plugin, "claycategory"), Utils.newItemD(Material.DIAMOND_SWORD, "§b粘土科技 - 武器"),
			C_BASICS);
	public static final LockedCategory C_FOOD = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory"),
			Utils.newItemD(Material.ENCHANTED_GOLDEN_APPLE, "§b粘土科技 - 食物"), C_BASICS);
	public static final LockedCategory C_FOODMATERIALS = new LockedCategory(
			new NamespacedKey(ClayTech.plugin, "claycategory"), Utils.newItemD(Material.COCOA_BEANS, "§b粘土科技 - 食物饮料材料"),
			C_BASICS);
	public static final LockedCategory C_DRINK = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory"),
			Utils.newItemD(Material.WATER_BUCKET, "§b粘土科技 - 饮料"), C_BASICS);
	public static final LockedCategory C_MATERIALS = new LockedCategory(
			new NamespacedKey(ClayTech.plugin, "claycategory"), Utils.newItemD(Material.COAL, "§b粘土科技 - 材料"), C_BASICS);
	public static final LockedCategory C_ARMORS = new LockedCategory(new NamespacedKey(ClayTech.plugin, "claycategory"),
			Utils.newItemD(Material.DIAMOND_CHESTPLATE, "§b粘土科技 - 装备"), C_BASICS);
	public static final LockedCategory C_DECORATES = new LockedCategory(
			new NamespacedKey(ClayTech.plugin, "claycategory"), Utils.newItemD(Material.PLAYER_HEAD, "§b粘土科技 - 装饰品"),
			C_BASICS);

	// 注册
	public static final ItemStack MAGIC_CLAY = Utils.addLore(Utils.newItemD(Material.CLAY_BALL, "§6魔法の粘土"),
			"§b粘土科技的基础物品,许多粘土科技的合成都需要这个");
	public static final ItemStack CLAY_STICK = Utils.addLore(Utils.newItemD(Material.STICK, "§6克莱の木棍"),
			"§b服主克莱咖啡收藏的木棍,可以做某些工具");
	public static final ItemStack ARTIFICIAL_GOLD_NUGGET = Utils.newItemD(Material.GOLD_NUGGET, "§6人造金粒");
	public static final ItemStack ARTIFICIAL_GOLD_INGOT_O = Utils.newItemD(Material.COAL, "§6人造金锭原矿");
	public static final ItemStack ARTIFICIAL_GOLD_INGOT = Utils.newItemD(Material.GOLD_INGOT, "§6人造金锭");
	public static final ItemStack ARTIFICIAL_GOLD_BLOCK = Utils.newItemD(Material.GOLD_BLOCK, "§6人造金块");
	public static final ItemStack ARTIFICIAL_ENCHANTED_GOLDEN_APPLE = Utils.newItemD(Material.ENCHANTED_GOLDEN_APPLE,
			"§5§l人造附魔金苹果");
	public static final ItemStack BLIND_CORE = Utils.newItemD(Material.COAL, "§7失明核心");
	public static final ItemStack BLIND_SWORD = Utils.addLore(
			Utils.addLore(Utils.newItemD(Material.DIAMOND_SWORD, "§7失明剑"), "§6在攻击后给予被攻击玩家5秒钟的失明V效果"), "§7失明 V");
	public static final ItemStack CONFUSION_CORE = Utils.newItemD(Material.WITHER_SKELETON_SKULL, "§7反胃核心");
	public static final ItemStack BLACK_ROCK_BLOCK = Utils.newItemD(Material.OBSIDIAN, "§7黑岩块");
	public static final ItemStack SLOWNESS_CORE = Utils.newItemD(Material.OBSIDIAN, "§7缓慢核心");
	public static final ItemStack POISON_EYE = Utils.newItemD(Material.SPIDER_EYE, "§7剧毒之眼");
	public static final ItemStack POISON_CORE = Utils.newItemD(Material.SPIDER_EYE, "§7剧毒核心");
	public static final ItemStack ADVANCED_BLIND_CORE = Utils.newItemD(Material.COAL, "§7高级失明核心");
	public static final ItemStack ADVANCED_CONFUSION_CORE = Utils.newItemD(Material.WITHER_SKELETON_SKULL, "§7高级反胃核心");
	public static final ItemStack ADVANCED_SLOWNESS_CORE = Utils.newItemD(Material.OBSIDIAN, "§7高级缓慢核心");
	public static final ItemStack ADVANCED_POISON_CORE = Utils.newItemD(Material.SPIDER_EYE, "§7高级剧毒核心");
	public static final ItemStack FOUR_BOW = Utils.setLoreList(Utils.newItemD(Material.BOW, "§7灵气四合弓"),
			new String[] { "§6在攻击后给予被攻击玩家5秒钟的失明V、缓慢V、反胃V和2秒钟的中毒III效果", "§7失明 V", "§7缓慢 V", "§7反胃 V", "§7中毒 III" });
	public static final ItemStack POISON_SWORD = Utils.setLoreList(Utils.newItemD(Material.DIAMOND_SWORD, "§7梓毒剑"),
			new String[] { "§6在攻击后给予被攻击玩家2秒钟的中毒III效果", "§7中毒 III" });
	public static final ItemStack ANTI_SLOWNESS_BOOTS = Utils.setLoreList(Utils.newItemD(Material.IRON_BOOTS, "§7反缓慢靴"),
			new String[] { "§6别人打你会获得缓慢3效果2秒", "§7反弹缓慢 III" });
	public static final ItemStack COCOA_BEAN = Utils.setLoreList(Utils.newItemD(Material.COCOA_BEANS, "§e可可豆"),
			new String[] { "§6Yay!美味的可可豆!", "§7可用于制作咖啡和巧克力等" });
	public static final ItemStack PLASTIC = Utils.setLoreList(Utils.newItemD(Material.WHITE_DYE, "§e塑料"),
			new String[] { "§6爱护环境,请勿随意丢弃", "§7可用于制作饮料瓶" });
	public static final ItemStack RAW_CHICKEN_FOOT = Utils.setLoreList(Utils.newItemD(Material.RABBIT_FOOT, "§e鸡腿"),
			new String[] { "§6鸡腿", "§7不可食用，请放冶炼炉里烤后再食用。" });
	public static final ItemStack CHICKEN_FOOT = Utils.setLoreList(Utils.newItemD(Material.RABBIT_FOOT, "§e炸鸡腿"),
			new String[] { "§6炸鸡腿", "§7食用后增加8点饱食度." });
	public static final ItemStack RAW_BREAD = Utils.setLoreList(Utils.newItemD(Material.BREAD, "§e面包胚"),
			new String[] { "§6面包胚", "§7食用后增加6点饱食度." });
	public static final ItemStack DIRTY_TEA = Utils.setLoreList(Utils.newItemD(Material.KELP, "§e脏茶叶"),
			new String[] { "§6脏茶叶", "§7右键来清洗,变成茶叶!", "§7打草有几率获得" });
	public static final ItemStack RAW_TEA = Utils.setLoreList(Utils.newItemD(Material.KELP, "§e茶叶"),
			new String[] { "§6茶叶", "§7可以制作茶叶粉", "§7脏茶叶清洗获得" });
	public static final ItemStack TEA_POWDER = Utils.setLoreList(Utils.newItemD(Material.BROWN_DYE, "§e茶叶粉"),
			new String[] { "§6茶叶粉", "§7可以制作茶" });
	public static final ItemStack LEMON_POWDER = Utils.setLoreList(Utils.newItemD(Material.YELLOW_DYE, "§e柠檬粉"),
			new String[] { "§6柠檬粉", "§7可以制作柠檬汁" });
	public static final ItemStack FLOUR = Utils.setLoreList(Utils.newItemD(Material.SUGAR, "§e面粉"),
			new String[] { "§6面粉", "§7可以用来做好多东西", "§7收获小麦有几率获得" });
	public static final ItemStack STARCH = Utils.setLoreList(Utils.newItemD(Material.SUGAR, "§e淀粉"),
			new String[] { "§6淀粉", "§7可以用来做好多东西", "§7收获马铃薯有几率获得" });
	public static final ItemStack SNAIL_HEALTHY = Utils.setLoreList(Utils.newItemD(Material.NAUTILUS_SHELL, "§e螺蛳"),
			new String[] { "§6螺蛳", "§7可以用来做螺蛳粉", "§7钓鱼有几率获得" });
	public static final ItemStack SNAIL_BAD = Utils.setLoreList(Utils.newItemD(Material.NAUTILUS_SHELL, "§e福寿螺"),
			new String[] { "§6福寿螺", "§7吃一个试试?", "§7钓鱼有几率获得" });
	public static final ItemStack HONEY_SWEET = Utils.setLoreList(Utils.newItemD(Material.HONEYCOMB, "§e蜂蜜糖"),
			new String[] { "§6蜂蜜糖", "§7介个东西太甜了⑧..", "§7食用后增加8点饱食度." });

	// 头颅
	public static final ItemStack CLAYCOFFEE_HEAD = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/58c55ba01ccc7e79b5495e5c4e00080ff6c92a832b2905fed724f2f68a3bb94c"),
			"§6服主的头"), new String[] { "§b服主的头,快拿去扔吧!" });
	public static final ItemStack STALIN_HEAD = Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/96fb8390f61a2cc51c08201834369829d9ba301effc01b6a7cb10d830c6c5043"),
			"§b斯大林的头");
	public static final ItemStack MARX_HEAD = Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/57304952eff15a9fb81fa070fa549f85243c790f8fa61bf6e196a7516ce85a48"),
			"§b马克思的头");
	public static final ItemStack LANTERN_C = Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/7cc217a9b9e3ce3cd0484c7e8ce49d1cf741281bdda5a4d6cb821f378752718"),
			"§b灯笼");
	public static final ItemStack CLOCK_C = Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/ba10da526e5111cfb6e3ebd47693e162dd52d41a2182028daa7c2b19aa3143"),
			"§b黑色时钟");
	public static final ItemStack CLAY_COFFEE = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/411511bdd55bcb82803c8039f1c155fd43062636e23d4d46c4d761c04d22c2"),
			"§e粘土咖啡"), new String[] { "§6粘土咖啡!", "§7食用后可增加5点饱食度和3分钟夜视效果", "§e不可放置" });;
	public static final ItemStack DRINK_BOTTLE = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/9179ce4849723434e84747ec85fbbfb1121456c8aeb2e9171fb8328921d45"),
			"§e饮料瓶"), new String[] { "§6饮料瓶", "§7制作饮料的必备物品", "§7没有瓶子怎么装饮料呢?拿嘴装吗?", "§e不可放置" });
	public static final ItemStack DIRTY_DRINK_BOTTLE = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/9179ce4849723434e84747ec85fbbfb1121456c8aeb2e9171fb8328921d45"),
			"§e脏饮料瓶"), new String[] { "§6脏饮料瓶", "§7这个饮料瓶需要用洗矿机洗洗..", "§7哪个小男孩会喝脏饮料瓶呢?右键我来清洗吧!(需要一个水桶)", "§e不可放置" });
	public static final ItemStack SPICY_CHICKEN_BURGER = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f"),
			"§e香辣鸡腿堡"), new String[] { "§6香辣鸡腿堡", "§7食用后增加15饱食度和20秒力量II效果", "§7宁难道在抄袭CFC?", "§e不可放置" });
	public static final ItemStack BABA_BURGER = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f"),
			"§e老八秘制小憨包"), new String[] { "§6老八秘制小憨包", "§7吃个试试?(造成的任何后果服主不负责)", "§e不可放置" });
	public static final ItemStack RAW_VEGETABLE = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/477dd842c975d8fb03b1add66db8377a18ba987052161f22591e6a4ede7f5"),
			"§e生菜"), new String[] { "§6生菜", "§7食用后增加1点饱食度", "§e不可放置" });
	public static final ItemStack LEMON = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/957fd56ca15978779324df519354b6639a8d9bc1192c7c3de925a329baef6c"),
			"§e柠檬"), new String[] { "§6柠檬", "§7食用后增加1点饱食度和10秒反胃IV效果", "§7我柠檬了", "§7破坏树叶有几率获得", "§e不可放置" });

	public static final ItemStack LEMON_POWDER_DRINK = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/d8e94ddd769a5bea748376b4ec7383fd36d267894d7c3bee011e8e4f5fcd7"),
			"§e柠檬汁"), new String[] { "§6柠檬汁", "§7食用后增加6点饱食度和10秒反胃IV效果", "§7我柠檬了", "§e不可放置" });
	public static final ItemStack TEA_DRINK = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/d8e94ddd769a5bea748376b4ec7383fd36d267894d7c3bee011e8e4f5fcd7"),
			"§e茶"), new String[] { "§6茶", "§7食用后增加6点饱食度和30秒抗性提升III效果", "§e不可放置" });
	public static final ItemStack LEMON_TEA_DRINK = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/d8e94ddd769a5bea748376b4ec7383fd36d267894d7c3bee011e8e4f5fcd7"),
			"§e柠檬茶"), new String[] { "§6柠檬茶", "§7食用后增加12点饱食度和60秒抗性提升III效果", "§7这东西冷一点可能就是冰红茶了..", "§e不可放置" });
	public static final ItemStack CHOCOLATE = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/819f948d17718adace5dd6e050c586229653fef645d7113ab94d17b639cc466"),
			"§e巧克力"), new String[] { "§6巧克力", "§7食用后增加15点饱食度和30秒抗性提升III效果", "§7饿货!来条憨力架!横扫饥饿,做回憨逼~", "§e不可放置" });
	public static final ItemStack SNAIL_FOOD = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/26834b5b25426de63538ec82ca8fbecfcbb3e682d8063643d2e67a7621bd"),
			"§e螺蛳粉"), new String[] { "§6螺蛳粉", "§7食用后增加12点饱食度和30秒抗性提升III效果", "§7好臭啊..不过挺好吃的.", "§e不可放置" });

	// 机器
	public static final ItemStack CLAY_CRAFTING_TABLE = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/6d6c65b44c34b1acc2ccb346752397125f0d9ffa0ab3c50a99d1db3b74c63"),
			"§e粘土融合器"),
			new String[] { "", "§f能够制作某些粘土科技物品", "",
					LoreBuilder.machine(MachineTier.BASIC, MachineType.MACHINE).replaceAll("&", "§"),
					LoreBuilder.powerBuffer(128).replaceAll("&", "§"), "§8\u21E8 §e\u26A1 §716 J/s" });
	public static final ItemStack CLAY_FOOD_CAULDRON = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/dfd9b2f42d5f1c2a77b511fe41a4c6b5c192fb10b2ceadde05bd1af52a151"),
			"§e电锅"),
			new String[] { "", "§f能够制作粘土科技中的食物", "",
					LoreBuilder.machine(MachineTier.AVERAGE, MachineType.MACHINE).replaceAll("&", "§"),
					LoreBuilder.powerBuffer(512).replaceAll("&", "§"), "§8\u21E8 §e\u26A1 §732 J/s" });
	public static final ItemStack CLAY_FOOD_CHALKING_MACHINE = Utils.setLoreList(Utils.setDisplayName(SkullItem.fromURL(
			"http://textures.minecraft.net/texture/98636123b1a3755abd8aef6d85b2a85bf10f486edefdd1a3cef7679d825"),
			"§e食物打粉机"),
			new String[] { "", "§f能够制作粘土科技中的某些粉末", "",
					LoreBuilder.machine(MachineTier.AVERAGE, MachineType.MACHINE).replaceAll("&", "§"),
					LoreBuilder.powerBuffer(512).replaceAll("&", "§"), "§8\u21E8 §e\u26A1 §732 J/s" });
}
