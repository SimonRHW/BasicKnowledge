package com.simon.java.design.sample;


import java.net.UnknownHostException;

public class IdGenerationFailureException extends Exception {

    public IdGenerationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
