package ca.six.aj8.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import ca.six.aj8.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.BehaviorSubject;

public class SensorActivity3 extends AppCompatActivity implements SensorEventListener {
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
         */
        Observable.interval(200, TimeUnit.MILLISECONDS)
                .map((time) -> proxy.getValue()) //即每0.2秒就取一次proxy中的值. proxy再也不是一发就收了.
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((sensorEvent -> {
                    String result = sensorEvent.sensor.toString() + " \n " + Arrays.toString(sensorEvent.values);
                    tv.setText(result);
                    System.out.println("szw result = " + result);
                }));

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
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        proxy.onNext(sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
