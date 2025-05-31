package net.dairycultist.nomorewandering.mixin;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.dimension.Dimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Dimension.class)
public class MDimension {

    @Shadow
    public BiomeSource biomeSource;

    /**
     * @author dairycultist
     * @reason FUCK you
     */
    @Overwrite
    public void initBiomeSource() {

        biomeSource = new FixedBiomeSource(Biome.SHRUBLAND, 0.6F, 0.3F);
    }
}
