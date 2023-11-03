package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Package;
import com.mobiquity.service.Parser;
import com.mobiquity.service.Reader;
import com.mobiquity.service.WeightCalculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * The Packer class is responsible for processing the packages and selecting items according to weight limits.
 */
@Component
public class Packer {

  private static final Logger logger = LoggerFactory.getLogger(Packer.class);


    private static  Parser parser;
    private static  Reader reader;
    private static  WeightCalculation weightCalculation;

    @Autowired
    public  void setParser(Parser parser) {
        Packer.parser = parser;
    }

    @Autowired
    public  void setReader(Reader reader) {
        Packer.reader = reader;
    }

    @Autowired
    public  void setWeightCalculation(WeightCalculation weightCalculation) {
        Packer.weightCalculation = weightCalculation;
    }

    /**
   * Processes the input file to pack items into packages according to weight limits and returns the result.
   *
   * @param filePath The path to the input file containing package details.
   * @return A string representing the selected items for each package based on weight constraints.
   * @throws APIException If incorrect parameters are passed or API errors occur.
   * @throws IOException  If an I/O error occurs during file reading.
   */
  public static String pack(String filePath) throws APIException, IOException {
    logger.debug("File path received: {}", filePath);

    // Parse the data into Package objects
    List<Package> packages = parser.parseData(reader.readData(filePath));
    logger.debug("Parsed package: {}", packages);

    // Calculate the weights and return the package selection
    return weightCalculation.calculateWeightAndReturnPackage(packages);
  }
}
