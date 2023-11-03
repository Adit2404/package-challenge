package com.mobiquity.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class PackageCalculationTest {

    @Test
    public void shouldCreatePackageCalculationWithValidAttributes() {
        // Given
        String testId = "1";
        List<Item> testItems = List.of(new Item(1, 1.0, 1.0));

        // When
        PackageCalculation packageCalculation = new PackageCalculation(testId, testItems);

        // Then
        Assertions.assertThat(packageCalculation).isNotNull();
        Assertions.assertThat(packageCalculation.getId()).isEqualTo(testId);
        // Assert additional conditions if needed for the items or further validations
    }
}
