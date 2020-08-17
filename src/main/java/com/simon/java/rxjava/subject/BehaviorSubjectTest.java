package com.simon.java.rxjava.subject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import org.jetbrains.annotations.NotNull;

/**
 * 观察者/订阅者会收到订阅之前的最后一个数据，再继续接受之后发射过来的数据，
 * 若BehaviorSubject订阅之前未发射过数据，则发射一个默认值。
 */
public class BehaviorSubjectTest {
    public static void main(String[] args) {
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.createDefault(0);
//        behaviorSubject.onNext(-1);
        behaviorSubject.subscribe(new Observer<Integer>() {
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
        behaviorSubject.onNext(1);
        behaviorSubject.onNext(2);
        behaviorSubject.onComplete();
    }
}
