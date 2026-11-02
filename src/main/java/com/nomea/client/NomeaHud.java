package com.nomea.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayDeque;

public class NomeaHud {

    private static final int RED = 0xFFFF2020;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xDD080808;

    private static final ArrayDeque<Long> leftClicks = new ArrayDeque<>();
    private static final ArrayDeque<Long> rightClicks = new ArrayDeque<>();

    private static boolean wasLeftPressed = false;
    private static boolean wasRightPressed = false;

    public static void register() {

        HudRenderCallback.EVENT.register((context, tickCounter) -> {

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null) {
                return;
            }

            long now = System.currentTimeMillis();
            long window = client.getWindow().getHandle();

            boolean leftPressed =
                GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_LEFT)
                == GLFW.GLFW_PRESS;

            boolean rightPressed =
                GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_RIGHT)
                == GLFW.GLFW_PRESS;

            if (leftPressed && !wasLeftPressed) {
                leftClicks.addLast(now);
            }

            if (rightPressed && !wasRightPressed) {
                rightClicks.addLast(now);
            }

            wasLeftPressed = leftPressed;
            wasRightPressed = rightPressed;

            removeOldClicks(leftClicks, now);
            removeOldClicks(rightClicks, now);

            int lmb = leftClicks.size();
            int rmb = rightClicks.size();

            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();

            // Coordinates
            int posX = (int) Math.floor(client.player.getX());
            int posY = (int) Math.floor(client.player.getY());
            int posZ = (int) Math.floor(client.player.getZ());

            // Main HUD
            context.fill(7, 7, 175, 103, BLACK);
            context.fill(7, 7, 175, 9, RED);
            context.fill(7, 7, 9, 103, RED);
            context.fill(7, 101, 175, 103, 0xFF8B0000);

            context.drawText(client.textRenderer,
                Text.literal("NOMEA CLIENT"), 17, 15, RED, true);

            context.drawText(client.textRenderer,
                Text.literal("FPS: " + client.getCurrentFps()),
                17, 32, WHITE, true);

            context.drawText(client.textRenderer,
                Text.literal("X: " + posX + "  Y: " + posY),
                17, 51, WHITE, true);

            context.drawText(client.textRenderer,
                Text.literal("Z: " + posZ),
                17, 69, 0xFFFF5555, true);

            context.drawText(client.textRenderer,
                Text.literal("XHD PREMIUM"), 17, 85, RED, true);

            // Keystrokes
            int keyX = 15;
            int keyY = screenHeight - 85;

            context.fill(keyX - 5, keyY - 5,
                keyX + 100, keyY + 80, BLACK);

            context.fill(keyX - 5, keyY - 5,
                keyX + 100, keyY - 3, RED);

            drawKey(context, client, "W",
                keyX + 30, keyY,
                client.options.forwardKey.isPressed());

            drawKey(context, client, "A",
                keyX, keyY + 30,
                client.options.leftKey.isPressed());

            drawKey(context, client, "S",
                keyX + 30, keyY + 30,
                client.options.backKey.isPressed());

            drawKey(context, client, "D",
                keyX + 60, keyY + 30,
                client.options.rightKey.isPressed());

            drawKey(context, client, "SPACE",
                keyX, keyY + 60,
                client.options.jumpKey.isPressed(), 90);

            // CPS Panel
            int cpsX = screenWidth - 115;
            int cpsY = screenHeight - 75;

            context.fill(cpsX, cpsY,
                cpsX + 105, cpsY + 65, BLACK);

            context.fill(cpsX, cpsY,
                cpsX + 105, cpsY + 2, RED);

            context.fill(cpsX, cpsY,
                cpsX + 2, cpsY + 65, RED);

            context.fill(cpsX, cpsY + 63,
                cpsX + 105, cpsY + 65, 0xFF8B0000);

            context.drawText(client.textRenderer,
                Text.literal("NOMEA CPS"),
                cpsX + 10, cpsY + 8, RED, true);

            context.drawText(client.textRenderer,
                Text.literal("LMB: " + lmb),
                cpsX + 10, cpsY + 25, WHITE, true);

            context.drawText(client.textRenderer,
                Text.literal("RMB: " + rmb),
                cpsX + 10, cpsY + 42, 0xFFFF5555, true);
        });
    }

    private static void removeOldClicks(
        ArrayDeque<Long> clicks,
        long now
    ) {
        while (!clicks.isEmpty()
            && now - clicks.peekFirst() >= 1000) {
            clicks.removeFirst();
        }
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
        int background = pressed ? 0xFFFF2020 : 0xEE101010;
        int border = pressed ? 0xFFFF7777 : 0xFF8B0000;

        context.fill(x, y, x + width, y + 25, background);
        context.fill(x, y, x + width, y + 2, border);
        context.fill(x, y + 23, x + width, y + 25, border);

        int textWidth = client.textRenderer.getWidth(label);

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
