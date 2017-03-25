package com.crincongtz.proximitysensor;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "ProximitySensor";

    RelativeLayout relativeLayout;
    SensorManager sensorManager;
    Sensor sensor;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        textView = (TextView) findViewById(R.id.tv_value);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "Sensor: " + sensorEvent.values[2]);

        String text = String.valueOf(sensorEvent.values[0]);
        textView.setText(text);

        float value = Float.parseFloat(text);

        if (value == 1 ){
            relativeLayout.setBackgroundColor(Color.BLUE);
        } else if (value == 3){
            relativeLayout.setBackgroundColor(Color.GREEN);
        } else {
            relativeLayout.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
