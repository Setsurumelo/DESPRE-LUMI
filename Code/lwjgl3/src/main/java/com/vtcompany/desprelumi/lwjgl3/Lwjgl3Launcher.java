package com.vtcompany.desprelumi.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.vtcompany.desprelumi.Main;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Main(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Despre Lumi");
        configuration.useVsync(true);
        configuration.setResizable(false);
        configuration.setForegroundFPS(60);
        configuration.setWindowedMode(1024, 720);
        configuration.setWindowIcon("LogoJuego128.png", "LogoJuego64.png", "LogoJuego32.png", "LogoJuego16.png");
        return configuration;
    }
}