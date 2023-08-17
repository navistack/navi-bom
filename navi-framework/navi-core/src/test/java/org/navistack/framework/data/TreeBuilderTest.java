package org.navistack.framework.data;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TreeBuilderTest {
    private final Collection<GeoDivision> ORIGINAL_COLLECTION = Arrays.asList(
            // Asia
            GeoDivision.of("CN-11", "Beijing", "Municipality", "CN"),
            GeoDivision.of("CN-12", "Tianjin", "Municipality", "CN"),
            GeoDivision.of("CN-31", "Shanghai", "Municipality", "CN"),
            GeoDivision.of("CN-50", "Chongqing", "Municipality", "CN"),
            GeoDivision.of("CN", "China", "Country/Region", "AS"),
            GeoDivision.of("AS", "Asia", "Continent"),
            // Europe
            GeoDivision.of("FR", "France", "Country/Region", "EU"),
            GeoDivision.of("RU", "Russia", "Country/Region", "EU"),
            GeoDivision.of("GB", "United Kingdom", "Country/Region", "EU"),
            GeoDivision.of("EU", "Europe", "Continent"),
            // North America
            GeoDivision.of("US", "United States", "Country/Region", "NA"),
            GeoDivision.of("NA", "North America", "Continent")
    );

    private final Collection<HierarchicalGeoDivision> EXPECTED_COLLECTION = Arrays.asList(
            HierarchicalGeoDivision.of(
                    "AS",
                    "Asia",
                    "Continent",
                    Arrays.asList(
                            HierarchicalGeoDivision.of(
                                    "CN",
                                    "China",
                                    "Country/Region",
                                    "AS",
                                    Arrays.asList(
                                            HierarchicalGeoDivision.of("CN-11", "Beijing", "Municipality", "CN"),
                                            HierarchicalGeoDivision.of("CN-12", "Tianjin", "Municipality", "CN"),
                                            HierarchicalGeoDivision.of("CN-31", "Shanghai", "Municipality", "CN"),
                                            HierarchicalGeoDivision.of("CN-50", "Chongqing", "Municipality", "CN")
                                    )
                            )
                    )
            ),
            HierarchicalGeoDivision.of(
                    "EU",
                    "Europe",
                    "Continent",
                    Arrays.asList(
                            HierarchicalGeoDivision.of("FR", "France", "Country/Region", "EU"),
                            HierarchicalGeoDivision.of("RU", "Russia", "Country/Region", "EU"),
                            HierarchicalGeoDivision.of("GB", "United Kingdom", "Country/Region", "EU")
                    )
            ),
            HierarchicalGeoDivision.of(
                    "NA",
                    "North America",
                    "Continent",
                    Arrays.asList(
                            HierarchicalGeoDivision.of("US", "United States", "Country/Region", "NA")
                    )
            )
    );

    @Test
    void shouldBuildSuccessfully() {
        Collection<HierarchicalGeoDivision> actualCollection = new TreeBuilder<GeoDivision, HierarchicalGeoDivision>()
                .idMapper(GeoDivision::getId)
                .parentIdMapper(GeoDivision::getParentId)
                .mapper(HierarchicalGeoDivision::of)
                .childPicker(HierarchicalGeoDivision::addChild)
                .build(ORIGINAL_COLLECTION);
        assertThat(actualCollection)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(EXPECTED_COLLECTION);
    }

    @Test
    void shouldCollectSuccessfully() {
        Collector<GeoDivision, ?, Collection<HierarchicalGeoDivision>> collector = new TreeBuilder<GeoDivision, HierarchicalGeoDivision>()
                .idMapper(GeoDivision::getId)
                .parentIdMapper(GeoDivision::getParentId)
                .mapper(HierarchicalGeoDivision::of)
                .childPicker(HierarchicalGeoDivision::addChild)
                .toCollector();
        Collection<HierarchicalGeoDivision> actualCollection = ORIGINAL_COLLECTION.stream()
                .collect(collector);
        assertThat(actualCollection)
                .usingRecursiveComparison()
                .ignoringCollectionOrder()
                .isEqualTo(EXPECTED_COLLECTION);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenCollectionIsNull() {
        assertThatThrownBy(() -> TreeBuilder.of().build(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldReturnEmptyCollectionWhenCollectionIsEmpty() {
        assertThat(TreeBuilder.of().build(Collections.emptyList()))
                .isNotNull()
                .isEmpty();
    }

    @Test
    void shouldThrowNullPointerExceptionWhenPropertyNotSet() {
        List<HierarchicalGeoDivision> continents = Arrays.asList(
                HierarchicalGeoDivision.of("AF", "Africa", "Continent"),
                HierarchicalGeoDivision.of("AN", "Antarctica", "Continent"),
                HierarchicalGeoDivision.of("AS", "Asia", "Continent"),
                HierarchicalGeoDivision.of("EU", "Europe", "Continent"),
                HierarchicalGeoDivision.of("NA", "North America", "Continent"),
                HierarchicalGeoDivision.of("OC", "Oceania", "Continent"),
                HierarchicalGeoDivision.of("SA", "South america", "Continent")
        );
        assertThatThrownBy(() -> {
            new TreeBuilder<HierarchicalGeoDivision, HierarchicalGeoDivision>()
                    .parentIdMapper(HierarchicalGeoDivision::getParentId)
                    .childPicker(HierarchicalGeoDivision::addChild)
                    .mapper(Function.identity())
                    .build(continents);
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessage("idMapper must not be null");
        assertThatThrownBy(() -> {
            new TreeBuilder<HierarchicalGeoDivision, HierarchicalGeoDivision>()
                    .idMapper(HierarchicalGeoDivision::getId)
                    .childPicker(HierarchicalGeoDivision::addChild)
                    .mapper(Function.identity())
                    .build(continents);
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessage("parentIdMapper must not be null");
        assertThatThrownBy(() -> {
            new TreeBuilder<HierarchicalGeoDivision, HierarchicalGeoDivision>()
                    .idMapper(HierarchicalGeoDivision::getId)
                    .parentIdMapper(HierarchicalGeoDivision::getParentId)
                    .childPicker(HierarchicalGeoDivision::addChild)
                    .build(continents);
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessage("mapper must not be null");
        assertThatThrownBy(() -> {
            new TreeBuilder<HierarchicalGeoDivision, HierarchicalGeoDivision>()
                    .idMapper(HierarchicalGeoDivision::getId)
                    .parentIdMapper(HierarchicalGeoDivision::getParentId)
                    .mapper(Function.identity())
                    .build(continents);
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessage("childPicker must not be null");
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenDuplicateKeyDetected() {
        List<HierarchicalGeoDivision> continents = Arrays.asList(
                HierarchicalGeoDivision.of("AS", "Asia", "Continent"),
                HierarchicalGeoDivision.of("CN", "China", "Country/Region", "AS"),
                HierarchicalGeoDivision.of("AS", "Asia", "Continent"),
                HierarchicalGeoDivision.of("CN", "China", "Country/Region", "AS")
        );
        assertThatThrownBy(() -> continents.stream().collect(TreeBuilder.collector()))
                .isInstanceOf(IllegalStateException.class);
    }
}
