package in.specialsoft.securesteps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText et_message;
    TextView tv_locDetails;
    private FusedLocationProviderClient Fusedlocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fusedlocation = LocationServices.getFusedLocationProviderClient(this);
        et_message = findViewById(R.id.et_message);
        tv_locDetails = findViewById(R.id.tv_locDetails);
    }

    public void toHome(View view) {
        Intent intent = new Intent(MainActivity.this,ParentActivity.class);
        startActivity(intent);
    }

    public void toLocation(View view) {
        Toast.makeText(this, "Getting Current Location", Toast.LENGTH_SHORT).show();
        toGetLocation();
    }


    public void toGetLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                //get the location
                Fusedlocation.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){

                            try {
                                Double lati = location.getLatitude();
                                Double longi = location.getLongitude();

                                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                //etLocation.setText(""+lati+","+longi);
                                String locality = addresses.get(0).getLocality();
                                String state = addresses.get(0).getAdminArea();
                                String addressLine = addresses.get(0).getAddressLine(0);
                                tv_locDetails.setText("locality : "+locality+", "+state+"\naddress : "+addressLine);
                                //et_message.setText("locality : "+locality+"\n address : "+addressLine+"\n AdminArea : "+ addresses.get(0).getAdminArea());

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else
            {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}