package net.dairycultist.nomorewandering;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.block.TemplateOreBlock;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import static net.minecraft.block.Block.STONE_SOUND_GROUP;

public class NoMoreWandering {

    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

    public static Block COPPER_ORE;
//    public static Block COPPER_BLOCK;
    public static Item COPPER_INGOT;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {

        COPPER_ORE = new TemplateOreBlock(NAMESPACE.id("copper_ore"), 0)
                .setHardness(3.0F)
                .setResistance(5.0F)
                .setSoundGroup(STONE_SOUND_GROUP)
                .setTranslationKey(NAMESPACE, "copper_ore");
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event) {

        COPPER_INGOT = new TemplateItem(NAMESPACE.id("copper_ingot"))
                .setTranslationKey(NAMESPACE, "copper_ingot");
    }
}
