package net.dairycultist.nomorewandering.mixin;

import net.dairycultist.nomorewandering.CherryTreeFeature;
import net.minecraft.world.biome.ForestBiome;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ForestBiome.class)
public class MForestBiome {

    @Inject(method = "getRandomTreeFeature", at = @At(value = "HEAD"), cancellable = true)
    public void getRandomTreeFeature(Random random, CallbackInfoReturnable<Feature> cir) {

        if (random.nextInt(3) == 0)
            cir.setReturnValue(new CherryTreeFeature());
    }
}
