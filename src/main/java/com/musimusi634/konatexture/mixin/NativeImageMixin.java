package com.musimusi634.konatexture.mixin;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.util.FastColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.Random;

@Mixin(value = NativeImage.class)
public class NativeImageMixin {
    @Inject(method = "read", at = @At("RETURN"))
    private static void readInject(@Nullable InputStream inputstream, CallbackInfoReturnable<NativeImage> cir){
        Random random = new Random();
        NativeImage image = cir.getReturnValue();
        for (int y = 0; y < image.getHeight(); y++){
            for (int x = 0; x < image.getWidth(); x++){
                int imageColor = image.getPixelRGBA(x, y);
                int konaColor = FastColor.ABGR32.color(
                        FastColor.ABGR32.alpha(imageColor),
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)
                );
                image.setPixelRGBA(x, y, konaColor);
            }
        }
    }
}