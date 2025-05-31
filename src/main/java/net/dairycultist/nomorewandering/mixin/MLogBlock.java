package net.dairycultist.nomorewandering.mixin;

import net.dairycultist.nomorewandering.NoMoreWanderingClient;
import net.minecraft.block.LogBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LogBlock.class)
public class MLogBlock {

    // meta 3 is cherry

    @Inject(method = "getTexture", at = @At(value = "HEAD"), cancellable = true)
    public void getTexture(int side, int meta, CallbackInfoReturnable<Integer> cir) {

        if (meta == 3)
            cir.setReturnValue(side == 0 || side == 1 ? NoMoreWanderingClient.CHERRY_LOG_END : NoMoreWanderingClient.CHERRY_LOG_SIDE);
    }
}
