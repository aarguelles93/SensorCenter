package com.uninorte.androidfragmenttest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentAcc extends Fragment implements SensorEventListener {

	private String TAG = FragmentAcc.class.getSimpleName();

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView txtVX;
    private TextView txtVY;
    private TextView txtVZ;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_acc, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        txtVX = (TextView) rootView.findViewById(R.id.txtVX);
        txtVY = (TextView) rootView.findViewById(R.id.txtVY);
        txtVZ = (TextView) rootView.findViewById(R.id.txtVZ);

		return rootView;
	}

    @Override
    public void onResume(){
        super.onResume();
        //
        sensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause(){
        super.onPause();
        //
        if(sensorManager != null){
            sensorManager.unregisterListener(this, accelerometer);
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "Acelerometro nulo", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        txtVX.setText(event.values[0] + "m/s^2");
        txtVY.setText(event.values[1] + "m/s^2");
        txtVZ.setText(event.values[2] + "m/s^2");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
