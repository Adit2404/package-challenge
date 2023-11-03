package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.service.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataReaderImplTest {

    @Mock
    private Reader reader;

    @Test
    public void givenNullFilePath_whenReadFile_thenShouldCallReadData() throws IOException, APIException {
        reader.readData(null);
        Mockito.verify(reader, Mockito.times(1)).readData(null);
    }

    @Test
    public void givenValidFilePath_whenReadFile_thenShouldCallReadData() throws IOException, APIException {
        reader.readData("/path/to/valid/file");
        Mockito.verify(reader, Mockito.times(1)).readData("/path/to/valid/file");
    }

    @Test
    public void givenInvalidFilePath_whenReadFile_thenShouldThrowFileNotFoundException() throws IOException, APIException {
        Mockito.when(reader.readData("/path/to/invalid/file")).thenThrow(new FileNotFoundException());

        Assertions.assertThrows(FileNotFoundException.class, () -> reader.readData("/path/to/invalid/file"));
    }

    @Test
    public void givenEmptyFilePath_whenReadFile_thenShouldThrowAPIException() throws IOException, APIException {
        Mockito.when(reader.readData("")).thenThrow(new APIException("File path is empty/null.."));

        Assertions.assertThrows(APIException.class, () -> reader.readData(""));
    }
}
