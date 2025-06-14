package net.dairycultist.copper_and_caverns.mixin;

import net.minecraft.world.gen.Generator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Random;

@Mixin(Generator.class)
public interface AGenerator {

    @Accessor("random")
    Random getRandom();
}
