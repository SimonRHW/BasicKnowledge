package com.simon.java.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TransformerWithCompose {

    public static void main(String[] args) {
        Observable.just(1, 2, 3)
                .compose(transformer())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull String s) throws Exception {
                        System.out.println("s=" + s);
                    }
                });
    }

    public static <String> ObservableTransformer<Integer, java.lang.String> transformer() {
        return new ObservableTransformer<Integer, java.lang.String>() {
            @Override
            public ObservableSource<java.lang.String> apply(@NonNull Observable<Integer> upstream) {
                return upstream.map(new Function<Integer, java.lang.String>() {
                    @Override
                    public java.lang.String apply(@NonNull Integer integer) throws Exception {
                        return java.lang.String.valueOf(integer);
                    }
                });
            }
        };
    }
}
