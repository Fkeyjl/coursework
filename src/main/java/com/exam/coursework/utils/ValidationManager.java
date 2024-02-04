package com.exam.coursework.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidationManager {
    private static Validator getValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return  factory.getValidator();
    }

    public static  <T>  boolean isValidate(T object){
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        return constraintViolations.isEmpty();
    }

    public static   <T>  String getFirsErrorMessage(T object){
        Validator validator = getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        return constraintViolations.iterator().next().getMessage();
    }

}
