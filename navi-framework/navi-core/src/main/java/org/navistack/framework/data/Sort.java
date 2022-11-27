package org.navistack.framework.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sort {
    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private Collection<Order> orders;

    private static final Sort UNSORTED = Sort.by(new Order[0]);

    public static Sort by(Order... orders) {
        return new Sort(Arrays.asList(orders));
    }

    public static Sort by(Direction direction, String... properties) {
        List<Order> orders = Arrays.stream(properties).map(p -> new Order(direction, p))
                .collect(Collectors.toList());

        return new Sort(orders);
    }

    public static Sort by(String... properties) {
        return by(DEFAULT_DIRECTION, properties);
    }

    public static Sort unsorted() {
        return UNSORTED;
    }

    @Data
    @AllArgsConstructor
    public static class Order {
        Direction direction;
        String property;

        public boolean isAscending() {
            return this.direction.isAscending();
        }

        public boolean isDescending() {
            return this.direction.isDescending();
        }
    }

    public enum Direction {
        ASC,
        DESC;

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }
    }
}
