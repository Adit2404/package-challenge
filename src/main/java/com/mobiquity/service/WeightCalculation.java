package com.mobiquity.service;

import com.mobiquity.model.Package;
import java.util.List;

/**
 * A functional interface responsible for calculating weights and returning a string representation of selected packages.
 */
@FunctionalInterface
public interface WeightCalculation {

    /**
     * Calculates the weight limits for a list of packages and returns a string representation of the selected packages.
     *
     * @param packages A list of Package objects representing different packages with items.
     * @return A string representing the selected items for each package based on weight constraints.
     */
    String calculateWeightAndReturnPackage(List<Package> packages);
}
