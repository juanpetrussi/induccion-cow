package com.induccion.cow.validators;

import org.apache.commons.lang.StringUtils;

public class ValidatorsUtils {

    public static boolean isIntegerPositive(String value) {
        try{
            Integer paramInteger = Integer.valueOf(value);
             if (paramInteger <= 0){
                return false;
            }
            return true;
        }catch (NumberFormatException n) {
            return false;
        }
    }

    public static boolean isDecimalPositive(String value) {
        try{
            Float paramInteger = Float.valueOf(value);
            if (paramInteger <= 0){
                return false;
            }
            return true;
        }catch (NumberFormatException n) {
            return false;
        }
    }

    public static boolean isStringValid(String value) {
        return StringUtils.isNotBlank(value);
    }
}
