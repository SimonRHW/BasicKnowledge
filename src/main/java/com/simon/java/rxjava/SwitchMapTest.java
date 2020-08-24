package com.simon.java.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

/**
 * 如果是在同一线程产生数据，因为当第二个数据项来临时，第一个已经完成了，此时其和 concatMap 是完全一致的。
 * 如果是并发产生数据项，当第二个数据项来临时，如果前一个任务尚未执行结束，就会被后一个任务给取消。
 */
public class SwitchMapTest {

    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .switchMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NotNull Integer integer) throws Exception {
//                        return Observable.just("switchmap:" + integer).subscribeOn(Schedulers.newThread());
                        return Observable.just("switchmap:" + integer);
                    }
                }).observeOn(Schedulers.trampoline())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(@NotNull String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });
    }
}
