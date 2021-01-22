package com.simon.java.rxjava.subject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.ReplaySubject;
import org.jetbrains.annotations.NotNull;

/**
 * 缓存n条数据，当订阅时只发送缓存过的数据和之后数据。
 * 默认是一个无界的buffer
 * 提供一下方法进行设置：
 * createWithSize(n) 只会保留最近的 n 个元素。
 * createWithTime(t, u) 只会保留最近 t 时间内的数据。
 * createWithTimeAndSize(n, t, u) 只会保留最近 t 时间内的数据，且不超过 n 个。
 */
public class ReplaySubjectTest {

    public static void main(String[] args) {

        ReplaySubject<Integer> replaySubject;
        replaySubject = ReplaySubject.create();
//        replaySubject = ReplaySubject.createWithSize(4);
        replaySubject.onNext(0);
        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);
        replaySubject.onNext(4);
        replaySubject.onNext(5);
        replaySubject.subscribe(new Observer<Integer>() {
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
        replaySubject.onNext(6);
    }
}
