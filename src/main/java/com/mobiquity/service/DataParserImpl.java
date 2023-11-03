package com.mobiquity.service;

import com.mobiquity.exception.ParserException;
import com.mobiquity.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.mobiquity.model.Package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.mobiquity.constant.PackerConstant.*;

@Service
public class DataParserImpl implements Parser {

    private static final Logger logger = LoggerFactory.getLogger(DataParserImpl.class);

    @Override
    public List<Package> parseData(List<String> data) throws ParserException {
        if (Objects.isNull(data) || data.isEmpty()) {
            logger.error("File data is empty...");
            return new ArrayList<>();
        }

        // Parse each line of data to create a list of Package objects
        return data.stream()
                .map(this::parsePackage)
                .collect(Collectors.toList());
    }

    private Package parsePackage(String data) {
        // Split data into weight and products
        String[] weightToProducts = data.split(WEIGHT_SPLIT_DELIMITER);
        if (weightToProducts.length != WEIGHT_PRODUCT_SPLIT_LEN) {
            logger.error("Line must contain exactly one `:` but {}", data);
            throw new ParserException("Line must contain exactly one `:`");
        }
        try {
            double weight = Double.parseDouble(weightToProducts[0]);

            // Process products and create a list of Item objects
            String[] rawProducts = weightToProducts[1].split(PRODUCTS_SPLIT_PATTERN);
            List<Item> items = Arrays.stream(rawProducts)
                    .map(this::parseProduct)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Check for product limit, truncate if necessary
            if (items.size() > ITEMS_LIMIT) {
                logger.debug("Products limit is exceeding from {}, getting sublist from 0 to {}", ITEMS_LIMIT, ITEMS_LIMIT);
                items = items.subList(0, ITEMS_LIMIT);
            }

            return new Package(weight, items);
        } catch (NumberFormatException e) {
            logger.error("Invalid format defined in file", e);
            throw new ParserException("Invalid format for weight", e);
        }
    }

    private Item parseProduct(String product) {
        if (product.isBlank()) {
            return null;
        }

        // Split product attributes and create Item objects
        String[] productSplit = product.split(PRODUCT_SPLIT_DELIMITER);
        if (productSplit.length > MAX_PRODUCT_ATTR) {
            logger.error("Products are more than expected: {} products but expectation is {}", productSplit.length, MAX_PRODUCT_ATTR);
            throw new ParserException("Invalid format for product, have missing attributes");
        }
        try {
            int index = Integer.parseInt(productSplit[0]);
            double weight = Double.parseDouble(productSplit[1]);
            double cost = Double.parseDouble(productSplit[2].substring(1));
            if (weight > PACKAGE_WEIGHT_LIMIT) {
                logger.error("Weight is more than defined limit {}", PACKAGE_WEIGHT_LIMIT);
                return null;
            }

            return new Item(index, weight, cost);
        } catch (NumberFormatException e) {
            logger.error("Invalid format defined in file", e);
            throw new ParserException("Invalid format for product attribute", e);
        }
    }
}
