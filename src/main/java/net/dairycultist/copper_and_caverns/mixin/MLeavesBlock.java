package net.dairycultist.copper_and_caverns.mixin;

import net.dairycultist.copper_and_caverns.CopperAndCavernsClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.LeavesBlock;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public class MLeavesBlock {

    @Unique private static boolean isFancy = false;

    // meta 3 is cherry

    @Inject(method = "getTexture", at = @At(value = "HEAD"), cancellable = true)
    public void getTexture(int side, int meta, CallbackInfoReturnable<Integer> cir) {

        if ((meta & 3) == 3)
            cir.setReturnValue(isFancy ? CopperAndCavernsClient.CHERRY_LEAVES_FANCY : CopperAndCavernsClient.CHERRY_LEAVES);
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "getColor", at = @At(value = "HEAD"), cancellable = true)
    public void getColor(int meta, CallbackInfoReturnable<Integer> cir) {

        if ((meta & 3) == 3)
            cir.setReturnValue(-1);
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "getColorMultiplier", at = @At(value = "HEAD"), cancellable = true)
    public void getColorMultiplier(BlockView blockView, int x, int y, int z, CallbackInfoReturnable<Integer> cir) {

        if ((blockView.getBlockMeta(x, y, z) & 3) == 3)
            cir.setReturnValue(-1);
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "setFancyGraphics", at = @At(value = "HEAD"))
    public void setFancyGraphics(boolean isFancy, CallbackInfo ci) {

        MLeavesBlock.isFancy = isFancy;
    }
}
