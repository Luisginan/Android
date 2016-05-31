package bintang.id.mapsnavigation;
/**
 * Created by Bintang on 3/1/2016.
 * Purpose :
 * 1. Learn how to load map using Fragment
 * 2. Learn how to add marker
 * 3. Learn how to get location using Location Manager
 * 4. Learn how send and parse return data from Google Map API
 */
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private static GoogleMap gMap;
    Button btnClear, btnDraw;
    ArrayList<LatLng> markerPoints;
    double latitude, longitude;

    private GoogleApiClient googleApiClient;
    private Location location;
    private LocationRequest locationRequest;
    PolylineOptions polyLineOptions = null;
    Polyline polyline = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        markerPoints = new ArrayList<LatLng>();

        initComponent();
        initMap();
        buttonAction();
        mapClickAction();

        googleAPIClient();

    }

    private void googleAPIClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this) // connection callback
                .addOnConnectionFailedListener(this) // when connection failed
                .addApi(LocationServices.API) // called api
                .build();
    }

    private void initComponent() {
        btnClear = (Button) findViewById(R.id.btnReset);
        btnDraw = (Button) findViewById(R.id.btnDraw);
    }

    //region Button Action
    private void buttonAction() {
        btnClearAction();
        btnDrawAction();
    }

    private void mapClickAction() {
        mapActionClick();
        mapWindowInfoActionClick();
        mapWindowLongClick();
    }

    private void btnClearAction() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gMap.clear();
                markerPoints.clear();
            }
        });
    }

    private void btnDrawAction() {
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });
    }

    private void updateUI(){
        LatLng origin = null;
        LatLng destination = null;
        if (markerPoints.size() == 0) {
            return;
        } else if (markerPoints.size() == 1) {
            origin = new LatLng(location.getLatitude(), location.getLongitude());
            destination = markerPoints.get(0);
        } else if (markerPoints.size() >= 2) {
            origin = markerPoints.get(0);
            destination = markerPoints.get(1);
        }
        String url = getDirectionsUrl(origin, destination);// Getting URL to the Google Directions API
        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(url);// Start downloading json data from Google Directions API
    }
    //endregion

    //region Map Action
    private void mapActionClick() {
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                drawMarker(latLng);
            }
        });
    }

    private void mapWindowInfoActionClick() {
        gMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                int lastIndex = markerPoints.size();
                if (lastIndex > 0) {
                    markerPoints.remove(lastIndex - 1);
                    marker.remove();
                }

                if (polyline != null) {
                    polyline.remove();
                }
            }
        });
    }

    private void mapWindowLongClick() {
        gMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                gMap.clear();
                markerPoints.clear();
            }
        });
    }

    //endregion

    private void drawMarker(LatLng latLng) {
        if (markerPoints.size() >= 10) {
            return;
        }

        MarkerOptions options = initMarkers(latLng);

        gMap.addMarker(options);
    }

    private MarkerOptions initMarkers(LatLng latLng) {
        markerPoints.add(latLng);
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        setMarkersColor(options);
        options.title(getAddressFromLatLng(latLng));
        options.snippet(latLng.toString());
        return options;
    }

    private void setMarkersColor(MarkerOptions options) {
        if (markerPoints.size() == 1) {
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        } else if (markerPoints.size() == 2) {
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        } else {
            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        }
    }

    private String getAddressFromLatLng(LatLng latLng) {

        Geocoder geocoder = new Geocoder(this);
        String address = "";
        try {
            address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAddressLine(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return address;
    }

    //region Implement methods of Activity
    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume");
        super.onResume();
        initMap();

    }

    @Override
    protected void onStart() {
        Log.d("MainActivity","onStart");
        super.onStart();
        googleApiClient.connect();

    }

    @Override
    protected void onStop() {
        Log.d("MainActivity","onStop");
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
            gMap = null;
        }
    }
    //endregion

    //region Implement methods of ConnectionCallbacks

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("MainActivity","onConnected");
        if (location == null) {
            //Self check permission for API 23 and above
            if (checkSelfPermission()) return;
            locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(10);

            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            initMap();
            startLocationUpdates();
        }
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("MainActivity","onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("MainActivity","onConnectionFailed");
    }

    //endregion

    //region Implement methods of OnMapReadyCallback
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("MainActivity","onMapReady");
        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Log.d("MainActivity","onInfoWindowClick");
        Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show();
    }
    //endregion

    private void initMap() {
        if (checkSelfPermission()) return;
        if (gMap == null) {
            gMap = ((MapFragment) getFragmentManager().
                    findFragmentById(R.id.map)).getMap();
        }

        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.setMyLocationEnabled(true);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 10));
        configureMarker();
        googleMapSetUI(true);
    }

    private boolean googleMapSetUI(boolean bool) {
        gMap.getUiSettings().setZoomControlsEnabled(bool);
        gMap.getUiSettings().setScrollGesturesEnabled(bool);
        gMap.getUiSettings().setZoomGesturesEnabled(bool);
        gMap.getUiSettings().setMyLocationButtonEnabled(bool);
        gMap.getUiSettings().setRotateGesturesEnabled(bool);

        return true;
    }

    private void configureMarker(){
        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(latitude, longitude));
        marker.title(getResources().getString(R.string.starting_point));
        marker.snippet(getResources().getString(R.string.starting_point_snippet));

        gMap.addMarker(marker);
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        String str_origin   = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest     = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor       = "sensor=false";

        // Waypoints
        String waypoints = "";
        for (int i = 2; i < markerPoints.size(); i++) {
            LatLng point = (LatLng) markerPoints.get(i);
            if (i == 2)
                waypoints = "waypoints=";
            waypoints += point.latitude + "," + point.longitude + "|";
        }

        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + waypoints;// Building the parameters to the web service
        String output = "json";// Output format
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;// Building the url to the web service

        return url;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();// Creating an http connection to communicate with url
            urlConnection.connect();// Connecting to url
            iStream = urlConnection.getInputStream();// Reading data from url

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            br.close();

        } catch (Exception e) {
            Log.d("Error while downloading", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private boolean checkSelfPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    //region implement methods LocationListener
    @Override
    public void onLocationChanged(Location location) {
        Log.d("MainActivity","onLocationChanged");
        this.location = location;
        if(polyline != null){
            polyline.remove();
        }
        updateUI();
    }
    //endregion

    //region Asynctask Classes
    private class DownloadTask extends AsyncTask<String, Void, String> {
        //Storing data from web service
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try{
                data = downloadUrl(url[0]);// Fetching the data from web service
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                routes = parser.parse(jObject);// Starts parsing data
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {

            ArrayList<LatLng> points = null;

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();


                List<HashMap<String, String>> path = result.get(i);// Fetching i-th route

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }
                // Adding all the points in the route to LineOptions
                polyLineOptions.addAll(points);
                polyLineOptions.width(5);
                polyLineOptions.color(Color.RED);
            }
            polyline = gMap.addPolyline(polyLineOptions);
        }
    }
    //endregion


}
