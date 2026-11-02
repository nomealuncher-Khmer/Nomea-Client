package com.nomea.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class NomeaMenu extends Screen {

    public NomeaMenu() {
        super(Text.literal("NOMEA CLIENT"));
    }

    @Override
    public void render(
        DrawContext context,
        int mouseX,
        int mouseY,
        float delta
    ) {
        MinecraftClient client = MinecraftClient.getInstance();

        int left = width / 2 - 130;
        int top = height / 2 - 95;
        int right = width / 2 + 130;
        int bottom = height / 2 + 95;

        // Dark Background
        context.fill(0, 0, width, height, 0x99000000);

        // Black Glass Panel
        context.fill(left, top, right, bottom, 0xEE101010);

        // Glass Highlight
        context.fill(
            left + 2, top + 2,
            right - 2, top + 3,
            0x55FFFFFF
        );

        // Red Neon Border
        context.fill(left, top, right, top + 2, 0xFFFF1010);
        context.fill(left, top, left + 2, bottom, 0xFFFF1010);
        context.fill(left, bottom - 2, right, bottom, 0xFF8B0000);
        context.fill(right - 2, top, right, bottom, 0xFF8B0000);

        // Premium Logo
        drawGlow(context, "NOMEA CLIENT", width / 2, top + 20);

        drawCentered(
            context,
            "XHD PREMIUM GLASS",
            width / 2,
            top + 42,
            0xFFFFFFFF
        );

        // Separator
        context.fill(
            left + 20, top + 62,
            right - 20, top + 63,
            0xFFFF2020
        );

        // Information
        context.drawText(
            textRenderer,
            Text.literal("BLACK & RED THEME"),
            left + 25, top + 85,
            0xFFFF5555,
            true
        );

        context.drawText(
            textRenderer,
            Text.literal("FPS: " + client.getCurrentFps()),
            left + 25, top + 110,
            0xFFFFFFFF,
            true
        );

        drawCentered(
            context,
            "RIGHT SHIFT TO CLOSE",
            width / 2,
            bottom - 20,
            0xFFFF5555
        );

        super.render(context, mouseX, mouseY, delta);
    }

    private void drawCentered(
        DrawContext context,
        String message,
        int centerX,
        int y,
        int color
    ) {
        Text text = Text.literal(message);

        int x = centerX - textRenderer.getWidth(text) / 2;

        context.drawText(
            textRenderer,
            text,
            x, y,
            color,
            true
        );
    }

    private void drawGlow(
        DrawContext context,
        String message,
        int centerX,
        int y
    ) {
        Text text = Text.literal(message);

        int x = centerX - textRenderer.getWidth(text) / 2;

        // Red Aura
        context.drawText(textRenderer, text, x - 1, y, 0x66FF0000, false);
        context.drawText(textRenderer, text, x + 1, y, 0x66FF0000, false);
        context.drawText(textRenderer, text, x, y - 1, 0x88FF0000, false);
        context.drawText(textRenderer, text, x, y + 1, 0x88FF0000, false);

        // Bright Red Text
        context.drawText(
            textRenderer,
            text,
            x, y,
            0xFFFF2020,
            true
        );
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
