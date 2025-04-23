package com.me.orbitsim.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.me.orbitsim.core.EventManager;
import com.me.orbitsim.core.IRenderListener;
import com.me.orbitsim.core.IResizeListener;
import com.me.orbitsim.core.InputManager;
import com.me.orbitsim.math.Vector2;

public class CameraHandler extends InputAdapter implements IRenderListener, IResizeListener {
    private final OrthographicCamera camera = new OrthographicCamera();

    public Vector2 position = new Vector2();
    public double zoom = 1.0;
    public double zoomSensitivity = 0.05;
    public double maxZoom = 2.0;
    public double minZoom = 0.25;

    public CameraHandler() {
        EventManager.addRenderListener(this);
        EventManager.addResizeListener(this);
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0,0,0);
        InputManager.getInstance().addProcessor(this);
    }

    @Override
    public void onRender(double dt) {
        camera.position.set((float) position.x, (float) position.y, 0);
        camera.update();
    }

    @Override
    public void onResize(int width, int height) {
        camera.setToOrtho(true, width, height);
    }

    private int lastMouseX = Gdx.input.getX();
    private int lastMouseY = Gdx.input.getY();

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastMouseX = screenX;
        lastMouseY = screenY;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 delta = new Vector2(screenX - lastMouseX, screenY - lastMouseY);
        position = position.subtract(delta.multiply(zoom));
        lastMouseX = screenX;
        lastMouseY = screenY;
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        zoom += amountY * zoomSensitivity;
        zoom = Math.min(Math.max(minZoom, zoom), maxZoom);
        camera.zoom = (float) zoom;
        return true;
    }

    public com.badlogic.gdx.math.Matrix4 getMatrix() {
        return camera.combined;
    }

}
