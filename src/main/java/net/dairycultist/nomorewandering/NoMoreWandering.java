package net.dairycultist.nomorewandering;

import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class NoMoreWandering {

    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

//    public static Block LAPIS_SANDSTONE;
//
//    @EventListener
//    public void registerBlocks(BlockRegistryEvent event) {
//
//        LAPIS_SANDSTONE = new TemplateSandstoneBlock(NAMESPACE.id("lapis_sandstone"))
//                .setHardness(0.8F)
//                .setSoundGroup(STONE_SOUND_GROUP)
//                .setTranslationKey(NAMESPACE, "lapis_sandstone");
//    }

//    @EventListener
//    public void registerItems(ItemRegistryEvent event) {
//
//        FABRIC = new TemplateItem(NAMESPACE.id("fabric"))
//                .setTranslationKey(NAMESPACE, "fabric");
//    }
}
