package com.uninorte.androidfragmenttest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentGps extends Fragment implements LocationListener {

	private String TAG = FragmentGps.class.getSimpleName();

    private LocationManager locationManager;

    private TextView editTLatitud;
    private TextView editTLongitud;
    private TextView editTVelocidad;




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_gps, container, false);

        editTLatitud = (TextView) rootView.findViewById(R.id.editTLatitud);
        editTLongitud = (TextView) rootView.findViewById(R.id.editTLatitud);
        editTVelocidad = (TextView) rootView.findViewById(R.id.editTVelocidad);

		return rootView;
	}

    @Override
    public void onResume(){
        super.onResume();
        //
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(!enabled){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            // Establecer propiedades de la alerta
            builder.setTitle("GPS desabilitado");
            builder.setMessage("¿Desea habilitar el GPS para establecer su posición?");
            builder.setCancelable(false);
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    return;
                }
            });

            builder = builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                    return;
                }
            });

            AlertDialog dialogo = builder.create();
            dialogo.show();
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

    }

    @Override
    public void onStop(){
        super.onStop();
        //
        if(locationManager != null){
            locationManager.removeUpdates(this);
        }
    }


    @Override
    public void onLocationChanged(Location location){
        editTLatitud.setText(location.getLatitude()+"");
        editTLongitud.setText(location.getLongitude()+"");
        editTVelocidad.setText( (location.getSpeed()*3.6)+"" );
    }

    @Override
    public void onProviderDisabled(String provider){
        Toast toast = Toast.makeText(getActivity(), "No se puede establecer ubicación", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast toast = Toast.makeText(getActivity(), "Conexión establecida", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //
    }

}
