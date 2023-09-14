package com.simon.java.rxjava.processor;

import io.reactivex.processors.BehaviorProcessor;

public class BehaviorProcessorTest {

    public static void main(String[] args) {
        BehaviorProcessor<String> behaviorProcessor = BehaviorProcessor.create();
        behaviorProcessor.onNext("1");
        behaviorProcessor.onNext("2");
        behaviorProcessor.onNext("3");
        behaviorProcessor.retry().subscribe(s ->
                        System.out.println("onNext1 " + s),
                throwable -> {

                });
        behaviorProcessor.onNext("4");
        behaviorProcessor.retry().subscribe(s ->
                        System.out.println("onNext2 " + s),
                throwable -> {

                });
        behaviorProcessor.onNext("5");
        behaviorProcessor.onNext("6");
    }
}
