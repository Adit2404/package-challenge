package com.mobiquity.constant;

/**
 * Constants class defining various delimiters, patterns, and limitations for the Packer application.
 */
public final class PackerConstant {
    // Delimiters used for splitting the input string
    public static final String WEIGHT_SPLIT_DELIMITER = ":";
    public static final String PRODUCT_SPLIT_DELIMITER = ",";
    public static final String PRODUCTS_SPLIT_PATTERN = "[()]";
    public static final String NEW_LINE = "\n";

    // Constants for package and item limits
    public static final double PACKAGE_WEIGHT_LIMIT = 100; // Maximum weight limit for a package
    public static final int ITEMS_LIMIT = 15; // Maximum number of items to choose from

    // Constants related to product attributes
    public static final int MAX_PRODUCT_ATTR = 3; // Maximum number of attributes for a product
    public static final int WEIGHT_PRODUCT_SPLIT_LEN = 2; // Length of weight and cost attributes for a product

    // Private constructor to prevent instantiation of the class
    private PackerConstant() {
        // Empty private constructor to prevent instantiation of this class.
    }
}
