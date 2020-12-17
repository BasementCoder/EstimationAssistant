package com.garagonic.estimationAssistant.tools;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Fn {

    private static Validator entityValidator;

    public static boolean isStringPopulated(String value) {
      return !isStringEmpty(value);
    }

    public static boolean isStringEmpty(String value) {
        return value == null || "".equals(value);
    }

    public static Validator getEntityValidator(){
        if (entityValidator == null) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            entityValidator = factory.getValidator();
        }
        return entityValidator;
    }
}
