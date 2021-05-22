package in.specialsoft.securesteps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isNetworkConnected())
            {
                Intent noError = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(noError);
            }
            else
            {
                Toast.makeText(this, "Internet Not Available", Toast.LENGTH_SHORT).show();
                Intent networkError = new Intent(SplashScreen.this,NetworkErrorActivity.class);
                startActivity(networkError);
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}