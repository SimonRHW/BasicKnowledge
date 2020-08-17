package com.simon.java.rxjava.subject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.AsyncSubject;
import org.jetbrains.annotations.NotNull;

/**
 * 观察者/订阅者只会接受到onComplete之前的最后一个数据
 */
public class AsyncSubjectTest {

    public static void main(String[] args) {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("0");
        asyncSubject.onNext("1");
        asyncSubject.onNext("2");
//        asyncSubject.onComplete();

        asyncSubject.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull String s) {
                System.out.println("onNext" + s);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                System.out.println("onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

        asyncSubject.onNext("3");
        asyncSubject.onComplete();
//        asyncSubject.onError(new IllegalArgumentException(" error"));
    }


}
