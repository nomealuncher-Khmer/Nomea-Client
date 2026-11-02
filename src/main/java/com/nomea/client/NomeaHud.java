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

            // Mouse Click Detection
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
            wasRightPressed = right
