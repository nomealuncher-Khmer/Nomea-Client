package com.nomea.client;

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
        int width = this.width;
        int height = this.height;

        // Dark Glass Background
        context.fill(
            0, 0, width, height,
            0xCC050505
        );

        // Premium Main Panel
        int left = width / 2 - 130;
        int top = height / 2 - 95;
        int right = width / 2 + 130;
        int bottom = height / 2 + 95;

        context.fill(
            left, top, right, bottom,
            0xEE101010
        );

        // Glass Highlight
        context.fill(
            left + 2, top + 2,
            right - 2, top + 3,
            0x66FFFFFF
        );

        // Red Neon Border
        context.fill(left, top, right, top + 2, 0xFFFF1010);
        context.fill(left, top, left + 2, bottom, 0xFFFF1010);
        context.fill(left, bottom - 2, right, bottom, 0xFF8B0000);
        context.fill(right - 2, top, right, bottom, 0xFF8B0000);

        // Logo
        context.drawCenteredText(
            this.textRenderer,
            Text.literal("✦ NOMEA CLIENT ✦"),
            width / 2,
            top + 18,
            0xFFFF2020
        );

        // Subtitle
        context.drawCenteredText(
            this.textRenderer,
            Text.literal("XHD PREMIUM EDITION"),
            width / 2,
            top + 35,
            0xFFFFFFFF
        );

        // Separator
        context.fill(
            left + 20, top + 55,
            right - 20, top + 56,
            0xFFFF2020
        );

        // Information
        context.drawText(
            this.textRenderer,
            Text.literal("PREMIUM GLASS INTERFACE"),
            left + 25,
            top + 75,
            0xFFFF5555,
            true
        );

        context.drawText(
            this.textRenderer,
            Text.literal("BLACK & RED THEME"),
            left + 25,
            top + 95,
            0xFFFFFFFF,
            true
        );

        context.drawText(
            this.textRenderer,
            Text.literal("FPS: " + client.getCurrentFps()),
            left + 25,
            top + 115,
            0xFFFF2020,
            true
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
          }
