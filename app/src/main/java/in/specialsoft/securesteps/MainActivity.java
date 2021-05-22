package in.specialsoft.securesteps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toHome(View view) {
        Intent intent = new Intent(MainActivity.this,ParentActivity.class);
        startActivity(intent);
    }

    public void toLocation(View view) {
        Toast.makeText(this, "Getting Current Location", Toast.LENGTH_SHORT).show();
    }
}