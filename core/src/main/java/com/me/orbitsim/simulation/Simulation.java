package com.me.orbitsim.simulation;

import com.badlogic.gdx.utils.Array;
import com.me.orbitsim.core.IRenderListener;

public final class Simulation implements IRenderListener {

    public static final double GRAVITY_CONSTANT = 0.000000000066743;

    private static final Simulation INSTANCE = new Simulation();

    private Simulation() {};

    public static Simulation getInstance() {
        return INSTANCE;
    }

    private final Array<ISimulationObject> simulationObjects = new Array<>();
    private final Array<IGravitySource> gravitySources = new Array<>();

    public double timeScale = 2.0;
    public double timeStep = 1.0 / 60.0;
    public double soften = 1e-3;

    private double accumulatedDeltaTime = 0.0;
    private double totalTime = 0.0;

    public void step() {
        double dt = timeStep * timeScale;
        totalTime += dt;

        for (ISimulationObject simulationObject : simulationObjects) {
            simulationObject.simulationStep(dt);
        }
    }

    public void onRender(double dt) {
        accumulatedDeltaTime += dt;

        while (accumulatedDeltaTime >= timeStep) {
            step();
            accumulatedDeltaTime -= timeStep;
        }
    }

    public void addSimulationObject(ISimulationObject simulationObject) {
        simulationObjects.add(simulationObject);

        if (simulationObject instanceof IGravitySource) {
            gravitySources.add((IGravitySource) simulationObject);
        }
    }

    public Array<IGravitySource> getGravitySources() {
        return gravitySources;
    }

}
