package com.github.justinwon777.humancompanions.entity;

import com.github.justinwon777.humancompanions.HumanCompanions;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CompanionData {

    public static Random rand = new Random();

    public static Item[] MEAT = new Item[] {
            Items.COOKED_MUTTON,
            Items.COOKED_PORKCHOP,
            Items.COOKED_BEEF,
            Items.COOKED_CHICKEN,
            Items.COOKED_RABBIT,
//            Items.MUTTON,
//            Items.PORKCHOP,
//            Items.BEEF,
//            Items.CHICKEN,
//            Items.RABBIT,
//            Items.RABBIT_STEW
    };

    public static Item[] SEAFOOD = new Item[] {
            Items.COOKED_SALMON,
            Items.COOKED_COD,
            Items.SALMON,
            Items.COD,
            Items.TROPICAL_FISH
    };

    public static Item[] VEGETABLE = new Item[] {
//            Items.GOLDEN_CARROT,
            Items.BEETROOT,
            Items.CARROT,
            Items.POTATO,
            Items.BAKED_POTATO,
            Items.BROWN_MUSHROOM,
            Items.RED_MUSHROOM,
//            Items.DRIED_KELP,
//            Items.MUSHROOM_STEW,
//            Items.BEETROOT_SOUP,
            Items.PUMPKIN
    };

    public static Item[] FRUIT = new Item[] {
//            Items.GOLDEN_APPLE,
//            Items.ENCHANTED_GOLDEN_APPLE,
//            Items.MELON,
            Items.MELON_SLICE,
            Items.APPLE,
            Items.SWEET_BERRIES,
            Items.CHORUS_FRUIT
    };

    public static Item[] BAKED = new Item[] {
            Items.CAKE,
            Items.COOKIE,
            Items.BREAD,
            Items.PUMPKIN_PIE
    };

    public static StringTextComponent[] tameFail = new StringTextComponent[]{
            new StringTextComponent("I need more food."),
            new StringTextComponent("Is that all you got?"),
            new StringTextComponent("I'm still hungry."),
            new StringTextComponent("Can I have some more?"),
            new StringTextComponent("I'm going to need a bit more."),
            new StringTextComponent("That's not enough."),
    };

//    public static StringTextComponent[] notTamed = new StringTextComponent[]{
//            new StringTextComponent("Do you have any food?"),
//            new StringTextComponent("I'm hungry."),
//            new StringTextComponent("Have you seen any food around here?"),
//            new StringTextComponent("I could use some food."),
//            new StringTextComponent("I wish I had some food."),
//            new StringTextComponent("I'm starving."),
//    };

    public static StringTextComponent[] WRONG_FOOD = new StringTextComponent[]{
            new StringTextComponent("That's not what I asked for."),
            new StringTextComponent("I didn't ask for that."),
            new StringTextComponent("Looks like you didn't understand my request."),
            new StringTextComponent("Did you forget what I asked for?"),
            new StringTextComponent("I don't remember asking for that")
    };

    public static StringTextComponent[] MEAT_MESSAGES = new StringTextComponent[]{
            new StringTextComponent("I've been craving some meat."),
            new StringTextComponent("I haven't had animal flesh in a while,"),
            new StringTextComponent("Do you have any meat?"),
            new StringTextComponent("Could you get some meat for me?")
    };

    public static StringTextComponent[] SEAFOOD_MESSAGES = new StringTextComponent[]{
            new StringTextComponent("I've been craving some seafood."),
            new StringTextComponent("I haven't had seafood in a while,"),
            new StringTextComponent("Do you have any seafood?"),
            new StringTextComponent("Could you get some seafood for me?")
    };

    public static StringTextComponent[] VEGETABLE_MESSAGES = new StringTextComponent[]{
            new StringTextComponent("I've been craving some vegetables."),
            new StringTextComponent("I haven't had vegetables in a while,"),
            new StringTextComponent("Do you have any vegetables?"),
            new StringTextComponent("Could you get some vegetables for me?")
    };

    public static StringTextComponent[] FRUIT_MESSAGES = new StringTextComponent[]{
            new StringTextComponent("I've been craving some fruits."),
            new StringTextComponent("I haven't had fruits in a while,"),
            new StringTextComponent("Do you have any fruits?"),
            new StringTextComponent("Could you get some fruits for me?")
    };

    public static StringTextComponent[] BAKED_MESSAGES = new StringTextComponent[]{
            new StringTextComponent("I've been craving something baked."),
            new StringTextComponent("Do you have any baked goods?"),
            new StringTextComponent("I haven't had baked food in a while."),
            new StringTextComponent("I wish there was a bakery around here?"),
            new StringTextComponent("Could you bake something for me?")
    };

    public static Map<Integer, Item[]> FOOD_GROUPS = new HashMap<Integer, Item[]>() {{
        put(0, MEAT);
        put(1, SEAFOOD);
        put(2, VEGETABLE);
        put(3, FRUIT);
        put(4, BAKED);
    }};

    public static Map<Integer, StringTextComponent[]> FOOD_MESSAGES = new HashMap<Integer, StringTextComponent[]>() {{
        put(0, MEAT_MESSAGES);
        put(1, SEAFOOD_MESSAGES);
        put(2, VEGETABLE_MESSAGES);
        put(3, FRUIT_MESSAGES);
        put(4, BAKED_MESSAGES);
        put(5, WRONG_FOOD);
    }};

    public static Class<?>[] alertMobs = new Class<?>[]{
            BlazeEntity.class,
            EndermanEntity.class,
            EndermiteEntity.class,
            GhastEntity.class,
            GiantEntity.class,
            GuardianEntity.class,
            HoglinEntity.class,
            MagmaCubeEntity.class,
            PhantomEntity.class,
            ShulkerEntity.class,
            SilverfishEntity.class,
            SlimeEntity.class,
            SpiderEntity.class,
            VexEntity.class,
            AbstractSkeletonEntity.class,
            ZoglinEntity.class,
            ZombieEntity.class,
            AbstractRaiderEntity.class
    };

    public static Class<?>[] huntMobs = new Class<?>[]{
            ChickenEntity.class,
            CowEntity.class,
            MooshroomEntity.class,
            PigEntity.class,
            RabbitEntity.class,
            SheepEntity.class
    };

    // skins[0] == male, skins[1] == female
    public static ResourceLocation[][] skins = new ResourceLocation[][] {
            new ResourceLocation[] {
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-man-hugh.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/alexandros.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/cyrus.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/diokles.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/dion.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/georgios.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/ioannis.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-peasant-schwaechlich.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-peasant-without-vest.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-peasant-with-vest-on.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/panos.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/viking-blue-tunic.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/cronos-jojo.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-man-alard.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/peasant-ginger.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/townsman-green-tunic.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/polish-farmer.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/peasant.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/rustic-farmer.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/male/medieval-villager.png"),
            },
            new ResourceLocation[] {
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/a-rogue-i-guess.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/deidre-gramville.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/deidre-gramville2.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/eleora-halle.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/fantastic-blue.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/ftu-emma.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/girl-medieval-peasant.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/medieval-barmaid.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/runaway.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/shannon-flux.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/the-traveller.png"),
                    new ResourceLocation(HumanCompanions.MOD_ID, "textures/entities/female/x-ayesha.png"),
            }
    };

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

    public static ItemStack getSpawnArmor(EquipmentSlotType armorType) {
        float materialFloat = rand.nextFloat();
        if (materialFloat <= 0.4F) {
            return ItemStack.EMPTY;
        } else if(materialFloat < 0.70F) {
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
        } else if(materialFloat < 0.90F) {
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

    public static String getRandomName(int sex) {
        Random rand = new Random();
        String firstName = firstNames[sex][rand.nextInt(firstNames[sex].length)];
        String lastName = lastNames[rand.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    // Names source: https://github.com/ironarachne/namegen/blob/main/swedishnames.go
    // firstNames[0] == male, firstNames[1] == female
    public static String[][] firstNames = new String[][]{
            new String[] {
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
            },
            new String[] {
                    "Agda", "Agneta", "Agnetha", "Aina", "Alfhild", "Alicia", "Alva", "Anette", "Anja", "Anneli", "Annika", "Asta", "Astrid",
                    "Barbro", "Bengta", "Berit", "Birgit", "Birgitta", "Bodil", "Brita", "Britt", "Britta",
                    "Cajsa", "Carin", "Carina", "Carita", "Catharina", "Cathrine", "Catrine", "Charlotta", "Christin", "Cilla",
                    "Dagny",
                    "Ebba", "Eira", "Eleonor", "Elin", "Elina","Ellinor", "Elna", "Elsa", "Elsie", "Embla",
                    "Emelie", "Erica", "Erika", "Erna", "Evy",
                    "Fredrika", "Freja", "Frida",
                    "Gabriella", "Gerd", "Gerda", "Gertrud", "Gittan", "Greta", "Gry", "Gudrun", "Gull", "Gunborg",
                    "Gunda", "Gunhild", "Gunhilda", "Gunilla", "Gunn", "Gunnel", "Gunvor",
                    "Hanna", "Hanne", "Hedda", "Hedvig", "Helga", "Henrika", "Hillevi", "Hilma", "Hulda",
                    "Idun", "Ingeborg", "Ingegerd", "Inger", "Ingrid",
                    "Jannike", "Jennie", "Joline", "Jonna", "Josefin", "Josefina","Josefine", "Juni",
                    "Kaja", "Kajsa", "Kamilla", "Karin", "Karita", "Karla", "Katja", "Katrin", "Kersti", "Kerstin",
                    "Kia", "Kjerstin", "Klara", "Kristin", "Kristine",
                    "Laila", "Linn", "Linnea", "Lis", "Lisbet", "Lisbeth", "Liselott", "Liselotte", "Liv",
                    "Lo", "Lotta", "Lottie", "Lova", "Lovis", "Lovisa",
                    "Maj", "Maja", "Majken", "Malena", "Malin", "Margaretha", "Margit", "Mari", "Mariann", "Marit",
                    "Marita", "Mathilda", "Meja", "Merit", "Meta", "Mikaela", "Milla", "Milly", "Mimmi", "Minna", "Moa", "Mona",
                    "Nanna", "Nea", "Nellie", "Nelly",
                    "Ottilia",
                    "Pernilla", "Petronella",
                    "Ragna", "Ragnhild", "Rakel", "Rebecka", "Rigmor", "Rika", "Ronja", "Runa", "Rut",
                    "Saga", "Sanna", "Sassa", "Signe", "Sigrid", "Siri", "Siv", "Sofie", "Solveig", "Solvig", "Stina"
                    , "Susann", "Susanne", "Svea", "Sylvi",
                    "Tanja", "Tekla", "Terese", "Teresia", "Tessan", "Thea", "Therese", "Thorborg", "Thyra", "Tilde"
                    , "Tindra", "Tora", "Torborg", "Tova", "Tove", "Tuva", "Tyra",
                    "Ulla", "Ulrica", "Ulrika",
                    "Vanja", "Vendela", "Vilhelmina", "Viveka", "Vivi",
                    "Ylva",
            }

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
