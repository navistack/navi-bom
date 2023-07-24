package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Maths {
    public long clamp(long val, long min, long max) {
        return Math.min(Math.max(val, min), max);
    }

    public int clamp(int val, int min, int max) {
        return Math.min(Math.max(val, min), max);
    }

    public float clamp(float val, float min, float max) {
        return Math.min(Math.max(val, min), max);
    }

    public double clamp(double val, double min, double max) {
        return Math.min(Math.max(val, min), max);
    }

    public int ceilDiv(int x, int y) {
        int r = x / y;
        if ((x ^ y) >= 0 && (r * y != x)) {
            r++;
        }
        return r;
    }

    public long ceilDiv(long x, int y) {
        return ceilDiv(x, (long)y);
    }

    public long ceilDiv(long x, long y) {
        long r = x / y;
        if ((x ^ y) >= 0 && (r * y != x)) {
            r++;
        }
        return r;
    }
}
