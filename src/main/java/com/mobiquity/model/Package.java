package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Represents a package with weight limit and a list of items.
 */
@AllArgsConstructor
@EqualsAndHashCode(exclude = "ID")
public class Package {
    @Getter
    private final double weightLimit; // Maximum weight limit for the package
    @Getter
    private final String ID = UUID.randomUUID().toString(); // Unique identifier for the package
    private final List<Item> items; // List of items contained within the package

    /**
     * Retrieves a copy of the list of items in the package.
     *
     * @return A list of Item objects, or null if no items are present.
     */
    public List<Item> getItems() {
        if (Objects.isNull(items)) {
            return null;
        }
        return items.isEmpty() ? new ArrayList<>() :
                items.stream()
                        .map(p -> new Item(p.getIndex(), p.getWeight(), p.getCost()))
                        .collect(Collectors.toList());
    }
}
