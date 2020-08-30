package cn.claycoffee.ClayTech.utils;

import cn.claycoffee.ClayTech.ClayTech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lang {
    public static String[] LocaleList = {"zh-CN", "zh-TW", "en-GB", "en-US", "ja"};
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
            return "Missing locale.";
        } else {
            return format(ClayTech.getLangYML().getCustomConfig().getString("Items." + name));
        }
    }

    @SuppressWarnings("unchecked")
    public static List<String> readItemLore(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Items." + name + "_LORE") == null) {
            return Arrays.asList(new String[]{"Missing locale."});
        } else {
            return format(
                    ((List<String>) ClayTech.getLangYML().getCustomConfig().getList("Items." + name + "_LORE")));
        }
    }

    public static String readGeneralText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("General." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("General." + name).replaceAll("&", "§");
        }
    }

    public static String readCategoriesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Categories." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Categories." + name).replaceAll("&", "§");
        }
    }

    public static String readResearchesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Researches." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Researches." + name).replaceAll("&", "§");
        }
    }

    public static String readMachinesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Machines." + name) == null) {
            return "Missing locale.";
        } else {
            return format(ClayTech.getLangYML().getCustomConfig().getString("Machines." + name));
        }
    }

    public static String readMachineRecipesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("MachineRecipes." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("MachineRecipes." + name).replaceAll("&", "§");
        }
    }

    public static String readPlanetsText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Planets." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Planets." + name).replaceAll("&", "§");
        }
    }

    public static String readResourcesText(String name) {
        if (ClayTech.getLangYML().getCustomConfig().getString("Resources." + name) == null) {
            return "Missing locale.";
        } else {
            return ClayTech.getLangYML().getCustomConfig().getString("Resources." + name).replaceAll("&", "§");
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
        for(String s : list) {
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
            ret.add(s);
        }
        return ret;
    }
}
