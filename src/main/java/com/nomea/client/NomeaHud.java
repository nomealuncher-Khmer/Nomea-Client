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

            // Detect mouse clicks
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

            // Remove clicks older than one second
            removeOldClicks(leftClicks, now);
            removeOldClicks(rightClicks, now);

            int lmb = leftClicks.size();
            int rmb = rightClicks.size();

            // CPS Panel - Bottom Right
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            int x = width - 115;
            int y = height - 75;

            // Glass Background
            context.fill(
                x, y, x + 105, y + 65,
                BLACK
            );

            // Neon Border
            context.fill(x, y, x + 105, y + 2, RED);
            context.fill(x, y, x + 2, y + 65, RED);
            context.fill(x, y + 63, x + 105, y + 65, 0xFF8B0000);

            // Glass Highlight
            context.fill(
                x + 3, y + 4,
                x + 102, y + 5,
                0x55FFFFFF
            );

            // Title
            context.drawText(
                client.textRenderer,
                Text.literal("NOMEA CPS"),
                x + 10, y + 8,
                RED,
                true
            );

            // LMB
            context.drawText(
                client.textRenderer,
                Text.literal("LMB: " + lmb),
                x + 10, y + 25,
                WHITE,
                true
            );

            // RMB
            context.drawText(
                client.textRenderer,
                Text.literal("RMB: " + rmb),
                x + 10, y + 42,
                0xFFFF5555,
                true
            );
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
}
