package com.simon.java.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class TakeUntilTest {

    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .takeUntil(new Predicate< Integer >() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        System.out.println("==================takeUntil " + integer);
                        return integer >= 3;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("==================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer s) {
                        System.out.println("==================onNext " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("==================onError " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("==================onComplete ");
                    }
                });
    }

}
