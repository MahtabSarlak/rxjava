package ir.tourismit.tit;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.SafeObserver;
import io.reactivex.schedulers.Schedulers;

public class Main {
    public static void main(String[] args) {
        Observable<String> myObservable = Observable.create(emitter ->  {
                try {
                    emitter.onNext("data_1");
                    emitter.onNext("data_2");
                    emitter.onNext("data_2");
                    emitter.onComplete();

                } catch (Exception e) {
                    emitter.onError(e);
                }
        });
        Maybe<String> myMaybe = Maybe.create(emitter ->  {
                try {
                emitter.onSuccess("success");
                } catch (Exception e) {
                    emitter.onError(e);
                }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("onError" + e);

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete!");
            }
        };
        Observer<Integer> integerObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer.toString());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete!");
            }
        };
       Observable<Integer>observable1= Observable.range(1,5).map(item -> item*2);
        Observable<String> observable = Observable.fromCallable(() -> {
            // do something and return
            return "return from callable";
        });
        myObservable.map(i->i+"*");
       observable.subscribeWith(observer);
       observable1.subscribeWith(integerObserver);
        myObservable.subscribeWith(observer);
        myMaybe.subscribeWith(new DisposableMaybeObserver<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("observer -- success : "+ s);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
        Single.fromCallable(() -> {
            return("sth from callable");
        }).subscribeWith(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onSuccess(String s) {
                System.out.println("success "+ s);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

        Flowable.create(flowableEmitter -> {
            try {
                flowableEmitter.onNext("sthhh");
            } catch (Exception e) {
            }
            flowableEmitter.onComplete();
        }, BackpressureStrategy.BUFFER).subscribe(o -> System.out.println("** "+o));

        System.out.println("Print finished async method.");
    }
}
