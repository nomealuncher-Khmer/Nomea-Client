package com.nomea.client;

import net.fabricmc.api.ClientModInitializer;

public class NomeaClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("NOMEA CLIENT LOADED!");
        System.out.println("Welcome to Nomea Client!");
    }
}
