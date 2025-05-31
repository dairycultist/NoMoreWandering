package net.dairycultist.nomorewandering.mixin;

import net.minecraft.block.Block;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OverworldChunkGenerator.class)
public class MOverworldChunkGenerator {

    @Shadow
    private OctavePerlinNoiseSampler perlinNoise2;

    /**
     * @author dairycultist
     * @reason FUCK you
     */
    @Inject(method = "buildTerrain", at = @At(value = "HEAD"), cancellable = true)
    public void buildTerrain(int chunkX, int chunkZ, byte[] blocks, Biome[] biomes, double[] temperatures, CallbackInfo ci) {

        int i = 0;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {

                    byte block = 0;

                    if (y < 64)
                        block = (byte) Block.WATER.id;

                    if (perlinNoise2.sample((chunkX * 16 + x) / 6.0, (chunkZ * 16 + z) / 6.0) + 64 - y > 0)
                        block = (byte) Block.STONE.id;

                    blocks[i++] = block;
                }
            }
        }

        ci.cancel();
    }
}
