package com.nomea.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class NomeaHud {

    public static void register() {

        HudRenderCallback.EVENT.register((context, tickCounter) -> {

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) {
                return;
            }

            int red = 0xFFFF2020;
            int white = 0xFFFFFFFF;

            context.drawText(
                client.textRenderer,
                Text.literal("✦ NOMEA CLIENT ✦"),
                10, 10, red, true
            );

            context.drawText(
                client.textRenderer,
                Text.literal("FPS: " + client.getCurrentFps()),
                10, 25, white, true
            );
        });
    }
}
