package net.adamcowell14.freefly;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.adamcowell14.freefly.Freefly;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.util.TypedActionResult;

import java.awt.*;
import java.util.Objects;

public class FreeflyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        UseItemCallback.EVENT.register((net.minecraft.entity.player.PlayerEntity player, net.minecraft.world.World world, net.minecraft.util.Hand hand) -> {

            if(Freefly.running) {
                String item = player.getInventory().getMainHandStack().getItem().toString();
                if (Objects.equals(item, "minecraft:trident")) {
                    world.setRainGradient(0.5F);
                }
            }
            return TypedActionResult.pass(ItemStack.EMPTY);
        });
    }
}
