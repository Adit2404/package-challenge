package com.mobiquity.model;

import org.junit.jupiter.api.Test;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;

public class PackageTest {

    @Test
    public void shouldTestGettersForPackage() {
        // Given
        PojoClass pojoClass = PojoClassFactory.getPojoClass(Package.class);

        // When
        Validator validator = ValidatorBuilder.create()
                .with(new GetterMustExistRule())
                .with(new GetterTester())
                .build();

        // Then
        validator.validate(pojoClass);
        // Add more assertions or additional checks if needed
    }
}
