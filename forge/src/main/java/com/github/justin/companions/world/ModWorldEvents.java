//package com.github.justin.companions.world;
//
//import com.github.justin.companions.Companions;
//import com.github.justin.companions.core.EntityInit;
//import com.github.justin.companions.entity.ArcherEntity;
//import com.github.justin.companions.entity.KnightEntity;
//import net.minecraft.world.level.biome.MobSpawnSettings;
//import net.minecraftforge.event.entity.EntityJoinWorldEvent;
//import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
//import net.minecraftforge.eventbus.api.EventPriority;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(modid = Companions.MOD_ID)
//public class ModWorldEvents {
//
//    @SubscribeEvent(priority = EventPriority.HIGH)
//    public static void structureSpawn(final StructureSpawnListGatherEvent event) {
//        if (event.getStructure().getFeatureName().equals("village")) {
//            event.addEntitySpawn(
//                    EntityInit.KnightEntity.get().getCategory(),
//                    new MobSpawnSettings.SpawnerData(EntityInit.KnightEntity.get(), 100, 1, 1)
//            );
//            event.addEntitySpawn(
//                    EntityInit.ArcherEntity.get().getCategory(),
//                    new MobSpawnSettings.SpawnerData(EntityInit.ArcherEntity.get(), 100, 1, 1)
//            );
//        }
//    }
//
//    @SubscribeEvent
//    public static void companionJoin(final EntityJoinWorldEvent event) {
//        if (event.getEntity() instanceof KnightEntity) {
//            System.out.println("knight spawn");
//        } else if (event.getEntity() instanceof ArcherEntity) {
//            System.out.println("archer spawn");
//        }
//    }
//}
