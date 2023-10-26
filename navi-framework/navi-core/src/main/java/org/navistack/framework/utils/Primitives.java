package org.navistack.framework.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Primitives {
    public boolean eq(Byte left, Byte right) {
        return left != null && left.equals(right);
    }

    public boolean eq(Short left, Short right) {
        return left != null && left.equals(right);
    }

    public boolean eq(Integer left, Integer right) {
        return left != null && left.equals(right);
    }

    public boolean eq(Long left, Long right) {
        return left != null && left.equals(right);
    }

    public boolean eq(Float left, Float right) {
        return left != null && left.equals(right);
    }

    public boolean eq(Double left, Double right) {
        return left != null && left.equals(right);
    }

    public boolean eq(Boolean left, Boolean right) {
        return left != null && left.equals(right);
    }

    public boolean neq(Byte left, Byte right) {
        return left == null || !left.equals(right);
    }

    public boolean neq(Short left, Short right) {
        return left == null || !left.equals(right);
    }

    public boolean neq(Integer left, Integer right) {
        return left == null || !left.equals(right);
    }

    public boolean neq(Long left, Long right) {
        return left == null || !left.equals(right);
    }

    public boolean neq(Float left, Float right) {
        return left == null || !left.equals(right);
    }

    public boolean neq(Double left, Double right) {
        return left == null || !left.equals(right);
    }

    public boolean neq(Boolean left, Boolean right) {
        return left == null || !left.equals(right);
    }

    public boolean gt(Byte left, Byte right) {
        return left != null && right != null && left.compareTo(right) > 0;
    }

    public boolean gt(Short left, Short right) {
        return left != null && right != null && left.compareTo(right) > 0;
    }

    public boolean gt(Integer left, Integer right) {
        return left != null && right != null && left.compareTo(right) > 0;
    }

    public boolean gt(Long left, Long right) {
        return left != null && right != null && left.compareTo(right) > 0;
    }

    public boolean gt(Float left, Float right) {
        return left != null && right != null && left.compareTo(right) > 0;
    }

    public boolean gt(Double left, Double right) {
        return left != null && right != null && left.compareTo(right) > 0;
    }

    public boolean gt(Boolean left, Boolean right) {
        return left != null && right != null && left.compareTo(right) > 0;
    }

    public boolean ge(Byte left, Byte right) {
        return left != null && right != null && left.compareTo(right) >= 0;
    }

    public boolean ge(Short left, Short right) {
        return left != null && right != null && left.compareTo(right) >= 0;
    }

    public boolean ge(Integer left, Integer right) {
        return left != null && right != null && left.compareTo(right) >= 0;
    }

    public boolean ge(Long left, Long right) {
        return left != null && right != null && left.compareTo(right) >= 0;
    }

    public boolean ge(Float left, Float right) {
        return left != null && right != null && left.compareTo(right) >= 0;
    }

    public boolean ge(Double left, Double right) {
        return left != null && right != null && left.compareTo(right) >= 0;
    }

    public boolean ge(Boolean left, Boolean right) {
        return left != null && right != null && left.compareTo(right) >= 0;
    }

    public boolean lt(Byte left, Byte right) {
        return left != null && right != null && left.compareTo(right) < 0;
    }

    public boolean lt(Short left, Short right) {
        return left != null && right != null && left.compareTo(right) < 0;
    }

    public boolean lt(Integer left, Integer right) {
        return left != null && right != null && left.compareTo(right) < 0;
    }

    public boolean lt(Long left, Long right) {
        return left != null && right != null && left.compareTo(right) < 0;
    }

    public boolean lt(Float left, Float right) {
        return left != null && right != null && left.compareTo(right) < 0;
    }

    public boolean lt(Double left, Double right) {
        return left != null && right != null && left.compareTo(right) < 0;
    }

    public boolean lt(Boolean left, Boolean right) {
        return left != null && right != null && left.compareTo(right) < 0;
    }

    public boolean le(Byte left, Byte right) {
        return left != null && right != null && left.compareTo(right) <= 0;
    }

    public boolean le(Short left, Short right) {
        return left != null && right != null && left.compareTo(right) <= 0;
    }

    public boolean le(Integer left, Integer right) {
        return left != null && right != null && left.compareTo(right) <= 0;
    }

    public boolean le(Long left, Long right) {
        return left != null && right != null && left.compareTo(right) <= 0;
    }

    public boolean le(Float left, Float right) {
        return left != null && right != null && left.compareTo(right) <= 0;
    }

    public boolean le(Double left, Double right) {
        return left != null && right != null && left.compareTo(right) <= 0;
    }

    public boolean le(Boolean left, Boolean right) {
        return left != null && right != null && left.compareTo(right) <= 0;
    }
}
