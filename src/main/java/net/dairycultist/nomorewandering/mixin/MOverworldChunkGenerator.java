package net.dairycultist.nomorewandering.mixin;

import net.dairycultist.nomorewandering.NoMoreWandering;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import net.minecraft.world.gen.feature.OreFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(OverworldChunkGenerator.class)
public class MOverworldChunkGenerator {

    @Shadow private World world;
    @Shadow private Random random;

    @Inject(method = "decorate", at = @At(value = "TAIL"))
    public void decorate(ChunkSource source, int chunkX, int chunkZ, CallbackInfo ci) {

        int chunkBlockX = chunkX * 16;
        int chunkBlockZ = chunkZ * 16;

        // copper ore
        for(int i = 0; i < 16; i++) {

            new OreFeature(NoMoreWandering.COPPER_ORE.id, 7).generate(this.world, this.random,
                    chunkBlockX + this.random.nextInt(16),
                    this.random.nextInt(24) + 48,
                    chunkBlockZ + this.random.nextInt(16)
            );
        }
    }

    @ModifyArg(method = "decorate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 35), index = 0)
    private int diamondHeight(int in) {
        return 32;
    }

    @ModifyArg(method = "decorate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 26), index = 0)
    private int ironHeight(int in) {
        return 40;
    }

    @ModifyArg(method = "decorate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 32), index = 0)
    private int redstoneHeight(int in) {
        return 32;
    }
}
