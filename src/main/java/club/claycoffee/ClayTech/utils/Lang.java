package club.claycoffee.ClayTech.utils;

import java.util.Arrays;
import java.util.List;

import club.claycoffee.ClayTech.ClayTech;

public class Lang {
	public static String[] LocaleList = {"zh-CN", "zh-TW", "en-UK", "en-US"};
	
	
	public static String readItemText(String name) {
		if(ClayTech.currentLang.getString("Items."+name) == null) {
			return "Missing locale.";
		}
		else {
			return ClayTech.currentLang.getString("Items."+name).replaceAll("&", "§");
		}
	}
	@SuppressWarnings("unchecked")
	public static List<String> readItemLore(String name) {
		if(ClayTech.currentLang.getString("Items."+name+"_LORE") == null) {
			return Arrays.asList(new String[] {"Missing locale."});
		}
		else {
			return Utils.replaceList(((List<String>) ClayTech.currentLang.getList("Items."+name+"_LORE")),"&","§");
		}
	}
	public static String readGeneralText(String name) {
		if(ClayTech.currentLang.getString("General."+name) == null) {
			return "Missing locale.";
		}
		else {
			return ClayTech.currentLang.getString("General."+name).replaceAll("&", "§");
		}
	}
	public static String readCategoriesText(String name) {
		if(ClayTech.currentLang.getString("Categories."+name) == null) {
			return "Missing locale.";
		}
		else {
			return ClayTech.currentLang.getString("Categories."+name).replaceAll("&", "§");
		}
	}
	public static String readResearchesText(String name) {
		if(ClayTech.currentLang.getString("Researches."+name) == null) {
			return "Missing locale.";
		}
		else {
			return ClayTech.currentLang.getString("Researches."+name).replaceAll("&", "§");
		}
	}
	public static String readMachinesText(String name) {
		if(ClayTech.currentLang.getString("Machines."+name) == null) {
			return "Missing locale.";
		}
		else {
			return ClayTech.currentLang.getString("Machines."+name).replaceAll("&", "§");
		}
	}
	public static String readMachineRecipesText(String name) {
		if(ClayTech.currentLang.getString("MachineRecipes."+name) == null) {
			return "Missing locale.";
		}
		else {
			return ClayTech.currentLang.getString("MachineRecipes."+name).replaceAll("&", "§");
		}
	}
}
