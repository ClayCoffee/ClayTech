package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTech;

import java.util.Arrays;
import java.util.List;

public class Lang {

    public static String readItemText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Items." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return readItemTextDefault(name);
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Items." + name);
        }
    }

    public static String readItemTextDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Items." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getDefaultLangYML().getCustomConfig().getString("Items." + name);
        }
    }

    public static List<String> readItemLore(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Items." + name + "_LORE") == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return Arrays.asList(new String[]{"Missing locale."});
            return readItemLoreDefault(name);
        } else {
            return 
                    ((List<String>) ClayTech.getLangYML().getCustomConfig().getList("Items." + name + "_LORE"));
        }
    }

    public static List<String> readItemLoreDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Items." + name + "_LORE") == null) {
            return Arrays.asList(new String[]{"Missing locale."});
        } else {
            return 
                    ((List<String>) ClayTech.getDefaultLangYML().getCustomConfig().getList("Items." + name + "_LORE"));
        }
    }

    public static String readGeneralText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("General." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return readGeneralTextDefault(name);
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("General." + name).replaceAll("&", "§");
        }
    }

    public static String readGeneralTextDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("General." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getDefaultLangYML().getCustomConfig().getString("General." + name).replaceAll("&", "§");
        }
    }

    public static String readCategoriesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Categories." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return readCategoriesDefault(name);
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Categories." + name).replaceAll("&", "§");
        }
    }

    public static String readCategoriesDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Categories." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getDefaultLangYML().getCustomConfig().getString("Categories." + name).replaceAll("&", "§");
        }
    }

    public static String readResearchesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Researches." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return readResearchesDefault(name);
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Researches." + name).replaceAll("&", "§");
        }
    }

    public static String readResearchesDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Researches." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getDefaultLangYML().getCustomConfig().getString("Researches." + name).replaceAll("&", "§");
        }
    }

    public static String readMachinesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Machines." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return readMachinesDefault(name);
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Machines." + name).replaceAll("&", "§");
        }
    }

    public static String readMachinesDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Machines." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getDefaultLangYML().getCustomConfig().getString("Machines." + name).replaceAll("&", "§");
        }
    }

    public static String readMachineRecipesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("MachineRecipes." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return readMachineRecipesDefault(name);
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("MachineRecipes." + name).replaceAll("&", "§");
        }
    }

    public static String readMachineRecipesDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("MachineRecipes." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getDefaultLangYML().getCustomConfig().getString("MachineRecipes." + name).replaceAll("&", "§");
        }
    }

    public static String readPlanetsText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Planets." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return readPlanetsDefault(name);
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Planets." + name).replaceAll("&", "§");
        }
    }

    public static String readPlanetsDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Planets." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getDefaultLangYML().getCustomConfig().getString("Planets." + name).replaceAll("&", "§");
        }
    }

    public static String readResourcesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Planets." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return readResourcesDefault(name);
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Planets." + name).replaceAll("&", "§");
        }
    }

    public static String readResourcesDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Resources." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getDefaultLangYML().getCustomConfig().getString("Resources." + name).replaceAll("&", "§");
        }
    }
}
