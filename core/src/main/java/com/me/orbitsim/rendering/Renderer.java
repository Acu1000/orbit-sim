package com.me.orbitsim.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.me.orbitsim.core.IRenderListener;

public class Renderer implements IRenderListener {

    private static final Renderer INSTANCE = new Renderer();

    private Renderer() {
        System.out.println("AAAAA");
    }

    public static Renderer getInstance() {
        return INSTANCE;
    }

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final CameraHandler camera = new CameraHandler();

    private final Array<IRenderable> renderables = new Array<>();


    public void onRender(double dt) {

        camera.onRender(dt);

        shapeRenderer.setProjectionMatrix(camera.getMatrix());
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
