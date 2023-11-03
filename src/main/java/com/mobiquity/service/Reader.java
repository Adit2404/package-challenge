package com.mobiquity.service;

import com.mobiquity.exception.APIException;

import java.io.IOException;
import java.util.List;

/**
 * A functional interface responsible for reading data from a file.
 */
@FunctionalInterface
public interface Reader {

    /**
     * Reads data from the specified file path.
     *
     * @param filePath The path to the file containing the data.
     * @return A list of strings representing the data read from the file.
     * @throws IOException   If an I/O error occurs while reading the file.
     * @throws APIException  If an API error occurs during the file reading process.
     */
    List<String> readData(String filePath) throws IOException, APIException;
}
