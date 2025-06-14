package net.dairycultist.copper_and_caverns;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.util.Identifier;

public class CopperAndCavernsClient {

    public static int CHERRY_LOG_END;
    public static int CHERRY_LOG_SIDE;
    public static int CHERRY_LEAVES;
    public static int CHERRY_LEAVES_FANCY;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {

        CHERRY_LOG_END = Atlases.getTerrain().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "block/cherry_log_end")).index;
        CHERRY_LOG_SIDE = Atlases.getTerrain().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "block/cherry_log_side")).index;
        CHERRY_LEAVES = Atlases.getTerrain().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "block/cherry_leaves")).index;
        CHERRY_LEAVES_FANCY = Atlases.getTerrain().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "block/cherry_leaves_fancy")).index;

        Item.STONE_SWORD.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_sword")).index
        );

        Item.STONE_PICKAXE.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_pickaxe")).index
        );

        Item.STONE_AXE.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_axe")).index
        );

        Item.STONE_SHOVEL.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_shovel")).index
        );

        Item.STONE_HOE.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_hoe")).index
        );

        Item.CHAIN_HELMET.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_helmet")).index
        );

        Item.CHAIN_CHESTPLATE.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_chestplate")).index
        );

        Item.CHAIN_LEGGINGS.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_leggings")).index
        );

        Item.CHAIN_BOOTS.setTextureId(
                Atlases.getGuiItems().addTexture(Identifier.of(CopperAndCaverns.NAMESPACE, "item/copper_boots")).index
        );
    }
}
