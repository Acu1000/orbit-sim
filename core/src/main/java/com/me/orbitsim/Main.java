package com.me.orbitsim;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import com.me.orbitsim.entities.SimpleCelestialBody;
import com.me.orbitsim.rendering.Renderer;
import com.me.orbitsim.simulation.CelestialBody;
import com.me.orbitsim.simulation.Simulation;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private Simulation simulation;
    private Renderer renderer;

    @Override
    public void create() {
        simulation = Simulation.getInstance();
        renderer = Renderer.getInstance();

        SimpleCelestialBody planet1 = (SimpleCelestialBody) new SimpleCelestialBody()
            .setColor(Color.ORANGE)
            .setName("Planet 1")
            .setMass(10)
            .setRadius(30)
            .setPosition(0, 0);
        simulation.addSimulationObject(planet1);
        renderer.addRenderable(planet1);

        SimpleCelestialBody planet2 = (SimpleCelestialBody) new SimpleCelestialBody()
            .setColor(Color.YELLOW)
            .setName("Planet 2")
            .setMass(10)
            .setRadius(50)
            .setPosition(100, 0);
        simulation.addSimulationObject(planet2);
        renderer.addRenderable(planet2);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);

        double dt = Gdx.graphics.getDeltaTime();
        simulation.onRender(dt);

        renderer.render(dt);

    }
}
