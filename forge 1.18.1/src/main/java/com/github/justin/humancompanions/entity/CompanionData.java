package com.github.justin.humancompanions.entity;

import com.github.justin.humancompanions.HumanCompanions;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.Hoglin;

import java.util.Random;

public class CompanionData {

    public static Class<?>[] alertMobs = new Class<?>[]{
            Blaze.class,
            CaveSpider.class,
            Drowned.class,
            ElderGuardian.class,
            EnderMan.class,
            Endermite.class,
            Evoker.class,
            Ghast.class,
            Giant.class,
            Guardian.class,
            Hoglin.class,
            Husk.class,
            Illusioner.class,
            MagmaCube.class,
            Phantom.class,
            Pillager.class,
            Shulker.class,
            Silverfish.class,
            Skeleton.class,
            Slime.class,
            Spider.class,
            Stray.class,
            Vex.class,
            Vindicator.class,
            Witch.class,
            WitherSkeleton.class,
            Zoglin.class,
            Zombie.class,
            ZombieVillager.class,
            ZombifiedPiglin.class
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
    };

    public static String getRandomName() {
        Random rand = new Random();
        String firstName = maleFirstNames[rand.nextInt(maleFirstNames.length)];
        String lastName = lastNames[rand.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    public static String getRandomCompanionType() {
        Random rand = new Random();
        int type = rand.nextInt(3);
        String typeString = null;
        switch (type) {
            case 0 -> typeString = "knight";
            case 1 -> typeString = "archer";
            case 2 -> typeString = "arbalist";
        }
        return typeString;
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
