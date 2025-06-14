package net.dairycultist.copper_and_caverns.mixin;

import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerEntityRenderer.class)
public class MPlayerEntityRenderer {

    @ModifyArg(
            method = "bindTexture(Lnet/minecraft/entity/player/PlayerEntity;IF)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;bindTexture(Ljava/lang/String;)V"),
            index = 0
    )
    protected String redirectChainmailToCopper(String path) {

        if (path.equals("/armor/chain_1.png"))
            return "/assets/copper_and_caverns/stationapi/textures/entity/copper_1.png";

        if (path.equals("/armor/chain_2.png"))
            return "/assets/copper_and_caverns/stationapi/textures/entity/copper_2.png";

        return path;
    }
}
