package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Represents the calculation result for a package, containing its ID and selected items.
 */
@AllArgsConstructor
@EqualsAndHashCode
public class PackageCalculation {
    @Getter
    private final String id; // Identifier for the package calculation
    private final List<Item> items; // List of selected items for the package

    /**
     * Generates a string representation of selected item indices for this package calculation.
     * If no items are present, it returns "-".
     *
     * @return A string of selected item indices, or "-" if no items are present.
     */
    @Override
    public String toString() {
        if (Objects.isNull(items) || items.isEmpty()) {
            return "-";
        }
        // Construct a string with selected item indices
        StringJoiner sj = new StringJoiner(",");
        items.forEach(item -> sj.add(String.valueOf(item.getIndex())));
        return sj.toString();
    }
}
