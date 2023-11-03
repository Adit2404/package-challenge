package com.mobiquity.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.mobiquity.model.Package;
import java.util.List;
import java.util.Objects;
import com.mobiquity.knapscak.*;

@Service
public class WeightCalculationImpl implements WeightCalculation {

    private static final Logger logger = LoggerFactory.getLogger(WeightCalculationImpl.class);

    // Instance of the KnapSack algorithm implementation
    private final KnapSack knapSack = KnapSack.getInstance();

    @Override
    public String calculateWeightAndReturnPackage(List<Package> packages) {
        if (Objects.isNull(packages) || packages.isEmpty()) {
            logger.error("Packages received as empty...");
            return "";  // Return an empty string if packages list is empty
        }

        // Use the KnapSack algorithm to calculate the optimal package and return its calculation as a string
        return knapSack.calculate(packages)
                .getCalculationsAsString();
    }
}
