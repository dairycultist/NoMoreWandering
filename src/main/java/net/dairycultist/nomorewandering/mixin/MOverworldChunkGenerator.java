package net.dairycultist.nomorewandering.mixin;

import net.minecraft.block.Block;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OverworldChunkGenerator.class)
public class MOverworldChunkGenerator {

    @Unique private static final double CAVERN_PILLAR_FREQUENCY = 0.15;
    @Unique private static final double CAVERN_HALF_MAX_HEIGHT = 16.0;
    @Unique private static final int CAVERN_MIDPOINT_Y = 36;

    @Shadow private OctavePerlinNoiseSampler perlinNoise2;
    @Shadow private OctavePerlinNoiseSampler perlinNoise3;

    @Inject(method = "buildTerrain", at = @At(value = "HEAD"), cancellable = true)
    public void buildTerrain(int chunkX, int chunkZ, byte[] blocks, Biome[] biomes, double[] temperatures, CallbackInfo ci) {

        int i = 0;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                final double sample2 = perlinNoise2.sample((chunkX * 16 + x) / 6.0, (chunkZ * 16 + z) / 6.0);
                final double sample3 = perlinNoise3.sample((chunkX * 16 + x) / 6.0, (chunkZ * 16 + z) / 6.0);
                final double cavernHeight = CAVERN_HALF_MAX_HEIGHT - CAVERN_HALF_MAX_HEIGHT / Math.max(1, Math.abs(sample3) - CAVERN_PILLAR_FREQUENCY + 1);

                for (int y = 0; y < 128; y++) {

                    byte block = 0;

                    // sea level
                    if (y < 64)
                        block = (byte) Block.WATER.id;

                    // surface terrain
                    if (y - sample2 < 64)
                        block = (byte) Block.STONE.id;

                    // carve out caverns
                    if (Math.abs(CAVERN_MIDPOINT_Y - y) < cavernHeight)
                        block = 0;

                    blocks[i++] = block;
                }
            }
        }

        ci.cancel();
    }
}
