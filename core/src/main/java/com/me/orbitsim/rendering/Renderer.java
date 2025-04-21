package com.me.orbitsim.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class Renderer {

    private static final Renderer INSTANCE = new Renderer();

    private Renderer() {
        System.out.println("AAAAA");
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0, 0, 0);
    }

    public static Renderer getInstance() {
        return INSTANCE;
    }

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final OrthographicCamera camera = new OrthographicCamera();

    private final Array<IRenderable> renderables = new Array<>();


    public void render(double dt) {

        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (IRenderable renderable : renderables) {
            renderable.render(this);
        }

        shapeRenderer.end();
    }

    public void addRenderable(IRenderable renderable) {
        renderables.add(renderable);
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }
}
