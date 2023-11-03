package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.helper.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import static com.mobiquity.helper.Consts.*;

@SpringBootTest
public class PackerTest {

    @Autowired
    private Packer packer;

    @Test
    public void givenValidInputFile_whenPackInvoked_thenReturnsExpectedOutput() throws APIException, IOException {
        // Given valid input file path, when the 'pack' method is invoked, it should return the expected output
        String actualOutput = packer.pack(INPUT_FILE_PATH);

        // Asserts that the actual output matches the expected output from the input file
        Assertions.assertEquals(DataHelper.expectedInputFileResult(), actualOutput);
    }

    @Test
    public void givenInvalidFilePath_whenPackInvoked_thenThrowsFileNotFoundException() {
        // Given an invalid file path, the 'pack' method invocation should throw FileNotFoundException
        Assertions.assertThrows(FileNotFoundException.class, () ->
                packer.pack(INVALID_FILE_PATH));
    }

    @Test
    public void givenEmptyFilePath_whenPackInvoked_thenThrowsAPIException() {
        // When an empty file path is given, the 'pack' method invocation should throw an APIException
        Assertions.assertThrows(APIException.class, () ->
                packer.pack(EMPTY_FILE_PATH));
    }

    @Test
    public void givenNullFilePath_whenPackInvoked_thenThrowsAPIException() {
        // When a null file path is given, the 'pack' method invocation should throw an APIException
        Assertions.assertThrows(APIException.class, () ->
                packer.pack(null));
    }

    @Test
    public void givenValidEmptyInputFile_whenPackInvoked_thenReturnsEmptyString() throws APIException, IOException {
        // Given a valid but empty input file, the 'pack' method invocation should return an empty string
        String actualOutput = packer.pack(EMPTY_INPUT_FILE_PATH);

        // Asserts that the actual output is an empty string
        Assertions.assertEquals(EMPTY, actualOutput);
    }
}
