package com.me.orbitsim.math;

public class Vector2 {

    public final double x;
    public final double y;

    public Vector2() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public double distance_squared(Vector2 vector) {
        double dx = x - vector.x;
        double dy = y - vector.y;
        return dx * dx + dy * dy;
    }

    public double distance(Vector2 vector) {
        double dx = x - vector.x;
        double dy = y - vector.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2 normalize() {
        double mag = magnitude();
        return new Vector2(x / mag, y / mag);
    }

    public Vector2 set_magnitude(double new_magnitude) {
        double scale = new_magnitude / magnitude();
        return new Vector2(x * scale, y * scale);
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(x - other.x, y - other.y);
    }

    public Vector2 multiply(double a) {
        return new Vector2(x * a, y * a);
    }

    public Vector2 divide(double a) {
        return new Vector2(x / a, y / a);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }
}
