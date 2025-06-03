package net.dairycultist.nomorewandering.mixin;

import net.dairycultist.nomorewandering.NoMoreWandering;
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

        this.items[0][1] = NoMoreWandering.COPPER_INGOT;
    }
}
