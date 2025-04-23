package com.me.orbitsim.core;

import com.badlogic.gdx.*;
import com.me.orbitsim.simulation.Simulation;

public class InputManager extends InputMultiplexer {

    private static InputManager instance = new InputManager();

    private InputManager() {
        Gdx.input.setInputProcessor(this);
    }

    public static InputManager getInstance() {
        return instance;
    }

    private final Simulation simulation = Simulation.getInstance();

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            simulation.timeScale *= 0.5;
            return true;
        }
        else if (keycode == Input.Keys.RIGHT) {
            simulation.timeScale *= 2.0;
            return true;
        }
        return false;
    }
}
