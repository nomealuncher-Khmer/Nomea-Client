package com.nomea.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.lwjgl.glfw.GLFW;

public class NomeaClient implements ClientModInitializer {

    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {

        System.out.println("NOMEA CLIENT LOADED!");

        NomeaHud.register();

        openMenuKey = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.nomeaclient.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyBinding.Category.MISC
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (openMenuKey.wasPressed()) {

                if (client.currentScreen == null) {
                    client.setScreen(new NomeaMenu());
                } else if (client.currentScreen instanceof NomeaMenu) {
                    client.setScreen(null);
                }
            }
        });

        System.out.println("NOMEA MENU ACTIVATED!");
    }
}
