package com.simon.java.suspendcps;

import java.util.concurrent.ExecutionException;

public class Debug {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        JavaWithContinuation javaWithContinuation = new JavaWithContinuation();
        javaWithContinuation.testGetUserInfoAsync();
        javaWithContinuation.testGetUserInfoJFlow();
        javaWithContinuation.testGetUserInfoAsync();
    }
}
