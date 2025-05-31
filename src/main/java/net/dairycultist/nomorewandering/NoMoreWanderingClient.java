package net.dairycultist.nomorewandering;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.util.Identifier;

public class NoMoreWanderingClient {

    public static int CHERRY_LOG_END;
    public static int CHERRY_LOG_SIDE;
    public static int CHERRY_LEAVES;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {

        CHERRY_LOG_END = Atlases.getTerrain().addTexture(Identifier.of(NoMoreWandering.NAMESPACE, "block/cherry_log_end")).index;
        CHERRY_LOG_SIDE = Atlases.getTerrain().addTexture(Identifier.of(NoMoreWandering.NAMESPACE, "block/cherry_log_side")).index;
        CHERRY_LEAVES = Atlases.getTerrain().addTexture(Identifier.of(NoMoreWandering.NAMESPACE, "block/cherry_leaves")).index;

        Item.STONE_SWORD.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(NoMoreWandering.NAMESPACE, "item/copper_sword")).index
        );

        Item.STONE_PICKAXE.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(NoMoreWandering.NAMESPACE, "item/copper_pickaxe")).index
        );

        Item.STONE_AXE.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(NoMoreWandering.NAMESPACE, "item/copper_axe")).index
        );

        Item.STONE_SHOVEL.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(NoMoreWandering.NAMESPACE, "item/copper_shovel")).index
        );

        Item.STONE_HOE.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(NoMoreWandering.NAMESPACE, "item/copper_hoe")).index
        );
    }
}
