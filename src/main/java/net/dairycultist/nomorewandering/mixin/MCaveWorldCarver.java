package net.dairycultist.nomorewandering.mixin;

import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CaveWorldCarver.class)
public class MCaveWorldCarver {

    @Unique private static final double CAVERN_PILLAR_FREQUENCY = 1.2;
    @Unique private static final double CAVERN_HALF_MAX_HEIGHT = 16.0;
    @Unique private static final int CAVERN_MIDPOINT_Y = 36;

    @Unique private OctavePerlinNoiseSampler perlin;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void init(CallbackInfo ci) {

        perlin = new OctavePerlinNoiseSampler(((AGenerator) this).getRandom(), 4);
    }

    @Inject(method = "place(Lnet/minecraft/world/World;IIII[B)V", at = @At(value = "HEAD"))
    protected void place(World world, int startChunkX, int startChunkZ, int chunkX, int chunkZ, byte[] blocks, CallbackInfo ci) {

        int i = 0;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                final double sample = perlin.sample((chunkX * 16 + x) / 10.0, (chunkZ * 16 + z) / 10.0);
                final double cavernHeight = CAVERN_HALF_MAX_HEIGHT - CAVERN_HALF_MAX_HEIGHT / Math.max(1, Math.abs(sample) - CAVERN_PILLAR_FREQUENCY + 1);

                for (int y = 0; y < 128; y++) {

                    // carve out caverns
                    if (Math.abs(CAVERN_MIDPOINT_Y - y) < cavernHeight)
                        blocks[i] = 0;

                    i++;
                }
            }
        }
    }
}
