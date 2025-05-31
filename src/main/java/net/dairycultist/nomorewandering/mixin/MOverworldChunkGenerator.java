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

    @Unique private static final double CAVERN_PILLAR_FREQUENCY = 0.35;
    @Unique private static final double CAVERN_HEIGHT_MULT = 8.0;
    @Unique private static final int CAVERN_MIDPOINT_Y = 36;

    @Shadow private OctavePerlinNoiseSampler perlinNoise2;
    @Shadow private OctavePerlinNoiseSampler perlinNoise3;

    @Inject(method = "buildTerrain", at = @At(value = "HEAD"), cancellable = true)
    public void buildTerrain(int chunkX, int chunkZ, byte[] blocks, Biome[] biomes, double[] temperatures, CallbackInfo ci) {

        int i = 0;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                final double sample2 = perlinNoise2.sample((chunkX * 16 + x) / 6.0, (chunkZ * 16 + z) / 6.0) + 64;
                final double sample3 = (Math.pow(Math.abs(perlinNoise3.sample((chunkX * 16 + x) / 10.0, (chunkZ * 16 + z) / 10.0)), 0.5) - CAVERN_PILLAR_FREQUENCY) * CAVERN_HEIGHT_MULT;

                for (int y = 0; y < 128; y++) {

                    byte block = 0;

                    // sea level
                    if (y < 64)
                        block = (byte) Block.WATER.id;

                    // surface terrain
                    if (sample2 - y > 0)
                        block = (byte) Block.STONE.id;

                    // carve out caverns based on what the height of the cavern should be at each (x,z)
                    if (Math.abs(CAVERN_MIDPOINT_Y - y) < sample3)
                        block = 0;

                    blocks[i++] = block;
                }
            }
        }

        ci.cancel();
    }
}
