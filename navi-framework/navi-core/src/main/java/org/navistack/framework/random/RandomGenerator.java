package org.navistack.framework.random;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public interface RandomGenerator {
    DoubleStream doubles();

    DoubleStream doubles(double randomNumberOrigin, double randomNumberBound);

    DoubleStream doubles(long streamSize);

    DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound);

    IntStream ints();

    IntStream ints(int randomNumberOrigin, int randomNumberBound);

    IntStream ints(long streamSize);

    IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound);

    LongStream longs();

    LongStream longs(long randomNumberOrigin, long randomNumberBound);

    LongStream longs(long streamSize);

    LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound);

    boolean nextBoolean();

    void nextBytes(byte[] bytes);

    float nextFloat();

    double nextDouble();

    int nextInt();

    int nextInt(int bound);

    long nextLong();
}
