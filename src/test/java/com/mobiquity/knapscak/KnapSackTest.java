package com.mobiquity.knapscak;

import com.mobiquity.helper.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnapSackTest {

    private KnapSack sack = KnapSack.getInstance();

    @Test
    void calculate() {
        String result = sack.calculate(DataHelper.packagesForWightCal())
                .getCalculationsAsString();
        Assertions.assertEquals("4", result);
    }

    @BeforeEach
    public void clean() {
        sack.clear();
    }
}

