package com.simon.kotlin.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;

import static com.simon.kotlin.coroutines.CoroutinesSuspendKt.*;

public class JavaWithContinuation {

    public static void testGetUserInfoAsync() throws ExecutionException, InterruptedException {
        String s = getUserInfoAsync().get();
        System.out.printf("s");
    }

    public static void testGetUserInfoJFlow() {
        Flow.Publisher<String> userInfoJFlow = getUserInfoJFlow();
        userInfoJFlow.subscribe(new Flow.Subscriber<String>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {

            }

            @Override
            public void onNext(String item) {
                System.out.printf(item);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public static void testGetUserInfoCPS() {
        getUserInfo(new Continuation<String>() {
                        @Override
                        public void resumeWith(@NotNull Object o) {
                            var str = (String) o;
                            System.out.printf(str);
                        }

                        @NotNull
                        @Override
                        public CoroutineContext getContext() {
                            return EmptyCoroutineContext.INSTANCE;
                        }
                    }
        );
    }

}
