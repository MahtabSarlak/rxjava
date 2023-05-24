package ir.tourismit.tit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class SimpleAsync {
    public static void main(String[] args) {
        System.out.println("Starting simple async");
                System.out.println("Finished simple async");
    }
}
