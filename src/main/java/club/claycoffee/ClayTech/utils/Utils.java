package club.claycoffee.ClayTech.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public final class Utils {
	// ��
	public static ItemStack setDisplayName(ItemStack is, String Name) {
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(Name);
		is.setItemMeta(im);
		return (is);
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
			}
		
		catch(NullPointerException e) {return(new String[]{"null"});}
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

	public static boolean ExitsInList(String text, String[] list) {
		try {
			for (String str : list) {
				if (str.equals(text))
					return true;
			}
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}
	public static int GetItemIndex(Inventory inv,ItemStack is) {
		int i = 0;
		Iterator<ItemStack> it = inv.iterator();
		while(it.hasNext()) {
			try {
				if(it.next().getItemMeta().getDisplayName().equals(is.getItemMeta().getDisplayName()) && Utils.getLoreList(it.next()).equals(Utils.getLoreList(is))) {
					return i;
				}
				i++;
			}catch(NullPointerException e) {return -1;}
		}
		return -1;
	}
	public static boolean IsItemContain(Inventory inv,ItemStack is) {
		Iterator<ItemStack> it = inv.iterator();
		while(it.hasNext()) {
			try {
				if(it.next().getItemMeta().getDisplayName().equals(is.getItemMeta().getDisplayName()) && Utils.getLoreList(it.next()).equals(Utils.getLoreList(is))) {
					return true;
				}
			}catch(NullPointerException e) {return false;}
		}
		return false;
	}
	public static boolean IsItemContainVanlia(Inventory inv,ItemStack is) {
		Iterator<ItemStack> it = inv.iterator();
		while(it.hasNext()) {
			try {
					if(it.next().getType()==is.getType()) {
						return true;
					}
				}
				catch(NullPointerException e) {return false;}
		}
		return false;
	}
	public static int GetItemIndexVanlia(Inventory inv,ItemStack is) {
		int i = 0;
		Iterator<ItemStack> it = inv.iterator();
		while(it.hasNext()) {
			try {
					if(it.next().getType()==is.getType()) {
						return i;
					}
					i++;
				}
				catch(NullPointerException e) {return -1;}
		}
		return -1;
	}
	public static boolean HasEnchantment(ItemStack is,Enchantment et) {
		if(is.hasItemMeta()) {
			if(is.getItemMeta().hasEnchants()) {
				if(is.getItemMeta().hasEnchant(et)) {
					return true;
				}else return false;
			}else return false;
		}else return false;
	}
	@SuppressWarnings("deprecation")
	public static boolean isValidEnchantment(String name) {
		if(Enchantment.getByName(name) != null) return true;
		return false;
	}
	public static String[] convertList(List<?> list) {
		return list.toArray(new String[list.size()]);
	}
	public static boolean keyInMap(Map<String,String> map,String key) {
		for(String eachKey : map.keySet()) {
			if(eachKey.equals(key)) {
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
		}
		catch (NullPointerException e) {
			return false;
		}
		return false;
	}
}