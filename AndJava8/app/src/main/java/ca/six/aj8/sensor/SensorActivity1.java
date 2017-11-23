package ca.six.aj8.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Arrays;

import ca.six.aj8.R;

public class SensorActivity1 extends AppCompatActivity implements SensorEventListener {
    private SensorManager manager;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_btn);
        tv = findViewById(R.id.tv_simple);
        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
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
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String result = sensorEvent.sensor.toString() + " \n " + Arrays.toString(sensorEvent.values);
        tv.setText(result);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
