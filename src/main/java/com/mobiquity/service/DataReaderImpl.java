package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DataReaderImpl implements Reader {

    private static final Logger logger = LoggerFactory.getLogger(DataReaderImpl.class);

    @Override
    public List<String> readData(String filePath) throws IOException, APIException {
        if (Objects.isNull(filePath) || filePath.isBlank()) {
            logger.error("File path is empty/null");
            throw new APIException("File path is empty/null..");
        }

        // List to hold read data from the file
        List<String> results = new ArrayList<>();

        // Read file using BufferedReader to fetch each line of data
        File file = new File(filePath);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                results.add(line);
            }
        }

        // Logging and returning the read data as a list
        logger.debug("File data as list: {}", results);
        return results;
    }
}
