package org.navistack.framework.utils;

import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GenericBuilderTest {
    @Test
    void shouldSetPropertiesProperlyWhenProvided() {
        Product product = GenericBuilder.of(Product::new)
                .set(Product::setProductId, "PRODUCT_ID")
                .set(Product::setCategoryId, "CATEGORY_ID")
                .set(Product::setName, "PRODUCT_NAME")
                .set(Product::setDescription, "DESCRIPTION")
                .build();
        Assertions.assertThat(product)
                .extracting(Product::getProductId).isEqualTo("PRODUCT_ID");
        Assertions.assertThat(product)
                .extracting(Product::getCategoryId).isEqualTo("CATEGORY_ID");
        Assertions.assertThat(product)
                .extracting(Product::getName).isEqualTo("PRODUCT_NAME");
        Assertions.assertThat(product)
                .extracting(Product::getDescription).isEqualTo("DESCRIPTION");
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSupplierIsNull() {
        Assertions.assertThatThrownBy(() -> {
            GenericBuilder.of(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSetterIsNull() {
        Assertions.assertThatThrownBy(() -> {
            GenericBuilder.of(Product::new)
                    .set(null, null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenSupplierReturnNull() {
        Assertions.assertThatThrownBy(() -> {
            GenericBuilder.of(() -> null)
                    .build();
        }).isInstanceOf(NullPointerException.class);
    }

    @Data
    public static class Product {
        private String productId;
        private String categoryId;
        private String name;
        private String description;
    }
}
