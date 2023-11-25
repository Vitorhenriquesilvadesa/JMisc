package org.vtko.cgi.core;

import org.vtko.cgi.engine.config.EngineConfig;

public class Main {

    public static void main(String[] args) {

        EngineConfig.init("src/org/vtko/cgi/engine/config");
        MainGameLoop loop = new MainGameLoop();
        loop.start();
    }
}
