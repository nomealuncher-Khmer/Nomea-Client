package com.nomea.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class NomeaHud {

    private static final int BLACK_GLASS = 0xCC080808;
    private static final int RED = 0xFFFF1010;
    private static final int DARK_RED = 0xFF650000;
    private static final int WHITE = 0xFFFFFFFF;

    public static void register() {

        HudRenderCallback.EVENT.register((context, tickCounter) -> {

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) {
                return;
            }

            // Premium Glass Panel
            context.fill(7, 7, 158, 59, BLACK_GLASS);

            // Glass Highlight
            context.fill(9, 9, 156, 10, 0x44FFFFFF);

            // Red Neon Border
            context.fill(7, 7, 158, 8, RED);
            context.fill(7, 7, 8, 59, RED);
            context.fill(7, 58, 158, 59, DARK_RED);

            // Red Aura Accent
            context.fill(9, 11, 11, 55, 0xAAFF2020);

            // Premium Logo
            drawGlowText(
                context,
                client,
                "NOMEA CLIENT",
                17, 15
            );

            // Separator
            context.fill(17, 29, 148, 30, 0x88FF2020);

            // FPS Counter
            context.drawText(
                client.textRenderer,
                Text.literal("FPS"),
                17, 38,
                RED,
                true
            );

            context.drawText(
                client.textRenderer,
                Text.literal(String.valueOf(client.getCurrentFps())),
                48, 38,
                WHITE,
                true
            );

            // Premium Edition
            context.drawText(
                client.textRenderer,
                Text.literal("XHD PREMIUM"),
                85, 38,
                0xFFFF5555,
                true
            );
        });
    }

    private static void drawGlowText(
        net.minecraft.client.gui.DrawContext context,
        MinecraftClient client,
        String message,
        int x,
        int y
    ) {

        Text text = Text.literal(message);

        // Neon Aura
        context.drawText(
            client.textRenderer, text,
            x - 1, y, 0x66FF0000, false
        );

        context.drawText(
            client.textRenderer, text,
            x + 1, y, 0x66FF0000, false
        );

        context.drawText(
            client.textRenderer, text,
            x, y - 1, 0x88FF0000, false
        );

        context.drawText(
            client.textRenderer, text,
            x, y + 1, 0x88FF0000, false
        );

        // Bright Red Text
        context.drawText(
            client.textRenderer, text,
            x, y, RED, true
        );
    }
}
