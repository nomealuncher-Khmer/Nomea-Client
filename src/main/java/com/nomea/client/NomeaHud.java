package com.nomea.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class NomeaHud {

    private static final int RED = 0xFFFF2020;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xDD080808;

    public static void register() {

        HudRenderCallback.EVENT.register((context, tickCounter) -> {

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) {
                return;
            }

            // Premium HUD
            context.fill(7, 7, 158, 59, BLACK);
            context.fill(7, 7, 158, 8, RED);
            context.fill(7, 7, 8, 59, RED);
            context.fill(7, 58, 158, 59, 0xFF8B0000);

            context.drawText(
                client.textRenderer,
                Text.literal("NOMEA CLIENT"),
                17, 15, RED, true
            );

            context.drawText(
                client.textRenderer,
                Text.literal("FPS: " + client.getCurrentFps()),
                17, 38, WHITE, true
            );

            // Keystrokes HUD
            int x = 15;
            int y = client.getWindow().getScaledHeight() - 85;

            context.fill(
                x - 5, y - 5,
                x + 100, y + 80,
                BLACK
            );

            context.fill(
                x - 5, y - 5,
                x + 100, y - 3,
                RED
            );

            // W
            drawKey(
                context, client, "W",
                x + 30, y,
                client.options.forwardKey.isPressed()
            );

            // A
            drawKey(
                context, client, "A",
                x, y + 30,
                client.options.leftKey.isPressed()
            );

            // S
            drawKey(
                context, client, "S",
                x + 30, y + 30,
                client.options.backKey.isPressed()
            );

            // D
            drawKey(
                context, client, "D",
                x + 60, y + 30,
                client.options.rightKey.isPressed()
            );

            // SPACE
            drawKey(
                context, client, "SPACE",
                x, y + 60,
                client.options.jumpKey.isPressed(),
                90
            );
        });
    }

    private static void drawKey(
        DrawContext context,
        MinecraftClient client,
        String label,
        int x,
        int y,
        boolean pressed
    ) {
        drawKey(context, client, label, x, y, pressed, 27);
    }

    private static void drawKey(
        DrawContext context,
        MinecraftClient client,
        String label,
        int x,
        int y,
        boolean pressed,
        int width
    ) {

        int background = pressed
            ? 0xFFFF2020
            : 0xEE101010;

        int border = pressed
            ? 0xFFFF7777
            : 0xFF8B0000;

        context.fill(
            x, y, x + width, y + 25,
            background
        );

        context.fill(
            x, y, x + width, y + 2,
            border
        );

        context.fill(
            x, y + 23, x + width, y + 25,
            border
        );

        int textWidth =
            client.textRenderer.getWidth(label);

        context.drawText(
            client.textRenderer,
            Text.literal(label),
            x + (width - textWidth) / 2,
            y + 8,
            WHITE,
            true
        );
    }
    }
