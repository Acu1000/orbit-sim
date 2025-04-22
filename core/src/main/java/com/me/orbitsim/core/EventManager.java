package com.me.orbitsim.core;

import com.badlogic.gdx.utils.Array;

public class EventManager {

    static EventManager instance = new EventManager();

    private EventManager() {}

    /*public static EventManager getInstance() {
        return instance;
    }*/

    private final Array<IRenderListener> renderListeners = new Array<>();

    public static void addRenderListener(IRenderListener listener) {
        instance.renderListeners.add(listener);
    }

    public static void onRender(double dt) {
        for (IRenderListener listener : instance.renderListeners) {
            listener.onRender(dt);
        }
    }


    private final Array<IResizeListener> resizeListeners = new Array<>();

    public static void addResizeListener(IResizeListener listener) {
        instance.resizeListeners.add(listener);
    }

    public static void onResize(int width, int height) {
        for (IResizeListener listener : instance.resizeListeners) {
            listener.onResize(width, height);
        }
    }

}
