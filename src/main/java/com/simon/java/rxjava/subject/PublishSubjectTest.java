package com.simon.java.rxjava.subject;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import org.jetbrains.annotations.NotNull;

/**
 * 观察者只接受PublishSubject订阅之后的数据。
 * 发送OnComplete后，再次subscribe 发送数据 也不会接收
 */
public class PublishSubjectTest {

    public static void main(String[] args) {

        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.onNext(0);
        publishSubject.onNext(1);

        publishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull Integer integer) {
                System.out.println("onNext " + integer);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                System.out.println("OnError " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("OnComplete");
            }
        });
        publishSubject.onNext(2);
        publishSubject.onNext(3);
        publishSubject.onComplete();
        publishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NotNull Disposable d) {

            }

            @Override
            public void onNext(@NotNull Integer integer) {
                System.out.println("onNext " + integer);
            }

            @Override
            public void onError(@NotNull Throwable e) {
                System.out.println("OnError " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("OnComplete");
            }
        });
        publishSubject.onNext(4);
        publishSubject.onNext(5);


    }
}
