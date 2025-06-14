package net.dairycultist.copper_and_caverns.mixin;

import net.dairycultist.copper_and_caverns.CopperAndCaverns;
import net.minecraft.recipe.ArmorRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorRecipes.class)
public class MArmorRecipes {

    @Shadow
    private Object[][] items;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void registerRecipesMixin(CallbackInfo ci) {

        this.items[0][1] = CopperAndCaverns.COPPER_INGOT;
    }
}
