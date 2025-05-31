package net.dairycultist.nomorewandering.mixin;

import net.dairycultist.nomorewandering.NoMoreWanderingClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public class MLeavesBlock {

    // meta 3 is cherry

    @Inject(method = "getTexture", at = @At(value = "HEAD"), cancellable = true)
    public void getTexture(int side, int meta, CallbackInfoReturnable<Integer> cir) {

        if ((meta & 3) == 3)
            cir.setReturnValue(NoMoreWanderingClient.CHERRY_LEAVES);
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
}
