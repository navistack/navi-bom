package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Maths {
    public long clamp(long val, long min, long max) {
        if (val < min) {
            return min;
        } else if (val > max) {
            return max;
        } else {
            return val;
        }
    }

    public int clamp(int val, int min, int max) {
        if (val < min) {
            return min;
        } else if (val > max) {
            return max;
        } else {
            return val;
        }
    }

    public short clamp(short val, short min, short max) {
        if (val < min) {
            return min;
        } else if (val > max) {
            return max;
        } else {
            return val;
        }
    }
}
