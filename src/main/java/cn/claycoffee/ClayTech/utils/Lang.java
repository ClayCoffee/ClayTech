package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lang {
    public static String cantPlace;
    public static String blindFive;
    public static String slownessFive;
    public static String confusionFive;
    public static String poisonThree;
    public static String antiSlownessThree;
    public static String durability;
    public static String cantEat;
    public static String cantInteract;
    public static String rocketPrefix;
    public static String spaceSuitPrefix;
    public static String fuelPrefix;
    public static String oxygenPrefix;
    public static String protectLevel;
    public static String oxygenDistributerPrefix;

    public static void init() {
        cantPlace = readGeneralText("CantPlaceLore");
        blindFive = readGeneralText("Blind_5_effect");
        slownessFive = readGeneralText("Slowness_5_effect");
        confusionFive = readGeneralText("Confusion_5_effect");
        poisonThree = readGeneralText("Poison_3_effect");
        antiSlownessThree = readGeneralText("Anti_Slowness_5_effect");
        durability = readGeneralText("Durability");
        cantEat = readGeneralText("CantEat");
        cantInteract = readGeneralText("CantInteract");
        rocketPrefix = readGeneralText("Rocket");
        spaceSuitPrefix = readGeneralText("SpaceSuit");
        fuelPrefix = readGeneralText("Fuel");
        oxygenPrefix = readGeneralText("Oxygen");
        protectLevel = readGeneralText("ProtectLevel");
        oxygenDistributerPrefix = readGeneralText("OxygenDistributer");
    }

    public static String readItemText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Items." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return format(readItemTextDefault(name));
        } else {
            return format(ClayTech.getLangYML().getCustomConfig().getString("Items." + name));
        }
    }

    public static String readItemTextDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Items." + name) == null) {
            return "Missing locale.";
        } else {
            return format(ClayTech.getDefaultLangYML().getCustomConfig().getString("Items." + name));
        }
    }

    @SuppressWarnings("unchecked")
    public static List<String> readItemLore(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Items." + name + "_LORE") == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return Arrays.asList(new String[]{"Missing locale."});
            return format(readItemLoreDefault(name));
        } else {
            return format(
                    ((List<String>) ClayTech.getLangYML().getCustomConfig().getList("Items." + name + "_LORE")));
        }
    }

    public static List<String> readItemLoreDefault(String name) {
        if (ClayTech.getDefaultLangYML().getCustomConfig().getString("Items." + name + "_LORE") == null) {
            return Arrays.asList(new String[]{"Missing locale."});
        } else {
            return format(
                    ((List<String>) ClayTech.getDefaultLangYML().getCustomConfig().getList("Items." + name + "_LORE")));
        }
    }

    public static String readGeneralText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("General." + name) == null) {
            if (ClayTech.getLocale().equals("en-US"))
                return "Missing locale.";
            return format(readGeneralTextDefault(name));
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
            return format(readCategoriesDefault(name));
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
            return format(readResearchesDefault(name));
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
            return format(readMachinesDefault(name));
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
            return format(readMachineRecipesDefault(name));
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
            return format(readPlanetsDefault(name));
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
            return format(readResourcesDefault(name));
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

    public static String format(String s) {
        s = s.replaceAll("&", "§");
        s = s.replaceAll("<cantplace>", cantPlace);
        s = s.replaceAll("<cantinteract>", cantInteract);
        s = s.replaceAll("<canteat>", cantEat);
        s = s.replaceAll("<blindFive>", blindFive);
        s = s.replaceAll("<slownessfive>", slownessFive);
        s = s.replaceAll("<confusionfive>", confusionFive);
        s = s.replaceAll("<poisonthree>", poisonThree);
        s = s.replaceAll("<antislownessthree>", antiSlownessThree);
        s = s.replaceAll("<durability>", durability);
        s = s.replaceAll("<rocket>", rocketPrefix);
        s = s.replaceAll("<spacesuit>", spaceSuitPrefix);
        s = s.replaceAll("<fuel>", fuelPrefix);
        s = s.replaceAll("<oxygen>", oxygenPrefix);
        s = s.replaceAll("<protectlevel>", protectLevel);
        s = s.replaceAll("<oxygendistributer>", oxygenDistributerPrefix);

        return s;
    }

    public static List<String> format(List<String> list) {
        List<String> ret = new ArrayList<>();
        for (String s : list) {
            s = s.replaceAll("&", "§");
            s = s.replaceAll("<cantplace>", cantPlace);
            s = s.replaceAll("<cantinteract>", cantInteract);
            s = s.replaceAll("<canteat>", cantEat);
            s = s.replaceAll("<blindfive>", blindFive);
            s = s.replaceAll("<slownessfive>", slownessFive);
            s = s.replaceAll("<confusionfive>", confusionFive);
            s = s.replaceAll("<poisonthree>", poisonThree);
            s = s.replaceAll("<antislownessthree>", antiSlownessThree);
            s = s.replaceAll("<durability>", durability);
            s = s.replaceAll("<rocket>", rocketPrefix);
            s = s.replaceAll("<spacesuit>", spaceSuitPrefix);
            s = s.replaceAll("<fuel>", fuelPrefix);
            s = s.replaceAll("<oxygen>", oxygenPrefix);
            s = s.replaceAll("<protectlevel>", protectLevel);
            s = s.replaceAll("<oxygendistributer>", oxygenDistributerPrefix);
            ret.add(s);
        }
        return ret;
    }
}
