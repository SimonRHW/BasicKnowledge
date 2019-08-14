package com.simon.java.rxjava;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class IntervalTest {
    public static void main(String[] args) {
        testOne();
    }

    public static void testOne() {
        long start = System.currentTimeMillis();
        Disposable subscribe = Observable.interval(2, TimeUnit.SECONDS, Schedulers.trampoline())
                .take(3)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("onNext" + aLong);
                        System.out.println("onNext" + (System.currentTimeMillis() - start));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("onComplete");
                    }
                });
    }

    public static void testTwo() {
        long start = System.currentTimeMillis();
        DisposableObserver<Long> disposableObserver = Observable.interval(1, 2, TimeUnit.SECONDS, Schedulers.trampoline())
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        System.out.println("onNext" + aLong);
                        System.out.println("onNext" + (System.currentTimeMillis() - start));
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }

}
