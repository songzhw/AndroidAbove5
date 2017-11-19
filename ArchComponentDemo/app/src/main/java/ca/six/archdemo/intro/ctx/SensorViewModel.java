package ca.six.archdemo.intro.ctx;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

/**
 * Created by songzhw on 2017/11/19.
 */

public class SensorViewModel extends AndroidViewModel {
    private SensorManager sensorManager;

    public SensorViewModel(@NonNull Application application) {
        super(application);
        sensorManager = (SensorManager) application.getSystemService(Context.SENSOR_SERVICE);
    }


}
