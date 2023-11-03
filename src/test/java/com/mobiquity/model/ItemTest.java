package com.mobiquity.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ItemTest {

    @Test
    public void shouldCreateItemWithValidAttributes() {
        // Given
        int testIndex = 1;
        double testWeight = 1.0;
        double testCost = 1.0;

        // When
        Item item = new Item(testIndex, testWeight, testCost);

        // Then
        Assertions.assertThat(item).isNotNull();
        Assertions.assertThat(item.getIndex()).isEqualTo(testIndex);
        Assertions.assertThat(item.getWeight()).isEqualTo(testWeight);
        Assertions.assertThat(item.getCost()).isEqualTo(testCost);
    }
}
