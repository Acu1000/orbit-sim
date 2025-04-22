package com.me.orbitsim.simulation;

import com.me.orbitsim.math.Vector2;

public class CelestialBody implements IGravitySource, ISimulationObject {

    protected Simulation simulation = Simulation.getInstance();

    protected String name;
    protected Vector2 position = new Vector2();
    protected Vector2 velocity = new Vector2();
    protected double mass = 0.0;
    protected double radius = 0.0;
    protected boolean fixed = false;


    @Override
    public void simulationStep(double dt) {
        for (IGravitySource gravitySource : simulation.getGravitySources()) {
            if (gravitySource.equals(this)) {
                continue;
            }
            double dist = position.distance(gravitySource.getPosition());

            double force = mass
                * gravitySource.getMass()
                * dist
                * Simulation.GRAVITY_CONSTANT
                / Math.pow(dist*dist + simulation.soften * simulation.soften, 1.5);

            Vector2 dv = gravitySource.getPosition().subtract(position).set_magnitude(force * dt / mass);
            velocity = velocity.add(dv);
        }
        position = position.add(velocity.multiply(dt));
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }


    public CelestialBody() {}

    public CelestialBody setName(String name) {
        this.name = name;
        return this;
    }

    public CelestialBody setPosition(Vector2 position) {
        this.position = position;
        return this;
    }

    public CelestialBody setPosition(double x, double y) {
        this.position = new Vector2(x, y);
        return this;
    }

    public CelestialBody setVelocity(Vector2 velocity) {
        this.velocity = velocity;
        return this;
    }

    public CelestialBody setVelocity(double x, double y) {
        this.velocity = new Vector2(x, y);
        return this;
    }

    public CelestialBody setMass(double mass) {
        this.mass = mass;
        return this;
    }

    public CelestialBody setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public CelestialBody setFixed(boolean fixed) {
        this.fixed = fixed;
        return this;
    }
}
