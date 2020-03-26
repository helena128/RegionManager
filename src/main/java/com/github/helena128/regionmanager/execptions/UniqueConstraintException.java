package com.github.helena128.regionmanager.execptions;

import static java.lang.String.format;

public class UniqueConstraintException extends RuntimeException {

    private static final String MESSAGE = "Value for field %s should be unique";

    public UniqueConstraintException(String fieldName) {
        super(format(MESSAGE, fieldName));
    }
}
