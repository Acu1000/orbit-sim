package com.me.orbitsim.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.me.orbitsim.rendering.IRenderable;
import com.me.orbitsim.rendering.Renderer;
import com.me.orbitsim.simulation.CelestialBody;

public class SimpleCelestialBody extends CelestialBody implements IRenderable {

    protected Color color = Color.WHITE;

    @Override
    public void render(Renderer renderer) {
        ShapeRenderer shapeRenderer = renderer.getShapeRenderer();
        shapeRenderer.setColor(color);
        shapeRenderer.circle((float)position.x, (float)position.y, (float)radius);
    }

    public SimpleCelestialBody() {}

    public SimpleCelestialBody setColor(Color color) {
        this.color = color;
        return this;
    }
}
