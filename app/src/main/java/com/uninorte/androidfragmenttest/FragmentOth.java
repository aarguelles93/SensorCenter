package com.uninorte.androidfragmenttest;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import android.hardware.SensorManager;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOth extends Fragment implements SensorEventListener {

    private String TAG = FragmentAcc.class.getSimpleName();

    SensorManager sensorManager;
    List<Sensor> sensores;

    TextView txtVSensores;

    String gravedad ="";
    String campoMagnetico ="";
    String giroscopio ="";
    String vectorRotacion ="";
    String orientacion ="";
    String proximidad ="";

    String luz ="";
    String temperaturaAmb ="";
    String temperatura ="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_oth, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensores = sensorManager.getSensorList(Sensor.TYPE_ALL);
       // sensores.remove(Sensor.TYPE_ACCELEROMETER);

        txtVSensores = (TextView) rootView.findViewById(R.id.txtVSensores);
        txtVSensores.setText("");

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        //
        for(Sensor sensor: sensores){
            sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d("S",sensor.getName());
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        //
        for(Sensor sensor: sensores){
            sensorManager.unregisterListener(this,sensor);
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        synchronized (this){
            switch (sensorEvent.sensor.getType()){
                case Sensor.TYPE_ORIENTATION:
                    orientacion = "Orientación "+": ";
                    for (int i=0; i<3; i++){
                        orientacion += (sensorEvent.values[i]+"°"+"\n");
                    }
                break;
                case Sensor.TYPE_GRAVITY:
                    gravedad = "Gravedad "+": ";
                    for (int i=0; i<3; i++){
                        gravedad += (sensorEvent.values[i]+"m/s^2"+"\n");
                    }
                break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    campoMagnetico = "Magnetismo "+": ";
                    for (int i=0; i<3; i++){
                        campoMagnetico += (sensorEvent.values[i]+"μT"+"\n");
                    }
                break;
                case Sensor.TYPE_GYROSCOPE:
                    giroscopio = "Giroscopio "+": ";
                    for (int i=0; i<3; i++){
                        giroscopio += (sensorEvent.values[i]+"rad/s"+"\n");
                    }
                break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    vectorRotacion = "Vector de rotación "+": ";
                    for (int i=0; i<=3; i++){
                        vectorRotacion += (sensorEvent.values[i]+"\n");
                    }
                    break;
                case Sensor.TYPE_PROXIMITY:
                    proximidad = ("Proximidad "+": "+sensorEvent.values[0]+"cm"+"\n");
                break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    temperaturaAmb = ("Temperatura ambiente "+": "+sensorEvent.values[0]+"°C"+"\n");
                break;
                case Sensor.TYPE_TEMPERATURE:
                    temperatura = ("Temperatura "+": "+sensorEvent.values[0]+"°C"+"\n");
                break;
                case Sensor.TYPE_LIGHT:
                    luz = ("Luz "+": "+sensorEvent.values[0]+"lx"+"\n");
                break;
            }
            txtVSensores.setText(orientacion+"\n"+gravedad+"\n"+campoMagnetico+"\n"+giroscopio+"\n"+proximidad+"\n"+temperaturaAmb+"\n"+temperatura+"\n"+luz);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
