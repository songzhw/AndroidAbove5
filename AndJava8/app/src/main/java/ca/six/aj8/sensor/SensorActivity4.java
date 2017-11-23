package ca.six.aj8.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ca.six.aj8.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.BehaviorSubject;

// 1. just observer the sensor value
// 2. if precise sample rate is a requirement, we will use interval()
// 3. Imagine a scenario where you need to batch the data into an x-second (let's say x = 5) long sampling window,
    // persist them, separate values based on the sensor type and return the average of each batch.
public class SensorActivity4 extends AppCompatActivity implements SensorEventListener {
    // Subject is a special type that can act both as an Observer and an Observable
    private BehaviorSubject<SensorEvent> proxy = BehaviorSubject.create();
    private SensorManager manager;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        /*
        The interval() method givs us an accurate timer and ensures the consistent time frame between the values.
        The window() method collects data in batches according to its parameter.
         */
        Observable.interval(200, TimeUnit.MILLISECONDS)
                .map((time) -> proxy.getValue()) //即每0.2秒就取一次proxy中的值. proxy再也不是一发就收了.
                .window(5, TimeUnit.SECONDS) // 返回一个Observable<Observable<T>>. 所以下面flatMap的参数就是Observable<T>了
                .flatMap(list -> list.toMultimap(sensorEvent -> sensorEvent.sensor.getName()).toObservable())
                .map(map -> { //stream() needs API 24(Android 7.0)
                    Map<String, Float> results = new HashMap<>();
                    for (Map.Entry<String, Collection<SensorEvent>> entry : map.entrySet()) {
                        float sum = 0;
                        for (SensorEvent event : entry.getValue()) {
                            sum += event.values[0];
                        }
                        float average = sum / entry.getValue().size();
                        results.put(entry.getKey(), average);
                    }
                    return results;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(map -> {
                    for (Map.Entry<String, Float> entry : map.entrySet()) {
                        String result = "[Value in 5 seconds] = " + entry.getKey() + " - " + entry.getValue();
                        tv.setText(result);
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this,
                manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 100);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.err.println("szw onPause()");
        manager.unregisterListener(this);

        // TODO stop the interval observable too !!!
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        proxy.onNext(sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}

/*
[How to stop Observable.interval()]
:
    private AtomicLong lastTick = new AtomicLong(0L);
    private Subscription subscription;

    void resume() {
        System.out.println("resumed");
        subscription = Observable.interval(5, TimeUnit.SECONDS, Schedulers.io())
                                 .map(tick -> lastTick.getAndIncrement())
                                 .subscribe(tick -> System.out.println("tick = " + tick));
    }

    void stop() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            System.out.println("stopped");
            subscription.unsubscribe();
        }
    }

 */