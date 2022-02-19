package com.simon.java.design.sample;


interface IdGenerator {
    String generate() throws IdGenerationFailureException;
}
