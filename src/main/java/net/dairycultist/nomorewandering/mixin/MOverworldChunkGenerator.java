package net.dairycultist.nomorewandering.mixin;

import net.dairycultist.nomorewandering.NoMoreWandering;
import net.minecraft.block.Block;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
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
    @Shadow private OctavePerlinNoiseSampler perlinNoise2;

    @Inject(method = "buildTerrain", at = @At(value = "HEAD"), cancellable = true)
    public void buildTerrain(int chunkX, int chunkZ, byte[] blocks, Biome[] biomes, double[] temperatures, CallbackInfo ci) {

        int i = 0;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                final double height = perlinNoise2.sample((chunkX * 16 + x) / 6.0, (chunkZ * 16 + z) / 6.0);

                for (int y = 0; y < 128; y++) {

                    byte block = 0;

                    // sea level
                    if (y < 64)
                        block = (byte) Block.WATER.id;

                    // surface terrain
                    if (y - height < 64)
                        block = (byte) Block.STONE.id;

                    blocks[i++] = block;
                }
            }
        }

        ci.cancel();
    }

    @Inject(method = "decorate", at = @At(value = "TAIL"))
    public void decorate(ChunkSource source, int chunkX, int chunkZ, CallbackInfo ci) {

        // trees
        int chunkBlockX = chunkX * 16;
        int chunkBlockZ = chunkZ * 16;

        Biome biome = this.world.method_1781().getBiome(chunkBlockX + 16, chunkBlockZ + 16);

        for (int i = 0; i < 3; i++) {

            int x = chunkBlockX + this.random.nextInt(16) + 8;
            int z = chunkBlockZ + this.random.nextInt(16) + 8;

            Feature tree = biome.getRandomTreeFeature(this.random);
            tree.prepare(1.0, 1.0, 1.0);
            tree.generate(this.world, this.random, x, this.world.getTopY(x, z), z);
        }

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
        return 48;
    }

    @ModifyArg(method = "decorate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 32), index = 0)
    private int redstoneHeight(int in) {
        return 32;
    }
}
