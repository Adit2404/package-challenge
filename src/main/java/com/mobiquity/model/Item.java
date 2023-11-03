package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents an item with an index, weight, and cost.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public final class Item {
    private final int index;      // Unique identifier for the item
    private final double weight;  // Weight of the item
    private final double cost;    // Cost or value of the item
}
