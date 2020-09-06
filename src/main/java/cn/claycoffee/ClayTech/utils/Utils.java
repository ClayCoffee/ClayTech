package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTech;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.io.InputStreamReader;
import java.util.*;

public final class Utils {
    // ��
    public static ItemStack setDisplayName(ItemStack is, String Name) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(Name);
        is.setItemMeta(im);
        return (is);
    }

    public static Object getBlockMetadata(Block b, String s) {
        for (MetadataValue mv : b.getMetadata(s)) {
            if (mv.getOwningPlugin() == ClayTech.getInstance()) {
                return mv.value();
            }
        }
        return null;
    }

    // ��
    public static ItemStack setLore(ItemStack is, int Line, String content) {
        ItemMeta im = is.getItemMeta();
        List<String> Lore = im.getLore();
        Lore.set(Line, content);
        im.setLore(Lore);
        is.setItemMeta(im);
        return (is);
    }

    public static String ArrayToString(String[] Array, String split, String LastSplit) {
        String returnMessage = "";
        int i = 0;
        for (String each : Array) {
            i = i + 1;
            if (i == Array.length) {
                returnMessage += each + LastSplit;
            } else {
                returnMessage += each;
                continue;
            }
        }
        return (returnMessage);
    }

    public static String readPlayerMetadataString(Player p, String key) {
        for (MetadataValue mv : p.getMetadata(key)) {
            if (mv.getOwningPlugin().equals(ClayTech.getInstance())) {
                return mv.asString();
            }
        }
        return null;

    }

    public static boolean readPlayerMetadataBoolean(Player p, String key) {
        for (MetadataValue mv : p.getMetadata(key)) {
            if (mv.getOwningPlugin().equals(ClayTech.getInstance())) {
                return mv.asBoolean();
            }
        }
        return false;

    }

    public static ItemStack setLore(ItemStack is, List<String> content) {
        ItemMeta im = is.getItemMeta();
        List<String> Lore = im.getLore();
        Lore = content;
        im.setLore(Lore);
        is.setItemMeta(im);
        return (is);
    }

    public static Map<String, String> sectionToMap(ConfigurationSection sec) {
        Map<String, String> ret = new HashMap<String, String>();
        for (String eachKey : sec.getKeys(false)) {
            String eachValue = sec.getString(eachKey);
            ret.put(eachKey, eachValue);
        }
        return ret;
    }

    public static List<String> sectionKeyToList(ConfigurationSection sec) {
        List<String> ret = new ArrayList<String>();
        if (sec == null) {
            return Arrays.asList(new String[]{"null"});
        }
        if (sec.getKeys(false) == null) {
            return Arrays.asList(new String[]{"null"});
        }
        for (String eachKey : sec.getKeys(false)) {
            ret.add(eachKey);
        }
        return ret;
    }

    public static List<String> replaceList(List<String> array, String bereplace, String replace) {
        int n = 0;
        for (String i : array) {
            array.set(n, i.replaceAll(bereplace, replace));
            n++;
        }
        return array;
    }

    // ��
    public static ItemStack addLore(ItemStack is, String content) {
        ItemMeta im = is.getItemMeta();
        if (im.getLore() == null) {
            List<String> Lore = new ArrayList<String>();
            Lore.add(content);
            im.setLore(Lore);
            is.setItemMeta(im);
        } else {
            List<String> Lore = im.getLore();
            Lore.add(content);
            im.setLore(Lore);
            is.setItemMeta(im);
        }
        return (is);
    }

    public static ItemStack setLoreList(ItemStack is, String[] LoreList) {
        ItemMeta im = is.getItemMeta();
        List<String> Lore = im.getLore();
        Lore = Arrays.asList(LoreList);
        im.setLore(Lore);
        is.setItemMeta(im);
        return (is);
    }

    // ��
    public static ItemStack addEnchant(ItemStack is, Enchantment ench, int level) {
        is.addEnchantment(ench, level);
        return (is);
    }

    public static ItemStack addEnchantH(ItemStack is, Enchantment ench, int level) {
        is.addUnsafeEnchantment(ench, level);
        return (is);
    }

    // ��
    public static ItemStack removeEnchant(ItemStack is, Enchantment ench) {

        ItemMeta im = is.getItemMeta();
        if (im.hasEnchant(ench)) {
            im.removeEnchant(ench);
        }
        is.setItemMeta(im);
        return (is);
    }

    // ��
    public static String getDisplayName(ItemStack is) {
        if (is.getItemMeta().hasDisplayName() == true) {
            return (is.getItemMeta().getDisplayName());
        } else {
            return ("Ĭ��");
        }
    }

    // ��
    public static List<String> getLoreList(ItemStack is) {
        return (is.getItemMeta().getLore());
    }

    // ��
    public static String[] getLore(ItemStack is) {
        List<String> Lore;
        try {
            Lore = is.getItemMeta().getLore();
            return (Lore.toArray(new String[Lore.size()]));
        } catch (NullPointerException e) {
            return (new String[]{"null"});
        }
    }

    // ��
    public static Map<Enchantment, Integer> getEnchantments(ItemStack is) {
        return (is.getItemMeta().getEnchants());
    }

    // ��
    public static List<Enchantment> getEnchantmentsNameList(ItemStack is) {
        Map<Enchantment, Integer> m = getEnchantments(is);
        List<Enchantment> list = new ArrayList<Enchantment>();
        for (Enchantment ench : m.keySet()) {
            list.add(ench);
        }
        return (list);
    }

    // ��
    public static Enchantment[] getEnchantmentsName(ItemStack is) {
        List<Enchantment> list = getEnchantmentsNameList(is);
        if (list == null) {
            return (null);
        } else {
            return (list.toArray(new Enchantment[list.size()]));
        }
    }

    // ��
    public static List<Integer> getEnchantmentsLevelList(ItemStack is) {
        Map<Enchantment, Integer> m = getEnchantments(is);
        List<Integer> list = new ArrayList<Integer>();
        for (int i : m.values()) {
            list.add(i);
        }
        return (list);
    }

    // ��
    public static int[] getEnchantmentsLevel(ItemStack is) {
        List<Integer> list = getEnchantmentsLevelList(is);
        if (list == null) {
            return (null);
        } else {
            return (intergersToInts(list.toArray(new Integer[list.size()])));
        }
    }

    // ��
    public static int[] intergersToInts(Integer[] integer) {
        int length = integer.length;
        int[] ary = new int[length];
        for (int i = 0; i < length; i++) {
            ary[i] = integer[i] == null ? 0 : integer[i];
        }
        return (ary);
    }

    // ��
    public static void dropItems(Location loc, ItemStack is) {
        loc.getWorld().dropItem(loc, is);
    }

    // ��
    public static void info(String message) {
        Bukkit.getServer().getLogger().info(message);
    }

    public static ItemStack newItemD(Material material, String displayname) {
        return (setDisplayName(new ItemStack(material, 1), displayname));
    }

    public static ItemStack newItem(Material material) {
        return (new ItemStack(material, 1));
    }

    public static String getMetadata(Block b, String path) {
        List<MetadataValue> md = b.getMetadata(path);
        if (md.size() > 0) {
            for (MetadataValue each : md) {
                if (each.getOwningPlugin().equals(ClayTech.getInstance())) {
                    return each.asString();
                }
            }
        } else {
            return null;
        }
        return null;
    }

    public static void setMetadata(Block b, String path, String info) {
        b.setMetadata(path, new FixedMetadataValue(ClayTech.getInstance(), info));
    }

    public static boolean ExitsInList(String text, String[] list) {
        try {
            for (String str : list) {
                if (str.equals(text))
                    return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public static boolean ExitsInList(int text, int[] list) {
        try {
            for (int i : list) {
                if (i == text)
                    return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public static int GetItemIndex(Inventory inv, ItemStack is) {
        int i = 0;
        Iterator<ItemStack> it = inv.iterator();
        while (it.hasNext()) {
            try {
                if (it.next().getItemMeta().getDisplayName().equals(is.getItemMeta().getDisplayName())
                        && Utils.getLoreList(it.next()).equals(Utils.getLoreList(is))) {
                    return i;
                }
                i++;
            } catch (NullPointerException e) {
                return -1;
            }
        }
        return -1;
    }

    public static boolean IsItemContain(Inventory inv, ItemStack is) {
        Iterator<ItemStack> it = inv.iterator();
        while (it.hasNext()) {
            try {
                if (it.next().getItemMeta().getDisplayName().equals(is.getItemMeta().getDisplayName())
                        && Utils.getLoreList(it.next()).equals(Utils.getLoreList(is))) {
                    return true;
                }
            } catch (NullPointerException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean IsItemContainVanlia(Inventory inv, ItemStack is) {
        Iterator<ItemStack> it = inv.iterator();
        while (it.hasNext()) {
            try {
                if (it.next().getType() == is.getType()) {
                    return true;
                }
            } catch (NullPointerException e) {
                return false;
            }
        }
        return false;
    }

    public static int GetItemIndexVanlia(Inventory inv, ItemStack is) {
        int i = 0;
        Iterator<ItemStack> it = inv.iterator();
        while (it.hasNext()) {
            try {
                if (it.next().getType() == is.getType()) {
                    return i;
                }
                i++;
            } catch (NullPointerException e) {
                return -1;
            }
        }
        return -1;
    }

    public static boolean HasEnchantment(ItemStack is, Enchantment et) {
        if (is.hasItemMeta()) {
            if (is.getItemMeta().hasEnchants()) {
                if (is.getItemMeta().hasEnchant(et)) {
                    return true;
                } else
                    return false;
            } else
                return false;
        } else
            return false;
    }

    @SuppressWarnings("deprecation")
    public static boolean isValidEnchantment(String name) {
        if (Enchantment.getByName(name) != null)
            return true;
        return false;
    }

    public static String[] convertList(List<?> list) {
        return list.toArray(new String[list.size()]);
    }

    public static boolean keyInMap(Map<String, String> map, String key) {
        for (String eachKey : map.keySet()) {
            if (eachKey.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean ExitsInListL(String text, List<String> list) {
        try {
            for (String str : list) {
                if (str.equals(text)) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    public static void updateConfig(Plugin plugin, DataYML updateConfig) {
        try {
            FileConfiguration current = YamlConfiguration
                    .loadConfiguration(new InputStreamReader(plugin.getResource("config.yml"), "UTF8"));
            FileConfiguration config = updateConfig.getCustomConfig();
            List<String> currentconfig = new ArrayList<String>();
            for (String each : config.getKeys(false)) {
                currentconfig.add(each);
            }
            for (String each : current.getKeys(false)) {
                if (!Utils.ExitsInListL(each, currentconfig)) {
                    try {
                        if (current.isString(each)) {
                            config.set(each, current.getString(each));
                        }
                        if (current.isInt(each)) {
                            config.set(each, current.getInt(each));
                        }
                        if (current.isList(each)) {
                            config.set(each, current.getList(each));
                        }
                        if (current.isBoolean(each)) {
                            config.set(each, current.getBoolean(each));
                        }

                    } catch (Exception e) {
                        Utils.info("§cThere is an error when reading the config file.Replacing the new config file..");
                        config = current;
                        updateConfig.saveCustomConfig();
                        updateConfig.reloadCustomConfig();
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e2) {
            Utils.info("§cThere is an error when reading the config file.");
            e2.printStackTrace();
        }
    }

    public static void updateLocale(Plugin plugin, String locale, DataYML currentLangYML) {
        try {
            FileConfiguration current = YamlConfiguration
                    .loadConfiguration(new InputStreamReader(plugin.getResource(locale + ".yml"), "UTF8"));
            FileConfiguration currentLang = currentLangYML.getCustomConfig();
            for (String select : current.getKeys(false)) {
                if (Utils.sectionKeyToList(current.getConfigurationSection(select)).get(0).equalsIgnoreCase("null")
                        || current.getConfigurationSection(select).getKeys(false) == null) {
                    currentLang.createPath(current.getConfigurationSection(select), select);
                    currentLangYML.saveCustomConfig();
                    continue;
                } else {
                    List<String> found = Utils.sectionKeyToList(current.getConfigurationSection(select));
                    List<String> found2 = Utils.sectionKeyToList(currentLang.getConfigurationSection(select));
                    for (String each : found) {
                        if (!Utils.ExitsInListL(each, found2)) {
                            try {
                                if (current.isString(select + "." + each)) {
                                    currentLang.set(select + "." + each, current.getString(select + "." + each));
                                }
                                if (current.isInt(select + "." + each)) {
                                    currentLang.set(select + "." + each, current.getInt(select + "." + each));
                                }
                                if (current.isList(select + "." + each)) {
                                    currentLang.set(select + "." + each, current.getList(select + "." + each));
                                }
                            } catch (Exception e) {
                                Utils.info(
                                        "§cThere is an error when reading the language file.Replacing the new language file..");
                                currentLang = current;
                                currentLangYML.saveCustomConfig();
                                currentLangYML.reloadCustomConfig();
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            Utils.info("§cThere is an error when reading the language file.Replacing the new language file..");
            e2.printStackTrace();
        }
    }
}