package com.github.Dewynion.embercore.util.geometry;

import org.bukkit.util.Vector;

public class EulerAngles {
    public static EulerAngles zero = new EulerAngles(0f, 0f, 0f);
    public static EulerAngles right = new EulerAngles(0f, 90f, 0f);
    public static EulerAngles left = new EulerAngles(0f, -90f, 0f);
    public static EulerAngles up = new EulerAngles(90f, 0f, 0f);
    public static EulerAngles down = new EulerAngles(-90f, 0f, 0f);
    public static EulerAngles backward = new EulerAngles(0f, 180f, 0f);
    public static EulerAngles upsideDown = new EulerAngles(180f, 0f, 0f);

    // Or: pitch, yaw, roll
    protected float x, y, z;

    /**
     * Stores a 3-angle representation of a rotation. Uses degrees.
     */
    public EulerAngles(float x, float y, float z) {
        this.x = (float) GeometryUtil.clampAngle(x);
        this.y = (float) GeometryUtil.clampAngle(y);
        this.z = (float) GeometryUtil.clampAngle(z);
    }

    public float getX() {
        return x;
    }

    /**
     * Pitch is another way of referring to rotation about the x axis, such as a plane's nose tilting up and down.
     * Returns the same thing as {@link #getX()}.
     */
    public float getPitch() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Yaw is another way of referring to rotation about the y axis, such as the spin of a compass needle.
     * Returns the same thing as {@link #getY()}.
     */
    public float getYaw() {
        return y;
    }

    public float getZ() {
        return z;
    }

    /**
     * Roll is another way of referring to rotation about the z axis, such as a plane rolling side to side.
     * Returns the same thing as {@link #getZ()}.
     */
    public float getRoll() {
        return z;
    }

    public EulerAngles setAngle(float x, float y, float z) {
        this.x = (float) GeometryUtil.clampAngle(x);
        this.y = (float) GeometryUtil.clampAngle(y);
        this.z = (float) GeometryUtil.clampAngle(z);
        return this;
    }

    public EulerAngles add(EulerAngles angles) {
        return setAngle(this.x + angles.x, this.y + angles.y, this.z + angles.z);
    }

    public EulerAngles subtract(EulerAngles angles) {
        return setAngle(this.x - angles.x, this.y - angles.y, this.z - angles.z);
    }

    /**
     * Constructs a three-dimensional rotation, in degrees of rotation about the X, Y and Z axes,
     * from a direction vector. Optional roll parameter for rotation about the Z axis.
     */
    public static EulerAngles fromDirectionVector(Vector direction, float roll) {
        // important
        direction = direction.normalize();
        // minecraft uses the Y axis in a Vector to determine vertical orientation
        float pitch = (float) Math.toDegrees(Math.asin(direction.getY()));
        // heading or yaw is calculated from the two horizontal components
        float yaw = (float) Math.toDegrees(Math.atan2(direction.getZ(), direction.getX()));
        return new EulerAngles(pitch, yaw, roll);
    }

    /**
     * Shortcut for {@link #fromDirectionVector(Vector, float)} where roll = 0f.
     */
    public static EulerAngles fromDirectionVector(Vector direction) {
        return fromDirectionVector(direction, 0f);
    }

    /**
     * Returns a (normalized) direction vector based on this rotation.
     */
    public Vector toDirectionVector() {
        // pitch = angle up and down
        float y = (float) Math.sin(Math.toRadians(getPitch()));
        // yaw = angle on the horizontal plane
        // if z is the vertical axis on this plane and x is the horizontal,
        float x = (float) Math.cos(Math.toRadians(getYaw()));
        float z = (float) Math.sin(Math.toRadians(getYaw()));
        return new Vector(x, y, z).normalize();
    }

    public EulerAngles toRadians() {
        return new EulerAngles((float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z));
    }
}
