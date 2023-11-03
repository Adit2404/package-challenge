package com.mobiquity.service;

import com.mobiquity.helper.DataHelper;
import com.mobiquity.knapscak.KnapSack;
import com.mobiquity.model.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeightCalculationImplTest {

    @InjectMocks
    private WeightCalculationImpl weightCalculation;

    @Mock
    private KnapSack knapSack;

    @BeforeEach
    public void clean() {
        knapSack.clear();
    }

    @Test
    public void givenValidPackages_whenCalculateWeight_thenShouldReturnValidResult() {
        List<Package> packages = DataHelper.packagesForWightCal();
        Mockito.lenient().when(knapSack.calculate(ArgumentMatchers.any())).thenReturn(knapSack);
        Mockito.lenient().when(knapSack.getCalculationsAsString()).thenReturn("4");

        String result = weightCalculation.calculateWeightAndReturnPackage(packages);

        Assertions.assertEquals("4", result);
    }

    @Test
    public void givenNullPackages_whenCalculateWeight_thenShouldReturnEmpty() {
        String result = weightCalculation.calculateWeightAndReturnPackage(null);
        Assertions.assertEquals("", result);
    }

    @Test
    public void givenEmptyPackages_whenCalculateWeight_thenShouldReturnEmpty() {
        String result = weightCalculation.calculateWeightAndReturnPackage(Collections.emptyList());
        Assertions.assertEquals("", result);
    }
}
