package com.garage.util;

import java.text.MessageFormat;

public class StringUtil {

    public static String messageFormat(String message, Object... values){
        if (values != null && values.length > 0) {
            message = MessageFormat.format(message, values);
        }
        return message;
    }
}
