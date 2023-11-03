package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.ParserException;
import com.mobiquity.service.Parser;
import com.mobiquity.model.Item;
import com.mobiquity.model.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
class DataParserImplTest {

    @Mock
    private Parser parser;

    @Test
    public void givenNullData_whenParseData_thenShouldReturnEmptyList() {
        Mockito.doReturn(new ArrayList<>()).when(parser).parseData(null);

        List<Package> result = parser.parseData(null);

        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void givenValidData_whenParseData_thenShouldReturnCorrectPackages() {
        List<String> inputData = Arrays.asList(
                "81 : (1,53.38,€45) (2,88.62,€98)",
                "2 : (3,45.0,€55) (4,75.0,€35)"
        );

        List<Package> expectedPackages = Arrays.asList(
                new Package(81, Arrays.asList(new Item(1, 53.38, 45), new Item(2, 88.62, 98))),
                new Package(2, Arrays.asList(new Item(3, 45.0, 55), new Item(4, 75.0, 35)))
        );

        Mockito.doReturn(expectedPackages).when(parser).parseData(inputData);

        List<Package> result = parser.parseData(inputData);

        Assertions.assertEquals(expectedPackages, result);
    }

    @Test
    public void givenDataWithInvalidFormat_whenParseData_thenShouldThrowException() {
        List<String> inputData = Arrays.asList(
                "81  (1,53.38,€45)", // Missing colon
                "2 : (3,45.0,€55) (4,75.0,€35)"
        );

        doThrow(new ParserException("Invalid format")).when(parser).parseData(inputData);

        try {
            parser.parseData(inputData);
            Assertions.fail("Expected ParserException to be thrown");
        } catch (ParserException e) {
            Assertions.assertEquals("Invalid format", e.getMessage());
        }
    }

}
