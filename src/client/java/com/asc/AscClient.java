package com.asc;

import net.fabricmc.api.ClientModInitializer;

import com.asc.AscKeybinds;

public class AscClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AscKeybinds.RegisterKeybinds();
	}
}