package com.me.orbitsim.simulation;

import com.me.orbitsim.math.Vector2;

public interface IGravitySource {
    public double getMass();
    public Vector2 getPosition();
}
