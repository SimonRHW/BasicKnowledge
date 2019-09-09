package com.simon.java.rxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class retryWhenTest {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onError(new Exception("404"));
                e.onNext("ok");
                e.onComplete();
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                        System.out.println("==================retryWhen " + throwable.toString());
                        if (!throwable.toString().equals("java.lang.Exception: 404")) {
                            return Observable.just("可以忽略的异常");
                        } else {
                            return Observable.error(new Throwable("终止啦"));
                        }
                    }
                });
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("==================onSubscribe ");
            }

            @Override
            public void onNext(String s) {
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
