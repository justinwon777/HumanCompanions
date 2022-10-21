package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Random;

public class CompanionData {

    public static Random rand = new Random();

//    public static Map<Integer, Item[]> FOOD_GROUPS = new HashMap<>() {{
//        put(1, MEAT);
//        put(2, SEAFOOD);
//        put(3, VEGETABLE);
//        put(4, FRUIT);
//        put(5, BAKED);
//    }};
//
//    public static Map<Integer, TextComponent[]> FOOD_MESSAGES = new HashMap<>() {{
//        put(1, MEAT_MESSAGES);
//        put(2, SEAFOOD_MESSAGES);
//        put(3, VEGETABLE_MESSAGES);
//        put(4, FRUIT_MESSAGES);
//        put(5, BAKED_MESSAGES);
//        put(6, WRONG_FOOD);
//    }};
//
//    public static Item[] MEAT = new Item[] {
//            Items.COOKED_MUTTON,
//            Items.COOKED_PORKCHOP,
//            Items.COOKED_BEEF,
//            Items.COOKED_CHICKEN,
//            Items.COOKED_RABBIT,
//            Items.MUTTON,
//            Items.PORKCHOP,
//            Items.BEEF,
//            Items.CHICKEN,
//            Items.RABBIT,
//            Items.RABBIT_STEW
//    };
//
//    public static Item[] SEAFOOD = new Item[] {
//            Items.COOKED_SALMON,
//            Items.COOKED_COD,
//            Items.SALMON,
//            Items.COD,
//            Items.TROPICAL_FISH
//    };
//
//    public static Item[] VEGETABLE = new Item[] {
//            Items.GOLDEN_CARROT,
//            Items.BEETROOT,
//            Items.CARROT,
//            Items.POTATO,
//            Items.BAKED_POTATO,
//            Items.BROWN_MUSHROOM,
//            Items.RED_MUSHROOM,
//            Items.DRIED_KELP,
//            Items.MUSHROOM_STEW,
//            Items.BEETROOT_SOUP,
//            Items.PUMPKIN
//    };
//
//    public static Item[] FRUIT = new Item[] {
//            Items.GOLDEN_APPLE,
//            Items.ENCHANTED_GOLDEN_APPLE,
//            Items.MELON,
//            Items.MELON_SLICE,
//            Items.APPLE,
//            Items.GLOW_BERRIES,
//            Items.SWEET_BERRIES,
//            Items.CHORUS_FRUIT
//    };
//
//    public static Item[] BAKED = new Item[] {
//            Items.CAKE,
//            Items.COOKIE,
//            Items.BREAD,
//            Items.PUMPKIN_PIE
//    };

    public static Class<?>[] alertMobs = new Class<?>[]{
            Blaze.class,
            EnderMan.class,
            Endermite.class,
            Ghast.class,
            Giant.class,
            Guardian.class,
            Hoglin.class,
            MagmaCube.class,
            Phantom.class,
            Shulker.class,
            Silverfish.class,
            Slime.class,
            Spider.class,
            Vex.class,
            AbstractSkeleton.class,
            Zoglin.class,
            Zombie.class,
            Raider.class
    };

    public static Class<?>[] huntMobs = new Class<?>[]{
            Chicken.class,
            Cow.class,
            MushroomCow.class,
            Pig.class,
            Rabbit.class,
            Sheep.class
    };

    public static ResourceLocation[] maleSkins = new ResourceLocation[]{
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/medieval-man-hugh.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/alexandros.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/cyrus.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/diokles.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/dion.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/georgios.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/ioannis.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/medieval-peasant-schwaechlich.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/medieval-peasant-without-vest.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/medieval-peasant-with-vest-on.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/panos.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/viking-blue-tunic.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/cronos-jojo.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/medieval-man-alard.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/peasant-ginger.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/townsman-green-tunic.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/polish-farmer.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/peasant.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/rustic-farmer.png"),
            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/medieval-villager.png"),
    };

//    public static ResourceLocation[] femaleSkins = new ResourceLocation[]{
//            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/girl-medieval-peasant.png"),
//            new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/medieval-barmaid.png"),
//    };

    public static TextComponent[] tameFail = new TextComponent[]{
            new TextComponent("I need more food."),
            new TextComponent("Is that all you got?"),
            new TextComponent("I'm still hungry."),
            new TextComponent("Can I have some more?"),
            new TextComponent("I'm going to need a bit more."),
            new TextComponent("That's not enough."),
    };
    public static TextComponent[] notTamed = new TextComponent[]{
            new TextComponent("Do you have any food?"),
            new TextComponent("I'm hungry"),
            new TextComponent("I'm starving"),
            new TextComponent("Have you seen any food around here?"),
            new TextComponent("I could use some food"),
            new TextComponent("I wish I had some food"),
            new TextComponent("I'm starving."),
    };
//    public static TextComponent[] WRONG_FOOD = new TextComponent[]{
//            new TextComponent("That's not what I asked for."),
//            new TextComponent("I didn't ask for that."),
//    };
//    public static TextComponent[] MEAT_MESSAGES = new TextComponent[]{
//            new TextComponent("I've been craving some meat."),
//            new TextComponent("I haven't had animal flesh in a while,"),
//            new TextComponent("Do you have any meat?")
//    };
//    public static TextComponent[] SEAFOOD_MESSAGES = new TextComponent[]{
//            new TextComponent("I've been craving some seafood."),
//            new TextComponent("I haven't had seafood in a while,"),
//            new TextComponent("Do you have any seafood?")
//    };
//    public static TextComponent[] VEGETABLE_MESSAGES = new TextComponent[]{
//            new TextComponent("I've been craving some vegetables."),
//            new TextComponent("I haven't had vegetables in a while,"),
//            new TextComponent("Do you have any vegetables?")
//    };
//    public static TextComponent[] FRUIT_MESSAGES = new TextComponent[]{
//            new TextComponent("I've been craving some fruits."),
//            new TextComponent("I haven't had fruits in a while,"),
//            new TextComponent("Do you have any fruits?")
//    };
//    public static TextComponent[] BAKED_MESSAGES = new TextComponent[]{
//            new TextComponent("I've been craving something baked."),
//            new TextComponent("Do you have any baked goods?"),
//            new TextComponent("I wish there was a bakery around here?")
//    };

    public static int getHealthModifier() {
        float healthFloat = rand.nextFloat();
        if (healthFloat <= 0.03) {
            return -4;
        } else if (healthFloat <= 0.1) {
            return -3;
        } else if (healthFloat <= 0.2) {
            return -2;
        } else if (healthFloat <= 0.35) {
            return -1;
        } else if (healthFloat <= 0.65) {
            return 0;
        } else if (healthFloat <= 0.8) {
            return 1;
        } else if (healthFloat <= 0.9) {
            return 2;
        } else if (healthFloat <= 0.97) {
            return 3;
        } else {
            return 4;
        }
    }

    public static ItemStack getSpawnArmor(EquipmentSlot armorType) {
        float materialFloat = rand.nextFloat();
        if (materialFloat <= 0.4F) {
            return ItemStack.EMPTY;
        } else if(materialFloat <= 0.70F) {
            switch(armorType) {
                case HEAD:
                    return Items.LEATHER_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.LEATHER_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.LEATHER_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.LEATHER_BOOTS.getDefaultInstance();
            }
        } else if(materialFloat <= 0.90F) {
            switch(armorType) {
                case HEAD:
                    return Items.CHAINMAIL_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.CHAINMAIL_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.CHAINMAIL_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.CHAINMAIL_BOOTS.getDefaultInstance();
            }
        } else {
            switch(armorType) {
                case HEAD:
                    return Items.IRON_HELMET.getDefaultInstance();
                case CHEST:
                    return Items.IRON_CHESTPLATE.getDefaultInstance();
                case LEGS:
                    return Items.IRON_LEGGINGS.getDefaultInstance();
                case FEET:
                    return Items.IRON_BOOTS.getDefaultInstance();
            }
        }
        return ItemStack.EMPTY;
    }

    public static String getRandomName() {
        Random rand = new Random();
        String firstName = maleFirstNames[rand.nextInt(maleFirstNames.length)];
        String lastName = lastNames[rand.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    public static String[] maleFirstNames = new String[] {
            "Abraham", "Adam", "Acke", "Adolf", "Albert", "Albin", "Albrecht", "Alexander", "Alf", "Alfred", "Algot",
            "Alvar", "Anders", "Andreas", "Arne", "Aron", "Arthur", "Arvid", "Axel",
            "Bengt", "Bernhard", "Bernt", "Bertil", "Birger", "Bjarne", "Bo", "Bosse", "Bror", "Cai", "Caj", "Carl",
            "Christer", "Christoffer", "Claes", "Dag", "Daniel", "Danne", "Ebbe", "Eilert", "Einar", "Elias", "Elis",
            "Elmar", "Elof", "Elov", "Emil", "Emrik", "Enok", "Eric",
            "Erik", "Erland", "Erling", "Eskil", "Evert", "Folke", "Frans", "Fredrik", "Frej", "Fritiof", "Fritjof",
            "Gerhard", "Gottfrid", "Greger", "Gunnar", "Gunne", "Gustaf", "Gustav",
            "Halsten", "Halvar", "Hampus", "Hans", "Harald", "Hasse", "Henrik", "Hilding", "Hjalmar", "Holger",
            "Inge", "Ingemar", "Ingmar", "Ingvar", "Isac", "Isak", "Ivar",
            "Jakob", "Jan", "Janne", "Jarl", "Jens", "Jerk", "Jerker", "Joakim", "Johan", "John", "Jon", "Jonas",
            "Kalle", "Karl", "Kasper", "Kennet", "Kettil", "Kjell", "Klas", "Knut", "Krister", "Kristian", "Kristofer",
            "Lage", "Lars", "Lasse", "Leif", "Lelle", "Lennart", "Lias", "Loke", "Lorens", "Loui", "Love", "Ludde", "Ludvig",
            "Magnus", "Markus", "Martin", "Matheo", "Mats", "Matteus", "Mattias", "Mattis", "Matts", "Melker", "Micael", "Mikael", "Milian",
            "Nicklas", "Niklas", "Nils", "Njord", "Noak",
            "Ola", "Oliver", "Olle", "Olaf", "Olof", "Olov", "Orvar", "Osvald", "Otto", "Ove",
            "Patrik", "Peder", "Pehr", "Pelle", "Per", "Peter", "Petter", "Pontus", "Ragnar", "Ragnvald", "Rickard",
            "Rikard", "Robert", "Roffe", "Samuel", "Sigfrid", "Sigge", "Sigvard", "Sivert", "Sixten", "Staffan",
            "Stefan", "Stellan", "Stig", "Sune", "Svante", "Sven",  "Tage", "Thor", "Thore", "Thorsten", "Thorvald",
            "Tomas", "Tor", "Tore", "Torgny", "Torkel", "Torsten", "Torvald", "Truls", "Tryggve", "Ture", "Ulf",
            "Ulrik", "Uno", "Urban", "Valdemar", "Valter", "Verg",  "Verner", "Victor", "Vidar", "Vide", "Viggo",
            "Viktor", "Vilhelm", "Ville", "Vilmar", "Yngve",
    };

    public static String[] lastNames = new String[] {
            "Abrahamsson", "Abramsson", "Adamsson", "Adolfsson", "Adolvsson", "Ahlberg", "Ahlgren", "Albertsson",
            "Albinsson", "Albrechtsson", "Albrecktsson", "Albrektson", "Albrektsson", "Alexanderson", "Alexandersson"
            , "Alfredsson", "Alfson", "Alfsson", "Almstedt", "Alvarsson", "Andersson", "Andreasson", "Arthursson", "Arvidsson", "Axelsson",
            "Beck", "Bengtsdotter", "Bengtsson", "Berg", "Berge", "Bergfalk", "Berggren", "Berglund", "Bergman",
            "Bernhardsson", "Berntsson","Blom", "Blomgren", "Blomqvist", "Borg", "Breiner", "Byquist", "Byqvist",
            "Carlson", "Carlsson", "Claesson", "Dahl", "Dahlman", "Danielsson",
            "Einarsson", "Ek", "Eklund", "Eld", "Eliasson", "Elmersson", "Engberg", "Engman", "Ericson", "Ericsson", "Eriksson",
            "Falk", "Feldt", "Forsberg", "Fransson", "Fredriksson", "Frisk",
            "Gerhardsson", "Grahn", "Gunnarsson", "Gustafsson", "Gustavsson", "Hall", "Hallman", "Hansson",
            "Haraldsson", "Haroldson", "Henriksson", "Herbertsson", "Hermansson", "Hjort", "Holgersson", "Holm", "Holmberg", "Hult",
            "Ingesson", "Isaksson", "Ivarsson", "Jakobsson", "Janson", "Jansson", "Johansson", "Johnsson", "Jonasson"
            , "Jonsson", "Karlsson", "Kjellsson", "Klasson", "Knutson", "Knutsson", "Kron",
            "Lager", "Larson", "Larsson", "Leifsson", "Lennartsson", "Leonardsson", "Lind", "Lindbeck", "Lindberg",
            "Lindgren", "Lindholm", "Lindquist", "Lindqvist", "Ljung", "Ljungborg", "Ljunggren", "Ljungman",
            "Ljungstrand", "Lund", "Lundberg", "Lundgren", "Lundin", "Lundquist", "Lundqvist", "Magnusson",
            "Markusson", "Martin", "Martinsson", "Matsson", "Mattsson", "Mikaelsson", "Niklasson", "Nilsson",
            "Norling", "Nyberg", "Nykvist", "Nylund", "Nyquist", "Nyqvist", "Olander", "Oliversson", "Olofsdotter",
            "Olofsson", "Olson", "Olsson", "Ottosson", "Patriksson", "Persson", "Petersson", "Pettersson", "Pilkvist"
            , "Ragnvaldsson", "Rapp", "Rask", "Robertsson", "Rosenberg", "Samuelsson", "Sandberg", "Sigurdsson",
            "Simonsson", "Solberg","Sorenson", "Stefansson", "Stenberg", "Stendahl", "Stigsson", "Strand", "Sundberg"
            , "Svenson", "Svensson", "Tomasson", "Ulfsson", "Victorsson", "Vinter", "Waltersson", "Wang",
            "Westerberg", "Winter", "Winther", "Wuopio",
    };
}
