package com.mobiquity.service;

import java.util.List;

import com.mobiquity.exception.ParserException;
import com.mobiquity.model.Package;

/**
 * A functional interface responsible for parsing input data into a list of Package objects.
 */
@FunctionalInterface
public interface Parser {

    /**
     * Parses a list of strings into a list of Package objects.
     *
     * @param data A list of strings containing data to be parsed.
     * @return A list of Package objects parsed from the provided data.
     */
    List<Package> parseData(List<String> data) throws ParserException;
}
