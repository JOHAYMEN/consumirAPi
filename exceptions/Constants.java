package com.example.consumirApi.exceptions;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String NAME_NOT_FOUND = "Name not found in external Api";

}
