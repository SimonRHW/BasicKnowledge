package com.simon.java.suspendcps;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

import static com.simon.kotlin.coroutines.CoroutinesSuspendKt.*;

public class JavaWithContinuation {

    public void testGetUserInfoAsync() throws ExecutionException, InterruptedException {
        String user = getUserInfoAsync().get();
        System.out.println("testGetUserInfoAsync:" + user);
    }

    public void testGetUserInfoJFlow() {
        Flow.Publisher<String> userInfoJFlow = getUserInfoJFlow();
        userInfoJFlow.subscribe(new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("onSubscribe");
                this.subscription = subscription;
                this.subscription.request(1); //requesting data from publisher
                System.out.println("onSubscribe requested 1 item");
            }

            @Override
            public void onNext(String item) {
                //没有输出？？？
                System.out.println("testGetUserInfoJFlow:" + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    public String testGetUserInfoCPS() {
        final String[] temp = {""};
        CountDownLatch countDownLatch = new CountDownLatch(1);
        getUserInfo(new Continuation<String>() {
                        @Override
                        public void resumeWith(@NotNull Object o) {
                            var user = (String) o;
                            temp[0] = user;
                            try {
                                countDownLatch.await(5000, TimeUnit.MICROSECONDS);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println("testGetUserInfoCPS " + user);
                        }

                        @NotNull
                        @Override
                        public CoroutineContext getContext() {
                            return EmptyCoroutineContext.INSTANCE;
                        }
                    }
        );
        return  temp[0];
    }

}
