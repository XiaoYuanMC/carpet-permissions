package world.incest.carpetpermissions.mixin;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import carpet.utils.CommandHelper;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(CommandHelper.class)
public class CommandHelperMixin {
    @Inject(method = "canUseCommand", at = @At("HEAD"), cancellable = true)
    private static void canUseCommand(CommandSourceStack source, Object commandLevel, CallbackInfoReturnable<Boolean> cir) {
        String permissionString = Thread.currentThread().getStackTrace()[3].getClassName();

        if(source.isPlayer())
            cir.setReturnValue(Permissions.check(Objects.requireNonNull(source.getPlayer()), permissionString));
    }
}
